<%@page import="java.util.Collection"%>
<%@page import="ekh.bean.AmministratoreBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
Collection<?> matrici=(Collection<?>) request.getAttribute("matrici");


if(matrici==null){
	response.sendRedirect(response.encodeRedirectURL("./VisualizzaMatriciServlet"));
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
<title>Gestione Pacchetti Admin</title>
</head>
<body>
		<header class="header clearfix fixed">
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
	
	
	
	
	
	
	
</body>
</html>