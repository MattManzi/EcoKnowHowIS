<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
    
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

/*
Lista con tutti i parametri appartenenti alla matrice scelta in fase di creazione del pacchetto.

Servlet Necessarie:
	VisualizzaParametriServlet?action=pacchetto - OK
	ComponiPacchettoServlet?action=aggiungi - X
	ComponiPacchettoServlet?action=rimuovi - X
	ComponiPacchettoServlet?action=cancella - X
	AggiuntaPacchettoServlet?action=salva - OK
	
Se il pacchetto viene salvato, il cliente dovrà recarsi in scelta pacchetti analitico per selezionarlo.
*/

%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>