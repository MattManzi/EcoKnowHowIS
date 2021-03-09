<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="ekh.bean.*"%>
<!DOCTYPE html>

<%
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");

PacchettoBean pacchetto=null;
MatriceBean matrice=null;
Collection<?> parametri=null;

if (adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}else{
	pacchetto=(PacchettoBean)request.getSession().getAttribute("pacchetto");
	if(pacchetto==null){
		response.sendRedirect(response.encodeRedirectURL("./Pacchetto?action=visualizza"));
		return;
	}else{
		 matrice=(MatriceBean)request.getSession().getAttribute("matrice");
		 parametri=(Collection<?>)request.getSession().getAttribute("parametri");
	}
}

%>

<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/ModificaPacchettoAdmin.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>
<title>Modifica Pacchetto Admin</title>
<script>
function mod(form, input) {
  var x = document.getElementById(form);
  if (x.className === "hidden") {
    x.className = "show";
  } else {
    x.className = "hidden";
  }
  var x = document.getElementById(input);
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
	
	<div id="main">
		<div class="contenitore">
			<h1>Modifica Pacchetto</h1>		
			<div id="nomeMatrice">
				<p>Matrice di Riferimento: <%=matrice.getNome()%></p>
			</div>		
			<div id="nome">
				<p>Nome: <%=pacchetto.getNome() %></p>
				<input id="modNome" onclick="mod('formName', this.id)" type="button" value="Modifica"></input>		
				<form id="formName" class="hidden" action="<%=response.encodeURL("Pacchetto?action=nome") %>" method="post">
					<input type="text" name="dato">			
					<input type="submit" value="Salva">
				</form>
			</div>	
			<div id="Descrizioni">
				<p>Descrizione: <%=pacchetto.getDescrizione()%></p>
				<input id="modDescrizione" onclick="mod('formDescrizione', this.id)" type="button" value="Modifica"></input>		
				<form id="formDescrizione" class="hidden" action="<%=response.encodeURL("Pacchetto?action=descrizione") %>" method="post">
					<input type="text" name="dato">			
					<input type="submit" value="Salva">
				</form>
			</div>
			<div id="tipo">
				<p>Tipo di Pacchetto: <%=pacchetto.getTipo()%></p>
			</div>
			<div id="username">
				<p>Creatore del pacchetto: <%=pacchetto.getUsername()%></p>
			</div>
			<div id="prezzo">
				<p>Prezzo: <%=pacchetto.getPrezzo()%></p>
				<input id="modPrezzo" onclick="mod('formPrezzo', this.id)" type="button" value="Modifica"></input>		
				<form id="formPrezzo" class="hidden" action="<%=response.encodeURL("Pacchetto?action=prezzo") %>" method="post">
					<input type="text" name="dato">			
					<input type="submit" value="Salva">
				</form>
			</div>
		</div>	
		<div id="parametri">
			<div id="addPar">
				<form id="formAddParametro" action="Pacchetto?action=addParam" method="post">	
					<table>			
						<tr>
							<td><label for="id">Parametro:</label></td> 
							<td><select name="id">
								<option value="-">-</option>
								<% if(parametri != null && parametri.size()>0){
									Iterator<?> it=parametri.iterator();
									while(it.hasNext()){
										ParametroBean parametro=(ParametroBean) it.next();
								%>
										<option value="<%=parametro.getId()%>"><%=parametro.getNome()%> - <%=parametro.getSku()%></option>
								<%	
									}
								}
								%>
								</select></td>
						</tr>
						<tr>	
							<td colspan="2"><input type="submit" value="Aggiungi"></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="canvas" id="allPar">	
				<table>
					<tr>
						<th>Nome </th> 
						<th>Sku</th>
						<th>Limite Emissione</th>
						<th>Misura </th>
						<th>Prezzo </th>
						<th></th>
					</tr>
					
					<% if(pacchetto.getContenuto().size()>0){
						for(ParametroBean parametro:pacchetto.getContenuto()){						
					%>
							<tr> 
								<td><%=parametro.getNome()%></td>	
								<td><%=parametro.getSku()%></td>
								<td><%=parametro.getLimiteEmissione()%></td>
								<td><%=parametro.getuMisura()%></td>
								<td><%=parametro.getPrezzo()%></td>
								<td><button class="bott_rimuovi" id="cancella" onclick="canellcaParametroPacchetto(<%=parametro.getId()%>)">X</button></td>			
							</tr>
					<%	
						}
					}
					 %>
					<tr>
						<td colspan="3"></td>
						<td>Prezzo Totale:</td>
						<td><%=pacchetto.getPrezzo() %></td>
						<td></td>
					</tr>
				</table>
			</div>
		</div>	
	</div>
		
<%@ include file="Footer.jsp" %>	
</body>
</html>