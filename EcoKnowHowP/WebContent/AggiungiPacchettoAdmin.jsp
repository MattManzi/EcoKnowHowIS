<%@page import="java.util.Collection"%>
<%@page import="ekh.bean.AmministratoreBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 



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
<link href="css/HomePage.css" rel="stylesheet">
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<title>Insert title here</title>
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
				 %> <a href=><%=admin.getUsername()%></a> <%
 			}%>
 			</li>
		</ul>
	</header>
	<div class="canvas">
			<h1>Aggiungi Pacchetto </h1>
	
	<form action="AggiuntaMatriceServlet" method="post">
	<table id="tableMatriciAdmin">
		<tr>
			<td>Nome </td>	
			<td><input type="text" name="nome" maxlength="30"></td>		
		</tr>

		<tr>
			<td>Sottotitolo </td>
			<td><input type="text" name="sottotitolo" maxlength="30"></td>
		</tr>
		
		
		<tr>
			<td>Descrizione </td>	
			<td><input type="text" name="descrizione" maxlength="30"></td>			
		</tr>
	
		<tr> 
			<td colspan="2"><input type="submit" value="CONFERMA">
		</tr>
	</table>
	</form>
	</div>

</body>
</html>