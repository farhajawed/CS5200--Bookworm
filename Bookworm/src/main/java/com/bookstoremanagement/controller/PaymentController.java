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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookstoremanagement.PO.OrderDetailsPO;
import com.bookstoremanagement.PO.PaymentPO;
import com.bookstoremanagement.dao.BookDao;
import com.bookstoremanagement.dao.OrderDetailsDao;
import com.bookstoremanagement.dao.SellerDetailsDao;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.OrderBookDetails;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.SellerDetails;
import com.bookstoremanagement.model.UserAddress;
import com.bookstoremanagement.model.UserInfo;
import com.bookstoremanagement.model.UserLogin;
import com.bookstoremanagement.utilities.Constants;
import com.bookstoremanagement.utilities.Utilities;

@Controller
public class PaymentController {

	@Autowired
	public OrderDetailsDao orderDao;

	@Autowired
	public SellerDetailsDao sellerDao;

	@Autowired
	public UserDao userDao;

	@Autowired
	public BookDao bookDao;

	@RequestMapping(value = "/makePayment", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView makePayment(HttpServletRequest request, HttpServletResponse response, Principal principal) {
		try {
			UserLogin user = userDao.findUserInfoByUsername(principal.getName());
			OrderDetails order = orderDao.fetchOrderByUserAndStatus(user.getUserInfo(), "PENDING").get(0);
			int orderId = order.getId();
			ModelAndView mav = new ModelAndView("page");
			PaymentPO paymentDetails = new PaymentPO();
			paymentDetails.setUser(user);
			List<OrderBookDetails> booksInCart = order.getOrderbookdetails();
			for(OrderBookDetails cart : booksInCart) {
				SellerDetails seller = cart.getSellerdetails();
				Book book = seller.getBook();
				if(cart.getBookCount() > seller.getQuantity()) {
					mav.addObject("userClickEditOrder",true);
					mav.addObject("message", Constants.copiesUnavailableMessage(seller.getQuantity(), book.getTitle()));
					List<OrderDetailsPO> orders =  Utilities.getLatestOrderList(orderId, null, orderDao, bookDao);
					mav.addObject("orders",orders);
				} else {
					mav.addObject("userClickMakePayment", true);
					paymentDetails.setAmount(order.getAmount());
					mav.addObject("paymentDetails",paymentDetails);
				}
			}
			return mav;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PAYMENT_TECH_ERROR);
		}

	}


	@RequestMapping(value = "/saveOrCancelPayment", method = RequestMethod.POST)
	public ModelAndView saveOrCancelProfileChanges(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("paymentDetails") PaymentPO payDetails,  BindingResult bindingResult,@RequestParam("placeOrder") String value, Principal principal) {
		try {
			if(value.equals("placeOrder"))
				return savePayment(request,  response,  payDetails, principal);
			else if (value.equals("cancel")) {
				return cancelPayment(request,  response);
			}
			else {
				return Utilities.logoutOnException(request, response,Constants.PAYMENT_TECH_ERROR);
			}
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PAYMENT_TECH_ERROR);
		}
	}


	private ModelAndView savePayment(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("paymentDetails") PaymentPO payDetails,Principal principal) {
		try {
			ModelAndView mv = new ModelAndView("page");
			UserLogin user = userDao.findUserInfoByUsername(principal.getName());
			OrderDetails order = orderDao.fetchOrderByUserAndStatus(user.getUserInfo(), Constants.PAYSTATUS_PENDING).get(0);
			int orderId = order.getId();
			order.setPayStatus(Constants.PAYSTATUS_COMPLETED);
			order.setUserdetails(user.getUserInfo());
			UserInfo userFromUI = payDetails.getUser().getUserInfo();
			UserAddress deliveryAdd = userFromUI.getUseraddress().get(1);
			UserAddress registrationAdd = userFromUI.getUseraddress().get(0);
			String deliveryAddStr = deliveryAdd.getStreet1()+" "+ deliveryAdd.getStreet2()+" "+ deliveryAdd.getCity()+" "+ deliveryAdd.getState()+" "+ deliveryAdd.getPincode();
			String registrationAddStr = registrationAdd.getStreet1()+" "+ registrationAdd.getStreet2()+" "+ registrationAdd.getCity()+" "+ registrationAdd.getState()+" "+ registrationAdd.getPincode();
			UserInfo userInfo =  user.getUserInfo();
			userInfo.setUseraddress(new ArrayList<UserAddress>());
			userInfo.addAddressDetails(deliveryAdd);
			if(!registrationAddStr.equalsIgnoreCase(deliveryAddStr)) {
				user.setUserInfo(userInfo);
				userDao.updateUserDetails(user);
			}
			List<OrderBookDetails> cart = orderDao.fetchOrderBookDetailsById(orderId);
			Iterator<OrderBookDetails> orderBookDetailsListItr = cart.iterator();
			while(orderBookDetailsListItr.hasNext()) {
				OrderBookDetails orderBookDetail = orderBookDetailsListItr.next();
				SellerDetails sellerDetails = sellerDao.getSellerDetails(orderBookDetail.getSellerdetails().getId());
				int previousAvailableQunatity = sellerDetails.getQuantity();
				sellerDetails.setQuantity(previousAvailableQunatity - orderBookDetail.getBookCount());
				sellerDao.saveSellerDetails(sellerDetails);
			}
			orderDao.updateOrderDetails(order);
			request.getSession().removeAttribute("orderId");
			mv.addObject("userSuccessfulPay",true);
			return mv;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PAYMENT_TECH_ERROR);
		}
	}

	private ModelAndView cancelPayment(HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mv = new ModelAndView("page");
			mv.addObject("userClickHome",true);
			return mv;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_RELOAD_TECH_ERROR);
		}
	}
}
