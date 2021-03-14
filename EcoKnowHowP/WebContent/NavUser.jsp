<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
 <%
ClienteBean user = (ClienteBean) request.getSession().getAttribute("User"); 
%>
<!DOCTYPE html>
<html>
<head>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/Template.css" rel="stylesheet">
<title>Insert title here</title>
<script>
function menu() {
  var x = document.getElementById("nav");
  if (x.className === "nav") {
    x.className += " responsive";
  } else {
    x.className = "nav";
  }
}
</script>
</head>
<body>
	<header class="header clearfix fixed">
		<div class="logo">
			<a href="<%=response.encodeURL("HomePage.jsp")%>"><img src="./img/logo.png"></a>
		</div>
		<div class="nav" id="nav">
			<a href="javascript:void(0);" class="icon" onclick="menu()"><i class="fa fa-bars"></i></a>
		<div class="invisibile">
			<p><br></p>
			<p><br></p>
		</div>
			<%if (user == null){%>
				<a href="<%=response.encodeURL("LoginUser.jsp")%>">Login</a>					
			<%}else{%>				 
				<a href="AreaPersonaleCliente.jsp"><%=user.getUsername()%></a> 	
			<%}%>
			<a href="<%=response.encodeURL("ChiSiamo.jsp")%>">Chi Siamo?</a>	
			<a href="<%=response.encodeURL("SceltaMatriceCliente.jsp")%>">Crea il tuo Piano</a>
			
		</div>									
	</header>
</body>
</html>