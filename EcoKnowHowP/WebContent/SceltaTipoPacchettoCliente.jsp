<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	ClienteBean utente=(ClienteBean) request.getSession().getAttribute("User");	
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
<title>Insert title here</title>
</head>
<body>
<!-- NAV -->
	<div id="main">	
		<div class="card">	
			<div class="container">
				<a href="<%=response.encodeURL("AggiungiPacchetto.jsp") %>" >Crea un nuovo pacchetto</a>
				<p>Inserisci i parametri all'interno del nuovo pacchetto</p>
			</div>
		</div>
		<div class="card">	
			<div class="container">
				<a href="<%=response.encodeURL("Pacchetto?action=visualizza&tipo=analitico") %>" >Pacchetti Analitici</a>
				<p>Seleziona un pacchetto creato per un precedente piano</p>
			</div>
		</div>
		<div class="card">	
			<div class="container">
				<a href="<%=response.encodeURL("Pacchetto?action=visualizza&tipo=standard") %>" >Pacchetti Standard</a>
				<p>Seleziona un pacchetto proposto da noi!</p>
			</div>
		</div>	
	</div>
	<!-- FOO -->
</body>
</html>