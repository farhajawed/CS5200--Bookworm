<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="center-details">
				<h1>Login</h1>

				<!-- /login?error=true -->
				<c:if test="${param.error == 'true'}">
					<div style="color: red; margin: 10px 0px;">

						Login Failed!!!<br /> Reason :
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

					</div>
				</c:if>

				<form name='f'
					action="${pageContext.request.contextPath}/j_spring_security_check"
					method='POST'>
					<table>
						<tr>
							<td>User:</td>
							<td><input type='text' name='userdetails' value=''></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td><input name="submit" type="submit" value="Login"
								class="btn btn-primary" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>