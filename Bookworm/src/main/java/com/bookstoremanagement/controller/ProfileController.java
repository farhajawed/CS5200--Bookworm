package com.bookstoremanagement.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstoremanagement.PO.BookSellerDetailsPO;
import com.bookstoremanagement.PO.OrderBookDetailsPO;
import com.bookstoremanagement.PO.RegistrationPO;
import com.bookstoremanagement.PO.UserInfoPO;
import com.bookstoremanagement.dao.OrderDetailsDao;
import com.bookstoremanagement.dao.SellerDetailsDao;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.OrderBookDetails;
import com.bookstoremanagement.model.OrderDetails;
import com.bookstoremanagement.model.SellerDetails;
import com.bookstoremanagement.model.UserInfo;
import com.bookstoremanagement.model.UserLogin;
import com.bookstoremanagement.utilities.Constants;
import com.bookstoremanagement.utilities.Utilities;
import com.bookstoremanagement.validators.RegistrationValidation;

@Controller
@SessionAttributes("user")
public class ProfileController {

	@Autowired
	public UserDao userDao;

	@Autowired
	public OrderDetailsDao orderDao;

	@Autowired 
	public SellerDetailsDao sellerDao;

	@Autowired
	private RegistrationValidation registrationValidation;


	@RequestMapping(value = "/userInfo/{username}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView userInfo(HttpServletRequest request, HttpServletResponse response, Model model, Principal principal,@PathVariable("username") String value) {
		ModelAndView mv = new ModelAndView("page"); 
		try {
			String username = principal.getName();
			System.out.println("value userinfo - " +value);
			if(value !=null && !value.isEmpty()) {
				username = value;
			}
			int userId = Utilities.fetchUserRoleIdAndUsernameFromSessionOrDB(request, userDao, username);
			if(!username.equals(principal.getName()))
				userId = userDao.findUserInfoByUsername(username).getId();
			UserInfo user = userDao.fetchUser(userId).getUserInfo();
			UserInfoPO userPO = new UserInfoPO(user);
			userPO.setUsername(username);
			System.out.println("userInfo - " +user);
			String userRole = user.getUserLogin().getUserRole();
			System.out.println("userRole - "+userRole);
			mv.addObject("user",userPO);
			mv.addObject("userClickProfile",true);
			return mv;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PROFILE_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public ModelAndView updateProfile(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user,  BindingResult bindingResult,@RequestParam("userProfile") String value, Principal principal) {
		try {
			if(value.equals("editedProfile"))
				return editProfile( request,  response,  user, principal );
			else if (value.equals("changePassword"))
				return changePassword( request,  response, user);
			else if (value.equals("viewBooksOrdered"))
				return viewOrderedBooks(request,  response, user);
			else if (value.equals("viewBooksSold"))
				return viewSellerBooks( request,  response, user);
			else {
				return Utilities.logoutOnException(request, response,Constants.PROFILE_TECH_ERROR);
			}
		} catch (Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PROFILE_TECH_ERROR);
		}
	}

	private ModelAndView viewOrderedBooks(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user) {
		try {
			ModelAndView mav = new ModelAndView("page");
			UserInfo userInfo = new UserInfo(user);
			List<OrderDetails> completedOrders = orderDao.fetchOrderByUserAndStatus(userInfo, "COMPLETED");
			List<OrderBookDetailsPO> orderBookDetails = new ArrayList<OrderBookDetailsPO>();
			for(OrderDetails order : completedOrders) {
				for(OrderBookDetails cart : order.getOrderbookdetails()) {
					OrderBookDetailsPO cartPO = new OrderBookDetailsPO(cart);
					orderBookDetails.add(cartPO);
				}
			}
			String userRole = userInfo.getUserLogin().getUserRole();
			mav.addObject("userRole",userRole);
			mav.addObject("userClickViewOrderedBooks", true);
			mav.addObject("user", user);
			mav.addObject("orders", orderBookDetails);
			return mav;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.ORDER_HISTORY_TECH_ERROR);
		}
	}

