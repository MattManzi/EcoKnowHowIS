<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>

<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
PacchettoBean pacchetto=(PacchettoBean) request.getSession().getAttribute("pacchetto");
if (admin == null || adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}else{
	if(pacchetto==null){
		response.sendRedirect(response.encodeRedirectURL("./PacchettoControl?action=visualizza"));
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
	
	
		<%
				int prezzo=0;
				if(pacchetto.getContenuto().size()== 0){
			%>				
					<p>Il tuo pacchetto è vuoto. Aggiungi i parametri per comporre il pacchetto</p>
						
			<%
				}else{
					Iterator<?> it = pacchetto.getContenuto().iterator();
					while (it.hasNext()) {
						ParametroBean bean = (ParametroBean) it.next();
						prezzo+= bean.getPrezzo();
			%>	
						<div>
						<a href="<%=response.encodeURL("PacchettoControl?action=componi&function=rimuovi&id="+bean.getId())%>">X</a>
							<a href="" > <%=bean.getNome()%>  </a> 	
							<a href="" >   <%=bean.getPrezzo()%> </a> 		
						</div>		
			<%	
				}  
			%>
				<p> Prezzo:<%=prezzo%> Euro</p>
				<a href="<%=response.encodeURL("PacchettoControl?action=salva")%>">Procedi</a>
			<%		
				}
			%>
	
	

		<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>