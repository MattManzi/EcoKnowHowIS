<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/Login.css" rel="stylesheet">
<title>LoginAdmin</title>
</head>
<body>
	<div id="main">
		<div id="top">
			<a href="<%=response.encodeURL("HomePage.jsp") %>" class="logo"><img alt="EcoKnowHow" src="./img/logo.png" width="100%" height="100%"></a>
		</div>
		<fieldset>
			<form name="formLoginAdmin" action="<%=response.encodeURL("Login?action=admin") %>" method="post">	
				<input class="normal" id="username" type="text" name="username" placeholder="Username:"><br>
				<input class="normal" id="password" type="password" name="password" placeholder="Password:"><br>
				<input type="submit" value="Login">
			</form>
			<a href="<%=response.encodeURL("RecuperaPassword.jsp") %>"> Recupera Password </a>
		</fieldset>
	</div>
</body>
</html>