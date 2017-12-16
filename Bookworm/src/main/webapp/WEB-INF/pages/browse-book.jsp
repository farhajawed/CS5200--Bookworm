<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<div class="row">
		<h3>Search Books by Authors</h3>
		<div class="panel panel-default">
			<div class="panel-body">
				<c:forEach var="authors" items="${authors}">
				<a href="${contextRoot}/browse/author/${authors.name}">${authors.name}</a><br>
				</c:forEach>
			</div>
		</div>
		
		
		<h3>Search Books by Categories</h3>
		<div class="panel panel-default">
			<div class="panel-body">
				<c:forEach var="categories" items="${categories}">
				<a href="${contextRoot}/browse/category/${categories.name}">${categories.name}</a><br>
				</c:forEach>
			</div>
		</div>
		
	</div>

</div>