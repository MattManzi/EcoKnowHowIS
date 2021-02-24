<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*" %>
<!DOCTYPE html>
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
if ((admin == null) || (adminRoles == null) || (!adminRoles.booleanValue())) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}

Collection<?> clienti=(Collection<?>)request.getAttribute("clienti"); 

if(clienti==null){
	response.sendRedirect(response.encodeRedirectURL("./VisualizzaClientiServlet"));
	return;
}

/*
Lista con tutti i clienti.
In questa pagina sarà possibile rimuovere i clienti dal db.
Cliccare su un cliente per accedere al suo storico piani.

Servlet Necessarie:
	VisualizzaClientiServlet - OK
	SelectClientiAdminServlet - OK
	RimuoviClienteAdminServlet - X
*/

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
<title>Gestione Clienti Admin</title>
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
 %> <a href=><%=admin.getUsername()%></a> <%
 }
 %></li>
		</ul>
	</header>




</body>
</html>