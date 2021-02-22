<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LoginAdmin</title>
</head>
<body>
<a href="<%=response.encodeURL("HomePage.jsp") %>" class="logo"> EcoKnowHow </a>
	<div id="main">
		<form name="formLoginAdmin" action="<%=response.encodeURL("LoginAdminServlet") %>" method="post">
			<fieldset>
				<label for="username">Username</label> 
				<input class="normal" id="username" type="text" name="username" placeholder="enter username"><br> 
				<label for="password">Password</label> 
				<input class="normal" id="password" type="password" name="password" placeholder="enter password"><br>
				<input type="submit" value="Login">
			</fieldset>
		</form>
		<a href="<%=response.encodeURL("RecuperaPassword.jsp") %>" class="logo"> Recupera Password </a>
	</div>
</body>
</html>