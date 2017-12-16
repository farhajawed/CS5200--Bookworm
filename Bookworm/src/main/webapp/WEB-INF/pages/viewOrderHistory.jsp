<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<div class=container>
	<div class="row">
		<c:choose>
			<c:when test="${empty orders}">
				<c:choose>
					<c:when
						test="${sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name == sessionScope.uName || sessionScope.uName==null}">
						<h2>No item ordered.</h2>
					</c:when>
					<c:otherwise>
						<h2>
							<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
							has not ordered any item.
						</h2>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when
						test="${sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name == sessionScope.uName || sessionScope.uName==null}">
						<h1>My Order History</h1>
					</c:when>
					<c:otherwise>
						<h1>
							<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
							's Order History
						</h1>
					</c:otherwise>
				</c:choose>
				<table class="table">
					<thead>
						<tr>
							<th>Order Date</th>
							<th>ISBN</th>
							<th>Book Title</th>
							<th>Author</th>
							<th>Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cart" items="${orders}">
							<tr>
								<c:choose>
									<c:when test="${cart.order.updatedOn == null}">
										<td>${cart.order.createdOn}</td>
									</c:when>
									<c:otherwise>
										<td>${cart.order.updatedOn}</td>
									</c:otherwise>
								</c:choose>
								<td>${cart.sellerdetails.book.isbn}</td>
								<td><a
									href="${contextRoot}/show/book/${cart.sellerdetails.book.isbn}">
										${cart.sellerdetails.book.title}</a></td>
								<td>${cart.sellerdetails.book.author.name}</td>
								<td>$ ${cart.sellerdetails.price}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
