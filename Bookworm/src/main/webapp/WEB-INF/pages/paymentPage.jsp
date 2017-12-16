<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Payment</title>
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
	<c:if test="${param.error == 'true'}">
		<div style="color: red; margin: 10px 0px;">

			Unable to save changes to profile!!!<br /> Reason :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

		</div>
	</c:if>

	<form:form name='form'
		action="${pageContext.request.contextPath}/saveOrCancelPayment"
		modelAttribute="paymentDetails" method='POST' varStatus="vs">
		<table>
			<tr>
				<td>FirstName:</td>
				<td><form:input path="user.userInfo.firstname" type='text'
						name="firstname" id="firstname"
						value="${paymentDetails.user.userInfo.firstname}" /> <span
					id="nameloc"></span></td>
			</tr>
			<tr>
				<td>LastName:</td>
				<td><form:input path="user.userInfo.lastname" type='text'
						name="lastname" id="lastName"
						value="${paymentDetails.user.userInfo.lastname}" /></td>
			</tr>
			<tr>
				<form:input path="user.userInfo.email" type='hidden'
						name="email" id="email"
						value="${paymentDetails.user.userInfo.email}" />
			</tr>
			<tr>
				<form:input path="user.userInfo.phone" type='hidden'
						name="phone" id="phonr"
						value="${paymentDetails.user.userInfo.phone}" />
			</tr>
			<tr>
				<form:input path="user.userInfo.dob" type='hidden'
						name="dob" id="dob" value="${paymentDetails.user.userInfo.dob}" />
			</tr>
			<tr>
				<form:input path="user.userInfo.useraddress[0].street1"
						type='hidden' name="street1" id="useraddress.stree1"
						value="${paymentDetails.user.userInfo.useraddress[0].street1}" />
			</tr>
			<tr>
				<form:input path="user.userInfo.useraddress[0].street2"
						type='hidden' name="street2" id="useraddress.street2"
						value="${paymentDetails.user.userInfo.useraddress[0].street2}" />
			</tr>
			<tr>
				<form:input path="user.userInfo.useraddress[0].city"
						type='hidden' name="city" id="useraddress.city"
						value="${paymentDetails.user.userInfo.useraddress[0].city}" />
			</tr>
			<tr>
				<form:input path="user.userInfo.useraddress[0].state"
						type='hidden' name="state" id="useraddress.state"
						value="${paymentDetails.user.userInfo.useraddress[0].state}" />
			</tr>
			<tr>
				<form:input path="user.userInfo.useraddress[0].pincode"
						type='hidden' name="pincode" id="useraddress.pincode"
						value="${paymentDetails.user.userInfo.useraddress[0].pincode}" />
			</tr>
			<tr>
				<th>Delivery Address:</th>
			</tr>
			<hr>
			<tr>
				<td>Street1:</td>
				<td><form:input path="user.userInfo.useraddress[1].street1"
						type='text' name="street1" id="useraddress.stree1"
						value="${paymentDetails.user.userInfo.useraddress[0].street1}" /></td>
			</tr>
			<tr>
				<td>Street2:</td>
				<td><form:input path="user.userInfo.useraddress[1].street2"
						type='text' name="street2" id="useraddress.street2"
						value="${paymentDetails.user.userInfo.useraddress[0].street2}" /></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><form:input path="user.userInfo.useraddress[1].city"
						type='text' name="city" id="useraddress.city"
						value="${paymentDetails.user.userInfo.useraddress[0].city}" /></td>
			</tr>
			<tr>
				<td>State:</td>
				<td><form:input path="user.userInfo.useraddress[1].state"
						type='text' name="state" id="useraddress.state"
						value="${paymentDetails.user.userInfo.useraddress[0].state}" /></td>
			</tr>
			<tr>
				<td>Pincode:</td>
				<td><form:input path="user.userInfo.useraddress[1].pincode"
						type='text' name="pincode" id="useraddress.pincode"
						value="${paymentDetails.user.userInfo.useraddress[0].pincode}" /></td>
			</tr>
			<tr>
				<td>Amount:</td>
				<td><form:input path="amount" type='text' name="amount"
						id="amount" value="${paymentDetails.amount}" disabled="true" /> $</td>
			</tr>
			<tr>
				<td><form:button id="home" name="placeOrder" value="placeOrder"
						class="btn btn-primary">Pay and Place Order </form:button></td>
				<td><form:button id="home" name="placeOrder" value="cancel"
						class="btn btn-primary"> Cancel</form:button></td>
			</tr>
		</table>
	</form:form>

</body>
</html>


