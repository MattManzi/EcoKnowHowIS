<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
	ClienteBean utente=(ClienteBean) request.getSession().getAttribute("User");	
	Collection<?> matrici=(Collection<?>) request.getAttribute("matrici"); 
	
	if(utente != null && userRoles != null && userRoles.booleanValue()) {
		if(matrici==null){
			response.sendRedirect(response.encodeRedirectURL("./MatriceControl?action=visualizza"));
			return;
		}
	}else{
		response.sendRedirect("LoginUser.jsp");
		return;
	}
	
	
/*
In questa pagina il cliente potra scegliera su quale matrice basare il suo piano.
Clicca su una card e manda l'id della matrice alla servlet. La matrice verrà caricata in sessione.

Servlet:
	VisualizzaMatriciServlet -OK
	SceltaMatriceUserServlet -OK
*/
	
%>
<!DOCTYPE html>
<html>
<head>
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
						<a href="<%=response.encodeURL("MatriceControl?action=select&id="+ bean.getId()) %>"><%=bean.getNome()%></a>
						<h3><b><%=bean.getSottotitolo()%></b></h3>				
						<p><%=bean.getNota()%></p>
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