	private ModelAndView viewSellerBooks(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user) {
		try {
			ModelAndView mav = new ModelAndView("page");
			UserInfo userInfo = new UserInfo(user);
			List<SellerDetails> sellerDetails = sellerDao.listBooksOfSellerByUsername(user.getUsername());
			List<BookSellerDetailsPO> sellers = new ArrayList<BookSellerDetailsPO>();
			for(SellerDetails sellerDetail : sellerDetails) {
				BookSellerDetailsPO seller = new BookSellerDetailsPO(sellerDetail);
				int copiesSold = 0;
				List<OrderBookDetails> cartDetails = sellerDetail.getOrderBookDetails();
				for(OrderBookDetails cart : cartDetails) {

					if(sellerDetail.getId() == cart.getSellerdetails().getId() && cart.getOrderdetails().getPayStatus().equals(Constants.PAYSTATUS_COMPLETED))
						copiesSold = cart.getBookCount();
				}
				seller.setCopiesSold(copiesSold);
				sellers.add(seller);
			}
			String userRole = userInfo.getUserLogin().getUserRole();
			mav.addObject("userRole",userRole);
			mav.addObject("userClickViewBooksSold", true);
			mav.addObject("user", user);
			mav.addObject("sellerDetails", sellers);
			return mav;
		}
		catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.SALES_HISTORY_TECH_ERROR);
		}
	}

	private ModelAndView editProfile(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user,Principal principal) {
		ModelAndView mav = new ModelAndView("page");
		try {
			int userId = Utilities.fetchUserRoleIdAndUsernameFromSessionOrDB(request, userDao, user.getUsername());
			if(!user.getUsername().equals(principal.getName()))
				userId = userDao.findUserInfoByUsername(user.getUsername()).getId();		
			UserInfo existingUser = userDao.fetchUser(userId).getUserInfo();
			System.out.println(existingUser);
			UserInfoPO existingUserPO = new UserInfoPO(existingUser);
			mav.addObject("userClickEditProfile", true);
			String userRole = existingUser.getUserLogin().getUserRole();
			System.out.println("userRole - "+userRole);
			mav.addObject("userRole",userRole);
			mav.addObject("user", existingUserPO);
			return mav;
		}catch(Exception e) {
			e.printStackTrace();
			return Utilities.logoutOnException(request, response,Constants.PROFILE_EDIT_TECH_ERROR);
		}
	}
	private ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response, UserInfoPO user) {
		try {
			ModelAndView mav = new ModelAndView("page");
			mav.addObject("userClickChangePassword", true);
			mav.addObject("user", user);
			return mav;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PASSWORD_CHANGE_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/saveOrCancelEditedProfile", method = RequestMethod.POST)
	public ModelAndView saveOrCancelProfileChanges(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user,  BindingResult bindingResult,
			@RequestParam("editedProfile") String value, 
			@Validated(RegistrationPO.class) RegistrationPO registration, Errors errors,
			@RequestParam("image") MultipartFile file) {
		try {
			if(value.equals("save")) {
				System.out.println("Register user - "+ user);
				Utilities.setAddressForValidation(user, registration);
				System.out.println("Register registrationForm - "+ registration);
				registrationValidation.validate(registration, errors);
				if (!errors.hasErrors()) {
					return saveEditProfile( request,  response,  user, file);
				} else {
					ModelAndView mav = new ModelAndView("page");
					mav.addObject("userClickEditProfile", true);
					mav.addObject("message", errors);
					List<ObjectError> errorList = errors.getAllErrors();
					List<String> errorCodes = new ArrayList<String>();
					for(ObjectError e: errorList) {
						errorCodes.add(e.getCode());
						System.out.println("error edit ---"+e.getCode());
					}
					mav.addObject("errorCodes", errorCodes);
					mav.addObject("user", user);
					return mav;
				}
			}
			else if (value.equals("cancel")) {
				return cancelProfileButton(request,  response, user.getId());
			} 
			else  {
				return Utilities.logoutOnException(request, response,Constants.PROFILE_EDIT_SAVE_TECH_ERROR);
			}
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PROFILE_EDIT_SAVE_TECH_ERROR);
		}
	}


	private ModelAndView saveEditProfile(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user,MultipartFile file) {
		try {
			ModelAndView mav = new ModelAndView("page");
			Utilities.saveImageOnServer(user, file);
			System.out.println("user edit address- "+user.getUseraddress());
			UserInfo userInfo = new UserInfo(user);
			String userOldRole = userDao.fetchUser(userInfo.getId()).getUserRole();
			UserLogin userLogin = userInfo.getUserLogin();
			//			List<OrderDetails> orders = userInfo.getOrderdetails();
			//			if(orders == null)
			//				orders = new ArrayList<OrderDetails>();
			//			if(orders != null) {
			//				for(OrderDetails order : orders) {
			//					userInfo.addToOrderDetails(order);
			//				}
			//			} 
			userLogin.setUserInfo(userInfo);
			userDao.updateUserDetails(userLogin);
			System.out.println("User info edit profile - "+userInfo);
			String userRole = userInfo.getUserLogin().getUserRole();
			System.out.println("userRole - "+userRole);
			mav.addObject("userRole",userRole);
			if(!userOldRole.equals(userRole))
				mav.addObject("userClickLogin", true);
			else
				mav.addObject("userClickProfile", true);
			mav.addObject("user", user);
			return mav;
		} catch(Exception e) {
			e.printStackTrace();
			return Utilities.logoutOnException(request, response,Constants.PROFILE_EDIT_SAVE_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/saveOrCancelPasswordChanges", method = RequestMethod.POST)
	public ModelAndView saveOrCancelPasswordChanges(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user,  BindingResult bindingResult,@RequestParam("changePassword") String value, Principal principal) {
		try {
			if(value.equals("save"))
				return saveChangePassword( request,  response,  user, principal);
			else if (value.equals("cancel")) {
				return cancelProfileButton( request,  response, user.getId());
			}
			else {
				return Utilities.logoutOnException(request, response,Constants.PASSWORD_CHANGE_TECH_ERROR);
			}
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PASSWORD_CHANGE_TECH_ERROR);

		}
	}

	private ModelAndView saveChangePassword(HttpServletRequest request, HttpServletResponse response, UserInfoPO userPo,Principal principal) {
		try {
			ModelAndView mav = null;
			String userRole = userPo.getUserLogin().getUserRole();
			System.out.println("saveChangePassword userRole - "+userRole);
			String[] passwords = userPo.getPassword().split(",");
			String newPassword = passwords[0];
			String confirmNewPassword = passwords[1];
			if(newPassword.equals(confirmNewPassword)) {
				PasswordEncoder encoder = new BCryptPasswordEncoder();
				newPassword = encoder.encode(newPassword);
				UserLogin user = userDao.fetchUser(userPo.getId());
				user.setPassword(newPassword);
				userDao.updateUserDetails(user);
				if(principal!= null && principal.getName().equals(user.getUsername())) {
					mav = new ModelAndView("page");
					mav.addObject("userClickLogin", true);
				}
				else {
					mav = new ModelAndView("redirect:/userInfo/"+user.getUsername());
				}
			}
			return mav;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PASSWORD_CHANGE_SAVE_TECH_ERROR);
		}
	}

	private ModelAndView cancelProfileButton(HttpServletRequest request,HttpServletResponse response, int userId ) {
		try {
			ModelAndView mv = new ModelAndView("page");
			UserInfo userInfo = userDao.fetchUser(userId).getUserInfo();
			UserInfoPO userPO = new UserInfoPO(userInfo);
			mv.addObject("user",userPO);
			mv.addObject("userClickProfile",true);
			return mv;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PASSWORD_CHANGE_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/show/profile/{username}",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showBuyerProfile(HttpServletRequest request,HttpServletResponse response, @PathVariable String username,Principal principal) {	
		try {
			ModelAndView mv = new ModelAndView("page");
			if(principal != null) {
				mv.addObject("userClickShowBuyerProfile",true);
				int userId = Utilities.fetchUserRoleIdAndUsernameFromSessionOrDB(request, userDao, username);
				UserInfo userInfo = userDao.fetchUser(userId).getUserInfo();
				mv.addObject("user",userInfo);
			}else {
				mv.addObject("userClickLogin",true);
			}	
			return mv;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PROFILE_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/showAdmin/profile/{username}",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView showProfile(HttpServletRequest request,HttpServletResponse response, @PathVariable String username,Principal principal) {	
		try {
			ModelAndView mv = new ModelAndView("redirect:/userInfo/"+username);
			if(principal != null) {
				mv.addObject("username",username);
			}else {
				mv.addObject("userClickLogin",true);
			}	
			return mv;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.USER_PROFILE_TECH_ERROR);
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/delete/{username}",  method = {RequestMethod.GET})
	public ModelAndView deleteUser(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam int userId,@PathVariable String username,Principal principal,final RedirectAttributes redirectAttributes) {	
		ModelAndView mv = new ModelAndView("redirect:/adminUserSearch?name=&admin=viewAllUser");
		try {
			if(principal != null) {
				mv.addObject("userClickAdmin",true);
				UserLogin user = userDao.fetchUser(userId);
				userDao.deleteUser(user);
			}else {
				mv.addObject("userClickLogin",true);
			}	
		}catch (ConstraintViolationException ex) {
			String error = ex.getMessage();
			System.out.println("error admin delete - "+ error);
			redirectAttributes.addFlashAttribute("errorMsg", error);
		}
		catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.PROFILE_DELETE_TECH_ERROR);
		} finally {
			return mv;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage(HttpServletRequest request,HttpServletResponse response, 
			Model model, Principal principal) {
		ModelAndView mv = new ModelAndView("page"); 
		try {
			request.getSession().setAttribute("isAdmin", true);
			List<UserInfoPO> userPOs = new ArrayList<UserInfoPO>();
			mv.addObject("users",userPOs);
			mv.addObject("userClickAdmin",true);
		} 
		catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}finally{	
			return mv;
		}
	}
	@SuppressWarnings("finally")
	@RequestMapping(value = "/adminUserSearch", method = RequestMethod.GET)
	public ModelAndView adminSearchPage(HttpServletRequest request,HttpServletResponse response, 
			Model model, Principal principal,@RequestParam("name") String name, @RequestParam("admin") String value) {
		ModelAndView mv = new ModelAndView("page"); 
		try {
			request.getSession().setAttribute("isAdmin", true);
			int userId =  userDao.findUserInfoByUsername(principal.getName()).getId();
			UserLogin admin = userDao.fetchUser(userId);
			List<UserLogin> users = null;
			if(value.equals(Constants.VIEW_ALL_USERS)) {
				 users = userDao.fetchGivenUsersExceptAdmin(admin,"");
					mv.addObject("userClickViewAllUsers",true);
			}
			else {
				users = userDao.fetchGivenUsersExceptAdmin(admin,name);
				mv.addObject("userClickUserSearch",true);
			}
			List<UserInfoPO> userPOs = new ArrayList<UserInfoPO>();
			for(UserLogin usr : users)
			{
				UserInfo userDetails = usr.getUserInfo();
				UserInfoPO userPO = new UserInfoPO(userDetails);
				System.out.println("admin - enable  : "+ userPO.getUserLogin().getEnabled());
				userPOs.add(userPO);
			}
			mv.addObject("users",userPOs);
			
		} 
		catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}finally{	
			return mv;
		}
	}
}
