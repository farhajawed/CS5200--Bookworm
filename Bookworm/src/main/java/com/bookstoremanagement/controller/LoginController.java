package com.bookstoremanagement.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bookstoremanagement.PO.UserInfoPO;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.UserInfo;
import com.bookstoremanagement.model.UserLogin;
import com.bookstoremanagement.utilities.Constants;
import com.bookstoremanagement.utilities.Utilities;

@Controller
@SessionAttributes("userRole")
public class LoginController {
	@Autowired
	public UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			request.removeAttribute("userId");
			request.removeAttribute("userRole");
			request.removeAttribute("uName");
			request.removeAttribute("isAdmin");
			ModelAndView mv = new ModelAndView("page"); 
			mv.addObject("userClickLogin",true);
			return mv;
		} catch(Exception e){
			e.printStackTrace();
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public ModelAndView logoutSuccessfulPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			ModelAndView mv = new ModelAndView("page"); 
			mv.addObject("userClickLogout",true);
			request.getSession().removeAttribute("userId");
			request.getSession().removeAttribute("userRole");
			request.getSession().removeAttribute("uName");
			request.getSession().removeAttribute("isAdmin");
			return mv;
		} catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}
	}


	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied(HttpServletRequest request, HttpServletResponse response, Model model, Principal principal) {
		try {
			ModelAndView mv = new ModelAndView("page"); 
			mv.addObject("userAccessDenied",true);
			if (principal != null) {
				mv.addObject("message", "Hi " + principal.getName() +", "
				+ "<br> <br>"+Constants.ACCESS_DENIED);
			} else {
				mv.addObject("msg", Constants.ACCESS_DENIED);
			}
			return mv;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}
	}

	@RequestMapping(value = "/enableOrDisableUser/{username}")
	public ModelAndView enableUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String username,@RequestParam("enable") String value,Principal principal) {	
		try {
			ModelAndView mv = new ModelAndView("redirect:/admin");
			mv.addObject("userClickAdmin",true);
			UserLogin user = userDao.findUserInfoByUsername(username);
			if(value.equals("true")) {
				user.setEnabled(Constants.ENABLE_USER_ACCOUNT);
				userDao.updateUserDetails(user);
			}else if(value.equals("false")){
				user.setEnabled(Constants.DISABLE_USER_ACCOUNT);
				userDao.updateUserDetails(user);
			} else {
				return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
			}	
			return mv;
		}catch(Exception e) {
			return Utilities.logoutOnException(request, response,Constants.UNABLE_TO_LOAD_TECH_ERROR);
		}
	}
}