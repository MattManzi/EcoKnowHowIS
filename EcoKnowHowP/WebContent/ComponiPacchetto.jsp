<%@page import="ekh.control.PacchettoControl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
    
<%
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
Collection<?> parametri=(Collection<?>) request.getAttribute("parametri");
PacchettoBean pacchetto=(PacchettoBean) request.getSession().getAttribute("creaPacchetto");

if((admin != null && adminRoles != null && adminRoles.booleanValue()) 
		|| (user != null && userRoles!= null && userRoles.booleanValue())){
	if(parametri == null){
		response.sendRedirect(response.encodeRedirectURL("./ParametroControl?action=componi"));
		return;
	}
}else{
	response.sendRedirect("./HomePage.jsp");
	return;
}

/*
Lista con tutti i parametri appartenenti alla matrice scelta in fase di creazione del pacchetto.

Servlet Necessarie:
	VisualizzaParametriServlet?action=pacchetto - OK
	ComponiPacchettoServlet?action=aggiungi - X
	ComponiPacchettoServlet?action=rimuovi - X
	ComponiPacchettoServlet?action=cancella - X
	AggiuntaPacchettoServlet?action=salva - OK
	
Se il pacchetto viene salvato, il cliente dovrà recarsi in scelta pacchetti analitico per selezionarlo.
*/

%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/Template.css" rel="stylesheet">
<link href="css/ComponiPacchetto.css" rel="stylesheet">
<title>Insert title here</title>
<script>
function myFunction() {
  var x = document.getElementById("nav");
  if (x.className === "nav") {
    x.className += " responsive";
  } else {
    x.className = "nav";
  }
}
</script>
</head>
<body>
	<header class="header" id="header">
		<div class="logo">
			<a href="<%=response.encodeURL("HomePageAdmin.jsp")%>"><img src="./img/logo.png"></a>
		</div>
		<div class="nav" id="nav">
		<a href="javascript:void(0);" class="icon" onclick="myFunction()"><i class="fa fa-bars"></i></a>
		<div class="invisibile">
		<p><br></p>
		<p><br></p>
		<p><br></p>
		</div>
			<%if (admin == null){ %>
				<a href="<%=response.encodeURL("LoginAdmin.jsp")%>" class="active">Login</a>					
			<%}else{%>				
				<a href="<%=response.encodeURL("")%>" class="active"><%=admin.getUsername() %></a>	
			<%
			}
			%>
			<a href="<%=response.encodeURL("GestioneClientiAdmin.jsp")%>">Gestione Clienti</a>
			<a href="<%=response.encodeURL("GestioneMatriciAdmin.jsp")%>">Gestione Matrici</a>		   	
			<a href="<%=response.encodeURL("GestionePacchettiAdmin.jsp")%>">Gestione Pacchetti</a>
		</div>	
	</header>
<div class="divTable">
	<div class="divRow">
		<div class="contenitore_parametri">
		<%
			if ( parametri.size() > 0) {
			Iterator<?> it = parametri.iterator();
			while (it.hasNext()) {
				ParametroBean bean = (ParametroBean) it.next();
		%>
				<div class="prodottoCatalogo">
					<h2 class="nome__prodotto" > <a href="" > <%=bean.getNome()%> </a> </h2> 
					<h3 class="nome__prodotto" > <a href="" > <%=bean.getSku()%> </a> </h3>
					<h3 class="nome__prodotto" > <a href="" > <%=bean.getPrezzo()%> </a> </h3>
					<button>Dettagli</button>
					<a href="<%=response.encodeURL("PacchettoControl?action=componi&function=aggiungi&id="+bean.getId())%>">Aggiungi </a>
				</div>			
		<%
				}
			}
		%>
		</div >
		<div class="carrello ">
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
		</div>
	</div>
</div>
	
	<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>