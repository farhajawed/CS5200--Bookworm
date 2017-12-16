<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@page session="true"%>
<html>
<head>
<title>Edit Profile</title>
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
	<h1>Edit Profile</h1>

	<!-- /login?error=true -->
	<c:if test="${message != null}">
		<div style="color: red; margin: 10px 0px;">

			Unable to update changes.<br /> Reason : <br>
			<c:forEach var="error" items="${errorCodes}">
				<spring:message code="${error}" />
				<br>
			</c:forEach>
		</div>
	</c:if>

	<form:form name='form'
		action="${pageContext.request.contextPath}/saveOrCancelEditedProfile"
		enctype="multipart/form-data" modelAttribute="user" method='POST'
		varStatus="vs">
		<table>
			<tr>
				<td>Profile Picture<br />
				</td>
				<td><input id="image" name="image" type="file" /></td>
			</tr>
			<tr>
				<form:input path="username" type="hidden" value="${user.username}" />
				<form:input path="dob" type="hidden" value="${user.dob}" />
				<form:input path="password" type="hidden" value="${user.userLogin.password}" />
				<td>FirstName:</td>
				<td><input type='text' name="firstname" id="firstname"
					value="${user.firstname}" required="required"/></td>
			</tr>

			<tr>
				<td>LastName:</td>
				<td><input type='text' name="lastname" id="lastName"
					value="${user.lastname}" /></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><input type='email' name="email" id="email"
					value="${user.email}" required="required"/></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><input type='tel' name="phone" id="phone"
					value="${user.phone}" required="required"/></td>
			</tr>
			<tr>
				<td>Address:</td>
			</tr>
			<tr>
				<td>Street1:</td>
				<td><form:input path="useraddress[0].street1" type='text'
						name="street1" id="useraddress.stree1"
						value="${user.useraddress[0].street1}" required="required"/></td>
			</tr>
			<tr>
				<td>Street2:</td>
				<td><form:input path="useraddress[0].street2" type='text'
						name="street2" id="useraddress.street2"
						value="${user.useraddress[0].street2}" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><form:input path="useraddress[0].city" type='text'
						name="city" id="useraddress.city"
						value="${user.useraddress[0].city}" required="required"/></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><form:input path="useraddress[0].state" type='text'
						name="state" id="useraddress.state"
						value="${user.useraddress[0].state}" required="required"/></td>
			</tr>
			<tr>
				<td>Pincode:</td>
				<td><form:input path="useraddress[0].pincode" type='text'
						name="pincode" id="useraddress.pincode"
						value="${user.useraddress[0].pincode}" required="required"/></td>
			</tr>
			<tr>
				<c:if
					test="${session.userRole eq 'BUYER' || session.userRole eq 'SELLER' || session.userRole eq 'PRIME'}">
					<td><form:radiobutton path='userrole' value="BUYER" /> Buy
						Books</td>
					<td><form:radiobutton path='userrole' value="SELLER" /> Sell
						books</td>
					<td><form:radiobutton path='userrole' value="PRIME" /> Buy or
						Sell books</td>
				</c:if>
			</tr>

			<tr>
				<td><form:button id="home" name="editedProfile" value="save"
						class="btn btn-primary">Save Changes </form:button></td>
				<td><form:button id="home" name="editedProfile" value="cancel"
						class="btn btn-primary"> Cancel</form:button></td>
			</tr>
		</table>
	</form:form>

</body>
</html>


