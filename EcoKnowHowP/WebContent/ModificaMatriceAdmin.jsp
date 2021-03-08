<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
	MatriceBean matrice=null;
	Collection<?> parametri=null;
	
	if (adminRoles != null || adminRoles.booleanValue()) {
		matrice=(MatriceBean)request.getSession().getAttribute("matrice");
		parametri=(Collection<?>) request.getAttribute("parametri");
		if(matrice==null){
			response.sendRedirect("./GestioneMatriciAdmin.jsp");
			return;
		}
		if(parametri==null){
			response.sendRedirect(response.encodeRedirectURL("Parametro?action=visualizza&jsp=matrice"));
			return;
		}
	}else{
		response.sendRedirect("./LoginAdmin.jsp");
		return;
	}	
%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/ModificaMatriceAdmin.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>
<title>Modifica Matrice Admin</title>
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
			<h1>ModificaMatrice</h1>		
			<div id="nome">
				<p>Nome: <%=matrice.getNome() %></p>
				<input id="modNome" onclick="mod('formName',this.id)" type="button" value="Modifica"></input>		
				<form id="formName" class="hidden" action="<%=response.encodeURL("Matrice?action=nome") %>" method="post">
					<input type="text" name="dato" required="required">			
					<input type="submit" value="Salva">
				</form>
			</div>
			
			<div id="sottotitolo">
				<p>Sottotitolo: <%=matrice.getSottotitolo()%></p>
				<input id="modSottotitolo" onclick="mod('formSottotitolo',this.id)" type="button" value="Modifica"></input>		
				<form id="formSottotitolo" class="hidden" action="<%=response.encodeURL("Matrice?action=sottotitolo") %>" method="post">
					<input type="text" name="dato" required="required">			
					<input type="submit" value="Salva">
				</form>
			</div>		
	
	
			<div id="nota">
				<p>Nota: <%=matrice.getNota()%></p>
				<input id="modNota" onclick="mod('formNota',this.id)" type="button" value="Modifica"></input>		
				<form id="formNota" class="hidden" action="<%=response.encodeURL("Matrice?action=nota") %>" method="post">
					<input type="text" name="dato" required="required">			
					<input type="submit" value="Salva">
				</form>
			</div>
		</div>	
		<div id="parametri">
			<div id="addPar">
				<form id="formAddParametro" action="Parametro?action=addParM" method="post">
					<table>			
						<tr>
							<td>Nome parametro: </td> 
							<td><input type="text" name="nome" maxlength="100" required="required"></td>
						</tr>
						<tr>	
							<td>Sku: </td>
							<td><input type="text" name="sku" maxlength="20" required="required"></td>
						</tr>
						<tr>
							<td>Limite Emissione: </td>
							<td><input type="text" name="limiteEmissione" maxlength="20" required="required"></td>
						</tr>
						<tr>
							<td>Unità di Misura </td>
							<td><input type="text" name="uMisura" maxlength="20" required="required"></td>
						</tr>
						<tr>
							<td>Prezzo: </td>
							<td><input type="number" name="prezzo" id="addPrezzo" min="0.01" max="999" step="0.01" required="required"></td>
						</tr>				
						<tr>
							<td colspan="2"><input type="submit" value="CONFERMA">
						</tr>				
					</table>
				</form>	
			</div>
		
			<div class="canvas" id="allPar">	
				<table id="tableMatriciAdmin">
					<tr>
						<th>Nome </th> 
						<th>Sku</th>
						<th>Limite Emissione</th>
						<th>Misura </th>
						<th>Prezzo </th>
						<th></th>
					</tr>
					
					<% if(parametri != null && parametri.size()>0){
						Iterator<?> it=parametri.iterator();
						while(it.hasNext()){
							ParametroBean parametro=(ParametroBean) it.next();
					%>
					<tr> 
						<td > <%=parametro.getNome()%> </td>	
						<td > <%=parametro.getSku()%> </td>
						<td > <%=parametro.getLimiteEmissione()%> </td>
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
		</div>
	</div>
	<%@ include file="Footer.jsp" %>>
</body>
</html>