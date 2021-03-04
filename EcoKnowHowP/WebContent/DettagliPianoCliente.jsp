<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
PianoBean pianoAdmin = null;
PianoBean piano = null;
Boolean key = false;

if ((admin != null && adminRoles != null && adminRoles.booleanValue())
		|| (user != null || userRoles != null && userRoles.booleanValue())) {
	if(admin!=null){
		pianoAdmin = (PianoBean) request.getSession().getAttribute("pianoAdmin");
		if(pianoAdmin==null){
			response.sendRedirect("./GestioneClientiAdmin.jsp");
			return;
		}else{
			key = true;	
		}
	}else{
		piano = (PianoBean) request.getAttribute("pianoAdmin");
		if(piano==null){
			response.sendRedirect("./GestioneClientiAdmin.jsp");
			return;
		}
	}
} else {
	response.sendRedirect("./HomePage.jsp");
	return;
}

/*
Lista con tutti i parametri selezionati dal cliente e i dati inseriti nel modulo.
Il Cliente qui potrà solo scaricare il referto.
L'admin potrà modificare il prezzo e lo stato; caricare/scaricare il referto; scaricare la SDS.

if(key){
	stato modificabile
	input per il prezzo
	button dlSDS
}

if(piano.getReferto){
	button dlReferto
}else{
	button ulReferto
}

Servlet Necessarie:
	PianoControl?action=dlReferto - OK x
	PianoControl?action=ulReferto - OK x
	PianoControl?action=dlSDS - OK x
	PianoControl?action=prezzo - X
	PianoControl?action=stato - X
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