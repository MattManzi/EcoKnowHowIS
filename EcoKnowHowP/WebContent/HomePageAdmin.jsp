<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, ekh.bean.*"%>
<!DOCTYPE html>
<%

AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin"); 
	Boolean userRoles = (Boolean) session.getAttribute("userRoles");
	if(admin!=null &&(userRoles)){
		
		response.sendRedirect("./HomePage.jsp");
	}
	String msg = (String) request.getAttribute("msg");
	%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="<%=response.encodeURL("HomePage.jsp") %>" class="logo"> EcoKnowHow </a>
	<div id="main">
		<form name="formLogin" action="<%=response.encodeURL("LoginUser?action=login") %>" method="post">
			<fieldset>
				<label for="username">Username</label> 
				<input class="normal" id="username" type="text" name="username" placeholder="enter username"><br> 
				<label for="password">Password</label> 
				<input class="normal" id="password" type="password" name="password" placeholder="enter password"><br>
				<button type="button" onclick="loginUser()">Login</button>
				<%
				if(msg!=null){ 
				%>	
				<p id="error"><%=msg%></p>
				<%
				} 
				%>
			</fieldset>
		</form>
	</div>

</body>
</html>