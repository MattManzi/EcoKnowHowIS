<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, ekh.bean.*"%>
<!DOCTYPE html>
<%
ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("Cliente"); 
%>
<html>
<head>
<meta charset="ISO-8859-1">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="../css/HomePage.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<title>EcoKnowHow</title>
</head>
<body>
<<<<<<< Upstream, based on branch 'main' of https://github.com/MattManzi/EcoKnowHowIS.git
	<h1>Benvenuto</h1>
	<a href="jsp/UploadReferto.jsp">test Upload Referto</a><br>
	<a href="jsp/DownloadReferto.jsp">test Download Referto</a>
=======

	<header class="header clearfix fixed">

			<a href="<%=response.encodeURL("HomePage.jsp")%>"  class="header__logo">EcoKnowHow</a>
	
			<ul class="header__menu ">    
		    	<li class="header__menu__item"> <a href="<%=response.encodeURL("CreaPiano.jsp")%>">Crea il tuo Piano</a>  </li>
					<li class="header__menu__item"> <a href="<%=response.encodeURL("Pacchetti.jsp")%>">I nostri Pacchetti</a></li> 
					<li class="header__menu__item"><a href="<%=response.encodeURL("ChiSiamo.jsp")%>">Chi Siamo?</a></li>
				<%if (cliente == null){ %>
						
					<li class="header__menu__item"><a href="<%=response.encodeURL("Login.jsp")%>"> Login </a>
					
					<%}else{%>				 
					<a href=> <%=cliente.getUsername()%> </a> 
	
				<%
				}
				%>
				 </li>
			</ul> 
								
	</header>
		<section  class="img_HomePage">

		</section>	
	
	
	<section class="banner clearfix">	
	
			<div class="contenitore_card">
			
				    <div class="table-cell">
				    <div class="card">
				      <a href="<%=response.encodeURL("CreaPiano.jsp")%>"><h3>Crea il Piano</h3> </a>
				      <p>Some text</p>
				      <p>Some text</p>
				      	    </div>
				    </div>
				    

				  
			
				    <div class="table-cell">
				    <div class="card">
				      <a href="<%=response.encodeURL("RecuperaPiano.jsp")%>""><h3>Recupare il Piano</h3> </a>
				      <p>Some text</p>
				      <p>Some text</p>
				      	    </div>
				    </div>

			
				    <div class="table-cell">
				    	<div class="card">
				      <a href="<%=response.encodeURL("Pacchetti.jsp")%>""><h3>I Pacchetti</h3> </a>
				      <p>Some text</p>
				      <p>Some text</p>
				      </div>
				    </div>
		
			</div>
			
		</section>
	
	<footer class="footer">
	  <p>2020 Prova&copy;</p>
	</footer>

>>>>>>> 1140907 Non so che versione è ma ci sta il mio html e css xD
</body>
</html>