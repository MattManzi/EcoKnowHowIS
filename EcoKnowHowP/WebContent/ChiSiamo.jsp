<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
    <%
		ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("Cliente"); 
	%>

<!DOCTYPE html>
<html>
<head>

<title>EcoKnowHow</title>
</head>
<body>
	<%@ include file="NavUser.jsp" %>
	
	<%@ include file="Footer.jsp" %>

</body>
</html>