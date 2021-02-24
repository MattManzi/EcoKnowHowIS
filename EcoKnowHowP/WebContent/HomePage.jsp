<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, ekh.bean.*"%>
<!DOCTYPE html>
<%
ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("Cliente"); 
%>
<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/HomePage.css" rel="stylesheet">
<title>EcoKnowHow</title>
</head>
<body>
	<header class="header clearfix fixed">
		<a href="<%=response.encodeURL("HomePage.jsp")%>"  class="header__logo"> <img alt="" src="logo.png"> </a>
			<ul class="header__menu ">    
		    	<li class="header__menu__item"><a href="<%=response.encodeURL("SceltaMatriceUser.jsp")%>">Crea il tuo Piano</a> </li>
				<li class="header__menu__item"><a href="${pageContext.request.contextPath}/Pacchetti.jsp">I nostri Pacchetti</a></li> 
				<li class="header__menu__item"><a href="${pageContext.request.contextPath}/jsp/ChiSiamo.jsp">Chi Siamo?</a></li>
				
				<%if (cliente == null){ %>
					<li class="header__menu__item"><a href="${pageContext.request.contextPath}/LoginUser.jsp">Login</a>					
				<%}else{%>				 
					<a href=><%=cliente.getUsername()%></a> 	
				<%
				}
				%>
				</li>
			</ul> 								
	</header>
	<section class="img_HomePage"></section>		
	
	<section class="banner clearfix">	
		<div class="contenitore_card">
			<div class="table-cell">
				<div class="card">
					<a href="${pageContext.request.contextPath}/CreaPiano.jsp">Crea il Piano</a>
					<p>Some text</p>
					<p>Some text</p>
				</div>
			</div>
			<div class="table-cell">
				<div class="card">
			    	<a href="${pageContext.request.contextPath}/RecuperaPiano.jsp">Recupare il Piano</a>
			   		<p>Some text</p>
			  		<p>Some text</p>
				</div>
			</div>
			<div class="table-cell">
				<div class="card">
					<a href="${pageContext.request.contextPath}//Pacchetti.jsp">I Pacchetti</a>
					<p>Some text</p>
					<p>Some text</p>
				</div>
			</div>
		</div>
	</section>
	<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>