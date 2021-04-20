<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
    <%

Collection<?> matrici=(Collection<?>) request.getAttribute("matrici");

	if(matrici==null){
		response.sendRedirect(response.encodeRedirectURL("./Matrice?action=visualizza"));
		return;
	}

%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="NavAdmin.jsp" %>
	
	<div>
		<p>inserimento bottoni in un secondo momento </p>
	</div>
		<div>
		<p>inserimento bottoni in un secondo momento </p>
	</div>
		<div>
		<p>inserimento bottoni in un secondo momento </p>
	</div>
		<div>
		<p>inserimento bottoni in un secondo momento </p>
	</div>
		<div>
		<p>inserimento bottoni in un secondo momento </p>
	</div>
		<div class="canvas">
		<a href="<%=response.encodeURL("AggiungiMatriceAdmin.jsp") %>"> Aggiungi</a>
		<table id="tableMatriciAdmin">
			<tr>
				<td>ID</td>
				<td>Nome </td> 
				<td>Sotto Titolo</td>
				<td>Modifica </td>
				<td>Cancella </td>
			</tr>
			<% if(matrici != null && matrici.size()>0){
				Iterator<?> it=matrici.iterator();
				while(it.hasNext()){
					MatriceBean bean=(MatriceBean) it.next();
			%>
					<tr> 
						<td > <%=bean.getId()%> </td>
						<td > <%=bean.getNome()%> </td>	
						<td > <%=bean.getSottotitolo()%> </td>	
						<td> <button class="bott_modifica" id="modifica" onclick="selectMatriceMod(<%=bean.getId()%>)">M</button></td>
						<td> <button class="bott_rimuovi" id="cancella" onclick="cancellaMatrice(<%=bean.getId()%>)">X</button></td>	
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