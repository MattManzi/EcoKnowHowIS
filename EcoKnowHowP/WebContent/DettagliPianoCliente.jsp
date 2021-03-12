<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) session.getAttribute("userRoles");
PianoBean pianoAdmin = null;
PianoBean piano = null;
ArrayList<ParametroBean> parametri=null;
Boolean key = false;

if (adminRoles != null && adminRoles.booleanValue()){
	pianoAdmin = (PianoBean) session.getAttribute("pianoAdmin");
	if(pianoAdmin==null){
		response.sendRedirect("./GestioneClientiAdmin.jsp");
		return;
	}else{
		key = true;	
		parametri=pianoAdmin.getContenuto();
	}
}else if(userRoles != null && userRoles.booleanValue()) {	
		piano = (PianoBean) request.getAttribute("pianoAdmin");
		if(piano==null){
			response.sendRedirect("./GestioneClientiAdmin.jsp");
			return;	
		}else{
			parametri=piano.getContenuto();
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
<title>Insert title here</title>
</head>
<body>
	<%@ include file="NavAdmin.jsp" %>
	
	<div id="main" class="table">
		<div id="parametri" class="cell">
			<%
				if(parametri!=null && parametri.size()>0){
			%>		
					
			<%
				}
			%>
		</div>
		<div id="modulo" class="cell">
		
		</div>	
	</div>
	
	<%@ include file="Footer.jsp" %>	
</body>
</html>