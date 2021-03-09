<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
Collection<?> piani=null;
ClienteBean cliente = null;

if ((admin != null && adminRoles != null && adminRoles.booleanValue())
		|| (user != null || userRoles != null && userRoles.booleanValue())) {
	if (admin != null) {
		cliente = (ClienteBean) request.getSession().getAttribute("cliente");
		if (cliente == null) {
			response.sendRedirect("./GestioneClientiAdmin.jsp");
			return;
		} else {
			piani = (Collection<?>) request.getAttribute("piani");
			if (piani == null) {
				response.sendRedirect(response.encodeRedirectURL("./PianoControl?action=pianiCliente&username=" + cliente.getUsername()));
				return;
			}
		}
	} else {
		piani = (Collection<?>) request.getAttribute("piani");
		if (piani == null) {
			response.sendRedirect(response.encodeRedirectURL("./PianoControl?action=pianiCliente&username=" + user.getUsername()));
			return;
		}
	}
} else {
	response.sendRedirect("./HomePage.jsp");
	return;
}

/*
Lista con tutti i piani del cliente, loggato o scelto dall'admin.

Servlet Necessarie:
	PianoControl?action=pianiCliente&username= - OK
	PianoControl?action=select&id= - OK
	PianoControl?action=delete
*/
%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<!-- L'admin e il cliente visualizzano lo storico Cliente  -->
</body>
</html>