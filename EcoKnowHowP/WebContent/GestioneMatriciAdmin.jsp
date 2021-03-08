<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<%

AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
Collection<?> matrici=(Collection<?>) request.getAttribute("matrici");
if (admin == null || adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}else{
	if(matrici==null){
		response.sendRedirect(response.encodeRedirectURL("./Matrice?action=visualizza"));
		return;
	}
}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/Template.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Gestione Matrice Admin</title>
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
</head>
<body>
	<header class="header clearfix" id="header">
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
		<button class="bott_aggiungi"> <a  href="${pageContext.request.contextPath}/AggiungiMatriceAdmin.jsp"> Aggiungi</a></button>
		<table id="tableMatriciAdmin">
			<tr>
				<td>ID</td>
				<td>Nome </td> 
				<td>Sotto Titolo</td>
				<td>Modifica </td>
				<td>Cancella </td>
			</tr>
			<% if(matrici != null && matrici.size()>0){
				Iterator<?> it=matrici.iterator();
				while(it.hasNext()){
					MatriceBean bean=(MatriceBean) it.next();
			%>
			<tr> 
				<td > <%=bean.getId()%> </td>
				<td > <%=bean.getNome()%> </td>	
				<td > <%=bean.getSottotitolo()%> </td>	
				<td> <button class="bott_modifica" id="modifica" onclick="selectMatriceMod(<%=bean.getId()%>)">M</button></td>
				<td> <button class="bott_rimuovi" id="cancella" onclick="cancellaMatrice(<%=bean.getId()%>)">X</button></td>	
			</tr>
			<%	
				}
			}
			 %>
		</table>
	</div>
	
		
	<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>