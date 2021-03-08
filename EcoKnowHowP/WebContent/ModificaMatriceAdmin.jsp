<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
	MatriceBean bean=(MatriceBean)request.getSession().getAttribute("matrice");
	Collection<?> parametri=(Collection<?>) request.getAttribute("parametri");
	
	if ( adminRoles == null || !adminRoles.booleanValue()) {
		response.sendRedirect("./LoginAdmin.jsp");
		return;
	}else{
		if(bean==null){
			response.sendRedirect("./GestioneMatriciAdmin.jsp");
			return;
		}
		
		if(parametri==null){
			response.sendRedirect(response.encodeRedirectURL("Parametro?action=visualizza&jsp=matrice"));
			return;
		}
	}
	
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/ModificaMatriceAdmin.css" rel="stylesheet">

<title>Modifica Matrice Admin</title>

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
	
	<%@ include file="NavAdmin.jsp" %>


	<div class="contenitore">

		<h1>ModificaMatrice</h1>
		
		<div id="nome">
			<p style="display: inline-block;">Nome: <%=bean.getNome() %></p>
			<input id="modNome" onclick="myFunctionNome()" type="button" value="Modifica"></input>		
			<form id="formName" class="hidden" action="<%=response.encodeURL("Matrice?action=nome") %>" method="post">
				<input type="text" name="dato">			
				<input type="submit" value="Salva">
			</form>
		</div>
		
		<div id="sottotitolo">
			<p style="display: inline-block;">Sottotitolo: <%=bean.getSottotitolo()%></p>
			<input id="modSottotitolo" onclick="myFunctionSottotitolo()" type="button" value="Modifica"></input>		
			<form id="formSottotitolo" class="hidden" action="<%=response.encodeURL("Matrice?action=sottotitolo") %>" method="post">
				<input type="text" name="dato">			
				<input type="submit" value="Salva">
			</form>
		</div>		


		<div id="Descrizioni">
		<p style="display: inline-block;">Descrizione: <%=bean.getDescrizione()%></p>
		<input id="modDescrizione" onclick="myFunctionDescrizione()" type="button" value="Modifica"></input>		
		<form id="formDescrizione" class="hidden" action="<%=response.encodeURL("Matrice?action=descrizione") %>" method="post">
			<input type="text" name="dato">			
			<input type="submit" value="Salva">
		</form>
	</div>
</div>

<div>
	<form action="ParametroControl?action=addParM" method="post">
		<table>
		
			<tr>
				<td>Nome parametro: </td> 
				<td><input type="text" name="nome" maxlength="30"></td>
			</tr>
			<tr>	
				<td>Sku: </td>
				<td><input type="text" name="sku" maxlength="30"></td>
			</tr>
			<tr>
				<td>Limite Emissione: </td>
				<td><input type="text" name="limiteEmissione" maxlength="30"></td>
			</tr>
			<tr>
				<td>Unità di Misura </td>
				<td><input type="text" name="uMisura" maxlength="30"></td>
			</tr>
			<tr>
				<td>Prezzo: </td>
				<td><input type="text" name="prezzo" maxlength="30"></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="CONFERMA">
			</tr>
			
		</table>
	</form>	
</div>

	<div class="canvas">	
		<table id="tableMatriciAdmin">
			<tr>
				<th>ID</th>
				<th>Nome </th> 
				<th>Sku</th>
				<th>Campione </th>
				<th>Campionamento </th>
				<th>Misura </th>
				<th>Prezzo </th>
			</tr>
			
			<% if(parametri != null && parametri.size()>0){
				Iterator<?> it=parametri.iterator();
				while(it.hasNext()){
					ParametroBean parametro=(ParametroBean) it.next();
			%>
			<tr> 
				<td > <%=parametro.getId()%> </td>
				<td > <%=parametro.getNome()%> </td>	
				<td > <%=parametro.getSku()%> </td>
				<td > <%=parametro.getCampione()%> </td>
				<td > <%=parametro.getCampionamento()%> </td>
				<td > <%=parametro.getuMisura()%> </td>
				<td > <%=parametro.getPrezzo()%> </td>
				<td> <button class="bott_rimuovi" id="cancella" onclick="cancellaParMatrice(<%=parametro.getId()%>)">X</button></td>			
			</tr>
			<%	
				}
			}
			 %>
		</table>
	</div>


<div>
<table>

	<tr>
		<th>
		<th>
		<th>
	</tr>
	
	<tr>
		<td></td>
		<td></td>
		<td></td>
	
	</tr>
</table>
</div>
	
<%@ include file="Footer.jsp" %>>
</body>
</html>