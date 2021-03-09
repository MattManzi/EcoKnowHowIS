<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
    
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

/*
Form per la creazione IN SESSIONE di un pacchetto.
Solo info principali. Niente parametri

Servlet Necessarie:
	AggiuntaPacchettoServlet?action=crea - OK
	
Se il pacchetto viene salvato, il cliente dovrà recarsi in scelta pacchetti analitico per selezionarlo.
*/

%>       
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
</body>
</html>