<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
	MatriceBean bean=(MatriceBean)request.getSession().getAttribute("matrice");
	Collection<?> parametri=(Collection<?>) request.getAttribute("parametri");
	
	
	if(bean==null){
		response.sendRedirect(response.encodeRedirectURL("GestioneMatriciAdmin.jsp"));
		return;
	}
	
	if(parametri==null){
		response.sendRedirect(response.encodeRedirectURL("VisualizzaParametriServlet?action=matrice&idMatrice="+bean.getId()));
		return;
	}
	
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/HomePage2.css" rel="stylesheet">
<link href="css/ModificaMatriceAdmin.css" rel="stylesheet">

<title>Insert title here</title>
<script>

function myFunctionNome() {
  var x = document.getElementById("formName");
  if (x.className === "hidden") {
    x.className = "show";
  } else {
    x.className = "hidden";
  }
  var x = document.getElementById("modNome");
  if(x.value=="Modifica") {
	  x.value="Annulla";
  } else {
	  x.value="Modifica"
  }
}
</script>

<script>

function myFunctionDescrizione() {
  var x = document.getElementById("formDescrizione");
  if (x.className === "hidden") {
    x.className = "show";
  } else {
    x.className = "hidden";
  }
  var x = document.getElementById("modDescrizione");
  if(x.value=="Modifica") {
	  x.value="Annulla";
  } else {
	  x.value="Modifica"
  }
}
</script>

<script>

function myFunctionSottotitolo() {
  var x = document.getElementById("formSottotitolo");
  if (x.className === "hidden") {
    x.className = "show";
  } else {
    x.className = "hidden";
  }
  var x = document.getElementById("modSottotitolo");
  if(x.value=="Modifica") {
	  x.value="Annulla";
  } else {
	  x.value="Modifica"
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
			<a href="<%=response.encodeURL("GestioneMatriciAdmin.jsp")%>">Gestione Matrici</a>		   	
			<a href="<%=response.encodeURL("GestionePacchettiAdmin.jsp")%>">Gestione Pacchetti</a>
			<a href="<%=response.encodeURL("GestioneClientiAdmin.jsp")%>">Gestione Clienti</a>
			<%if (admin == null){ %>
				<a href="<%=response.encodeURL("LoginAdmin.jsp")%>" class="active">Login</a>					
			<%}else{%>				
				<a href="<%=response.encodeURL("")%>" class="active"><%=admin.getUsername() %></a>	
			<%
			}
			%>
			<a href="javascript:void(0);" class="icon" onclick="myFunction()"><i class="fa fa-bars"></i></a>
		</div>	
	</header>


	<div class="contenitore">

		<h1>ModificaMatrice</h1>
		
		<div id="nome">
			<p style="display: inline-block;">Nome: <%=bean.getNome() %></p>
			<input id="modNome" onclick="myFunctionNome()" type="button" value="Modifica"></input>		
			<form id="formName" class="hidden" action="<%=response.encodeURL("ModificaMatriceServlet?action=nome") %>" method="post">
				<input type="text" name="dato">			
				<input type="submit" value="Salva">
			</form>
		</div>
		
		<div id="sottotitolo">
			<p style="display: inline-block;">Sottotitolo: <%=bean.getSottotitolo()%></p>
			<input id="modSottotitolo" onclick="myFunctionSottotitolo(" type="button" value="Modifica"></input>		
			<form id="formSottotitolo" class="hidden" action="<%=response.encodeURL("ModificaMatriceServlet?action=sottotitolo") %>" method="post">
				<input type="text" name="dato">			
				<input type="submit" value="Salva">
			</form>
		</div>		


		<div id="Descrizioni">
		<p style="display: inline-block;">Descrizione: <%=bean.getDescrizione()%></p>
		<input id="modDescrizione" onclick="myFunctionDescrizione()" type="button" value="Modifica"></input>		
		<form id="formDescrizione" class="hidden" action="<%=response.encodeURL("ModificaMatriceServlet?action=descrizione") %>" method="post">
			<input type="text" name="dato">			
			<input type="submit" value="Salva">
		</form>
	</div>
</div>

	
	<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>