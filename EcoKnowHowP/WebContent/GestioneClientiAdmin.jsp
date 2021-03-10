<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<% 
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
Collection<?> clienti=(Collection<?>)request.getAttribute("clienti"); 

if (adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
	return;
}else{
	if(clienti==null){
		response.sendRedirect(response.encodeRedirectURL("./User?action=visualizza"));
		return;
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>
<title>Gestione Clienti Admin</title>
</head>
<body>
	<%@ include file="NavAdmin.jsp" %>
	
	<div class="canvas">
		<h3>Gestione Clietni</h3>
		<table id="tableMatriciAdmin">
			<tr>
				<td>Username</td>
				<td>Nome</td> 
				<td>Cognome</td>
				<td>Gestione</td> 
				<td>Rimuovi</td>
			</tr>
			<% if(clienti != null && clienti.size()>0){
				Iterator<?> it=clienti.iterator();
				while(it.hasNext()){
				ClienteBean bean=(ClienteBean) it.next();
			%>
					<tr> 
						<td><%=bean.getUsername()%></td>
						<td><%=bean.getNome()%></td>	
						<td><%=bean.getCognome()%></td>	
						<td><button class="bott_gestione" id="gestione" value="<%=bean.getUsername()%>" onclick="storicoClienti(this.value)">G</button></td>
						<td><button class="bott_rimuovi" id="cancella" value="<%=bean.getUsername()%>" onclick="cancellaClienti(this.value)">X</button></td>		
					</tr>
			<%	
				}
			}
			 %>
		</table>
	</div>
	
	<%@ include file="Footer.jsp" %>
</body>
</html>