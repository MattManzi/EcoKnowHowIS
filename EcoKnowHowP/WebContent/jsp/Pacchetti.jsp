<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, ekh.bean.*"%>
    <%
		ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("Cliente"); 
	%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="../css/HomePage.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>EcoKnowHow</title>
</head>
<body>
	<header class="header clearfix">

			<a href="<%=response.encodeURL("HomePage.jsp")%>"  class="header__logo">EcoKnowHow</a>
	
			<ul class="header__menu ">    
		    	<li class="header__menu__item"> <a href="<%=response.encodeURL("CreaPiano.jsp")%>">Crea il tuo Piano</a>  </li>
					<li class="header__menu__item"> <a href="<%=response.encodeURL("Pacchetti.jsp")%>">I nostri Pacchetti</a></li> 
					<li class="header__menu__item"><a href="<%=response.encodeURL("ChiSiamo.jsp")%>">Chi Siamo?</a></li>
				<%if (cliente == null){ %>
						
					<li class="header__menu__item"><a href="<%=response.encodeURL("Login.jsp")%>"> Login </a>
					
					<%}else{%>				 
					<a href=> <%=cliente.getUsername()%> </a> 
	
				<%
				}
				%>
				 </li>
			</ul> 
								
	</header>
								

</body>
</html>