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
<title>Insert title here</title>
</head>
<body>
	<!-- Questa pagina viene usata per il cambio password dall'area personale e per il cambio password in seguito ad un recupera password  -->
	<h1>ModificaPassword</h1>
	<form name="formModificaPassword" action="<%=response.encodeURL("RP?action=password&user="+user) %>" method="post">
		<input type="password" name="password">
		<input type="password" name="password2">
		<input type="submit" value="Conferma">
	</form>
</body>
</html>