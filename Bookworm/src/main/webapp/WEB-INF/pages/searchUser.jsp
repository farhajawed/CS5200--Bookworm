<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class=container>
	<div class="row">
		<h1>Search User</h1>
		<c:if test="${not empty errorMsg}">
			<div class="alert alert-danger">
				<strong> The selected user cannot be deleted at the moment.</strong>
			</div>
		</c:if>
		<div class="row">
			<form action="${contextRoot}/adminUserSearch" method="GET">
				<div class="input-group">
					<input type="text" name="name" class="form-control" size="13"
						placeholder="Search by username or firstname....">
					<div class="input-group-btn">
						<button type="submit" name="admin" value="searchUser" class="btn btn-danger">Search
							User</button>
						<p></p>
						<button type="submit" name="admin" value="viewAllUser" class="btn btn-danger">View All
							Users</button>
					</div>
				</div>
				<br /> <br />
			</form>
		</div>
	</div>
</div>