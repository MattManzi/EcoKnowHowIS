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
	<a href="${pageContext.request.contextPath}/jsp/HomePage.jsp" class="logo">EcoKnowHow</a>
	<div id="main">
		<fieldset>
		<form name="formLogin" action="${pageContext.request.contextPath}/jsp/LoginUser?action=login" method="post">
			<label for="username">Username:</label> 
			<input type="text" name="username" placeholder="enter username"><br> 
			<label for="password">Password:</label> 
			<input type="password" name="password" placeholder="enter password"><br>
			<a href="<%=response.encodeURL("jsp/Registrazione.jsp") %>">Recupera Password</a><br>
			<button type="button" onclick="loginUser()">Login</button>
		</form>
		<label for="registrati">Non hai un account?</label>
		<a id="registrati" href="${pageContext.request.contextPath}/jsp/RegistrazioneUser.jsp">Registrati ora</a>
		<%if(msg!=null){%>	
			<p id="error"><%=msg%></p>
		<%
		}
		%>
		</fieldset>
	</div>
</body>
</html>