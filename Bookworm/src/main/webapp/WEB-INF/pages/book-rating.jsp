<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>$( document ).ready( function() {
	    $( "#review" ).click( function() {
	        $( "#form1" ).toggle( 'slow' );
	    });
	});
</script>

<c:if test="${userRole eq 'BUYER' || userRole eq 'PRIME'|| userRole eq 'ADMIN'}">
   	<div id="review">
		<c:if test="${edit == true}">
			<button type="button" class="btn btn-info" id="btn">Edit your review</button>
		</c:if>
		<c:if test="${add == true}">
			<button type="button" class="btn btn-info" id="btn">Write a review</button>
		</c:if>
	</div>
	
   	<div class="form-group" id="form1">
	   	<form:form action="${pageContext.request.contextPath}/buyer/review" modelAttribute="rating" method="POST">
	   		<form:hidden path="book.id"/>
	   		<form:hidden path="book.isbn"/>
	   		<form:hidden path="id"/>
	   		<form:hidden path="userLogin.id"/>
			<label for="rating">Rate the book:</label>
			
			 <div class="rating">
	            <form:radiobutton path="score" id="star5" name="star" value="5" class="radio-btn hide" required="required"/>
	            <label for="star5" ><img src="${images}/star.png" style="height:9px"></label>
	            <form:radiobutton path="score" id="star4" name="star" value="4" class="radio-btn hide" />
	            <label for="star4" ><img src="${images}/star.png" style="height:9px"></label>
	            <form:radiobutton path="score" id="star3" name="star" value="3" class="radio-btn hide" />
	            <label for="star3" ><img src="${images}/star.png" style="height:9px"></label>
	            <form:radiobutton path="score" id="star2" name="star" value="2" class="radio-btn hide" />
	            <label for="star2" ><img src="${images}/star.png" style="height:9px"></label>
	            <form:radiobutton path="score" id="star1" name="star" value="1" class="radio-btn hide" />
	            <label for="star1" ><img src="${images}/star.png" style="height:9px"></label>
	            <div class="clear"></div><br>
	          </div>
	            <label for="comment">Review:</label>
				<form:textarea path="review" class="form-control" rows="5" id="comment" required="required"/><br>
			 <button type="submit" value="Post review" class="btn">Post Review</button>
	    </form:form>
     </div> 
</c:if>

<c:if test="${not empty ratings}">
<h2>Customer Reviews</h2>
<c:forEach var="ratings" items="${ratings}" >
	<div class="panel panel-default">
			<div class="panel-body">
				<c:forEach begin = "1" end = "${ratings.score}">
         			<span class="fa fa-star checked"></span>
     		   </c:forEach>
     		   <c:forEach begin = "${ratings.score+1}" end = "5">
         			<span class="fa fa-star"></span>
     		   </c:forEach>
     		   <br>
				<c:choose>
					<c:when test="${ratings.userLogin.username eq currentuser}">
			    		<a href="${contextRoot}/userInfo/${currentuser}">${currentuser}</a>
					 </c:when>
					  <c:otherwise>
					 	<a href="${contextRoot}/show/${ratings.userLogin.username}">
					 	${ratings.userLogin.username}</a>
					 </c:otherwise>
				</c:choose>
			     wrote on ${ratings.dt}:<br>${ratings.review}	 	
			</div>
	 </div>
</c:forEach>
</c:if>
		   
		    



		