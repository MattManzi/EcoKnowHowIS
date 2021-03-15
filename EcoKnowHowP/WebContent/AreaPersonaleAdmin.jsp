<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<%
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
AmministratoreBean adminP = (AmministratoreBean) request.getSession().getAttribute("Admin");
if (adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
}
%>
<html>
<head>
<title>Area Personale Admin</title>
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
	<div class="contenitore">
	<div class="canvas">
		<h1>Profilo</h1>
			<p>Username: <%=adminP.getUsername()%></p>
			<p>Email: <%=adminP.getEmail()%></p>
	
		<div id="pass">
			<p></p>
			<input id="modPass" onclick="mod('formEmail',this.id)" type="button" value="Modifica"></input>		
			<form id="formPass" class="hidden" action="<%=response.encodeURL("User?action=email") %>" method="post">
				<input type="text" name="dato" required="required">			
				<input type="submit" value="Salva">
			</form>
		</div>			



	</div>	
</div>	

	
	<%@ include file="Footer.jsp" %>
</body>
</html>