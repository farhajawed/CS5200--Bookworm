<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<div class="row">
	   	<c:choose>
		    <c:when test="${empty googlebook.items}">
		        <h3>We are not selling this book :(</h3>
		    </c:when>
		    <c:otherwise>
		   
			
					<img src="${googlebook.items[0].volumeInfo.imageLinks.thumbnail}"><br>
					<h2>${googlebook.items[0].volumeInfo.title}</h2>
					<p>ISBN-13: ${isbn}<p>
					<p>PUBLISHER: ${googlebook.items[0].volumeInfo.publisher}<p>
					<p>PUBLISH DATE: ${googlebook.items[0].volumeInfo.publishedDate}<p>
					<p>DESCRIPTION: ${googlebook.items[0].volumeInfo.description}<p>
					<p>AUTHOR: ${googlebook.items[0].volumeInfo.authors[0]}<p>
					<p>CATEGORY: ${googlebook.items[0].volumeInfo.categories[0]}<p>
					<p>PAGE COUNT: ${googlebook.items[0].volumeInfo.pageCount}<p>
					
					
     			   
				
					<c:url var="sellBookLink" value="/show-book-for-sell">
						<c:param name="title" value="${googlebook.items[0].volumeInfo.title}"/>
						<c:param name="imageLink" value="${googlebook.items[0].volumeInfo.imageLinks.thumbnail}"/>
						<c:param name="isbn" value="${isbn}"/>
						<c:param name="author" value="${googlebook.items[0].volumeInfo.authors[0]}"/>
						<c:param name="category" value="${googlebook.items[0].volumeInfo.categories[0]}"/>
				   </c:url>
				   
				   <a href="${sellBookLink}" class="btn btn-danger">Want to sell?</a>
		
			</c:otherwise>
		</c:choose>
	</div>
</div>

