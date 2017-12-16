<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">
		<h1>Add Details:</h1>
		<img src="${sellerDetails.book.imageLink}"><br> ISBN:
		${sellerDetails.book.isbn}<br> TITLE: ${sellerDetails.book.title}<br>
		AUTHOR: ${sellerDetails.book.author.name}<br> CATEGORY:
		${sellerDetails.book.category.name}


		<form:form action="${pageContext.request.contextPath}/advert-book"
			modelAttribute="sellerDetails" method="POST">


			<form:hidden path="id" />
			<form:hidden path="userLogin.id" />
			<form:hidden path="userLogin.username" />
			<form:hidden path="book.isbn" />
			<form:hidden path="book.title" />
			<form:hidden path="book.imageLink" />
			<form:hidden path="book.author.name" />
			<form:hidden path="book.category.name" />

			<table>

				<tr>
					<td><label>Price</label></td>
					<td><form:input path="price" placeholder="$" /> $</td>
				</tr>
				<tr>
					<td><label>Quantity</label></td>
					<td><form:input path="quantity" /></td>
				</tr>
				<tr>
					<td><label>Book Condition :</label></td>
				</tr>
				<tr>
					<td><form:radiobutton path="bookCondition" value="New" /> New</td>
				</tr>
				<tr>
					<td><form:radiobutton path="bookCondition"
							value="Used - Like New" /> Used - Like New</td>
				</tr>
				<tr>
					<td><form:radiobutton path="bookCondition"
							value="Used - Very Good" /> Used - Very Good</td>
				</tr>
				<tr>
					<td><form:radiobutton path="bookCondition" value="Used - Good" />
						Used - Good</td>
				</tr>
				<tr>
					<td><form:radiobutton path="bookCondition"
							value="Used - Acceptable" /> Used - Acceptable</td>
				</tr>

				<tr>
					<td><label></label></td>
					<td>
						<button type="submit" class="btn btn-success">Sell</button>
					</td>

				</tr>

			</table>
		</form:form>
	</div>
</div>