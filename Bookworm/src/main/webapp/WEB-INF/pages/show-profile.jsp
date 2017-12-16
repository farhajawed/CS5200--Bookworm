<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page
	import=" com.sun.jersey.core.util.Base64, java.nio.file.Paths, java.nio.file.Files"%>
<%@page session="true"%>
<c:set var="imageUrl" value="${user.imageUrl}" />
<% String path = (String) pageContext.getAttribute("imageUrl"); %>

<div class="container">
	<div class="row">
	<c:choose>
	<c:when test="${userLogin.userRole ne 'BUYER'}">
		<h3>Seller Information: ${userLogin.username}</h3>
	</c:when>
	<c:otherwise>
		<h3 id="usernameCss">Profile of ${userLogin.username}</h3>
	</c:otherwise>
	</c:choose>
	<c:if test="${user.imageUrl != null}">
		<img src="data:image/jpeg;base64,
				<%=new String(Base64.encode(Files.readAllBytes(Paths.get(path))))%>"
					class="img-responsive"  id="pp"/>
		</c:if> 
		<c:if test="${user.imageUrl eq null}">
						<img src="${images}/image-not-available.jpg" class="img-responsive" id="pp"/>
		</c:if>
	<br>
	<div id="center-details">
		<c:if test="${userLogin.userRole ne 'BUYER'}">
		<a href="${contextRoot}/seller-advertised-book/${userLogin.username}" class="btn btn-danger">
						All books sold by ${userLogin.username}</a>
		</c:if>
		<hr>
		<h3>Personal Details:</h3>	
		<p>Name: ${user.firstname} ${user.lastname}</p>
		<p>Date of Birth: ${user.dob}</p>
		<hr>
		<h3>Contact Details:</h3>
		Phone: ${user.phone}<br>
		Email: ${user.email}<br>
		<c:forEach var="address" items="${user.useraddress}">
		Address:
		${address.street1} ${address.street2}<br>
		${address.city},
		${address.state}-${address.pincode} 
		<hr>
		</c:forEach>
		
		<c:if test="${userLogin.userRole ne 'BUYER'}">				
		<h3>Contact Seller:</h3>
		
		<c:if test="${sent==true}">
			<div class="alert alert-info">
				<strong>Your message has been sent! Go to <a href="${pageContext.request.contextPath}/inbox">inbox folder</a></strong>
			</div>
		</c:if>
		
		<form:form action="${pageContext.request.contextPath}/send-message" modelAttribute="message" method="POST">
		  <div class="form-group">
		    <form:hidden path="userLogin.username"/>
		    <form:hidden path="userLogin.id"/>
		 
		    <label for="subject">Subject:</label>
		  <form:input path="conversation.subject" type="text" class="form-control" required="required"/>
		  
		    <label for="message">Message:</label>
		    <form:textarea path="text" class="form-control" rows="5" required="required"/>
		    <br/>
		    <button type="submit" class="btn btn-primary">Send Message</button>
		  </div>
		 </form:form>
		</c:if>
	
	</div>
	</div>
</div>