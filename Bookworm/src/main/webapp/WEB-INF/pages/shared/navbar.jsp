<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="userRole" value="${userRole}" />
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextRoot}/home">Bookworm</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${contextRoot}/home">Home</a></li>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li><a
					href="${contextRoot}/userInfo/${pageContext.request.userPrincipal.name}">Profile</a></li>
			</c:if>
			<li><a href="${contextRoot}/admin">Search User</a></li>
			<li><a href="${contextRoot}/sell-book">Sell Books</a></li>
			<li><a href="${contextRoot}/buy-book">Buy Books</a></li>
			<c:if test="${userRole eq 'PRIME' || userRole eq 'SELLER' || userRole eq 'ADMIN'}">
				<li><a
					href="${contextRoot}/seller-advertised-book/${pageContext.request.userPrincipal.name}">My
						Advertised Books</a></li>
			</c:if>
			<c:if test="${userRole ne 'SELLER'}">
				<li><a href="${contextRoot}/browse-book">Search Books</a></li>
			</c:if>
			<li><a href="${contextRoot}/about">About Us</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${pageContext.request.userPrincipal == null}">
				<li><a href="${contextRoot}/register"><span
						class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal == null}">
				<li><a href="${contextRoot}/login"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li><a href="#">Welcome,
						${pageContext.request.userPrincipal.name}</a></li>

				<c:if test="${sessionScope.userRole eq 'ADMIN'}">
					<li><a href="${contextRoot}/register"><span
							class="glyphicon glyphicon-user"></span> Add User</a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/inbox">Inbox</a></li>
				<li><a
					href="${pageContext.request.contextPath}/viewWishList/${pageContext.request.userPrincipal.name}">
						WishList</a></li>
				<li><a
					href="${pageContext.request.contextPath}/myCart/${pageContext.request.userPrincipal.name}">
						Cart</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>

			</c:if>
		</ul>
	</div>
</nav>
