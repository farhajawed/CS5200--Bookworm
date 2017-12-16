<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<div class=container>
	<div class="row">
		<c:choose>
			<c:when test="${empty sellerDetails}">
				<c:choose>
					<c:when
						test="${sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name == sessionScope.uName || sessionScope.uName==null}">
						<h2>No books offered for sale.</h2>
					</c:when>
					<c:otherwise>
						<h2>
							<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
							has not published any books for sale.
						</h2>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when
						test="${sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name == sessionScope.uName || sessionScope.uName==null}">

						<h1>My Sale History</h1>

					</c:when>
					<c:otherwise>
						<h1>
							<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
							's Sale History
						</h1>
					</c:otherwise>
				</c:choose>
				<table class="table">
					<thead>
						<tr>
							<th>ISBN</th>
							<th>Book Title</th>
							<th>Author</th>
							<th>Price</th>
							<th>Number of copies available</th>
							<th>Number of copies sold till date</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="seller" items="${sellerDetails}">
							<tr>
								<td>${seller.book.isbn}</td>
								<td><a href="${contextRoot}/show/book/${seller.book.isbn}">
										${seller.book.title}</a></td>
								<td>${seller.book.author.name}</td>
								<td>$ ${seller.sellerPrice}</td>
								<td>${seller.copiesAvailable}</td>
								<td>${seller.copiesSold}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
