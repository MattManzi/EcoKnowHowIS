<%@page import="java.util.Iterator"%>
<%@page import="ekh.bean.PacchettoBean"%>
<%@page import="java.util.Collection"%>
<%@page import="ekh.bean.AmministratoreBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
Collection<?> pacchetti=(Collection<?>) request.getAttribute("pacchetti");
if (admin == null || adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}else{
	if(pacchetti==null){
		response.sendRedirect(response.encodeRedirectURL("./Pacchetto?action=visualizza"));
		return;
	}
}



%>
<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/Template.css" rel="stylesheet">
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>
<title>Gestione Pacchetti Admin</title>
</head>
<script>
function myFunction() {
  var x = document.getElementById("nav");
  if (x.className === "nav") {
    x.className += " responsive";
  } else {
    x.className = "nav";
  }
}
</script>
<body>
	<header class="header" id="header">
		<div class="logo">
			<a href="<%=response.encodeURL("HomePageAdmin.jsp")%>"><img src="./img/logo.png"></a>
		</div>
		<div class="nav" id="nav">
		<a href="javascript:void(0);" class="icon" onclick="myFunction()"><i class="fa fa-bars"></i></a>
		<div class="invisibile">
		<p><br></p>
		<p><br></p>
		<p><br></p>
		</div>
			<%if (admin == null){ %>
				<a href="<%=response.encodeURL("LoginAdmin.jsp")%>" class="active">Login</a>					
			<%}else{%>				
				<a href="<%=response.encodeURL("")%>" class="active"><%=admin.getUsername() %></a>	
			<%
			}
			%>
			<a href="<%=response.encodeURL("GestioneClientiAdmin.jsp")%>">Gestione Clienti</a>
			<a href="<%=response.encodeURL("GestioneMatriciAdmin.jsp")%>">Gestione Matrici</a>		   	
			<a href="<%=response.encodeURL("GestionePacchettiAdmin.jsp")%>">Gestione Pacchetti</a>
		</div>	
	</header>
	
	
	<div class="canvas">	
		<table id="tableMatriciAdmin">
			<tr>
				<td>ID</td>
				<td>Nome </td> 
				<td>Sottotitolo</td>
				<td>Modifica </td>
				<td>Cancella </td>
			</tr>
			
			<% if(pacchetti!= null && pacchetti.size()>0){
				Iterator<?> it=pacchetti.iterator();
				while(it.hasNext()){
					PacchettoBean bean=(PacchettoBean) it.next();
			%>
			<tr> 
				<td> <%=bean.getId()%> </td>
				<td> <%=bean.getNome()%> </td>	
				<td> <%=bean.getDescrizione()%> </td>	
				<td> <button class="bott_modifica" id="modifica" onclick="selectPacchettoMod(<%=bean.getId()%>)">M</button></td>
				<td> <button class="bott_rimuovi" id="cancella" onclick="cancellaPacchetto(<%=bean.getId()%>)">X</button></td>	
			</tr>
			<%	
				}
			}
			 %>
		</table>
		<button class="bott_aggiungi"> <a href="<%=response.encodeURL("AggiungiPacchetto.jsp")%>"> Aggiungi</a></button>
	</div>
</body>
</html>