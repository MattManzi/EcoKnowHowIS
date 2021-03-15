<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	String user= (String) request.getAttribute("user");
	if(user==null || user.equals("")){
		response.sendRedirect(response.encodeRedirectURL("./RecuperPassword.jsp"));
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
		<h1>ModificaPassword</h1>
		<form name="formModificaPassword" action="<%=response.encodeURL("RP?action=password&user="+user) %>" method="post">
			<input type="password" name="password" placeholder="Nuova Password:">
			<input type="password" name="password2" placeholder="Conferma Password:">
			<input type="submit" value="Conferma">
		</form>
	</div>
</body>
</html>