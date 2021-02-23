<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/HomePage.css" rel="stylesheet">
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
		<a href="<%=response.encodeURL("RecuperaPasswor.jsp") %>"> Recupera Password </a>
	</div>
	
		<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>