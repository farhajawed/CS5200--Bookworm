package com.bookstoremanagement.excpetionHandling;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.bookstoremanagement.utilities.Constants;
import com.bookstoremanagement.utilities.Utilities;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(HttpSessionRequiredException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView internalServerError(HttpServletRequest request, HttpServletResponse response, HttpSessionRequiredException ex) {
		return Utilities.logoutOnException(request, response, Constants.GENERIC_ERROR);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView internalServerError(HttpServletRequest request, HttpServletResponse response, EntityNotFoundException ex) {
		return Utilities.logoutOnException(request, response, Constants.GENERIC_ERROR);
	}

	@ExceptionHandler(SQLGrammarException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView internalServerError(HttpServletRequest request, HttpServletResponse response, SQLGrammarException ex) {
		return Utilities.logoutOnException(request, response, Constants.GENERIC_ERROR);
	}

	@ExceptionHandler(PersistenceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView internalServerError(HttpServletRequest request, HttpServletResponse response, PersistenceException ex) {
		return Utilities.logoutOnException(request, response, Constants.GENERIC_ERROR);
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView badRequest(HttpServletRequest request, HttpServletResponse response, TypeMismatchException ex) {		
		return Utilities.logoutOnException(request, response, Constants.GENERIC_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ModelAndView methodNotAllowed(HttpServletRequest request, HttpServletResponse response, HttpRequestMethodNotSupportedException ex) {
		return Utilities.logoutOnException(request, response, Constants.GENERIC_ERROR);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView resourceNotFound(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException ex) {
		return Utilities.logoutOnException(request, response, Constants.GENERIC_ERROR);
	}
}
