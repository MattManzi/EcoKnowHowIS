<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	ClienteBean utente=(ClienteBean) request.getSession().getAttribute("User");	
	Collection<?> pacchetti=(Collection<?>) request.getAttribute("pacchetti");	
	if(utente != null && userRoles != null && userRoles.booleanValue()) {
		if(pacchetti==null){
			response.sendRedirect("SceltaTipoPacchettoCliente.jsp");
			return;
		}
	}else{
		response.sendRedirect("LoginUser.jsp");
		return;
	}
	/*
		Servlet Necessarie:
		AggiuntaPianoServlet?action=crea - X
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>SelezionaPacchetto</h1>
	
	<div id="main">	
		<div>
			<%
			if(pacchetti.size()>0){
				Iterator<?> it = pacchetti.iterator();
				while (it.hasNext()) {
					PacchettoBean bean=(PacchettoBean) it.next();
			
			%>
				<div class="card">	
					<div class="container">
						<a href="<%=response.encodeURL("PacchettoControl?action=select&id="+ bean.getId()) %>"><%=bean.getNome()%></a>
						<h3><b><%=bean.getDescrizione()%></b></h3>				
						<a href="<%=response.encodeURL("PacchettoControl?action=select&id="+ bean.getId()) %>">Dettagli</a>
					</div>
				</div>
			<%
				}
			}
			%>
	
		</div>
	</div>
</body>
</html>