<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>VerificaCodice</h1>
	<p>Inserire il codice ricevuto via email.</p>
	<form name="formVerificaCodiceregistrazione" action="<%=response.encodeURL("RegistrazioneUserServlet?action=registra") %>" method="post">
		<input type="text" name="codice">
		<input type="submit" value="verifica">
	</form>	
	<p>Non hai ricevuto l'e-mail? <a href="<%=response.encodeURL("RegistrazioneUserServlet?action=sendEmail") %>">Invia codice</a></p>
	
</body>
</html>