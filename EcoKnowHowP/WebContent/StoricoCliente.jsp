<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
AmministratoreBean admin_p = (AmministratoreBean) session.getAttribute("Admin");
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) session.getAttribute("userRoles");
ClienteBean userLog = (ClienteBean) session.getAttribute("User");
Collection<?> piani = null;
ClienteBean cliente = null;
Collection<?> clienti = (Collection<?>) request.getAttribute("clienti");
if ((adminRoles != null && adminRoles.booleanValue())
		|| (userRoles != null && userRoles.booleanValue())) {
	if (admin_p != null) {
		cliente = (ClienteBean) session.getAttribute("cliente");
		if (cliente == null) {
			response.sendRedirect("./GestioneClientiAdmin.jsp");
			return;
		} else {
			piani = (Collection<?>) request.getAttribute("piani");
			if (piani == null) {
				response.sendRedirect(response.encodeRedirectURL("./Piano?action=pianiCliente&username=" + cliente.getUsername()));
			return;
			}
		}
	} else {
		cliente = (ClienteBean) session.getAttribute("User");
		piani = (Collection<?>) request.getAttribute("piani");
		if (piani == null) {
			response.sendRedirect(response.encodeRedirectURL("./Piano?action=pianiCliente&username=" + userLog.getUsername()));
			return;
		}
	}
} else {
	response.sendRedirect("./HomePage.jsp");
	return;
} 
%>
<!DOCTYPE html>
<html>
<head>
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<title>Piano Cliente</title>
</head>
<body>
	<!-- L'admin e il cliente visualizzano lo storico Cliente  -->
	<%if(adminRoles != null){%>
		<%@ include file="NavAdmin.jsp" %>
	<%}else{%>
		<%@ include file="NavUser.jsp" %>
	<%}%>
	
	<div class="canvas">
		<h4>Nome:<%=cliente.getNome()%></h4>
		<h4>Cognome:<%=cliente.getCognome()%></h4>
		<table id="tableMatriciAdmin">
			<tr>
				<th>ID</th>
				<th>Modulo</th>
				<th>Stato</th>
				<th>Data</th>
			</tr>
			<%
			if (piani != null && piani.size() > 0) {
				Iterator<?> it = piani.iterator();
				while (it.hasNext()) {
					PianoBean bean = (PianoBean) it.next();
			%>
					<tr>
						<td><%=bean.getId()%></td>
						<td><a href="<%=response.encodeURL("Piano?action=select&id="+bean.getId())%>">Modulo</a></td>
						<td><%=bean.getStato()%></td>
					</tr>
			<%
				}
			}%>
		</table>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>