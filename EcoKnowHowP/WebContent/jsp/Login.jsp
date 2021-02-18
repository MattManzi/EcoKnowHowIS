<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, ekh.bean.*"%>
    
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
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="../css/HomePage.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>EcoKnowHow</title>

</head>
<body>
	<a href="<%=response.encodeURL("HomePage.jsp") %>" class="logo"> EcoKnowHow </a>
	<div id="main">
		<form name="formLogin" action="<%=response.encodeURL("LoginUser?action=login") %>" method="post">
			<fieldset>
				<label for="username">Username</label> 
				<input class="normal" id="username" type="text" name="username" placeholder="enter username"><br> 
				<label for="password">Password</label> 
				<input class="normal" id="password" type="password" name="password" placeholder="enter password"><br>
				<label>Non hai un account?  <a id="registra" href="<%=response.encodeURL("Registrazione.jsp") %>">Registrati ora</a></label>
				<button type="button" onclick="loginUser()">Login</button>
				<%
				if(msg!=null){ 
				%>	
				<p id="error"><%=msg%></p>
				<%
				} 
				%>
			</fieldset>
		</form>
	</div>
</body>
</html>