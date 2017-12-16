<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import=" java.util.*, com.bookstoremanagement.model.*"%>
<div class="container">
	<div class="row">
		<img src="${book.imageLink}" /><br>
		<h2>${book.title}</h2>
		<c:forEach var="google" items="${googlebook.items}">
			<h3>${google.volumeInfo.subtitle}</h3>
			<p>ISBN: ${book.isbn}
			<p>
			<p>AUTHOR: ${book.author.name}
			<p>
			<p>PUBLISHER: ${google.volumeInfo.publisher}
			<p>
			<p>PUBLISH DATE: ${google.volumeInfo.publishedDate}
			<p>
			<p>DESCRIPTION: ${google.volumeInfo.description}
			<p>
			<p>CATEGORY: ${google.volumeInfo.categories[0]}
			<p>
			<p>PAGE COUNT: ${google.volumeInfo.pageCount}
			<p>
				<c:if test="${avgRating ne 0}">
					<p>AVERAGE RATING: ${avgRating}</p>
				</c:if>
			<p>
			<p>AVERAGE GOOGLE BOOK RATING: ${google.volumeInfo.averageRating}<p>
			<a href="${google.accessInfo.webReaderLink}">Read it on google books</a>
		</c:forEach>

		<%@include file="book-rating.jsp"%>
       <c:if test="${userRole ne 'SELLER'}">
		<h2>Market Place</h2>

		<c:forEach var="seller" items="${book.sellerDetails}">
			<div class="panel panel-default">
				<div class="panel-body">
					<h3>
						<c:choose>
							<c:when test="${seller.userLogin.userRole eq 'ADMIN'}">
			    		Available at Bookworm
					</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${seller.userLogin.username eq currentuser}">
			    		Seller: <a href="${contextRoot}/userInfo/${currentuser}">${currentuser}</a>
									</c:when>
									<c:otherwise>
					 	Seller: <a
											href="${contextRoot}/show/${seller.userLogin.username}">${seller.userLogin.username}</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						<br>
					</h3>
					$${seller.price}<br> ${seller.quantity} books
					available(${seller.bookCondition})<br>
					<!-- ************ -->
					<c:choose>
						<c:when test="${pageContext.request.userPrincipal != null }">
							<form:form name='form'
								action="${pageContext.request.contextPath}/addToCartOrWishList/${pageContext.request.userPrincipal.name}"
								modelAttribute="cart" method='POST'>
								<form:input path='book.id' name='id' type="hidden"
									value="${book.id}" />
								<form:input path='book.isbn' name='isbn' type="hidden"
									value="${book.isbn}" />
								<form:input path='book.title' name='title' type="hidden"
									value="${book.title}" />
								<form:input path='book.author' name='author' type="hidden"
									value="${book.author}" />
								<form:input path='book.imageLink' name='imageLink' type="hidden"
									value="${book.imageLink}" />
								<input name='sellerId' type="hidden" value="${seller.id}" />
								<input name='orders' type="hidden" value="${orders}" />
								<c:if test="${seller.quantity > 0}">
									<form:button id="home" name="addTo" value="myCart"
										class="btn btn-primary">Add to Cart</form:button>
								</c:if>
								<form:button id="home" name="addTo" value="wishList"
									class="btn btn-primary">Add to Wishlist</form:button>
							</form:form>
						</c:when>
						<c:otherwise>
							<form:form name='form' action='${contextRoot}/login' modelAttribute="cart" method='POST'>
								<form:input path='book.id' name='id' type="hidden"
									value="${book.id}" />
								<form:input path='book.isbn' name='isbn' type="hidden"
									value="${book.isbn}" />
								<form:input path='book.title' name='title' type="hidden"
									value="${book.title}" />
								<form:input path='book.author' name='author' type="hidden"
									value="${book.author}" />
								<form:input path='book.imageLink' name='imageLink' type="hidden"
									value="${book.imageLink}" />
								<input name='sellerId' type="hidden" value="${seller.id}" />
								<input name='orders' type="hidden" value="${orders}" />
								<c:if test="${seller.quantity > 0}">
									<a href="${contextRoot}/login"><input type="button" value="Add to Cart"
										class="btn btn-primary"/></a>
								</c:if>
								<a href="${contextRoot}/login"><input type="button" value="Add to WishList"
										class="btn btn-primary"/></a>
							</form:form>

						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</c:forEach>
		</c:if>
	</div>
</div>


