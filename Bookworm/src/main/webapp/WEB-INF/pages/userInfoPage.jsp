<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import=" com.sun.jersey.core.util.Base64, java.nio.file.Paths, java.nio.file.Files"%>
<%@page session="true"%>

<html>
<head>
<title>${title}</title>
</head>
<body>

<c:set var="imageUrl" value="${user.imageUrl}" />
<% String path = (String) pageContext.getAttribute("imageUrl"); %>

<div class="container">
	<div class="row">
	<h3 id="usernameCss">
	<c:choose>
		<c:when test="${user.username eq currentuser.username}">
			My Profile
		</c:when>
		<c:otherwise>
			Profile of ${user.username}
		</c:otherwise>
	</c:choose>
	</h3>
	
	<form:form name='form'
			action="${pageContext.request.contextPath}/updateProfile"
			modelAttribute="user" method='POST'>
			
	       <c:if test="${user.imageUrl != null}">
			<img src="data:image/jpeg;base64,
				<%=new String(Base64.encode(Files.readAllBytes(Paths.get(path))))%>"
					class="img-responsive"  id="pp"/>
			</c:if> 
			<c:if test="${user.imageUrl eq null}">
						<img src="${images}/image-not-available.jpg" class="img-responsive" id="pp"/>
		   </c:if>
			
			<div id="center-details">
			<h3>Personal Details:</h3>	
			<p>Name: ${user.firstname} ${user.lastname}</p>
			<p>Date of Birth: ${user.dob}</p>
			<hr>
			<form:input path="password" type="hidden" value="${user.userLogin.password}" />
			
			<h3>Contact Details:</h3>
			Address:
			${user.useraddress[0].street1} ${user.useraddress[0].street2}<br>
			${user.useraddress[0].city},
			${user.useraddress[0].state}-${user.useraddress[0].pincode}<br><br>
			<p>Phone: ${user.phone}</p>
			<p>Email: ${user.email}</p>
			<hr>
			
			<form:button id="home" name="userProfile"
					value="editedProfile" class="btn btn-primary">Edit profile</form:button>
			<form:button id="home" name="userProfile"
					value="changePassword" class="btn btn-primary">Change password</form:button>
			<c:if
				test="${user.userrole eq 'BUYER' || user.userrole eq 'PRIME' || user.userrole eq 'ADMIN'}">
				   <form:button id="home" name="userProfile"
						value="viewBooksOrdered" class="btn btn-primary">View Order History</form:button>
			</c:if>
			<c:if
				test="${user.userrole eq 'SELLER' || user.userrole eq 'PRIME' || user.userrole eq 'ADMIN'}">
				   <form:button id="home" name="userProfile"
						value="viewBooksSold" class="btn btn-primary">View Sales History</form:button>
			</c:if>
		</div>
		
		</form:form>
	</div>
</div>
</body>
</html>