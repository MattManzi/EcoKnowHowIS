<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, ekh.bean.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/RegistrazioneUser.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/script/RegistrazioneUser.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery-3.5.1.js"></script>
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.js"></script> 
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/jsp/HomePage.jsp" class="logo">EcoKnowHow</a>
	<div>
		<fieldset><legend>Registrazione</legend>
			<form name="formRegistrazioneUser" action="${pageContext.request.contextPath}/RegistrazioneUserServlet?action=inserimentoDati" method="post">
				<table>
					<tr>
						<td><label for="nome">Nome:</label></td>	
						<td><input type="text" id="nome" name="nome" maxlength="30"></td>	
					</tr>
					<tr>
						<td><label for="cognome">Cognome:</label></td>	
						<td><input type="text" name="cognome" maxlength="30"></td>	
					</tr>
					<tr>
						<td><label for="funzioneAziendale">Funzione Aziendale:</label></td>	
						<td><input type="text" name="funzioneAziendale" maxlength="50"></td>	
					</tr>
					<tr>
						<td><label for="telefono">telefono:</label></td>	
						<td><input type="text" name="telefono" maxlength="10"></td>	
					</tr>
					<tr>
						<td><label for="ragioneSociale">Ragione Sociale:</label></td>	
						<td><input type="text" name="ragioneSociale" maxlength="50"></td>	
					</tr>
					<tr>
						<td><label for="via">Via:</label></td>	
						<td><input type="text" name="via" maxlength="40"></td>	
					</tr>
					<tr>
						<td><label for="civico">Civico:</label></td>	
						<td><input type="text" name="civico" maxlength="10"></td>	
					</tr>
					<tr>
						<td><label for="cap">CAP:</label></td>	
						<td><input type="text" name="cap" maxlength="5"></td>	
					</tr>
					<tr>
						<td><label for="comune">Comune:</label></td>	
						<td><input type="text" name="comune" maxlength="30"></td>	
					</tr>
					<tr>
						<td><label for="pIva">Partita IVA:</label></td>	
						<td><input type="text" name="pIva" maxlength="11"></td>	
					</tr>
					<tr>
						<td><label for="cf">Codice Fiscale:</label></td>	
						<td><input type="text" name="cf" maxlength="16"></td>	
					</tr>
					<tr>
						<td><label for="sdi">Codice destinatario(SDI):</label></td>	
						<td><input type="text" name="sdi" maxlength="6"></td>	
					</tr>
					<tr>
						<td><label for="pec">PEC:</label></td>	
						<td><input type="text" name="pec" maxlength="50"></td>	
					</tr>
					<tr>
						<td><label for="email">E-mail:</label></td>	
						<td><input type="text" name="email" maxlength="50"></td>	
					</tr>
					<tr>
						<td><label for="username">Username:</label></td>	
						<td><input type="text" name="username" maxlength="20"></td>	
					</tr>
					<tr>
						<td><label for="password">Password:</label></td>	
						<td><input type="text" name="password" maxlength="15"></td>	
					</tr>
					<tr>
						<td><label for="password2">Conferma Password:</label></td>	
						<td><input type="text" name="password2" maxlength="15"></td>	
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Conferma"></td>	
					</tr>
				</table>
			</form>
		</fieldset>
	</div>	
</body>
</html>