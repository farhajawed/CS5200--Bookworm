<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<div class="row">
		<c:if test="${empty sellerDetails}">
		    <h1>No match found :(</h1>
		</c:if>
		<c:forEach var="bs" items="${sellerDetails}">
		<a href="${contextRoot}/show/book/${bs[0]}">${bs[1]}</a>
		<br>
		${bs[2]}
		<br>
		${bs[3]}
		<br>
		Price Starting from $<c:out value="${bs[4]}"/>
		<br>
		<img src="${bs[5]}"/>
		<br><br><br>
		</c:forEach>
		
	</div>
</div>