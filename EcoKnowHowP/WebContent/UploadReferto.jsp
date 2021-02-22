<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Test Inserimento Referto</p>
	<form id="formFile" name="formFile" action="${pageContext.request.contextPath}/UploadRefertoServlet" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td><label for=codice>Codice:</label></td>
				<td><input type="text" name="codice"></td>
			</tr>
			<tr>
				<td colspan="2"><input id="fileCop" type="file" name="talkPhoto" value="" maxlength="255" accept="application/pdf"></td>	
			</tr>
			<tr>
				<td><input type="submit" value="Conferma"></td>
				<td><input type="reset" value="Reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>