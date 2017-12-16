<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
	<div class="row">
		<h1>My Messages</h1>
		<hr/>
		<c:if test="${deleted eq true}">
			<div class="alert alert-danger">
	 			 <strong>Conversation is deleted</strong>
			</div>
		</c:if>
	
	
  <c:choose>
	    <c:when test="${empty conversation}">
	       <h3>No messages to display</h3>
	    </c:when>
    
	    <c:otherwise>	
			<c:forEach var="c" items="${conversation}">
			<!-- construct an "delete" link with conversation id -->
			<c:url var="deleteLink" value="/delete-conversation">
				<c:param name="conversationId" value="${c[0]}"/>
			</c:url>
			<!-- construct an "view" link with conversation id -->
			<c:url var="viewConversation" value="/view-conversation">
				<c:param name="conversationId" value="${c[0]}"/>
			</c:url>
			
				<div class="panel panel-default">
					<div class="panel-body">
					<h4>
					<a href="${deleteLink}"><span class="glyphicon glyphicon-remove"></span></a>
					<!-- subject -->
					<c:if test="${c[3] eq true}"><b><a href="${viewConversation}" id="link">${c[1]}</a></b><br></c:if>
					<c:if test="${c[3] eq false}"><a href="${viewConversation}" id="link">${c[1]}</a><br></c:if>
					</h4>
					<!-- last reply -->
					<jsp:useBean id="dateObject" class="java.util.Date" />
					<c:set var="result" value="${c[2] * 1000}"/>
					<jsp:setProperty name="dateObject" property="time" value="${result}" />
					Last reply:<fmt:formatDate value="${dateObject}" pattern="dd/MM/yyyy HH:mm:ss" />
					</div>
				</div>
			</c:forEach>
	 </c:otherwise>
</c:choose>
		
</div>

</div>