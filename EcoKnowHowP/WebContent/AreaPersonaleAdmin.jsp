<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<%
Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");

AmministratoreBean adminP = (AmministratoreBean) request.getSession().getAttribute("Admin");
if (adminRoles == null || !adminRoles.booleanValue()) {
	response.sendRedirect("./LoginAdmin.jsp");
}
%>
<html>
<head>
<title>Area Personale Admin</title>
</head>
<body>
	<%@ include file="NavAdmin.jsp" %>
	
	<div>
	<% %>
	<h1></h1>
	<% %>
	</div>
	
	
	<%@ include file="Footer.jsp" %>
</body>
</html>