<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	ClienteBean utente=(ClienteBean) request.getSession().getAttribute("Utente");	
	Collection<?> matrici=(Collection<?>) request.getAttribute("matrici"); 
	
	/*if ((utente == null) || (userRoles == null) || (!userRoles.booleanValue())) {
		response.sendRedirect("LoginUser.jsp");
		return;
	}*/
	
	if(matrici==null){
		response.sendRedirect(response.encodeRedirectURL("./VisualizzaMatriciServlet?action=user"));
		return;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/SceltaMatriceUser.css" rel="stylesheet">
</head>
<body>
	<!-- NAV -->
	<div id="main">	
	<div>
		<%
		if(matrici.size()>0){
			Iterator<?> it = matrici.iterator();
			while (it.hasNext()) {
				MatriceBean bean=(MatriceBean) it.next();
		%>		
				
				<div class="card">	
					<div class="container">
						<a href="<%=response.encodeURL("SceltaMatriceUser?idMatrice="+ bean.getId()) %>"><%=bean.getNome()%></a>
						<h3><b><%=bean.getSottotitolo()%></b></h3>				
						<p><%=bean.getDescrizione()%></p>
					</div>
				</div>
		<%
			}
		}
		
		%>	
	</div>
	</div>
	<!-- FOO -->
</body>
</html>