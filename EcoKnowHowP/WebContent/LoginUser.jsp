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
	<a href="<%=response.encodeURL("HomePage.jsp")%>" class="logo">EcoKnowHow</a>
	<div id="main">
		<fieldset>
		<form name="formLogin" action="<%=response.encodeURL("Login?action=user")%>" method="post">
			<label for="username">Username:</label> 
			<input type="text" name="username" placeholder="enter username"><br> 
			<label for="password">Password:</label> 
			<input type="password" name="password" placeholder="enter password"><br>
			<a href="<%=response.encodeURL("RecuperaPassword.jsp") %>">Recupera Password</a><br>
			<input type="submit" value="Login">
		</form>
		<label for="registrati">Non hai un account?</label>
		<a id="registrati" href="<%=response.encodeURL("RegistrazioneUser.jsp") %>">Registrati ora</a>
		<%if(msg!=null){%>	
			<p id="error"><%=msg%></p>
		<%
		}
		%>
		</fieldset>
	</div>
		<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>