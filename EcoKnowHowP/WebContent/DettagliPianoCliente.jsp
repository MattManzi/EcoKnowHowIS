<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
Boolean key = false;

if ((admin != null && adminRoles != null && adminRoles.booleanValue())
		|| (user != null || userRoles != null && userRoles.booleanValue())) {
	if(admin!=null){
		PianoBean piano = (PianoBean) request.getSession().getAttribute("pianoAdmin");
		if(piano==null){
			response.sendRedirect("./GestioneClientiAdmin.jsp");
			return;
		}else{
			key = true;	
		}
	}else{
		PianoBean piano = (PianoBean) request.getAttribute("pianoAdmin");
		if(piano==null){
			response.sendRedirect("./GestioneClientiAdmin.jsp");
			return;
		}
	}
	PacchettoBean pacchetto=(PacchettoBean) request.getAttribute("pacchettoPiano");
	if(pacchetto==null){
		
	}
} else {
	response.sendRedirect("./HomePage.jsp");
	return;
}

/*
Lista con tutti il pacchetto usato dal cliente e i dati inseriti nel modulo.
Il Cliente qui non potra fare niente!
L'admin potrà modificare il prezzo e lo stato.

Servlet Necessarie:
	SelectPianoClienteServlet - OK
	ModificaPianoClienteAdminServler - X
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