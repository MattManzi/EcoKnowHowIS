<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/HomePage.css" rel="stylesheet">
<title>EcoKnowHow</title>
</head>
<body>
	<%@ include file="NavUser.jsp" %>
	<section class="img_HomePage"></section>		
	
	<section class="banner clearfix">	
		<div class="contenitore_card">
			<div class="table-cell">
			
				<div class="card">
					<a href="<%=response.encodeURL("SceltaMatriceCliente.jsp")%>">Crea il Piano</a>
					<p>Some text</p>
					<p>Some text</p>
				</div>
			</div>
			<div class="table-cell">
				<div class="card">
			    	<a href="${pageContext.request.contextPath}/RecuperaPiano.jsp">Recupare il Piano</a>
			   		<p>Some text</p>
			  		<p>Some text</p>
				</div>
			</div>
			<div class="table-cell">
				<div class="card">
					<a href="${pageContext.request.contextPath}//Pacchetti.jsp">I Pacchetti</a>
					<p>Some text</p>
					<p>Some text</p>
				</div>
			</div>
		</div>
	</section>
	
	<%@ include file="Footer.jsp" %>
</body>
</html>