<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<form action="${contextRoot}/search-book" method="GET">
			<div class="input-group">
				<input type="text" name="term" class="form-control" size="50"
					placeholder="Search by title or isbn....." required>
				<div class="input-group-btn">
				<!-- 
					<c:if test="${userClickBrowseBooks eq true or userClickHome eq true}">
						<button type="submit" value="Buy Books" class="btn btn-danger">Search Books</button>
					</c:if>
					<c:if test="${userClickBuyBooks eq true}">
						<button type="submit" value="Buy Books" class="btn btn-danger">Buy Books</button>
					</c:if>
				-->
				<button type="submit" value="Buy Books" class="btn btn-danger">Buy Books</button>
				</div>
			</div>
		</form>
	</div>
</div>