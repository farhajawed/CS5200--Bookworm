package com.bookstoremanagement.utilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bookstoremanagement.PO.OrderBookDetailsPO;
import com.bookstoremanagement.PO.OrderDetailsPO;
import com.bookstoremanagement.PO.RegistrationPO;
import com.bookstoremanagement.PO.UserInfoPO;
import com.bookstoremanagement.dao.BookDao;
import com.bookstoremanagement.dao.OrderDetailsDao;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.OrderBookDetails;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.SellerDetails;
import com.bookstoremanagement.model.UserLogin;

public class Utilities {

	/**
	 * @param user
	 * @param file
	 * saves the display picture for the user's profile and saves it's path in database and 
	 * creates a local copy of the file on server.
	 */
	public static void saveImageOnServer(UserInfoPO user, MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				Format formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
				Date date = new Date();
				String dateStr = formatter.format(date);
				byte[] bytes = file.getBytes();
				String name = user.getUsername()+"_"+dateStr+".jpeg";
				// Creating the directory to store file
				System.out.println("File name - "+ name);
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				user.setImage(serverFile);
				user.setImageUrl(serverFile.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

	/**
	 * @param request
	 * @return returns the latest CartPO in session.
	 */
	public static OrderBookDetailsPO getCartfromSession(HttpServletRequest request) {

		// Get Cart from Session.
		OrderBookDetailsPO cartInfo = (OrderBookDetailsPO) request.getSession().getAttribute("myCart");

		// If null, create it.
		if (cartInfo == null) {
			cartInfo = new OrderBookDetailsPO();

			// And store to Session.
			request.getSession().setAttribute("myCart", cartInfo);
		}

		return cartInfo;
	}

	/**
	 * @param request
	 * @param cartInfo
	 * Sets the OrderBookDetailsPO in session with key lastOrderedCart.
	 */
	public static void storeLastOrderedCartInSession(HttpServletRequest request, OrderBookDetailsPO cartInfo) {
		request.getSession().setAttribute("lastOrderedCart", cartInfo);
	}

	/**
	 * @param request
	 * @return OrderBookDetailsPO
	 * Returns the last ordered cart set in the session.
	 */
	public static OrderBookDetailsPO getLastOrderedCartInSession(HttpServletRequest request) {
		return (OrderBookDetailsPO) request.getSession().getAttribute("lastOrderedCart");
	}


	/**
	 * @param request
	 * @param userDao
	 * @param username
	 * @return user id of the currently logged in user, also set userId, role and name in session 
	 * against the key value userId,userRole and uName.
	 */
	public static int fetchUserRoleIdAndUsernameFromSessionOrDB(HttpServletRequest request, UserDao userDao, String username) {
		int userId = 0;
		UserLogin user = userDao.findUserInfoByUsername(username);
		if(request.getSession().getAttribute("userId") != null)
			userId = (Integer) request.getSession().getAttribute("userId");
		if(userId != user.getId()) {
			userId = user.getId();
			request.removeAttribute("userId");
			request.removeAttribute("userRole");
			request.getSession().setAttribute("userId",user.getId());
			request.getSession().setAttribute("userRole",user.getUserRole());
			request.getSession().setAttribute("uName",user.getUsername());
		}
		return userId;
	}

	/**
	 * @param orderId
	 * @param updatedOrderBookDetails
	 * @param orderDao
	 * @param bookDao
	 * @return list of order details POs for populating order details on client-side.
	 */
	public static List<OrderDetailsPO> getLatestOrderList(int orderId, List<OrderBookDetails> updatedOrderBookDetails, OrderDetailsDao orderDao, BookDao bookDao){
		List<OrderBookDetails> orderBookDetailsList = orderDao.fetchOrderBookDetailsById(orderId);
		List<OrderBookDetails> newOrderBookDetailsList = new ArrayList<OrderBookDetails>();
		if(updatedOrderBookDetails!=null) {
			Iterator<OrderBookDetails> updatedOrderBookDetailsItr = updatedOrderBookDetails.iterator();	
			while(updatedOrderBookDetailsItr.hasNext()) {
				OrderBookDetails orderBookDetails = updatedOrderBookDetailsItr.next();
				orderBookDetails = createUpdatedOrderList(orderId, orderBookDetails, orderBookDetailsList);
				newOrderBookDetailsList.add(orderBookDetails);
			}
		}
		Iterator<OrderBookDetails> orderBookDetailsItr = newOrderBookDetailsList.iterator();
		if(newOrderBookDetailsList.isEmpty())
			orderBookDetailsItr = orderBookDetailsList.iterator();
		return makeOrderDetailsPOList(orderBookDetailsItr, bookDao);
	}

	/**
	 * @param orderBookDetailsItr
	 * @param bookDao
	 * @return Generates the list of order details POs for populating order details on client-side.
	 */
	public static List<OrderDetailsPO> makeOrderDetailsPOList(Iterator<OrderBookDetails> orderBookDetailsItr, BookDao bookDao){
		List<OrderDetailsPO> orders = new ArrayList<OrderDetailsPO>();
		while(orderBookDetailsItr.hasNext()) {
			OrderBookDetails orderBookDetails = orderBookDetailsItr.next();
			SellerDetails seller = orderBookDetails.getSellerdetails();
			int bookId = seller.getBook().getId();
			Book book = bookDao.getBook(bookId);
			OrderDetailsPO order = new OrderDetailsPO();
			order.setId(orderBookDetails.getId());
			order.setBook(book);
			order.setBooks(new ArrayList<Book>());
			order.getBooks().add(book);
			order.setQuantity(orderBookDetails.getBookCount());
			order.setUnitPrice(seller.getPrice());
			order.setSellerId(seller.getId());
			System.out.println("orderBookDetails - "+orderBookDetails);
			if(orderBookDetails.getOrderdetails().getUserdetails().getUserLogin().getUserRole().equals(Constants.PRIME_ROLE)) {
				float discountedPrice = seller.getPrice() - (seller.getPrice()* Constants.DISCOUNT_PERCENT);
				order.setAmount(discountedPrice * orderBookDetails.getBookCount());
			} else
				order.setAmount(seller.getPrice() * orderBookDetails.getBookCount());
			orders.add(order);
		}
		return orders;
	}

	/**
	 * @param orderId
	 * @param orderBookDetails
	 * @param orderBookDetailsList
	 * @return the updated cart for a given order id.
	 */
	public static OrderBookDetails createUpdatedOrderList(int orderId, OrderBookDetails orderBookDetails, List<OrderBookDetails> orderBookDetailsList){
		System.out.println("createUpdatedOrderList orderBookDetails before - "+ orderBookDetails);
		Iterator<OrderBookDetails> orderBookDetailsItr = orderBookDetailsList.iterator();
		while(orderBookDetailsItr.hasNext()) {
			OrderBookDetails orderBookDetailsFromDb = orderBookDetailsItr.next();
			if(orderBookDetailsFromDb.getId() == orderBookDetails.getId()) {
				orderBookDetails.setBookCount(orderBookDetails.getBookCount());
				System.out.println("createUpdatedOrderList orderBookDetails after - "+ orderBookDetails);
			}
			return orderBookDetails;
		}
		return orderBookDetails;
	}

	/**
	 * @param orders
	 * @return From a given list of order, the order whose status is pending.
	 */
	public static OrderDetails getPendingOrder(List<OrderDetails> orders) {
		OrderDetails order = null;
		for(OrderDetails orderDetail: orders) {
			if(orderDetail.getPayStatus().equals("PENDING")) {
				order = orderDetail;
				break;
			}
		}
		return order;
	}

	public static ModelAndView logoutOnException(HttpServletRequest request, HttpServletResponse response, String errorMsg) { 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("errorOccured", true);
		mav.addObject("message", errorMsg);
		return mav;
	}

	public static void setAddressForValidation(UserInfoPO user, RegistrationPO registration) {
		if(user.getUseraddress() != null && user.getUseraddress().get(0) != null) {
			registration.setCity(user.getUseraddress().get(0).getCity());
			registration.setState(user.getUseraddress().get(0).getState());
			registration.setStreet1(user.getUseraddress().get(0).getStreet1());
			registration.setPincode(user.getUseraddress().get(0).getPincode());
		}
	}
}
