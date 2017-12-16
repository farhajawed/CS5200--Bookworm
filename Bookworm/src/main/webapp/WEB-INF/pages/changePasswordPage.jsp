<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Change Password</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			minDate : (new Date(1999, 1 - 1, 26)),
			maxDate : "+1M +10D",
			altFormat : "yy-mm-dd",
			dateFormat : "yy-mm-dd"
		});
	});
</script>
</head>
<body>
	<h1>Change Password</h1>

	<!-- /login?error=true -->
	<c:if test="${param.error == 'true'}">
		<div style="color: red; margin: 10px 0px;">

			Unable to change password!!!<br /> Reason :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

		</div>
	</c:if>

	<form:form name='form'
		action="${pageContext.request.contextPath}/saveOrCancelPasswordChanges"
		modelAttribute="user" method='POST'>
		<table>
			<tr>
				<td>New Password:</td>
				<td><form:input path='password' type='password' name="password"
						id="password" /> <span id="passwordloc"></span></td>
			</tr>

			<tr>
				<td>Confirm Password:</td>
				<td><form:input path='password' type='password' name="password"
						id="password" /> <span id="passwordloc"></span></td>
			</tr>
			<tr>
				<td><form:input path="username" id="username" name="username"
						type="hidden" value="${user.username}" /></td>
			</tr>
			<tr>
				<td><form:button id="home" name="changePassword" value="save" class="btn btn-primary">Change Password</form:button></td>
				<td><form:button id="home" name="changePassword" value="cancel" class="btn btn-primary"> Cancel</form:button></td>
			</tr>

		</table>
	</form:form>

</body>
</html>


