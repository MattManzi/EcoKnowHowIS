<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	ClienteBean utente=(ClienteBean) request.getSession().getAttribute("Utente");	
	MatriceBean matrice=(MatriceBean) request.getSession().getAttribute("SelectMatrice");	
	if(utente != null && userRoles != null && userRoles.booleanValue()) {
		if(matrice==null){
			response.sendRedirect("SceltaMatriceCliente.jsp");
			return;
		}
	}else{
		response.sendRedirect("LoginUser.jsp");
		return;
	}
	/*
		In questa pagina il cliente potra scegliere tra tre tipi di pacchetti:
			tipo								Servlet
			standar								VisualizzaPacchettiServlet?tipo=standard
			analiti								VisualizzaPacchettiServlet?tipo=analitico
			o creare un nuovo pacchetto			AggiungiPacchettoServlet
	
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- NAV -->
	<div id="main">	
		<div class="card">	
			<div class="container">
				<h1><b>Crea un nuovo pacchetto</b></h1>
				<p>Inserisci i parametri all'interno del nuovo pacchetto</p>
			</div>
		</div>
		<div class="card">	
			<div class="container">
				<h1><b>Pacchetti Analitici</b></h1>
				<p>Seleziona un pacchetto creato per un precedente piano</p>
			</div>
		</div>
		<div class="card">	
			<div class="container">
				<a href="<%=response.encodeURL("VisualizzaPacchettiServlet?tipo=standard") %>" >Pacchetti Standard</a>
				<p>Seleziona un pacchetto proposto da noi!</p>
			</div>
		</div>	
	</div>
	<!-- FOO -->
</body>
</html>