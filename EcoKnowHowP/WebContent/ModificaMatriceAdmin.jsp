<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*"%>
<%
	AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
	MatriceBean bean=(MatriceBean)request.getSession().getAttribute("matrice");
	
	if(bean==null){
		response.sendRedirect(response.encodeRedirectURL("GestioneMatriciAdmin.jsp"));
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/HomePage.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<header class="header clearfix fixed">
		<a href="<%=response.encodeURL("HomePageAdmin.jsp")%>"  class="header__logo">EcoKnowHow</a>
			<ul class="header__menu ">    
		    	<li class="header__menu__item"><a href="<%=response.encodeURL("GestioneMatriciAdmin.jsp")%>">Gestione Matrici</a> </li>
				<li class="header__menu__item"><a href="<%=response.encodeURL("GestionePacchettiAdmin.jsp")%>">Gestione Pacchetti</a></li> 
				<li class="header__menu__item"><a href="<%=response.encodeURL("GestioneClientiAdmin.jsp")%>">Gestione Clienti</a></li>
				
				<%if (admin == null){ %>
					<li class="header__menu__item"><a href="<%=response.encodeURL("LoginAdmin.jsp")%>">Login</a>					
				<%}else{%>				 
					<a href=><%=admin.getUsername()%></a> 	
				<%
				}
				%>
				</li>
			</ul> 								
	</header>
	
	<h1>ModificaMatrice</h1>
	
	<table>
		<tr>
			<td><label for="nome">Nome:</label></td>
			<td><%=bean.getNome() %></td>
			<td><button>modifica</button></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	
	</table>
	
	
	
	<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>