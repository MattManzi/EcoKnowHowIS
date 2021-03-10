<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
AmministratoreBean admin_p = (AmministratoreBean) request.getSession().getAttribute("Admin");
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
Collection<?> piani = null;
ClienteBean cliente = null;
Collection<?> clienti = (Collection<?>) request.getAttribute("clienti");
if ((admin_p != null && adminRoles != null && adminRoles.booleanValue())
		|| (user != null || userRoles != null && userRoles.booleanValue())) {
	if (admin_p != null) {
		cliente = (ClienteBean) request.getSession().getAttribute("cliente");
		if (cliente == null) {
	response.sendRedirect("./GestioneClientiAdmin.jsp");
	return;
		} else {
	piani = (Collection<?>) request.getAttribute("piani");
	if (piani == null) {
		response.sendRedirect(
				response.encodeRedirectURL("./Piano?action=pianiCliente&username=" + cliente.getUsername()));
		return;
	}
		}
	} else {
		piani = (Collection<?>) request.getAttribute("piani");
		if (piani == null) {
	response.sendRedirect(
			response.encodeRedirectURL("./Piano?action=pianiCliente&username=" + user.getUsername()));
	return;
		}
	}
} else {
	response.sendRedirect("./HomePage.jsp");
	return;
} /* Lista con tutti i piani del cliente, loggato o scelto dall'admin. Servlet Necessarie: PianoControl?action=pianiCliente&username= - OK PianoControl?action=select&id= - OK PianoControl?action=delete */
%>
<!DOCTYPE html>
<html>
<head>
<link href="css/GestioneMatriceAdmin.css" rel="stylesheet">
<title>Piano Cliente</title>
</head>
<body>
	<!-- L'admin e il cliente visualizzano lo storico Cliente  -->
	<%@ include file="NavAdmin.jsp"%>
	<div class="canvas">
		<h4>
			Nome:
			<%=cliente.getNome()%>
		</h4>
		<h4>
			Cognome:
			<%=cliente.getCognome()%>
		</h4>
		<table id="tableMatriciAdmin">
			<tr>
				<td>ID</td>
				<td>Modulo</td>
				<td>Stato</td>
				<td>Data</td>
			</tr>
			<%
			if (piani != null && piani.size() > 0) {
				Iterator<?> it = piani.iterator();
				while (it.hasNext()) {
					PianoBean bean = (PianoBean) it.next();
			%>
			<tr>
				<td><%=bean.getId()%></td>
				<td><%=bean.getModulo()%></td>
				<td><%=bean.getStato()%></td>
			</tr>
			<%
			}
			} else {
			%>
			<h3>Non hai ancora richiesto nessun pacchetto</h3>
			<%
			}
			%>
		</table>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>