<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class=container>
	<div class="row">
	<c:choose>
		    <c:when test="${empty sellerDetails}">
		       <h2>No books available to display</h2>
		    </c:when>
   <c:otherwise>
				<c:choose>
				<c:when test="${currentuser eq true}">
					<h1>My Books</h1>
				</c:when>
				<c:otherwise>
					<h1>Books of ${sellerDetails[0].userLogin.username}</h1>
				</c:otherwise>
	  			</c:choose>
		
		<!-- show error message for already ordered books -->
		<c:if test="${not empty error}">
		<div class="alert alert-danger">
 			 <strong>Your book cannot be deleted as there is an associated order</strong>
		</div>
		</c:if>
		<table class="table">
			<thead>
				<tr>
					<th>ISBN-13</th>
					<th>Book Title</th>
					<th>Author</th>
					<th>Price</th>
					<th>Quantity</th>
					<c:if test="${currentuser==true}">
					<th>Book Condition</th>
					<th>Last Updated</th>
					<th>Action</th>
				</c:if>
				</tr>
			</thead>
			<tbody>	
				<c:forEach var="sellerDetails" items="${sellerDetails}" > 
				
				<!-- construct an "update" link with seller details id -->
				<c:url var="updateLink" value="/show-form-for-update">
					<c:param name="sellerDetailsId" value="${sellerDetails.id}"/>
					<c:param name="username" value="${sellerDetails.userLogin.username}"/>
				</c:url>
				
				<!-- construct an "delete" link with seller details id -->
				<c:url var="deleteLink" value="/delete-book-of-seller">
					<c:param name="sellerDetailsId" value="${sellerDetails.id}"/>
					<c:param name="username" value="${sellerDetails.userLogin.username}"/>
				</c:url>
				
					<tr>
						<td>${sellerDetails.book.isbn}</td>
						<td><a href="${contextRoot}/show/book/${sellerDetails.book.isbn}">${sellerDetails.book.title}</a></td>
						<td>${sellerDetails.book.author.name}</td>
						<td>${sellerDetails.price}</td>
						<td>${sellerDetails.quantity}</td>
						 <c:if test="${currentuser==true}">
						<td>${sellerDetails.bookCondition}</td>
						<td>${sellerDetails.createDateTime}</td>
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							  onClick = "if(!(confirm('Are you sure you want to delete this book?'))) return false">Delete</a>
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
	</c:otherwise>
</c:choose>
		
		
	</div>
</div>
