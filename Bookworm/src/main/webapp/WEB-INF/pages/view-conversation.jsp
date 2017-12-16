<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="role" value="${userLogin.userRole}" />
<c:set var="otherUserColor" value="LIGHTBLUE" />
<c:set var="currentUserColor" value="PALEGREEN" />
<div class="container">
	<div class="row">
		<h3>
			Subject: ${messages[0].conversation.subject} <a
				href="${contextRoot}/show/${userLogin.username}">[${userLogin.username}]</a>
		</h3>


		<form:form action="${contextRoot}/add-message" modelAttribute="msg"
			method="POST">
			<div class="form-group">
				<form:hidden path="conversation.id" />
				<form:hidden path="userLogin.id" />

				<label for="message">Message:</label>
				<form:textarea path="text" class="form-control" rows="5"
					required="required" />
				<br />
				<button type="submit" class="btn btn-primary">Send message</button>
			</div>
		</form:form>


		<c:forEach var="messages" items="${messages}">
			<div class="panel panel-default">
				<c:if
					test="${ messages.userLogin.username eq  pageContext.request.userPrincipal.name}">
					<div class="panel-body bg"
						style='background-color:${currentUserColor};'>
				</c:if>
				<c:if
					test="${ messages.userLogin.username !=  pageContext.request.userPrincipal.name}">
					<div class="panel-body bg"
						style='background-color:${otherUserColor};'>
				</c:if>
				<p class='name'>
					<mark>${messages.userLogin.username}
						<jsp:useBean id="date" class="java.util.Date" />
						<c:set var="result" value="${messages.date * 1000}" />
						<jsp:setProperty name="date" property="time" value="${result}" />
						(
						<fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm:ss" />
						)
					</mark>
				</p>
				<p class="text">${messages.text}</p>
			</div>
	</div>
	</c:forEach>
</div>
</div>

