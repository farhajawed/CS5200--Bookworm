package com.bookstoremanagement.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bookstoremanagement.PO.OrderBookDetailsPO;
import com.bookstoremanagement.PO.OrderDetailsPO;
import com.bookstoremanagement.dao.BookDao;
import com.bookstoremanagement.dao.OrderDetailsDao;
import com.bookstoremanagement.dao.SellerDetailsDao;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.dao.WishListDao;
import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.OrderBookDetails;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.SellerDetails;
import com.bookstoremanagement.model.UserInfo;
import com.bookstoremanagement.model.UserLogin;
import com.bookstoremanagement.model.WishList;
import com.bookstoremanagement.utilities.Constants;
import com.bookstoremanagement.utilities.Utilities;

@SessionAttributes("order")
@Controller
public class AddToCartOrWishListController {


	@Autowired
	public OrderDetailsDao orderDao;

	@Autowired
	public SellerDetailsDao sellerDao;

	@Autowired
	public BookDao bookDao;

	@Autowired
	public UserDao userDao;

	@Autowired
	public WishListDao wishListDao;

	@RequestMapping(value = "/addToCartOrWishList/{username}", method = RequestMethod.POST)
	public ModelAndView addToCartOrWishList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("cart") OrderBookDetailsPO cart, BindingResult bindingResult,
			@RequestParam("sellerId") int sellerId, @RequestParam("addTo") String value, Principal principal,
			@PathVariable String username) { 
		try {
			if(principal == null) {
				ModelAndView mv = new ModelAndView("page");
				mv.addObject("userClickLogin",true);
				return mv;
			}
			else if(value.equals("myCart")) {
				return addToCart(request, response, cart, bindingResult, sellerId, username);
			}
			else if(value.equals("wishList")) {
				return addToWishList(request, response, cart, bindingResult, sellerId, username);
			}
			else {
				return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}
	}

	private ModelAndView addToCart(HttpServletRequest request, HttpServletResponse response, OrderBookDetailsPO cart, BindingResult bindingResult,
			int sellerId, String username ) {
		try {
			if(username!= null) {
				ModelAndView mav = new ModelAndView("page");
				mav.addObject("userClickAddToCart", true);
				OrderDetailsPO orderPO = new OrderDetailsPO();
				Book book = cart.getBook();
				int userId = Utilities.fetchUserRoleIdAndUsernameFromSessionOrDB(request, userDao, username);
				UserLogin user = userDao.fetchUser(userId);
				UserInfo userinfo = user.getUserInfo();
				orderPO.setUserdetails(userinfo);
				SellerDetails seller = sellerDao.getSellerDetails(sellerId);
				if(orderPO.getQuantity() <= seller.getQuantity()) {
					orderPO.setUnitPrice(seller.getPrice());
					if(user.getUserRole().equals(Constants.PRIME_ROLE)) {
						float discount = seller.getPrice() * Constants.DISCOUNT_PERCENT;
						orderPO.setAmount(seller.getPrice()-discount);
					} else
						orderPO.setAmount(seller.getPrice());
					orderPO.setQuantity(1);
				}
				if(book.getSellerDetails() == null)
					book.setSellerDetails(new ArrayList<SellerDetails>());
				book.getSellerDetails().add(seller);
				if(orderPO.getBooks() == null)
					orderPO.setBooks(new ArrayList<Book>());
				orderPO.getBooks().add(book);
				orderPO.setSellerId(sellerId);
				mav.addObject("order", orderPO);
				OrderBookDetailsPO lastOrderedCart = Utilities.getLastOrderedCartInSession(request);
				if(lastOrderedCart == null)
					lastOrderedCart = Utilities.getCartfromSession(request);
				lastOrderedCart.getOrders().add(orderPO);
				return mav;
			} else {
				ModelAndView mv = new ModelAndView("page");
				mv.addObject("userClickLogin",true);
				return mv;
			}
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_CART_TECH_ERROR);
		}
	}

	@SuppressWarnings("finally")
	private ModelAndView addToWishList(HttpServletRequest request, HttpServletResponse response, OrderBookDetailsPO cart, BindingResult bindingResult,
			int sellerId, String username) {
		try {
			if(username!= null) {
				ModelAndView mav = new ModelAndView("page");
				mav.addObject("userClickMyWishList", true);
				WishList wl = new WishList();
				UserLogin user = userDao.findUserInfoByUsername(username);
				wl.setUserDetails(user.getUserInfo());
				SellerDetails seller = sellerDao.getSellerDetails(sellerId);
				wl.setSellerBookDetails(seller);
				try {
					wishListDao.saveInWishList(wl);
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					UserLogin userLogin = userDao.findUserInfoByUsername(username);
					List<WishList> wishList = userLogin.getUserInfo().getWishList();
					mav.addObject("wishList", wishList);
					mav.addObject("seller", seller);
					return mav;
				}
			} else {
				ModelAndView mv = new ModelAndView("page");
				mv.addObject("userClickLogin",true);
				return mv;
			}
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_WISHLIST_TECH_ERROR);
		}
	}


	@RequestMapping(value = "/saveOrUpdateOrder/{username}", method = RequestMethod.POST)
	public ModelAndView saveOrder(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("order") OrderDetailsPO orderPO, 
			BindingResult bindingResult,@RequestParam("myCartProperty") String cartDetails,SessionStatus status, Principal principal,
			@PathVariable String username) {
		try {
			ModelAndView mav = new ModelAndView("page");
			mav.addObject("username", username);
			if(cartDetails.equals("updateQuantity")) {
				mav.addObject("userClickAddToCart", true);
				checkIfAskedCopiesAvailable(orderPO, mav);
				mav.addObject("order", orderPO);
				return mav;
			} 
			else if(cartDetails.equals("continueShopping")) {
				boolean isAvailable = checkIfAskedCopiesAvailable(orderPO, mav);
				if(isAvailable) {

					mav.addObject("userClickBuyBooks", true);
					OrderDetails order = new OrderDetails(orderPO);
					order.setPayStatus(Constants.PAYSTATUS_PENDING);
					order.setModeOfPay( Constants.MODE_OF_PAY_CASH);
					int orderId = 0;
					UserLogin user = userDao.findUserInfoByUsername(username);
					List<OrderDetails> pendingOrder = orderDao.fetchOrderByUserAndStatus(user.getUserInfo(),  Constants.PAYSTATUS_PENDING);
					if(!pendingOrder.isEmpty()) {
						orderId = pendingOrder.get(0).getId();
						order.setCreatedOn(pendingOrder.get(0).getCreatedOn());
					}
					if(orderPO.getUserdetails() == null) {
						UserInfo userinfo = user.getUserInfo();
						order.setUserdetails(userinfo);
					}
					if(orderId == 0) {
						createOrderBookDetails(orderPO, order,0);
						orderId = orderDao.saveOrder(order);
					}
					else {
						List<OrderBookDetails> bookOrderList = orderDao.fetchOrderBookDetailsById(orderId);
						Iterator<OrderBookDetails> booksInCartItr = bookOrderList.iterator();
						List<OrderBookDetails> booksInCart = new ArrayList<OrderBookDetails>();
						while(booksInCartItr.hasNext()) {
							OrderBookDetails bookInCart = booksInCartItr.next();
							if(bookInCart.getId() != orderPO.getId()) {
								booksInCart.add(bookInCart);
							}
						}
						order.setOrderbookdetails(booksInCart);
						createOrderBookDetails(orderPO, order, orderPO.getId());
						updateOrderDetailsOnAddOrDeleteBook(orderId,order.getOrderbookdetails(), order);
						order.setId(orderId);
						orderDao.saveOrUpdateOrder(order);
					}
					orderPO.setId(orderId);
				}  else {
					mav.addObject("userClickAddToCart", true);
				}
				mav.addObject("order", orderPO);
				return mav;
			} 
			else if(cartDetails.equals("editOrder")) {
				UserLogin user = userDao.findUserInfoByUsername(username);
				return returnMyCartView(request, response, user.getUserInfo());
			} 
			else {
				status.isComplete();
				return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
			}
		} catch(Exception e) {
			e.printStackTrace();
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}
	}

	private boolean checkIfAskedCopiesAvailable(OrderDetailsPO orderPO, ModelAndView mav) {
		boolean isAvailable = true;
		SellerDetails seller = sellerDao.getSellerDetails(orderPO.getSellerId());
		int copiesAvailable = seller.getQuantity();
		float sellerPrice = seller.getPrice();
		if(orderPO.getQuantity() <= copiesAvailable && orderPO.getQuantity() > 0) {
			if(seller.getUserLogin().getUserRole().equals(Constants.PRIME_ROLE)) {
				float discountedPrice = sellerPrice - (sellerPrice* Constants.DISCOUNT_PERCENT);
				orderPO.setAmount(discountedPrice * orderPO.getQuantity());
			} else
				orderPO.setAmount(sellerPrice * orderPO.getQuantity());
		}
		else {
			isAvailable = false;
			mav.addObject("message", Constants.copiesUnavailableMessage(copiesAvailable, ""));
		}
		return isAvailable;
	}

	private void createOrderBookDetails(OrderDetailsPO orderPO, OrderDetails order, int id){
		if(orderPO.getBooks()!= null) {
			Iterator<Book> bookItr = orderPO.getBooks().iterator();
			while(bookItr.hasNext()) {
				Book book = bookItr.next();
				OrderBookDetails cartBookDetail = new OrderBookDetails();
				cartBookDetail.setId(id);
				cartBookDetail.setSellerdetails(sellerDao.getSellerDetails(orderPO.getSellerId()));
				cartBookDetail.setBookCount(orderPO.getQuantity());
				order.addToCart(cartBookDetail);
			}
		}
	}

	private void updateOrderDetailsOnAddOrDeleteBook(int orderId, List<OrderBookDetails> orders, OrderDetails order) {
		List<OrderDetailsPO> orderList = Utilities.getLatestOrderList(orderId,orders,orderDao,bookDao);
		Iterator<OrderDetailsPO> orderListItr = orderList.iterator();
		float amount = 0;
		int quantity = 0;
		while(orderListItr.hasNext()) {
			OrderDetailsPO orderD = orderListItr.next();
			amount+= orderD.getAmount();
			quantity+=orderD.getQuantity();
		}
		if(amount != 0 && quantity !=0) {
			order.setAmount(amount);
			order.setQuantity(quantity);
		}
	}

	@RequestMapping(value = "/showCartForUpdate/{username}", method = RequestMethod.GET)
	public ModelAndView updateOrderById(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("bookOrderId") String id, Principal principal,
			@PathVariable String username) {
		try {
			OrderDetailsPO requiredOrder = null;
			int orderBookId =  Integer.parseInt(id);
			int orderId  = 0;
			List<OrderDetailsPO> orders = new ArrayList<OrderDetailsPO>();
			UserLogin user = userDao.findUserInfoByUsername(username);
			orderId = orderDao.fetchOrderByUserAndStatus(user.getUserInfo(), Constants.PAYSTATUS_PENDING).get(0).getId();
			orders =  Utilities.getLatestOrderList(orderId,null, orderDao, bookDao);
			Iterator<OrderDetailsPO> ordersItr = orders.iterator();
			while(ordersItr.hasNext()) {
				OrderDetailsPO order = ordersItr.next();
				if(order.getId() == orderBookId) {
					requiredOrder = order;
				}	
			}
			ModelAndView mav = new ModelAndView("page");
			mav.addObject("userClickAddToCart", true);
			mav.addObject("username", username);
			mav.addObject("order", requiredOrder);
			return mav;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}
	}


	@RequestMapping(value = "/deleteBookFromCart/{username}", method = RequestMethod.GET)
	public ModelAndView deleteOrderById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("bookOrderId") String id,@PathVariable String username, Principal principal) {
		try {
			int bookOrderId = Integer.parseInt(id);
			orderDao.deleteBookFromOrderById(bookOrderId);
			UserLogin user = userDao.findUserInfoByUsername(username);
			OrderDetails order = orderDao.fetchOrderByUserAndStatus(user.getUserInfo(), Constants.PAYSTATUS_PENDING).get(0);
			List<OrderBookDetails> orders = order.getOrderbookdetails();
			int orderId = order.getId();
			if(orders.isEmpty()) {
				orderDao.deleteOrderById(orderId);
			} else {
				OrderDetails newOrder = orderDao.fetchByOrderId(orderId);
				updateOrderDetailsOnAddOrDeleteBook(orderId,  orders, newOrder);
				orderDao.updateOrderDetails(newOrder);
			}
			return returnMyCartView(request, response, user.getUserInfo());
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.ORDER_DELETE_TECH_ERROR);
		}
	}


	@RequestMapping(value = "/myCart/{username}", method = RequestMethod.GET)
	public ModelAndView showMyCart(HttpServletRequest request, HttpServletResponse response, Principal principal,@PathVariable String username) {
		try {
			int id = Utilities.fetchUserRoleIdAndUsernameFromSessionOrDB(request, userDao, username);
			System.out.println("Username - "+ username );
			UserInfo user = userDao.fetchUser(id).getUserInfo();
			System.out.println("user - "+ user );
			ModelAndView mav = returnMyCartView(request, response, user);
			mav.addObject("order", new OrderDetailsPO());
			return mav;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_CART_TECH_ERROR);
		}
	}

	private ModelAndView returnMyCartView(HttpServletRequest request,HttpServletResponse response, UserInfo user) {
		try {
			List<OrderDetails> orderDetails = orderDao.fetchOrderByUserAndStatus(user, Constants.PAYSTATUS_PENDING);
			System.out.println("returnMyCartView - "+ user );
			ModelAndView mav = new ModelAndView("page");
			mav.addObject("userClickEditOrder", true);
			List<OrderDetailsPO> orders = new ArrayList<OrderDetailsPO>();
			if(!orderDetails.isEmpty()) {
				OrderDetails order = orderDetails.get(0);
				orders =  Utilities.getLatestOrderList(order.getId(), null, orderDao, bookDao);
			}
			mav.addObject("orders", orders);
			return mav;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_CART_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/viewWishList/{username}", method = RequestMethod.GET)
	public ModelAndView viewWishList(HttpServletRequest request, HttpServletResponse response, Principal principal, @PathVariable String username) {
		try {
			int id = Utilities.fetchUserRoleIdAndUsernameFromSessionOrDB(request, userDao, username);
			System.out.println("Username - "+ username );
			UserLogin userLogin = userDao.fetchUser(id);
			System.out.println("useuserLoginr - "+ userLogin );
			List<WishList> wishList = userLogin.getUserInfo().getWishList();
			ModelAndView mav = new ModelAndView("page");
			mav.addObject("userClickMyWishList", true);
			mav.addObject("wishList", wishList);
			mav.addObject("username", username);
			return mav;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_WISHLIST_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/removeFromWishList/{username}", method = RequestMethod.GET)
	public ModelAndView removeFromWishList(HttpServletRequest request, HttpServletResponse response, Principal principal, @PathVariable String username, @RequestParam("bookWishId") String id) {
		try {
			int bookWishId = Integer.parseInt(id);
			wishListDao.deleteFromWishList(bookWishId);
			if(username.equals(principal.getName()))
				username = principal.getName();
			UserLogin userLogin = userDao.findUserInfoByUsername(username);
			List<WishList> wishList = userLogin.getUserInfo().getWishList();
			ModelAndView mav = new ModelAndView("page");
			mav.addObject("userClickMyWishList", true);
			mav.addObject("wishList", wishList);
			return mav;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.WISHLIST_DELETE_TECH_ERROR);
		}
	} 

}
