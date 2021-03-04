<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
    
<%
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

/*
Form per la creazione di un modulo.

Servlet Necessarie:
	PianoControl?action=salva- X
	
*/

%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>




		<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>