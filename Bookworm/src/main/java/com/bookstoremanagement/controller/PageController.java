package com.bookstoremanagement.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.bookstoremanagement.PO.OrderBookDetailsPO;
import com.bookstoremanagement.dao.AuthorDao;
import com.bookstoremanagement.dao.BookDao;
import com.bookstoremanagement.dao.CategoryDao;
import com.bookstoremanagement.dao.RatingDao;
import com.bookstoremanagement.dao.SellerDetailsDao;
import com.bookstoremanagement.dao.UserDao;
import com.bookstoremanagement.model.Author;
import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.Category;
import com.bookstoremanagement.model.Rating;
import com.bookstoremanagement.model.UserLogin;
import com.bookstoremanagement.rest.client.GoogleBook;
import com.bookstoremanagement.rest.client.GoogleBooksApiClient;


@Controller
@SessionAttributes("userRole")
public class PageController {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SellerDetailsDao sellerDetailsDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private AuthorDao authorDao;

	@Autowired
	private RatingDao ratingDao;

	@Autowired
	private GoogleBooksApiClient client;


	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Principal principal) {		
		ModelAndView mv = new ModelAndView("page"); 
		mv.addObject("userClickHome",true);
		if(principal != null) {
			UserLogin user = userDao.findUserInfoByUsername(principal.getName());
			String userRole = userDao.fetchUser(user.getId()).getUserRole();
			request.getSession().setAttribute("userId", user.getId());
			request.getSession().setAttribute("userRole", userRole);
		}
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {		
		ModelAndView mv = new ModelAndView("page"); 
		mv.addObject("userClickAbout",true);
		return mv;
	}


	@RequestMapping(value = "/buy-book")
	public ModelAndView buybook() {		
		ModelAndView mv = new ModelAndView("page"); 
		mv.addObject("userClickBuyBooks",true);
		return mv;
	}

	@RequestMapping(value = "/browse-book")
	public ModelAndView browsebook() {		
		ModelAndView mv = new ModelAndView("page"); 
		List<Author>authors = authorDao.getAuthors();
		List<Category>categories = categoryDao.getCategory();
		mv.addObject("authors",authors);
		mv.addObject("categories",categories);
		mv.addObject("userClickBrowseBooks",true);
		return mv;
	}

	@RequestMapping(value = "/search-book", method = RequestMethod.GET)
	public ModelAndView searchBook(@RequestParam("term") String term) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickSearchBook",true);
		System.out.println("Term - "+ term);
		//List<Book>books = bookDao.list(term);
		List<Object>sellerDetails = sellerDetailsDao.list(term);
		//mv.addObject("books",books);
		mv.addObject("sellerDetails",sellerDetails);
		return mv;
	}
	
	@RequestMapping(value = "/browse/author/{authorName:.+}", method = RequestMethod.GET)
	public ModelAndView searchBookByAuthor(@PathVariable String authorName) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickSearchBook",true);
		List<Object>sellerDetails = sellerDetailsDao.listByAuthor(authorName);
		System.out.println(sellerDetails);
		mv.addObject("sellerDetails",sellerDetails);
		return mv;
	}
	
	@RequestMapping(value = "/browse/category/{categoryName:.+}", method = RequestMethod.GET)
	public ModelAndView searchBookByCategory(@PathVariable String categoryName) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickSearchBook",true);
		List<Object>sellerDetails = sellerDetailsDao.listByCategory(categoryName);
		System.out.println(sellerDetails);
		mv.addObject("sellerDetails",sellerDetails);
		return mv;
	}

	@RequestMapping(value = "/show/book/{isbn}")
	public ModelAndView showSingleBook(HttpServletRequest request, HttpServletResponse response,@PathVariable String isbn, Principal principal) {	

		System.out.println(isbn);
		ModelAndView mv = new ModelAndView("page"); 
		int userId = 0;
		if(request.getSession().getAttribute("userId") != null)
			userId = (Integer) request.getSession().getAttribute("userId");
		if(userId == 0 && principal !=null) {
			userId = userDao.findUserInfoByUsername(principal.getName()).getId();
		}
		mv.addObject("userClickShowBook",true);
		GoogleBook googlebook = client.findBookByIsbn(isbn);
		Book book  = bookDao.searchByIsbn(isbn);
		mv.addObject("googlebook",googlebook);
		mv.addObject("book",book);
		mv.addObject("avgRating",ratingDao.getAverageRating(isbn));
		mv.addObject("cart",new OrderBookDetailsPO());
		//ratings
		if(principal!=null) {
			System.out.println(userId);
			Rating rating = ratingDao.searchByIsbnUserName(isbn,principal.getName());
			if(rating==null) {
				Rating ratingnew = new Rating();
				ratingnew.setBook(book);
				UserLogin user = userDao.fetchUser(userId);
				ratingnew.setUserLogin(user);
				mv.addObject("rating",ratingnew);
				mv.addObject("add",true);

			}
			else {
				mv.addObject("rating",rating);
				mv.addObject("edit",true);
			}
			mv.addObject("currentuser",principal.getName());
		}
		List<Rating>ratings = ratingDao.getRatings(isbn);
		mv.addObject("ratings",ratings);
		System.out.println(ratings);
		return mv;
	}

	@RequestMapping(value = "/buyer/review", method = RequestMethod.POST)
	public ModelAndView buyerReviews(@ModelAttribute("rating")Rating rating, Principal principal,
			BindingResult result, ModelMap model) {
		ratingDao.saveRating(rating);
		String isbn = rating.getBook().getIsbn();
		ModelAndView mv = new ModelAndView("redirect:/show/book/"+isbn);
		return mv;
	}

}

