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
<link href="css/HomePage2.css" rel="stylesheet">
<link href="css/ModificaMatriceAdmin.css" rel="stylesheet">
<title>Insert title here</title>
<script>
function myFunction() {
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
	
	<h1>ModificaMatrice</h1>
	
	<div id="nome">
		<p style="display: inline-block;">Nome: <%=bean.getNome() %></p>
		<input id="modNome" onclick="myFunction()" type="button" value="Modifica"></input>		
		<form id="formName" class="hidden" action="<%=response.encodeURL("ModificaMatriceServlet?action=nome&id="+bean.getId()) %>" method="post">
			<input type="text" name="dato">			
			<input type="submit" value="Salva">
		</form>
	</div>
	
	
	
	<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>