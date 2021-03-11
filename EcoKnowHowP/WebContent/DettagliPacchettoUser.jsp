<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");	
	PacchettoBean pacchetto=null;
	if(userRoles != null && userRoles.booleanValue()) {
		pacchetto=(PacchettoBean) request.getAttribute("pacchettoDettagli");	
		if(pacchetto==null){
			response.sendRedirect("SelezionaPacchettoCliente.jsp");
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
<title>Insert title here</title>
</head>
<body>
	un div per ogni parametro all'interno del pacchetto
</body>
</html>