<%@page import="ekh.bean.MatriceBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="ekh.bean.AmministratoreBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
Collection<?> matrici=(Collection<?>) request.getAttribute("matrici");


if(matrici==null){
	response.sendRedirect(response.encodeRedirectURL("./VisualizzaMatriciServlet?action=admin"));
	return;
}
%>



<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/HomePage.css" rel="stylesheet">
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>
<title>Gestione Matrice Admin</title>
</head>
<body>


	<header class="header">
		<a href="<%=response.encodeURL("HomePage.jsp")%>" class="header__logo">EcoKnowHow</a>
		<ul class="header__menu ">
			<li class="header__menu__item"><a
				href="<%=response.encodeURL("GestioneMatriciAdmin.jsp")%>">Gestione
					Matrici</a></li>
			<li class="header__menu__item"><a
				href="${pageContext.request.contextPath}/GestionePacchettiAdmin.jsp">Gestione
					Pacchetti</a></li>
			<li class="header__menu__item"><a
				href="${pageContext.request.contextPath}/GestioneClientiAdmin.jsp">Gestione
					Clienti</a></li>

			<%
			if (admin == null) {
			%>
			<li class="header__menu__item"><a
				href="${pageContext.request.contextPath}/LoginUser.jsp">Login</a> <%
 				} else {
			 %>
				<a href=><%=admin.getUsername()%></a> <%
				 }
				 %>
 			</li>
		</ul>
	</header>

	
	<div class="canvas">	
		<button class="clearfix fixed"> <a  href="${pageContext.request.contextPath}/AggiungiMatriceAdmin.jsp"> Aggiungi</a></button>
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