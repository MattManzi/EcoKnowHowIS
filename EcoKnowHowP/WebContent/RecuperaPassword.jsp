<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- In questa pagina verrà chiesto all'utente di inserire la propria email -->
	<h1>Recupero Password</h1>
	
	<form name="formEmail" action="<%=response.encodeURL("RecuperaPasswordServlet?action=verifica") %>" method="post"> 
		<input type="text" name="email">
		<input type="submit" value="Invia Email">	
	</form>
</body>
</html>