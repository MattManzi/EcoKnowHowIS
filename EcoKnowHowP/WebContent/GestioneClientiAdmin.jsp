<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*" %>
<!DOCTYPE html>
<% 
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
Collection<?> clienti=(Collection<?>)request.getAttribute("clienti"); 

if (adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}else{
	if(clienti==null){
		response.sendRedirect(response.encodeRedirectURL("./User?action=visualizza"));
		return;
	}
}


/*
Lista con tutti i clienti.
In questa pagina sarà possibile rimuovere i clienti dal db.
Cliccare su un cliente per accedere al suo storico piani.

Servlet Necessarie:
	ClienteControl?action=visualizza - OK
	ClienteControl?action=select - OK
	ClienteControl?action=delete - OK
*/

%>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Gestione Clienti Admin</title>
<body>
<%@ include file="NavAdmin.jsp" %>






<%@ include file="Footer.jsp" %>




</body>
</html>