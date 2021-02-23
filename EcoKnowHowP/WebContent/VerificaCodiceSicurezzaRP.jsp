<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String user= (String) request.getAttribute("user");
	if(user==null || user.equals("")){
		response.sendRedirect(response.encodeRedirectURL("./RecuperPassword.jsp"));
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>VerificaCodice</h1>
	<p>Inserire il codice ricevuto via email.</p>
	<form name="formVerificaCodiceRP" action="<%=response.encodeURL("RecuperoPasswordServlet?action=codice&user="+user) %>" method="post">
		<input type="text" name="codice">
		<input type="submit" value="verifica">
	</form>	
	<p>Non hai ricevuto l'e-mail? <a href="<%=response.encodeURL("RecuperoPasswordServlet?action=sendEmail&user="+user) %>">Invia codice</a></p>
	
</body>
</html>