<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<%@page session="true"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!-- carousel -->
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
   <c:choose>
		<c:when test="${userRole eq 'SELLER'}">
	      <div class="item active">
	        <img src="${images}/seller-cover3.jpg" alt="book_cover1" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	           <h3>Reading is dreaming with open eyes!</h3>
	          <p>Buy and Sell your books!!</p>
	        </div>
	      </div>
	
	      <div class="item">
	        <img src="${images}/seller-cover2.png" alt="book_cover2" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	          <h3>Books are a uniquely portable magic</h3>
	          <p>Stephen King</p>
	        </div>
	      </div>
	    
	      <div class="item">
	        <img src="${images}/seller-cover4.png" alt="book_cover3" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	          <h3>Where is human nature so weak as in the bookstore?</h3>
	          <p>Henry Ward Beecher </p>
	        </div>
	      </div> 
	      </c:when>
	      
	      <c:when test="${userRole eq 'ADMIN'}">
	      <div class="item active">
	        <img src="${images}/admin.jpg" alt="book_cover1" style="width:100%; height:500px;">
	      </div>
	
	      <div class="item">
	        <img src="${images}/seller-cover2.png" alt="book_cover2" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	          <h3>Books are a uniquely portable magic</h3>
	          <p>Stephen King</p>
	        </div>
	      </div>
	    
	      <div class="item">
	        <img src="${images}/cover3.jpg" alt="book_cover3" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	          <h3>Where is human nature so weak as in the bookstore?</h3>
	          <p>Henry Ward Beecher </p>
	        </div>
	      </div> 
	      </c:when>
	      
	       <c:when test="${userRole eq 'PRIME'}">
	      <div class="item active">
	        <img src="${images}/prime-cover.jpg" alt="prime_cover" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	           <h3>Reading is dreaming with open eyes!</h3>
	          <p>Buy and Sell your books!!</p>
	        </div>
	      </div>
	
	      <div class="item">
	        <img src="${images}/prime-cover2.jpg" alt="prime_cover2" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	          <h3>Books are a uniquely portable magic</h3>
	          <p>Stephen King</p>
	        </div>
	      </div>
	    
	      <div class="item">
	        <img src="${images}/seller-cover4.png" alt="prime_cover3" style="width:100%; height:500px;">
	        <div class="carousel-caption">
	          <h3>Where is human nature so weak as in the bookstore?</h3>
	          <p>Henry Ward Beecher </p>
	        </div>
	      </div> 
	      </c:when>
	      
	      
	      <c:otherwise>
		      <div class="item active">
		        <img src="${images}/cover1.jpg" alt="book_cover1" style="width:100%; height:500px;">
		        <div class="carousel-caption">
		           <h3>Reading is dreaming with open eyes!</h3>
		          <p>Buy and Sell your books!!</p>
		        </div>
		      </div>
		
		      <div class="item">
		        <img src="${images}/cover2.jpg" alt="book_cover2" style="width:100%; height:500px;">
		        <div class="carousel-caption">
		          <h3>Books are a uniquely portable magic</h3>
		          <p>Stephen King</p>
		        </div>
		      </div>
		    
		      <div class="item">
		        <img src="${images}/cover3.jpg" alt="book_cover3" style="width:100%; height:500px;">
		        <div class="carousel-caption">
		          <h3>Where is human nature so weak as in the bookstore?</h3>
		          <p>Henry Ward Beecher </p>
		        </div>
		      </div> 
	      </c:otherwise>
	  </c:choose> 
       
    </div>
   
    
    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
    
  </div>
  
  