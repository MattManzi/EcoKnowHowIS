<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>

<%
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
if (adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}
%>

<html>
<head>
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<title>Aggiungi Matrice</title>
</head>
<body>
	<%@ include file="NavAdmin.jsp" %>
	
	<div class="canvas">
		<h1>Aggiungi Matrice </h1>	
		<form action="Matrice?action=aggiungi" method="post">
			<table id="tableMatriciAdmin">
				<tr>
					<td><label for="nome">Nome:</label></td>	
					<td><input type="text" name="nome" maxlength="30"></td>		
				</tr>		
				<tr>
					<td><label for="sottotitolo">Sottotitolo:</label></td>
					<td><input type="text" name="sottotitolo" maxlength="30"></td>
				</tr>				
				<tr>
					<td><label for="nota">Nota:</label></td>	
					<td><input type="text" name="nota" maxlength="30"></td>			
				</tr>
				<tr> 
					<td colspan="2"><input type="submit" value="CONFERMA">
				</tr>
			</table>
		</form>
	</div>
	
	<%@ include file="Footer.jsp" %>	
</body>
</html>