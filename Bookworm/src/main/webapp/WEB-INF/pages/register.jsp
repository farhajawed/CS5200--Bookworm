<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Registration</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			minDate : (new Date(1999, 1 - 1, 1)),
			maxDate : "+1M +10D",
			altFormat : "yy-mm-dd",
			dateFormat : "yy-mm-dd",
		}).datepicker('setDate', new Date(1999, 1 - 1, 1));
	});
</script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.userRole eq 'ADMIN'}">
			<h1>Add User</h1>
		</c:when>
		<c:otherwise>
			<h1>Registration</h1>
		</c:otherwise>
	</c:choose>

	<!-- /login?error=true -->
	<c:if test="${message != null}">
		<div class="alert alert-danger">

			Registration Failed!!!<br /> Reason : <br>
			<c:forEach var="error" items="${errorCodes}">
				<spring:message code="${error}" />
				<br>
			</c:forEach>
		</div>
	</c:if>
	<hr>
	<br />
	<h5>Please fill the form below :</h5>

	<form:form name='form'
		action="${pageContext.request.contextPath}/registerProcess"
		enctype="multipart/form-data" modelAttribute="user" method='POST'>
		<table>
			<tr>
				<td>Profile Picture<br /></td>
				<td><form:input path="image" id="image" name="image"
						type="file" /></td>
			</tr>
			<tr>
				<td>Username:</td>
				<td><form:input path='username' type='text' name='username' required="required"/>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path='password' type='password' name="password"
						id="password" required="required"/> </td>
			</tr>

			<tr>
				<td>FirstName:</td>
				<td><form:input path='firstname' type='text' name="firstname"
						id="firstname" required="required"/> </td>
			</tr>

			<tr>
				<td>LastName:</td>
				<td><form:input path='lastname' type='text' name="lastname"
						id="lastName" /></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><form:input path='email' type='email' name="email"
						id="email" required="required"/></td>
			</tr>

			<tr>
				<td>Date of Birth:</td>
				<td><form:input path='dob' type='date' name="dob"
						id="datepicker" required="required"/></td>
			</tr>

			<tr>
				<td>Phone:</td>
				<td><form:input path='phone' type='tel' name="phone" id="phone" required="required"/></td>
			</tr>
			<tr>
				<th>Address:</th>
			</tr>
			<tr>
				<td>Street1:</td>
				<td><form:input path='useraddress[0].street1' type='text'
						name="street1" id="useraddress.street1" required="required"/></td>
			</tr>
			<tr>
				<td>Street2:</td>
				<td><form:input path='useraddress[0].street2' type='text'
						name="street2" id="useraddress.street2" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><form:input path='useraddress[0].city' type='text'
						name="city" id="useraddress.city" required="required"/></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><form:input path='useraddress[0].state' type='text'
						name="state" id="useraddress.state" required="required"/></td>
			</tr>
			<tr>
				<td>Pincode:</td>
				<td><form:input path='useraddress[0].pincode' type='text'
						name="pincode" id="useraddress.pincode" required="required"/></td>
			</tr>
			<tr>
				<th>You wish to:
				</th>
			</tr>
			<tr>
				<td><form:radiobutton path='userrole' value="BUYER"
						checked="true" /> Buy Books</td>
				<td><form:radiobutton path='userrole' value="SELLER" /> Sell
					books</td>
				<td><form:radiobutton path='userrole' value="PRIME" /> Buy or
					Sell books</td>
			</tr>

			<c:choose>
				<c:when test="${sessionScope.userRole eq 'ADMIN'}">
					<tr>
						<td><form:button id="register" name="register"
								class="btn btn-primary">Add User</form:button></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td><form:button id="register" name="register"
								class="btn btn-primary">Register</form:button></td>
					</tr>
				</c:otherwise>
			</c:choose>

		</table>
	</form:form>

</body>
</html>


