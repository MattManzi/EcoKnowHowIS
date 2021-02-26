<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	ClienteBean utente=(ClienteBean) request.getSession().getAttribute("Utente");	
	Collection<?> pacchetti=(Collection<?>) request.getAttribute("pacchetti");	
	if(utente != null && userRoles != null && userRoles.booleanValue()) {
		if(pacchetti==null){
			response.sendRedirect("SceltaTipoPacchettoCliente.jsp");
			return;
		}
	}else{
		response.sendRedirect("LoginUser.jsp");
		return;
	}
	/*
		Servlet Necessarie:
		AggiuntaPianoServlet?action=crea - X
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>