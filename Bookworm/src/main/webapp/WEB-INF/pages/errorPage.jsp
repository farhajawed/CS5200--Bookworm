<%@page session="true"%>
<html>
<head>
<title>Error Access Denied</title>
</head>
<body>
	<h3 style="color: red;">${message}</h3>
	<p><a href="${contextRoot}/login">Click here</a> to login again.</p>
</body>
</html>