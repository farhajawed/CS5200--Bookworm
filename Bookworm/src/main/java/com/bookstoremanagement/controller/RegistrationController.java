package com.bookstoremanagement.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bookstoremanagement.PO.RegistrationPO;
import com.bookstoremanagement.PO.UserInfoPO;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.UserAddress;
import com.bookstoremanagement.model.UserInfo;
import com.bookstoremanagement.model.UserLogin;
import com.bookstoremanagement.utilities.Constants;
import com.bookstoremanagement.utilities.Utilities;
import com.bookstoremanagement.validators.RegistrationValidation;

@Controller
public class RegistrationController{

	@Autowired
	public UserDao userDao;

	@Autowired
	private RegistrationValidation registrationValidation;

	public void setRegistrationValidation(
			RegistrationValidation registrationValidation) {
		this.registrationValidation = registrationValidation;
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("page");
		try {
			mav.addObject("userClickSignUp", true);
			mav.addObject("user", new UserInfoPO());
			return mav;
		} catch(Exception e){
			return Utilities.logoutOnException(request, response,Constants.REGISTRATION_TECH_ERROR);
		}
	}
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") UserInfoPO user,  BindingResult bindingResult, 
			@RequestParam("image") MultipartFile file
			,@Validated(RegistrationPO.class) RegistrationPO registration, Errors errors) {
		try {
			System.out.println("Register user - "+ user);
			Utilities.setAddressForValidation(user, registration);
			System.out.println("Register registrationForm - "+ registration);
			registrationValidation.validate(registration, errors);
			if (!errors.hasErrors()) {
				saveUserToDB(request, response, user, file);
				if(request!= null && request.getSession()!= null && request.getSession().getAttribute("userRole")!=null 
						&& request.getSession().getAttribute("userRole").equals(Constants.ADMIN_ROLE)) {
					return new ModelAndView("redirect:/admin");
				} else {
					ModelAndView mav = new ModelAndView("page");
					mav.addObject("userClickRegister", true);
					return mav;
				}
			} else {
				ModelAndView mav = new ModelAndView("page");
				mav.addObject("userClickSignUp", true);
				mav.addObject("message", errors);
				List<ObjectError> errorList = errors.getAllErrors();
				List<String> errorCodes = new ArrayList<String>();
				for(ObjectError e: errorList) {
					errorCodes.add(e.getCode());
					System.out.println("error ---"+e.getCode());
				}
				mav.addObject("errorCodes", errorCodes);
				mav.addObject("user", user);
				return mav;
			}
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.REGISTRATION_TECH_ERROR);
		}
	}

	private void saveUserToDB(HttpServletRequest request, HttpServletResponse response,
			UserInfoPO user, MultipartFile file) {
		Utilities.saveImageOnServer(user, file);
		UserInfo userInfo = new UserInfo(user);
		UserLogin userLogin = new UserLogin();
		Iterator<UserAddress> userAddItr = user.getUseraddress().iterator();
		while(userAddItr.hasNext()) {
			UserAddress userAdd = userAddItr.next();
			userAdd.setIsPrimary(Constants.ISPRIMARY);
			userInfo.addAddressDetails(userAdd);
		}
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		userLogin.setPassword(encoder.encode(user.getPassword()));
		userLogin.setUsername(user.getUsername());
		userLogin.setUserRole(user.getUserrole());
		userLogin.setUserInfo(userInfo);
		userDao.register(userLogin);
	}

}