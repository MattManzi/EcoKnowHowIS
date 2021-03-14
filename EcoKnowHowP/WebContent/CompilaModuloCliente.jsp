<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*, java.time.LocalDate"%>
    
<%
Boolean userRoles = (Boolean) session.getAttribute("userRoles");

ModuloBean modulo=null;
ArrayList<String> obiettivi=null;
ArrayList<String> hp=null;
LocalDate today = LocalDate.now();

if(userRoles != null && userRoles.booleanValue()) {
	modulo=(ModuloBean)session.getAttribute("modulo");
	if(modulo!=null){
		if(modulo.getTipo().equals("B")){
			modulo=(ModuloAvanzatoBean)session.getAttribute("modulo");
			hp=((ModuloAvanzatoBean)modulo).getHp();
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
<script type="text/javascript" src="./script/CompilaModuloCliente.js"></script>
<link href="css/CompilaModuloCliente.css" rel="stylesheet">
<title>Modulo</title>
<script type="text/javascript">

function funRO(value) {
	if(document.getElementById("check").checked){
		document.getElementById("rsc2").disabled = true;
		document.getElementById("slc2").disabled = true;
		document.getElementById("pic2").disabled = true;
		document.getElementById("tfc2").disabled = true;
		document.getElementById("emc2").disabled = true;		
	}else{
		document.getElementById("rsc2").disabled = false;
		document.getElementById("slc2").disabled = false;
		document.getElementById("pic2").disabled = false;
		document.getElementById("tfc2").disabled = false;
		document.getElementById("emc2").disabled = false;		
	}
}
</script>
</head>
<body>
	<%@ include file="NavUser.jsp" %>
		
	<div id="main">
		<p>Campi Obbligatori (*)</p>
		<form id="formCompilaModulo" action="<%=response.encodeURL("Piano?action=crea")%>" method="post">
			<table id="info1">
				<tr class="hr">
					<th style="width: 30%;"></th>
					<th style="width: 35%;">Produttore del rifiuto.</th>
					<th style="width: 35%;">Committente dell'analisi.<br>
					<label for="key"> Il committente è il produttore.</label>
					<input id="check" type="checkbox" name="check" onchange="funRO(this.value)" value="true"></th>
				</tr>	
				<tr>
					<td id="rscTD" class="dato">Ragione Sociale:*</td>
					<td><input id="rsc" type="text" name="ragioneSocialeProd" maxlength="50"></td>
					<td><input id="rsc2" type="text" name="ragioneSocialeCom" maxlength="50"></td>
				</tr>	
				<tr>
					<td id="slcTD" class="dato">Sede legale o operativa:*</td>
					<td><input id="slc" type="text" name="sedeLegaleProd" maxlength="100"></td>
					<td><input id="slc2" type="text" name="sedeLegaleCom" maxlength="100"></td>
				</tr>	
				<tr>
					<td id="picTD" class="dato">P.IVA:*</td>
					<td><input id="pic" type="text" name="pIvaProd" maxlength="11" ></td>
					<td><input id="pic2" type="text" name="pIvaCom" maxlength="11"></td>
				</tr>	
				<tr>
					<td id="tfcTD" class="dato">Telefono:*</td>
					<td><input id="tfc" type="text" name="telefonoProd" maxlength="15" ></td>
					<td><input id="tfc2" type="text" name="telefonoCom" maxlength="15"></td>
				</tr>	
				<tr>
					<td id="emcTD" class="dato">E-mail:*</td>
					<td><input id="emc" type="text" name="emailProd" maxlength="50" ></td>
					<td><input id="emc2" type="text" name="emailCom" maxlength="50"></td>
				</tr>
			</table>
			<table id="info2">
				<tr class="hr">
					<th style="width: 30%;"></th>
					<th style="width: 35%;"></th>
					<th style="width: 35%;"></th>
				</tr>					
				<tr>
					<td class="dato">Data e Luogo di campionamento:*</td>
					<td><input type="date" name="data" max="<%=today %>"></td>
					<td><input type="text" name="luogo" maxlength="100" placeholder="Luogo"></td>
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
			</table>
			<%
				if(modulo.getTipo().equals("B") && hp!=null && hp.size()>0){
			%>
					<table id="info3">
						<tr class="hr">
							<th style="width: 30%;"></th>
							<th style="width: 35%;"></th>
							<th style="width: 35%;"></th>
						</tr>	
						<tr>
							<td colspan="2" class="dato">CODICE CER (attribuito dal produttore in base all’origine/provenienza del rifiuto):</td>
							<td><input type="text" name="cer"></td>
						</tr>
						<tr>
							<td class="dato">Stato Fisico:</td>
							<td colspan="2">
								<input type="radio" id="snp" name="statoFisico" value="Solido non polverulento">
								<label for="snp">Solido non polverulento</label>
								<input type="radio" id="sp" name="statoFisico" value="Solido polverulento">
								<label for="sp">Solido polverulento</label>
								<input type="radio" id="fpa" name="statoFisico" value="Fangoso palabile">
								<label for="fpa">Fangoso palabile</label><br>
								<input type="radio" id="fpo" name="statoFisico" value="Fangoso pompabile">
								<label for="fpo">Fangoso pompabile</label>
								<input type="radio" id="liq" name="statoFisico" value="Liquido">
								<label for="liq">Liquido</label></td>
						</tr>
						<tr>
							<td colspan="2" class="dato">Descrizione del processo produttivo che ha originato il rifiuto (riportare una breve descrizione delle materie prime e del processo di lavorazione):</td>
							<td><input type="text" name="descrizione" maxlength="80"></td>
						</tr>			
					</table>
					<table id="info4">
						<tr class="hr">
							<th colspan="3">HP ASSEGNATE DAL PRODUTTORE<br>
							Ove pertinente, il rifiuto contiene o ragionevolmente puo’ contenere sostanze con le seguenti caratteristiche di pericolo?</th>
						</tr>
			<%		
					for(String s:hp){
						String[] x=s.split(";");
			%>
						<tr>
							<td style="width: 55%;"><input type="checkbox" name="hp" value="<%=x[0] %>"> <%=x[1] %></td>
							<td style="width: 10%;"><%=x[0] %></td>
							<td style="width: 35%;"><input type="text" name="<%=x[0]%> %>"></td>
						</tr>
			<%		}
			%>				
					</table>
			<%
				}
			%>
			<table id="info5">
				<tr class="hr">
					<th>Obiettivo dell’analisi. <br>
					Selezionare più di un obiettivo analitico potrebbe comportare l'applicazione di un sovrapprezzo dopo la convalida del laboratorio.</th>
				</tr>			
				<%
					if(obiettivi!=null && obiettivi.size()>0){
						for(String s:obiettivi){
				%>
							<tr>
								<td><input type="checkbox" name="obiettivi" value="<%=s %>"> <%=s %></td>
							</tr>
				<%
						}
					}
				%>
			</table>					
			<div id="info6">
				<p>CLICCANDO SU CONFERMA IL COMMITTENTE E/O IL PRODUTTORE, AI SENSI DEL DPR 445/2000 ART. 76, DICHIARA SOTTO LA PROPRIA RESPONSABILITÀ DICHIARA CHE:</p>
				
				<p>- IL CAMPIONE CONSEGNATO È RAPPRESENTATIVA DELL’INTERA MATRICE OGGETTO DI INDAGINE.<br>
				- LE SOSTANZE DA RICERCARE SONO QUELLE INDICATE NEL PIANO ANALITICO.</p>
				<p>LA PRESENTE RICHIESTA SARA’ PARTE INTEGRANTE DEL RAPPORTO DI PROVA ANALITICO CHE VERRA’ PRODOTTO.</p>
				<br>
				<div id="conferma">
					<input type="date" name="dataConferma" max="<%=today %>"><input type="button" onclick="validate()" value="Conferma">
				</div>
			<br><br><br><br>
			</div>
		</form>
	</div>
		
	<%@ include file="Footer.jsp" %>
</body>
</html>