<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Test Dowload referto<p>
	<form id="formFile" name="formFile" action="${pageContext.request.contextPath}/DownloadRefertoServlet" method="get">
		<label for=codice>Codice:</label>
		<input type="text" name="codice">	
		<input type="submit" value="Conferma">
	</form>	
	
		<footer class="footer">
		<p>2020 Prova&copy;</p>
	</footer>
</body>
</html>