package com.bookstoremanagement.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bookstoremanagement.dao.SellerDetailsDao;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.Author;
import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.Category;
import com.bookstoremanagement.model.Messages;
import com.bookstoremanagement.model.SellerDetails;
import com.bookstoremanagement.model.UserInfo;
import com.bookstoremanagement.model.UserLogin;
import com.bookstoremanagement.rest.client.GoogleBook;
import com.bookstoremanagement.rest.client.GoogleBooksApiClient;
import com.bookstoremanagement.utilities.Utilities;


@Controller
@SessionAttributes("user")
public class SellBookController {
	
	@Autowired
	public UserDao userDao;
	
	@Autowired
	private GoogleBooksApiClient client;
	
	@Autowired
	private SellerDetailsDao sellerDetailsDao;


	
	@RequestMapping(value = "/sell-book")
	public ModelAndView sellbook() {		
		ModelAndView mv = new ModelAndView("page"); 
		mv.addObject("userClickSellBooks",true);
		return mv;
	}
	
	@RequestMapping(value = "/seller-advertised-book/{username}",method = RequestMethod.GET)
	public ModelAndView advertisedbook(Principal principal,@PathVariable String username) {		
		ModelAndView mv = new ModelAndView("page"); 
		if(principal!=null) {
			List<SellerDetails>sellerDetails = 
					sellerDetailsDao.
					listBooksOfSellerByUsername(username);
			mv.addObject("sellerDetails",sellerDetails);
			if(principal.getName().equals(username)) {
				mv.addObject("currentuser",true);
			}
		}
		mv.addObject("userClickMyBooks",true);
		return mv;
	}
	
	@RequestMapping(value = "/show-form-for-update", method = RequestMethod.GET)
    public ModelAndView showFormForUpdate(@RequestParam("sellerDetailsId") int id) {
	  ModelAndView mv = new ModelAndView("page");
	  SellerDetails sellerDetails = sellerDetailsDao.getSellerDetails(id);
	  mv.addObject("sellerDetails",sellerDetails);
	  mv.addObject("userClickUpdateBook",true);
	  return mv;
     
  }
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/delete-book-of-seller", method = RequestMethod.GET)
	public ModelAndView deleteCustomer(@RequestParam("sellerDetailsId") int id,
			@RequestParam("username") String username,
			final RedirectAttributes redirectAttributes) {	
		
		ModelAndView mv = new ModelAndView("redirect:/seller-advertised-book/"+username);
		try {
			sellerDetailsDao.deleteSellerDetails(id);
			
		}catch (ConstraintViolationException ex) {
			 String error = ex.getMessage();
			 redirectAttributes.addFlashAttribute("error", error);
		}
		finally{	
			return mv;
		}
	}
	
	@RequestMapping(value = "/seller-searches-book", method = RequestMethod.GET)
	public ModelAndView searchBookForSell(@RequestParam("isbn") String isbn) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("sellerClickSearchBook",true);
		GoogleBook googlebook =null;
		googlebook=client.findBookByIsbn(isbn);
		mv.addObject("googlebook",googlebook);
		mv.addObject("isbn",isbn);
		mv.addObject("sellerDetails",new SellerDetails());
		return mv;
	}
	
	@RequestMapping(value = "/show-book-for-sell", method = RequestMethod.GET)
    public ModelAndView showBookForSale(@RequestParam("isbn") String isbn,
    							@RequestParam("title") String title,
    							@RequestParam("author") String author,
    							@RequestParam("category") String category,
    							@RequestParam("imageLink") String imageLink,
    							Principal principal) {
	
	ModelAndView mv = new ModelAndView("page");
	  if(principal!= null) {
		  mv.addObject("userClickNext",true);
		  SellerDetails sellerDetails = new SellerDetails();
		  UserLogin userLogin = userDao.findUserInfoByUsername(principal.getName());
		  sellerDetails.setUserLogin(userLogin);
		  Author authorD = new Author(author);
		  Category categoryD = new Category(category);
		  if(category.equals(null)||category.equals("")) {
			  categoryD.setName("Others");
		  }
		  if(author.equals(null)||author.equals("")) {
			  authorD.setName("Others");
		  }
		  Book book = new Book(isbn,title,authorD,categoryD,imageLink);
		  sellerDetails.setBook(book);
		  mv.addObject("sellerDetails",sellerDetails); 
		  return mv;
	}else {
		mv.addObject("userClickLogin",true);
		return mv;
	}}
	
	@RequestMapping(value = "/advert-book", method = RequestMethod.POST)
    public ModelAndView advertBook(@ModelAttribute("sellerDetails")SellerDetails sellerDetails, 
    BindingResult result, ModelMap model) {
	  ModelAndView mv = new ModelAndView("page");
	  sellerDetailsDao.saveAllSellerDetails(sellerDetails);
	  mv.addObject("userClickAddDetailsForSale",true);
	  return mv; 
	 }
	
	@RequestMapping(value = "/seller-updates-book", method = RequestMethod.POST)
    public ModelAndView sellerUpdatesBook(@ModelAttribute("sellerDetails")SellerDetails sellerDetails, 
    BindingResult result, ModelMap model) {
	 String username = sellerDetails.getUserLogin().getUsername();
	  ModelAndView mv = new ModelAndView("redirect:/seller-advertised-book/"+username);
	  //get the id of the object to update
	  SellerDetails sd = sellerDetailsDao.getSellerDetails(sellerDetails.getId());
	  // set new values
	  sd.setPrice(sellerDetails.getPrice());
	  sd.setQuantity(sellerDetails.getQuantity());
	  sd.setBookCondition(sellerDetails.getBookCondition());
	  sellerDetailsDao.saveSellerDetails(sd);
	  return mv;
	 
  }
	
	
	@RequestMapping(value = "/show/{username}")
	public ModelAndView showSellerProfile(HttpServletRequest request,@PathVariable String username,Principal principal) {	

		ModelAndView mv = new ModelAndView("page");
		if(principal != null) {
			mv.addObject("userClickShowProfile",true);
			UserLogin userLogin = userDao.findUserInfoByUsername(username);
			int userId = Utilities.fetchUserRoleIdAndUsernameFromSessionOrDB(request, userDao, username);
			UserInfo userInfo = userDao.fetchUser(userId).getUserInfo();
			mv.addObject("user",userInfo);
			mv.addObject("userLogin",userLogin);
			Messages message = new Messages();
			message.setUserLogin(userLogin);
			mv.addObject("message",message);
			return mv;
		}else {
			mv.addObject("userClickLogin",true);
			return mv;
		}	
	}
	
	
	
}
