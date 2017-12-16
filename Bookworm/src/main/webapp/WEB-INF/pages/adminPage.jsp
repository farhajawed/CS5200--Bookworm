<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class=container>
	<div class="row">
		<c:if test="${not empty errorMsg}">
			<div class="alert alert-danger">
				<strong> The selected user is active and cannot be deleted
					at the moment.</strong>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${empty users}">
				<h2>No such users.</h2>
			</c:when>
			<c:otherwise>
				<h2>User(s)</h2>
				<table>
					<thead>
					</thead>

					<c:forEach var="user" items="${users}">

						<c:url var="enableLink"
							value="/enableOrDisableUser/${user.username}">
							<c:param name="enable" value="true" />
						</c:url>
						<c:url var="disableLink"
							value="/enableOrDisableUser/${user.username}">
							<c:param name="enable" value="false" />
						</c:url>
						<!-- construct an "update" link with user id -->
						<c:url var="updateLink"
							value="/showAdmin/profile/${user.username}">
							<c:param name="userId" value="${user.id}" />
						</c:url>
						<c:url var="cartLink" value="/myCart/${user.username}">
							<c:param name="userId" value="${user.id}" />
						</c:url>
						<c:url var="wishList" value="/viewWishList/${user.username}">
							<c:param name="userId" value="${user.id}" />
						</c:url>
						<!-- construct an "delete" link with user id -->
						<c:url var="deleteLink" value="/delete/${user.username}">
							<c:param name="userId" value="${user.id}" />
						</c:url>
						<tr>
							<td>${user.username}</td>
							<!-- display the links -->
							<td><c:if test="${user.userLogin.enabled == 0}">
									<a href="${enableLink}">Enable</a> |
									</c:if> <c:if test="${user.userLogin.enabled == 1}">
									<a href="${disableLink}">Disable</a> |
									</c:if> <a href="${updateLink}"> Profile</a> | <c:if
									test="${user.userrole eq 'PRIME' or user.userrole eq 'BUYER'}">
									<a href="${cartLink}"> Cart</a> |
									 <a href="${wishList}"> WishList</a> |
									</c:if> <a href="${deleteLink}" data-method="delete"
								onClick="if(!(confirm('Are you sure you want to remove this user?'))) return false">Remove</a>
							</td>
						</tr>
					</c:forEach>

					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
