<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>

<%
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");

PacchettoBean bean=null;
MatriceBean matrice=null;

if ( adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}else{
	bean=(PacchettoBean)request.getSession().getAttribute("pacchetto");
	if(bean==null){
		response.sendRedirect(response.encodeRedirectURL("./Pacchetto?action=visualizza"));
		return;
	}else{
		 matrice=(MatriceBean)request.getSession().getAttribute("matrice");
	}
}


%>

<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<title>Modifica Pacchetto Admin</title>

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

</head>

<body>

<%@ include file="NavAdmin.jsp" %>



<div class="contenitore">

		<h1>Modifica Parametro</h1>
		
		<div id="nomeMatrice">
			<p style="display: inline-block;">Matrice di Riferimento: <%=matrice.getNome()%></p>
		</div>		
		
	
		<div id="nome">
			<p style="display: inline-block;">Nome: <%=bean.getNome() %></p>
			<input id="modNome" onclick="myFunctionNome()" type="button" value="Modifica"></input>		
			<form id="formName" class="hidden" action="<%=response.encodeURL("Pacchetto?action=nome") %>" method="post">
				<input type="text" name="dato">			
				<input type="submit" value="Salva">
			</form>
		</div>	

	<div id="Descrizioni">
		<p style="display: inline-block;">Descrizione: <%=bean.getDescrizione()%></p>
		<input id="modDescrizione" onclick="myFunctionDescrizione()" type="button" value="Modifica"></input>		
		<form id="formDescrizione" class="hidden" action="<%=response.encodeURL("Pacchetto?action=descrizione") %>" method="post">
			<input type="text" name="dato">			
			<input type="submit" value="Salva">
		</form>
	</div>
	
	
	<div id="tipo">
		<p style="display: inline-block;">Tipo di Pacchetto: <%=bean.getTipo()%></p>
	</div>
	
	
	<div id="username">
		<p style="display: inline-block;">Creatore del pacchetto: <%=bean.getUsername()%></p>
	</div>
</div>


		<%
				int prezzo=0;
				if(bean.getContenuto().size()== 0){
			%>				
					<p>Il tuo pacchetto è vuoto. Aggiungi i parametri per comporre il pacchetto</p>
						
			<%
				}else{
					Iterator<?> it = bean.getContenuto().iterator();
					while (it.hasNext()) {
						ParametroBean parametro = (ParametroBean) it.next();
						prezzo+= bean.getPrezzo();
			%>	
						<div>
						<a href="<%=response.encodeURL("Pacchetto?action=componi&function=rimuovi&id="+bean.getId())%>">X</a>
							<a href="" > <%=parametro.getNome()%>  </a> 	
							<a href="" >   <%=parametro.getPrezzo()%> </a> 		
						</div>		
			<%	
					} 	
				}
			%>
	

		




<%@ include file="Footer.jsp" %>	
</body>
</html>