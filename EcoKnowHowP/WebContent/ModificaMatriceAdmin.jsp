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
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/HomePage.css" rel="stylesheet">
<link href="css/ModificaMatriceAdmin.css" rel="stylesheet">

<title>Insert title here</title>
</head>
<body>
	<header class="header clearfix fixed">
		<a href="<%=response.encodeURL("HomePageAdmin.jsp")%>"  class="header__logo"><img alt="" src="logo1.png">EcoKnowHow</a>
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
	<div class="contenitore">


	
	
	
	
	</div>
	
	
	<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>