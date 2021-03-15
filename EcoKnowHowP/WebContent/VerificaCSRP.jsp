<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	String user= (String) request.getAttribute("user");
	if(user==null || user.equals("")){
		response.sendRedirect(response.encodeRedirectURL("./RecuperaPassword.jsp"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<link href="css/Verifica.css" rel="stylesheet">
<title>Reimpostazione password</title>
</head>
<body>
	<div id="main">
		<h1>VerificaCodice</h1>
		<p>Inserire il codice ricevuto via email.</p>
		<form name="formVerificaCodiceRP" action="<%=response.encodeURL("RP?action=codice&user="+user) %>" method="post">
			<input type="text" name="codice" placeholder="Codice:">
			<input type="submit" value="verifica">
		</form>	
		<p>Non hai ricevuto l'e-mail? <a href="<%=response.encodeURL("RP?action=sendEmail&user="+user) %>">Invia codice</a></p>
	</div>
</body>
</html>