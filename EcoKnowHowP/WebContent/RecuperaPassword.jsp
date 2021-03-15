<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/Verifica.css" rel="stylesheet">
<title>Reimpostazione password</title>
</head>
<body>
	<div id="main">
		<h1>Recupero Password</h1>		
		<form name="formEmail" action="<%=response.encodeURL("RP?action=verifica") %>" method="post"> 
			<input type="text" name="email" placeholder="E-mail">
			<input type="submit" value="Invia Email">	
		</form>
	</div>
</body>
</html>