<%@page import="org.apache.catalina.mbeans.UserMBean"%>
<%@page import="org.apache.tomcat.jni.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
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


/*
Lista con tutti i clienti.
In questa pagina sarà possibile rimuovere i clienti dal db.
Cliccare su un cliente per accedere al suo storico piani.

Servlet Necessarie:
	ClienteControl?action=visualizza - OK
	ClienteControl?action=select - OK
	ClienteControl?action=delete - OK
*/

%>
<html>
<head>
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<script type="text/javascript" src="./script/alert.js"></script>
<title>Gestione Clienti Admin</title>
<body>
<%@ include file="NavAdmin.jsp" %>

<div class="canvas">
	<h3>Gestione Clietni</h3>
		<table id="tableMatriciAdmin">
			<tr>
				<td>Username</td>
				<td>Nome </td> 
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
				<td > <%=bean.getUsername()%></td>
				<td > <%=bean.getNome()%> </td>	
				<td > <%=bean.getCognome()%> </td>	
				<td> <button class="bott_gestione" id="gestione" onclick="storicoClienti(<%=bean.getUsername()%>)">G</button></td>
				<td> <button class="bott_rimuovi" id="cancella" onclick="cancellaClienti(<%=bean.getUsername()%>)">X</button></td>		
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