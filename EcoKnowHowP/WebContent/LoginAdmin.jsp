<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/Login.css" rel="stylesheet">
<link href="css/Template.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>LoginAdmin</title>
</head>
<body>

	

	<div class="canvas">
	<a href="<%=response.encodeURL("HomePage.jsp") %>" class="logo"> EcoKnowHow </a>
		<form name="formLoginAdmin" action="<%=response.encodeURL("Login?action=admin") %>" method="post">
			<fieldset class="">
				<label for="username">Username</label> 
				<input class="normal" id="username" type="text" name="username" placeholder="enter username"><br> 
				<label for="password">Password</label> 
				<input class="normal" id="password" type="password" name="password" placeholder="enter password"><br>
				<input type="submit" value="Login">
			</fieldset>
		</form>
		<a href="<%=response.encodeURL("RecuperaPassword.jsp") %>"> Recupera Password </a>
	</div>
	
	
<%@ include file="Footer.jsp" %>
</body>
</html>