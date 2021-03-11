<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h1>VerificaCodice</h1>
	<p>Inserire il codice ricevuto via email.</p>
	<form name="formVerificaCodiceregistrazione" action="<%=response.encodeURL("User?action=registra") %>" method="post">
		<input type="text" name="codice">
		<input type="submit" value="verifica">
	</form>	
	<p>Non hai ricevuto l'e-mail? <a href="<%=response.encodeURL("Email?action=sendEmail") %>">Invia codice</a></p>
	
</body>
</html>