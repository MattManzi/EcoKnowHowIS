<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
	Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
	Collection<?> matrici=null;
	Collection<?> nomi=null;
	
	
	if(userRoles != null && userRoles.booleanValue()) {
		matrici=(Collection<?>) request.getAttribute("matrici"); 
		nomi=(Collection<?>) session.getAttribute("nomi"); 
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
<title>Scelta Matrice</title>
<link href="css/SceltaMatriceUser.css" rel="stylesheet">
</head>
<body>
	<%@ include file="NavUser.jsp" %>
	
	<div id="main">
		<div id="scelta">

		<%if(nomi != null && nomi.size()>0){
				Iterator<?> it=nomi.iterator();
				while(it.hasNext()){
					String nome=(String) it.next();		
			%>
			<div class="nome">
				<a class="nome_matrice" href="<%=response.encodeURL("Matrice?action=visualizzaMatrici&nome="+nome) %>"><%=nome %> </a>
			</div>
			<%		}
				}%>
		</div>
		
		<div id="matrici">
		
		<% if(matrici != null && matrici.size() > 0){
			Iterator<?> it=matrici.iterator();
			while(it.hasNext()){
				MatriceBean matrice=(MatriceBean) it.next();
				%>

				<div class=card>
				
					<h4><%=matrice.getSottotitolo() %></h4>
					<h4><%=matrice.getNota() %></h4>
					<a href="<%=response.encodeURL("Matrice?action=select&id="+matrice.getId()) %>" >Seleziona</a>
				
				</div>



				<%		}
					}else{%>
				<div id="matrici">					
					<h2 class="non_selezione">Seleziona una Matrice per vedere le sue categorie</h2>
				</div>
			
			<%	
				}%>
	
		</div>	
	</div>

	<%@ include file="Footer.jsp" %>
</body>
</html>