package com.bookstoremanagement.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookstoremanagement.model.Author;
import com.bookstoremanagement.model.Book;
import com.bookstoremanagement.model.Category;
import com.bookstoremanagement.model.SellerDetails;


@Repository("sellerDetailsDAO")
@Transactional
public class SellerDetailsDaoImpl implements SellerDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private AuthorDao authorDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private BookDao bookDao;

	public List<Object> list(String term){
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query

		String hql="SELECT book.isbn,book.title,book.author.name,book.category.name,min(price),book.imageLink "
				+ "FROM SellerDetails s WHERE"
				+ " book.title like :title OR book.isbn = :isbn group by book.title,book.isbn";
		System.out.println("***");
		Query theQuery=currentSession.createQuery(hql);

		theQuery.setParameter("title","%"+term+"%");
		theQuery.setParameter("isbn",term);

		// execute query and get result list
		List<Object> list = theQuery.list();

		return list;
	}
	
	public List<Object> listByAuthor(String authorName){
		Session currentSession = sessionFactory.getCurrentSession();

		String hql="SELECT book.isbn,book.title,book.author.name,book.category.name,min(price),book.imageLink "
				+ "FROM SellerDetails s WHERE"
				+ " book.author.name = :authorName group by book.isbn";
	
		Query theQuery=currentSession.createQuery(hql);

		theQuery.setParameter("authorName",authorName);

		// execute query and get result list
		List<Object> list = theQuery.list();

		return list;
	}
	
	public List<Object> listByCategory(String categoryName){
		Session currentSession = sessionFactory.getCurrentSession();

		String hql="SELECT book.isbn,book.title,book.author.name,book.category.name,min(price),book.imageLink "
				+ "FROM SellerDetails s WHERE"
				+ " book.category.name = :categoryName group by book.isbn";
	
		Query theQuery=currentSession.createQuery(hql);
		theQuery.setParameter("categoryName",categoryName);
		List<Object> list = theQuery.list();

		return list;
	}

	public SellerDetails getSellerDetails(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		SellerDetails sellerDetails = currentSession.get(SellerDetails.class,theId);
		return sellerDetails;

	}

	public List<SellerDetails> listBooksOfSellerByUsername(String name){

		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<SellerDetails> theQuery = currentSession.createQuery("from SellerDetails "
				+ "WHERE userLogin.username = :username", SellerDetails.class);

		theQuery.setParameter("username",name);

		// execute query and get result list
		List<SellerDetails> sellerDetails = theQuery.getResultList();

		// return the results
		return sellerDetails;
	}


	public void saveSellerDetails(SellerDetails sellerDetails) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(sellerDetails);	
	}



	public void deleteSellerDetails(int id) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query theQuery = currentSession.createQuery("delete from SellerDetails where id=:sellerDetailsId");
		theQuery.setParameter("sellerDetailsId", id);

		theQuery.executeUpdate();

	}


	public void saveAllSellerDetails(SellerDetails sellerDetails) {
		Session currentSession = sessionFactory.getCurrentSession();
		Book book = sellerDetails.getBook();
		Author author = book.getAuthor();
		Category category = book.getCategory();
		int aid= authorDao.getPk(author.getName());
		int cid = categoryDao.getPk(category.getName());

		int bid=bookDao.getPk(book.getIsbn());
		if(bid==0) {
			//insert new author and category as none exists
			if(aid==0 && cid==0) {
				authorDao.saveAuthor(author);
				categoryDao.saveCategory(category);
				author.add(book);
				category.add(book);
				bookDao.saveBook(book);
			}
			// insert new author and fetch category id from existing one
			else if(aid==0 && cid!=0) {
				authorDao.saveAuthor(author);
				author.add(book);
				category = categoryDao.getCategory(cid);
				category.add(book);
				bookDao.saveBook(book);

			}

			else if(aid!=0 && cid==0) {
				categoryDao.saveCategory(category);
				category.add(book);
				author = authorDao.getAuthor(aid);
				author.add(book);
				bookDao.saveBook(book);

			}
			else {
				author = authorDao.getAuthor(aid);
				author.add(book);
				category = categoryDao.getCategory(cid);
				category.add(book);
				bookDao.saveBook(book);
			}
		}
		else {
			book = bookDao.getBook(bid);
		}

		book.add(sellerDetails);
		currentSession.saveOrUpdate(sellerDetails);
	}
}
