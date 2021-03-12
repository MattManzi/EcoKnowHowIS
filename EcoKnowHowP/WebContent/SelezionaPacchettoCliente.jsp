<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	Collection<?> pacchetti=null;
	
	if(userRoles != null && userRoles.booleanValue()) {
		 pacchetti=(Collection<?>) request.getAttribute("pacchetti");	
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
<title>Insert title here</title>
</head>
<body>
	<%@ include file="NavUser.jsp" %>
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
						<%=bean.getNome()%>/nome solo descrizione
						<h3><b><%=bean.getDescrizione()%></b></h3>				
						<a href="<%=response.encodeURL("Pacchetto?action=dettagli&id="+ bean.getId()) %>">Dettagli</a>
						<a href="<%=response.encodeURL("Pacchetto?action=select&id="+ bean.getId()) %>">Seleziona</a>
					</div>
				</div>
			<%
				}
			}
			%>
	
		</div>
	</div>
		<%@ include file="Footer.jsp" %>
</body>
</html>