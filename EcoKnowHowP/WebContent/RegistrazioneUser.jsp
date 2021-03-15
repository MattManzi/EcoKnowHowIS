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
<link rel="stylesheet" href="css/RegistrazioneUser.css" type="text/css">
</head>
<body>
	<div id="main">
		<div id="top">
			<a href="<%=response.encodeURL("HomePage.jsp") %>" class="logo"><img alt="EcoKnowHow" src="./img/logo.png" width="100%" height="100%"></a>
		</div>		
		<form id="formRegistrazioneUser" name="formRegistrazioneUser" action="<%=response.encodeURL("User?action=inserimentoDati")%>" method="post">				
			<div>
				<label for="nome">Nome:</label>	
				<input type="text" name="nome" maxlength="30">		
			</div>	
			<div>				
				<label for="cognome">Cognome:</label>	
				<input type="text" name="cognome" maxlength="30">	
			</div>	
			<div>		
				<label for="ivaCF">Partita IVA o CF:</label>	
				<input type="text" name="ivaCF" maxlength="16">	
			</div>	
			<div>				
				<label for="settore">Settore:</label>	
				<select name="settore">
					<option value="" autofocus="autofocus"></option>
					<option value="Prova1">Prova1</option>
					<option value="Prova2">Prova2</option>
					<option value="Prova3">Prova3</option>
					<option value="Prova4">Prova4</option>
					<option value="Prova5">Prova5</option>
				</select>	
			</div>	
			<div>
				<label for="sdi">Codice destinatario(SDI):</label>
				<input type="text" name="sdi" maxlength="6">	
			</div>	
			<div>									
				<label for="ragioneSociale">Ragione Sociale:</label>
				<input type="text" name="ragioneSociale" maxlength="50">	
			</div>			
			<div>
				<label for="via">Via:</label>	
				<input type="text" name="via" maxlength="40">	
			</div>	
			<div>	
				<label for="civico">Civico:</label>
				<input type="text" name="civico" maxlength="10">	
			</div>	
			<div>	
				<label for="cap">CAP:</label>
				<input type="text" name="cap" maxlength="5">
			</div>	
			<div>	
				<label for="comune">Comune:</label>	
				<input type="text" name="comune" maxlength="30">	
			</div>	
			<div>	
				<label for="telefono">Telefono:</label>					
				<input type="text" name="telefono" maxlength="15">
			</div>	
			<div>			
				<label for="pec">PEC:</label>
				<input type="text" name="pec" maxlength="50">
				</div>	
			<div>
				<label for="email">E-mail:</label>
				<input type="text" id="email" name="email" maxlength="50" onkeyup="testEmail(this.value)">
				</div>	
			<div>			
				<label for="username">Username:</label>	
				<input type="text" id="username" name="username" maxlength="20" onkeyup="testUser(this.value)">	
			</div>	
			<div>
				<label for="password">Password:</label>	
				<input type="password" id="password" name="password" maxlength="15">
			</div>	
			<div>
				<label for="password2">Conferma Password:</label>
				<input type="password" name="password2" maxlength="15">
			</div>	
			
			<input type="submit" value="Conferma">					
		</form>
	</div>
</body>
</html>