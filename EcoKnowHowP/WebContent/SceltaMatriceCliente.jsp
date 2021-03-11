<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
	Collection<?> matrici=null;
	Collection<?> nomi=null;
	
	
	if(userRoles != null && userRoles.booleanValue()) {
		matrici=(Collection<?>) request.getAttribute("matrici"); 
		nomi=(Collection<?>) request.getAttribute("nomi"); 
		if(nomi==null){
			response.sendRedirect(response.encodeRedirectURL("./Matrice?action=nomi"));
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
	<%@ include file="NavUser.jsp" %>
	
	<div id="main">
		<div id="scelta">
			Iterazione della collection nomi
			nome:nomi
			per ogni nome un <a></a> con href="Matrice?action=visualizza&nome="+nome
		</div>
		<div id="matrici">
			Iterazione della collection matrici
			se non è null è non è vuota
			<div>sottotitolo, note			
			<a></a> con href="Matrice?action=select&id="+id
			</div>
	
		</div>	
	</div>

	<%@ include file="Footer.jsp" %>
</body>
</html>