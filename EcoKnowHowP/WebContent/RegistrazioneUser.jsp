<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Registrazione</title>
<script type="text/javascript" src="./script/jquery-3.5.1.js"></script>
<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.js"></script> 
<script type="text/javascript" src="./script/RegistrazioneUser.js"></script>
<script type="text/javascript" src="./script/funzioniAjax.js"></script>
<link rel="stylesheet" href="/css/RegistrazioneUser.css" type="text/css">
</head>
<body>
	<a href="<%=response.encodeURL("HomePage.jsp")%>" class="logo">EcoKnowHow</a>
	<div>
		<fieldset><legend>Registrazione</legend>
			<form id="formRegistrazioneUser" name="formRegistrazioneUser" action="<%=response.encodeURL("User?action=inserimentoDati")%>" method="post">
				<table>
					<tr>
						<td><label for="nome">Nome:</label></td>	
						<td><input type="text" name="nome" maxlength="30"></td>	
					</tr>
					<tr>
						<td><label for="cognome">Cognome:</label></td>	
						<td><input type="text" name="cognome" maxlength="30"></td>	
					</tr>
					<tr>
						<td><label for="settore">Settore:</label></td>	
						<td><select name="settore">
							<option value="" autofocus="autofocus"></option>
							<option value="Prova1">Prova1</option>
							<option value="Prova2">Prova2</option>
							<option value="Prova3">Prova3</option>
							<option value="Prova4">Prova4</option>
							<option value="Prova5">Prova5</option>
						</select></td>	
					</tr>
					<tr>
						<td><label for="telefono">Telefono:</label></td>	
						<td><input type="text" name="telefono" maxlength="15"></td>	
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
						<td><label for="ivaCF">Partita IVA o CF:</label></td>	
						<td><input type="text" name="ivaCF" maxlength="16"></td>	
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
						<td id="emailTd"><label for="email">E-mail:</label></td>	
						<td><input type="text" id="email" name="email" maxlength="50" onkeyup="testEmail(this.value)"></td>	
					</tr>
					<tr>
						<td id="usernameTd"><label for="username">Username:</label></td>	
						<td><input type="text" id="username" name="username" maxlength="20" onkeyup="testUser(this.value)"></td>	
					</tr>
					<tr>
						<td><label for="password">Password:</label></td>	
						<td><input type="password" id="password" name="password" maxlength="15"></td>	
					</tr>
					<tr>
						<td><label for="password2">Conferma Password:</label></td>	
						<td><input type="password" name="password2" maxlength="15"></td>	
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