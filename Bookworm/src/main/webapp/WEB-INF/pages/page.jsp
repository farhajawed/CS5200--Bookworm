<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Book Store</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${css}/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="container-fluid">


		<!-- navigation bar -->
		<!-- search box -->
		<%@include file="./shared/navbar.jsp"%>


		<!-- Loading the home content -->
		<c:if test="${userClickHome == true && userRole !='SELLER'}">
			<%@include file="./shared/buy-book-search-box.jsp"%><br>
			<%@include file="home.jsp"%><br>
			<br>
		</c:if>

		<c:if test="${userClickHome == true && userRole eq 'SELLER'}">
			<%@include file="./shared/sell-book-search-box.jsp"%><br>
			<%@include file="home.jsp"%><br>
			<br>
		</c:if>

		<!-- Loading the search content -->
		<c:if test="${userClickSearchBook == true}">
			<%@include file="./shared/buy-book-search-box.jsp"%>
			<%@include file="search-result.jsp"%>
		</c:if>

		<!-- Loading the single book -->
		<c:if test="${userClickShowBook == true}">
			<%@include file="single-book.jsp"%>
		</c:if>

		<!-- Loading the about us content -->
		<c:if test="${userClickAbout == true}">
			<%@include file="about-us.jsp"%>
		</c:if>

		<!-- Loading the buy books content -->
		<c:if test="${userClickBuyBooks == true}">
			<%@include file="./shared/buy-book-search-box.jsp"%><br>
			<%@include file="buy-book.jsp"%><br>
		</c:if>

		<!-- Loading the sell books content -->
		<c:if test="${userClickSellBooks == true}">
			<%@include file="./shared/sell-book-search-box.jsp"%>
			<%@include file="sell-book.jsp"%>
		</c:if>

		<c:if test="${sellerClickSearchBook == true}">
			<%@include file="./shared/sell-book-search-box.jsp"%><br>
			<%@include file="sell-book-search-result.jsp"%>
		</c:if>

		<!-- Loading the browse book content -->
		<c:if test="${userClickBrowseBooks == true}">
			<%@include file="browse-book.jsp"%>
		</c:if>
		<!-- Loading the Admin Page -->
		<c:if test="${userClickAdmin == true}">
			<%@include file="searchUser.jsp"%>
		</c:if>
		<!-- Loading the Admin Page -->
		<c:if test="${userClickUserSearch == true}">
			<%@include file="searchUser.jsp"%>
			<%@include file="adminPage.jsp"%>
		</c:if>
		<c:if test="${userClickViewAllUsers == true}">
			<%@include file="adminPage.jsp"%>
		</c:if>
		<!-- Loading the login content -->
		<c:if test="${userClickLogin == true}">
			<%@include file="loginPage.jsp"%>
		</c:if>
		<!-- Loading the signup content -->
		<c:if test="${userClickSignUp == true}">
			<%@include file="register.jsp"%>
		</c:if>
		<c:if test="${userClickRegister == true}">
			<%@include file="successfulRegistration.jsp"%>
			<%@include file="home.jsp"%><br>
		</c:if>

		<!-- Loading the user info content -->
		<c:if test="${userClickProfile == true}">
			<%@include file="userInfoPage.jsp"%>
		</c:if>

		<!-- Loading the logout -->
		<c:if test="${userClickLogout == true}">
			<%@include file="logoutSuccessfulPage.jsp"%>
			<%@include file="home.jsp"%>
		</c:if>

		<!-- Loading the access denied -->
		<c:if test="${userAccessDenied == true}">
			<%@include file="403Page.jsp"%>
		</c:if>

		<!-- Loading the error page -->
		<c:if test="${errorOccured == true}">
			<%@include file="errorPage.jsp"%>
			<%@include file="home.jsp"%>
		</c:if>

		<!-- Loading the edit profile -->
		<c:if test="${userClickEditProfile == true}">
			<%@include file="editProfilePage.jsp"%>
		</c:if>

		<!-- Loading the change password -->
		<c:if test="${userClickChangePassword == true}">
			<%@include file="changePasswordPage.jsp"%>
		</c:if>
		<c:if test="${userClickNext == true}">
			<%@include file="sell-book-form.jsp"%>
		</c:if>
		<c:if test="${userClickAddDetailsForSale == true}">
			<%@include file="add-detail-form-sell.jsp"%>
		</c:if>
		<c:if test="${userClickMyBooks == true}">
			<%@include file="books-of-seller.jsp"%>
		</c:if>
		<c:if test="${userClickUpdateBook == true}">
			<%@include file="formForBookUpdateBySeller.jsp"%>
		</c:if>
		<!-- Loading add to cart   -->
		<c:if test="${userClickAddToCart == true}">
			<%@include file="UserCart.jsp"%>
		</c:if>
		<!-- Loading make payment  -->
		<c:if test="${userClickMakePayment == true}">
			<%@include file="paymentPage.jsp"%>
		</c:if>
		<c:if test="${userSuccessfulPay == true}">
			<%@include file="paymentSuccessfulPage.jsp"%>
			<%@include file="home.jsp"%>
		</c:if>
		<!-- Loading edit order  -->
		<c:if test="${userClickEditOrder == true}">
			<%@include file="editOrderPage.jsp"%>
		</c:if>
		<c:if test="${userClickShowProfile == true}">
			<%@include file="show-profile.jsp"%>
		</c:if>
		<!-- Loading wishlist  -->
		<c:if test="${userClickMyWishList == true}">
			<%@include file="showWishList.jsp"%>
		</c:if>
		<!-- Loading order history  -->
		<c:if test="${userClickViewOrderedBooks == true}">
			<%@include file="viewOrderHistory.jsp"%>
		</c:if>
		<!-- Loading books offered by seller  -->
		<c:if test="${userClickViewBooksSold == true}">
			<%@include file="viewBooksBySeller.jsp"%>
		</c:if>
		<c:if test="${userClickInbox == true}">
			<%@include file="inbox.jsp"%>
		</c:if>
		<c:if test="${userClickViewConversation == true}">
			<%@include file="view-conversation.jsp"%>
		</c:if>
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>


	</div>

</body>
</html>
