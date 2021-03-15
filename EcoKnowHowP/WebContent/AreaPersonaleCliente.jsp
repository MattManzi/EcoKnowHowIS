<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<%
Boolean userRoles = (Boolean) session.getAttribute("userRoles");
ClienteBean clienteP = (ClienteBean) request.getSession().getAttribute("User"); 
if (userRoles == null || !userRoles.booleanValue()) {
	response.sendRedirect("./LoginUser.jsp");
}
%>
<html>
<head>
<link href="css/AreaPersonale.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>

<title>Area peronale Cliente</title>
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
	<%@ include file="NavUser.jsp" %>
	
<div class="contenitore">
	<div class="canvas">
		<h1>Profilo</h1>
			<p>Nome: <%=clienteP.getNome()%></p>
			<p>Cognome: <%=clienteP.getCognome()%></p>
			<p>CF o Partita Iva: <%=clienteP.getIvaCF()%></p>
			<p>SDI: <%=clienteP.getSdi()%></p>
			<p>Settore: <%=clienteP.getSettore()%></p>		
		<div id="email">
			<p>Email: <%=clienteP.getEmail()%></p>
			<input id="modEmail" onclick="mod('formEmail',this.id)" type="button" value="Modifica"></input>		
			<form id="formEmail" class="hidden" action="<%=response.encodeURL("User?action=email") %>" method="post">
				<input type="text" name="dato" required="required">			
				<input type="submit" value="Salva">
			</form>
		</div>			
		<div id="telefono">
			<p>Telefono: <%=clienteP.getTelefono()%></p>
			<input id="modTelefono" onclick="mod('formTelefono',this.id)" type="button" value="Modifica"></input>		
			<form id="formTelefono" class="hidden" action="<%=response.encodeURL("User?action=telefono") %>" method="post">
				<input type="text" name="dato" required="required">			
				<input type="submit" value="Salva">
			</form>
		</div>
		<div id="pec">
			<p>Pec: <%=clienteP.getPec()%></p>
			<input id="modPec" onclick="mod('formPec',this.id)" type="button" value="Modifica"></input>		
			<form id="formPec" class="hidden" action="<%=response.encodeURL("User?action=pec") %>" method="post">
				<input type="text" name="dato" required="required">			
				<input type="submit" value="Salva">
			</form>
		</div>
		<div id="indirizzo">
			<p>Indirizzo: <%=clienteP.getIndirizzo()%></p>
			<input id="modIndirizzo" onclick="mod('formIndirizzo',this.id)" type="button" value="Modifica"></input>		
			<form id="formIndirizzo" class="hidden" action="<%=response.encodeURL("User?action=indirizzo") %>" method="post">
				<input type="text" name="dato" required="required">			
				<input type="submit" value="Salva">
			</form>
		</div>
		<div id="sociale">
			<p>Ragione Sociale: <%=clienteP.getRagioneSociale()%></p>
			<input id="modSociale" onclick="mod('formSociale',this.id)" type="button" value="Modifica"></input>		
			<form id="formSociale" class="hidden" action="<%=response.encodeURL("User?action=ragioneSociale") %>" method="post">
				<input type="text" name="dato" required="required">			
				<input type="submit" value="Salva">
			</form>
		</div>
	</div>	
</div>	
	<%@ include file="Footer.jsp" %>
</body>
</html>