<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
    
    <%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	ClienteBean utente = (ClienteBean) session.getAttribute("Utente");
	if(utente!=null &&(userRoles)){
		response.sendRedirect("./HomePage.jsp");
	}
	String msg = (String) request.getAttribute("msg");
	%>
<!DOCTYPE html>
<html>

<head>
<link href="css/Login.css" rel="stylesheet">
<title>EcoKnowHow</title>
</head>
<body>

	<div id="main">
		<div id="top">
			<a href="<%=response.encodeURL("HomePage.jsp") %>" class="logo"><img alt="EcoKnowHow" src="./img/logo.png" width="100%" height="100%"></a>
		</div>
		<fieldset>
		<form name="formLogin" action="<%=response.encodeURL("Login?action=user")%>" method="post">
			<input type="text" name="username" placeholder="Username:"><br> 
			<input type="password" name="password" placeholder="Password:"><br>
			<input type="submit" value="Login">
		</form>
		<a href="<%=response.encodeURL("RecuperaPassword.jsp") %>">Password dimenticata</a><br><br>
		<label for="registrati">Non hai un account? </label><a id="registrati" href="<%=response.encodeURL("RegistrazioneUser.jsp") %>">Registrati ora</a>
		</fieldset>
	</div>
</body>
</html>