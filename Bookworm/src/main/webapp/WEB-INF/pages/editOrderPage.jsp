
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Cart</title>
</head>
<c:if test="${message != null}">
	<div style="color: red; margin: 10px 0px;">${message}</div>
</c:if>
<body>
	<div class=container>
		<div class="row">
			<c:choose>
				<c:when
					test="${sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name != sessionScope.uName && sessionScope.uName != null}">
					<h1>
						<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
						's Cart
					</h1>
				</c:when>
				<c:otherwise>
					<h1>My Cart</h1>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when
					test="${empty orders && sessionScope.isAdmin eq true && pageContext.request.userPrincipal != null && pageContext.request.userPrincipal.name != sessionScope.uName && sessionScope.uName != null}">
					<h2>
						<c:out value="${sessionScope.uName}">${sessionScope.uName}</c:out>
						has not added any item in cart.
					</h2>
				</c:when>
				<c:when test="${empty orders}">
					<h2>No item in cart.</h2>
				</c:when>
				<c:otherwise>
					<form action="${contextRoot}/makePayment" method=POST>
						<table class="table">
							<thead>
								<tr>
									<th>Book Title</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orders}">
									<!-- construct an "update" link with order details id -->
									<c:choose>
										<c:when test="${sessionScope.uName == null}">
											<c:url var="updateLink"
												value="/showCartForUpdate/${pageContext.request.userPrincipal.name}">
												<c:param name="bookOrderId" value="${order.id}" />
											</c:url>
										</c:when>
										<c:otherwise>
											<c:url var="updateLink"
												value="/showCartForUpdate/${sessionScope.uName}">
												<c:param name="bookOrderId" value="${order.id}" />
											</c:url>
										</c:otherwise>
									</c:choose>

									<c:url var="makePayment" value="/makePayment/">
										<c:param name="bookOrderId" value="${order.id}" />
									</c:url>
									<!-- construct an "delete" link with order details id -->
									<c:choose>
										<c:when test="${sessionScope.uName == null}">
											<c:url var="deleteLink"
												value="/deleteBookFromCart/${pageContext.request.userPrincipal.name}">
												<c:param name="bookOrderId" value="${order.id}" />
											</c:url>
										</c:when>
										<c:otherwise>
											<c:url var="deleteLink"
												value="/deleteBookFromCart/${sessionScope.uName}">
												<c:param name="bookOrderId" value="${order.id}" />
											</c:url>
										</c:otherwise>
									</c:choose>

									<tr>
										<td>${order.book.title}</td>
										<td>$ ${order.unitPrice}</td>
										<td>${order.quantity}</td>
										<td>$ ${order.amount}</td>
										<td>
											<!-- display the update link --> <a href="${updateLink}">Update</a>
											| <a href="${deleteLink}"
											onClick="if(!(confirm('Are you sure you want to delete this book?'))) return false">Delete</a>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
						<c:if
							test="${sessionScope.isAdmin == null || sessionScope.isAdmin == 'true' && sessionScope.uName == pageContext.request.userPrincipal.name || sessionScope.uName == null}">
							<button type="submit" name='makePayment' value="makePayment"
								class="btn btn-primary">Make Payment</button>
						</c:if>
					</form>

				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
