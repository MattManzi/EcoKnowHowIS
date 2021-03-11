<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<%
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
Collection<?> matrici=(Collection<?>) request.getAttribute("matrici");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
if((adminRoles != null && adminRoles.booleanValue()) 
		|| (userRoles!= null && userRoles.booleanValue())){
	if(matrici == null){
		response.sendRedirect(response.encodeRedirectURL("./Matrice?action=visualizza&jsp=pacchetto"));
		return;
	}
}else{
	response.sendRedirect("./HomePage.jsp");
	return;
}
%>
<html>
<head>
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<title>Aggiungi Pacchetto</title>
</head>
<body>
	<%if(adminRoles != null){%>
		<%@ include file="NavAdmin.jsp" %>
	<%}else{%>
		<%@ include file="NavUser.jsp" %>
	<%}%>

	<div class="canvas">
		<h1>Aggiungi Pacchetto </h1>	
		<form action="Pacchetto?action=crea" method="post">
			<table id="tableMatriciAdmin">
				<tr>
					<td><label for="idMatrice">Matrice:</label></td>	
					<td><select name="idMatrice" >		
							<%if(matrici != null && matrici.size()>0){
								Iterator<?> it=matrici.iterator();
								while(it.hasNext()){
									MatriceBean bean=(MatriceBean) it.next();
							%>
									<option value="<%=bean.getId()%>" selected="selected"><%=bean.getSottotitolo()%> </option>
							<%	}
					
							}%>
						</select>
					</td>
				</tr>		
				<tr>
					<td><label for="nome">Nome:</label></td>	
					<td><input type="text" name="nome" maxlength="30"></td>		
				</tr>
				<tr>
					<td><label for="descrizione">Descrizione:</label></td>	
					<td><input type="text" name="descrizione" maxlength="30"></td>			
				</tr>
				<tr> 
					<td colspan="2"><input class="bott_conferma" type="submit" value="CONFERMA">
				</tr>
			</table>
		</form>
	</div>
	
	<%@ include file="Footer.jsp" %>
</body>
</html>