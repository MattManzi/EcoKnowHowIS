<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
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