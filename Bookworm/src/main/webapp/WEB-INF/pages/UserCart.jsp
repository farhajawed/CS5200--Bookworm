<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<form:form name='form'
		action="${pageContext.request.contextPath}/saveOrUpdateOrder/${username}"
		modelAttribute="order" method='POST'>
		<table>
			<tr>
				<c:forEach items="${order.books}" var="book">
					<c:if test="${book != null}">
						<tr>
							<td>Book:</td>
							<td>${book.title}</td>
							<td><input type='hidden' name='bookId' value='${book.id}' /></td>
							<td><form:input path="sellerId" type='hidden'
									name='sellerId' value='${order.sellerId}' /></td>
							<td><form:input path="id" type='hidden' name='id' value='${order.id}' /></td>
						</tr>
						<tr>
							<td>Unit Price:</td>
							<td>$ ${order.unitPrice}</td>
						</tr>
						<tr>
							<td>Quantity:</td>
							<td><input name='quantity' type='text'
								value='${order.quantity}' /></td>
						</tr>
						<tr>
							<td>Amount:</td>
							<td>$ ${order.amount}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${order.books != null}">
					<tr>
						<td><form:button id="home" name='myCartProperty'
								value="updateQuantity" class="btn btn-primary">Update Quantity </form:button></td>
						<td><form:button id="home" name='myCartProperty'
								value="continueShopping" class="btn btn-primary">Continue Shopping</form:button></td>
					</tr>
				</c:if>
			</tr>
		</table>
	</form:form>

</body>
</html>