<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class=container>
	<div class="row">
		<c:choose>
			<c:when
				test="${sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name != sessionScope.uName && sessionScope.uName != null}">
				<h1>
					<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
					's WishList
				</h1>
			</c:when>
			<c:otherwise>
				<h1>My WishList</h1>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${empty wishList}">
				<c:choose>
					<c:when
						test="${sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name != sessionScope.uName && sessionScope.uName != null}">
						<h2>
							<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
							has not wishlisted any item.
						</h2>
					</c:when>
					<c:when test="${empty wishList}">
						<h2>No item wishlisted.</h2>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<table class="table">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>Book Title</th>
							<th>Author</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="wish" items="${wishList}">
							<!-- construct an "update" link with seller details id -->

							<c:url var="deleteLink" value="/removeFromWishList/${username}">
								<c:param name="bookWishId" value="${wish.id}" />
							</c:url>
							<tr>
								<td>${wish.sellerBookDetails.book.isbn}</td>
								<td><a
									href="${contextRoot}/show/book/${wish.sellerBookDetails.book.isbn}">
										${wish.sellerBookDetails.book.title}</a></td>
								<td>${wish.sellerBookDetails.book.author.name}</td>
								<td><a href="${deleteLink}"
									onClick="if(!(confirm('Are you sure you want to remove this book from wish list?'))) return false">Remove</a>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
