<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<!-- In questa pagina verrà chiesto all'utente di inserire la propria email -->
	<h1>Recupero Password</h1>
	
	<form name="formEmail" action="<%=response.encodeURL("RP?action=verifica") %>" method="post"> 
		<input type="text" name="email">
		<input type="submit" value="Invia Email">	
	</form>
</body>
</html>