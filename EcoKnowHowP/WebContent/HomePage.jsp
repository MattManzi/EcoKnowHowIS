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
	<div id="main">
		<div class="img_HomePage"><img src="./img/sfondoHomePage.jpg" width="100%" height="auto"></div>		
		
		<div class="banner">	
			<div class="table-cell">				
				<div class="card">
					<a href="<%=response.encodeURL("SceltaMatriceCliente.jsp")%>">Crea il Piano</a>
					<p>Some text</p>
				</div>
			</div>
			<div class="table-cell">
				<div class="card">
			    	<a href="${pageContext.request.contextPath}/RecuperaPiano.jsp">Recupare il Piano</a>
			   		<p>Some text</p>
				</div>
			</div>
			<div class="table-cell">
				<div class="card">
					<a href="${pageContext.request.contextPath}//Pacchetti.jsp">I Pacchetti</a>
					<p>Some text</p>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="Footer.jsp" %>
	<h1> ciao</h1>
	<h1> ciao</h1>
	<h1> ciao</h1>
	<h1> ciao</h1>
	<h1> ciao</h1>
	
</body>
</html>