<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
    
<%
Boolean userRoles = (Boolean) session.getAttribute("userRoles");

ModuloBean modulo=null;
ArrayList<String> obiettivi=null;
ArrayList<String> hp=null;

if(userRoles != null && userRoles.booleanValue()) {
	modulo=(ModuloBean)session.getAttribute("modulo");
	if(modulo!=null){
		if(modulo.getTipo().equals("B")){
			hp=modulo.getHp();
		}
		obiettivi=modulo.getObiettivi();
	}else{
		response.sendRedirect("SceltaTipoPacchettoCliente.jsp");
		return;
	}
}else{
	response.sendRedirect("LoginUser.jsp");
	return;
}

/*
Form per la creazione di un modulo.

Servlet Necessarie:
	PianoControl?action=salva- X
	
*/

%>  
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="./script/jquery-3.5.1.js"></script>
<link href="css/CompilaModuloCliente.css" rel="stylesheet">
<title>Modulo</title>
</head>
<body>
	<%@ include file="NavUser.jsp" %>
		
	<div id="main">
		<form id="formCompilaModulo" action="" method="post">
			<table id="info1">
				<tr class="hr">
					<th style="width: 30%;"></th>
					<th style="width: 35%;">Produttore del rifiuto.</th>
					<th style="width: 35%;">Committente dell'analisi.<br>
					<label for="key"> Il committente è il produttore.</label>
					<input id="check" type="checkbox" name="check" value="true"></th>
				</tr>	
				<tr>
					<td class="dato">Ragione Sociale:</td>
					<td><input type="text" name="ragioneSocialeProd" maxlength="50"></td>
					<td class="com"><input id="rsc" type="text" name="ragioneSocialeCom" maxlength="50"></td>
				</tr>	
				<tr>
					<td class="dato">Sede legale o operativa:</td>
					<td><input type="text" name="sedeLegaleProd" maxlength="100"></td>
					<td class="com"><input id="slc" type="text" name="sedeLegaleCom" maxlength="100"></td>
				</tr>	
				<tr>
					<td class="dato">P.IVA:</td>
					<td><input type="text" name="pIvaProd" maxlength="11"></td>
					<td class="com"><input id="pic" type="text" name="pIvaCom" maxlength="11"></td>
				</tr>	
				<tr>
					<td class="dato">Telefono:</td>
					<td><input type="text" name="telefonoProd" maxlength="10"></td>
					<td class="com"><input id="tfc" type="text" name="telefonoCom" maxlength="10"></td>
				</tr>	
				<tr>
					<td class="dato">E-mail:</td>
					<td><input type="text" name="emailProd" maxlength="10"></td>
					<td class="com"><input id="emc" type="text" name="emailCom" maxlength="10"></td>
				</tr>
			</table>
			<table id="info2">
				<tr class="hr">
					<th style="width: 30%;"></th>
					<th style="width: 35%;"></th>
					<th style="width: 35%;"></th>
				</tr>					
				<tr>
					<td class="dato">Data e Luogo di campionamento:</td>
					<td><input type="date" name="data"></td>
					<td><input type="text" name="luogo" maxlength="100"></td>
				</tr>
				<tr>
					<td class="dato">Nome e Cognome campionatore:</td>
					<td><input type="text" name="nomeCampionatore"></td>
					<td><input type="text" name="cognomeCampionatore" maxlength="100"></td>
				</tr>
				<tr>
					<td colspan="2" class="dato">Norma di riferimento utilizzata per il campionamento:</td>
					<td><input type="text" name="norma"></td>
				</tr>	
				<tr>
					<td colspan="2" class="dato">Quantità campione in consegna al laboratorio<br>prelevato all’atto del campionamento:</td>
					<td><input type="text" name="quantitaCampione"></td>
				</tr>
				<tr>
					<td colspan="2" class="dato">Occorre inserire delle particolari note sul rapporto di prova?<br>Esempio modalità di campionamento.</td>
					<td><input type="text" name="note"></td>
				</tr>
			</table>
			<table id="info3">
				<tr class="hr">
					<th>Obiettivo dell’analisi: </th>
				</tr>			
				<%
					if(obiettivi!=null && obiettivi.size()>0){
						for(String s:obiettivi){
				%>
							<tr>
								<td><input type="checkbox" name="obiettivi" value="<%=s %>"><%=s %></td>
							</tr>
				<%
						}
					}
				%>
			</table>		
			<%
				if(hp!=null && hp.size()>0){
			%>
					<table id="info4">
			<%
					for(String s:hp){
			%>
						<tr>
							<td><input type="checkbox" name="obiettivi" value="<%=s %>"><%=s %></td>
						</tr>
			<%		}
			%>				
					</table>
			<%
				}
			%>
					
			<div id="info5">
				<p>CLICCANDO SU CONFERMA IL COMMITTENTE E/O IL PRODUTTORE, AI SENSI DEL DPR 445/2000 ART. 76, DICHIARA SOTTO LA PROPRIA RESPONSABILITÀ DICHIARA CHE:</p>
				
				<p>- IL CAMPIONE CONSEGNATO È RAPPRESENTATIVA DELL’INTERA MATRICE OGGETTO DI INDAGINE.<br>
				- LE SOSTANZE DA RICERCARE SONO QUELLE INDICATE NEL PIANO ANALITICO.</p>
				<p>LA PRESENTE RICHIESTA SARA’ PARTE INTEGRANTE DEL RAPPORTO DI PROVA ANALITICO CHE VERRA’ PRODOTTO.</p>
				<br>
				<div id="conferma">
					<input type="date" name="data"><input type="submit" value="Conferma">
				</div>
			<br><br><br><br>
			</div>
		</form>
	</div>
		
	<%@ include file="Footer.jsp" %>
</body>
</html>