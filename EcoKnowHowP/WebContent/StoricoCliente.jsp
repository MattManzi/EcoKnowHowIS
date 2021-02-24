<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*" %>
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

if (admin != null && adminRoles != null && adminRoles.booleanValue()){
	ClienteBean cliente=(ClienteBean)request.getSession().getAttribute("cliente"); 
	if(cliente==null){
		response.sendRedirect("./GestioneClientiAdmin.jsp");
		return;
	}else{
		Collection<?> piani=(Collection<?>)request.getSession().getAttribute("piani"); 
		if(piani==null){
			response.sendRedirect(response.encodeRedirectURL("./VisualizzaPianiClienteServlet?username="+cliente.getUsername()));
			return;
		}
	}
}else if(user != null || userRoles != null && userRoles.booleanValue()){
	Collection<?> piani=(Collection<?>)request.getSession().getAttribute("piani"); 
	if(piani==null){
		response.sendRedirect(response.encodeRedirectURL("./VisualizzaPianiClienteServlet?username="+user.getUsername()));
		return;
	}
}else{
	response.sendRedirect("./HomePage.jsp");
	return;
}
		

/*
Lista con tutti i piani del cliente, loggato o scelto dall'admin.
Cliccare su un piano per accedere ai dettagli.

Servlet Necessarie:
	VisualizzaPianiClienteServlet - OK
	SelectPianoClienteServler - OK
*/

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- L'admin e il cliente visualizzano lo storico Cliente  -->
</body>
</html>