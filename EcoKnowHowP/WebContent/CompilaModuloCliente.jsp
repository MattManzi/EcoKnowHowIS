<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
    
<%
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

/*
Form per la creazione di un modulo.

Servlet Necessarie:
	PianoControl?action=salva- X
	
*/

%>  
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
		nav
		
		<div id="main">
			form 
				primi input:
				
				da ragioneSocialeProd  //**Stessa Persona
				a luogo
				
				secondi input
				da nomeCampionatore
				a note
				
				obiettivi
				if(matrice.tipoModulo.equals(A)){
					checkBox ?
				}else if(matrice.tipoModulo.equals(B)){
				
				}else{
				
				}
		</div>
		
		footer
</body>
</html>