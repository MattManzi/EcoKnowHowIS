<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
    
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
<title>Insert title here</title>
</head>
<body>




		<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>