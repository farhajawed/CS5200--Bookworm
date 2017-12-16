<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
	<div class="row">
	
	<h1>Update Book</h1>
	<h4>${sellerDetails.book.title}</h4>
	By ${sellerDetails.book.author.name}<br>
	ISBN-13: ${sellerDetails.book.isbn}<br>
	<img src="${sellerDetails.book.imageLink}">
	
	<form:form action="${pageContext.request.contextPath}/seller-updates-book" modelAttribute="sellerDetails" method="POST">
		<form:hidden path="id"/>
		<form:hidden path="userLogin.id"/>
		<form:hidden path="userLogin.username"/>
		<table>
				<tbody>

					<tr>
						<td><label>Price</label></td>
						<td>$<form:input path="price" /></td>
					</tr>
					<tr>
						<td><label>Quantity</label></td>
						<td><form:input path="quantity" /></td>
					</tr>
					<tr>
						<td><label>Book Condition</label></td>
						<td><form:radiobutton path="bookCondition" value="New" />New
							<form:radiobutton path="bookCondition" value="Used - Like New" />Used - Like New
							<form:radiobutton path="bookCondition" value="Used - Very Good" />Used - Very Good
							<form:radiobutton path="bookCondition" value="Used - Good" />Used - Good
							<form:radiobutton path="bookCondition" value="Used - Acceptable" />Used - Acceptable
						</td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><button type="submit" value="Update" class="btn btn-danger">Update Book</button></td>
					</tr>

				</tbody>
			</table>

		</form:form>
	
	</div>
</div>