<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) session.getAttribute("userRoles");
PianoBean pianoAdmin = null;
PianoBean piano = null;
ArrayList<ParametroBean> parametri=null;
ModuloBean modulo=null;
ArrayList<String> obiettivi=null;
ArrayList<String> hp=null;
Boolean key = false;

if (adminRoles != null && adminRoles.booleanValue()){
	pianoAdmin = (PianoBean) session.getAttribute("pianoAdmin");
	if(pianoAdmin==null){
		response.sendRedirect("./GestioneClientiAdmin.jsp");
		return;
	}else{
		key = true;	
		parametri=pianoAdmin.getContenuto();
		modulo=pianoAdmin.getModulo();
		obiettivi=modulo.getObiettivi();
		if(modulo.checkTipo()){
			hp=modulo.getHp();
		}
	}
}else if(userRoles != null && userRoles.booleanValue()) {	
		piano = (PianoBean) request.getAttribute("piano");
		if(piano==null){
			response.sendRedirect("./StoricoCliente.jsp");
			return;	
		}else{
			parametri=piano.getContenuto();
			modulo=piano.getModulo();
			obiettivi=modulo.getObiettivi();
			if(modulo.checkTipo()){
				hp=modulo.getHp();
			}
		}
} else {
	response.sendRedirect("./HomePage.jsp");
	return;
}

%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

</head>
<body>
	<%@ include file="NavAdmin.jsp" %>
	
	<div id="main">
		<div>
		
		</div>
		<div class="table">
			<div id="parametri" class="cell">
				<%
				if(parametri!=null && parametri.size()>0){
					for(ParametroBean p:parametri){
				%>		
					<div class="param">
						<h3><%=p.getSku() %></h3>
						<h3><%=p.getNome() %></h3>
						<h3><%=p.getPrezzo() %></h3>
					</div>	
				<%
					}
				}
				%>
			</div>
			<div id="modulo" class="cell">
			<%
				if(modulo!=null){
			%>		
				<table>
					<tr>
						<th style="width: 30%;"></th>
						<th style="width: 35%;">Produttore del rifiuto.</th>
						<th style="width: 35%;">Committente dell'analisi.</th>
					</tr>
					<tr>
						<td class="dato">Ragione Sociale:</td>
						<td><%=modulo.getRagioneSocialeProd() %></td>
						<td><%=modulo.getRagioneSocialeCom() %></td>
					</tr>	
					<tr>
						<td class="dato">Sede legale o operativa:</td>
						<td><%=modulo.getSedeLegaleProd() %></td>
						<td><%=modulo.getSedeLegaleCom() %></td>
					</tr>	
					<tr>
						<td class="dato">P.IVA:</td>
						<td><%=modulo.getpIvaProd() %></td>
						<td><%=modulo.getpIvaCom() %></td>
					</tr>	
					<tr>
						<td class="dato">Telefono:</td>
						<td><%=modulo.getTelefonoProd() %></td>
						<td><%=modulo.getTelefonoCom() %></td>
					</tr>	
					<tr>
						<td class="dato">E-mail:</td>
						<td><%=modulo.getEmailProd() %></td>
						<td><%=modulo.getEmailCom() %></td>
					</tr>
					<tr>
						<td class="dato">Data e Luogo di campionamento:</td>
						<td><%=modulo.getData() %></td>
						<td><%=modulo.getLuogo() %></td>
					</tr>
					<tr>
						<td class="dato">Nome e Cognome campionatore:</td>
						<td><%=modulo.getNomeCampionatore() %></td>
						<td><%=modulo.getCognomeCampionatore() %></td>
					</tr>
					<tr>
						<td colspan="2" class="dato">Norma di riferimento utilizzata per il campionamento:</td>
						<td><%=modulo.getNorma() %></td>
					</tr>	
					<tr>
						<td colspan="2" class="dato">Quantità campione in consegna al laboratorio<br>prelevato all’atto del campionamento:</td>
						<td><%=modulo.getQuantitaCampione() %></td>
					</tr>
					<tr>	
							<td>Note sul rapporto di prova:</td>
						<%
							if(key){
								if(modulo.getRapporto()){
							%>		
									<td><a href="">Download</a></td>	
							<%
								}else{
							%>		
									<td>Nessuna File Caricato</td>	
							<%	
								}										
							}else{
							%>																
								<td><a href="">Upload</a></td>	
							<%
							}	
						%>
						</tr>
				</table>
				<%
				if(modulo.checkTipo() && hp!=null && hp.size()>0){
				%>	
					<table>
						<tr>
							<td>Codice CER:</td>
							<td><%=modulo.getCer()%></td>
						</tr>
						<tr>
							<td>Stato Fisico:</td>
							<td><%=modulo.getStatoFisico()%></td>
						</tr>
						<tr>
							<td>Descrizione del processo produttivo:</td>
							<td><%=modulo.getDescrizione()%></td>
						</tr>
						<tr>	
							<td>Schede Dati di Sicurezza:</td>
						<%
							if(key){
								if(pianoAdmin.isSchedaDS()){
							%>		
									<td><a href="">Download</a></td>	
							<%
								}else{
							%>		
									<td>Nessuna File Caricato</td>	
							<%	
								}										
							}else{
							%>																
								<td><a href="">Upload</a></td>	
							<%
							}	
						%>
						</tr>
					</table>
					<table>
						<tr>
							<th>HP: </th>
						</tr>
						<%
						for(String s:hp){
							String[] x=s.split("-");							
						%>		
							<tr>
								<td><%=x[0] %></td>
								<td><%=x[1] %></td>
							</tr>
						<%		
						}
						%>				
					</table>
				<%
				}
				%>
				<table>
					<tr>
						<th>Obiettivo dell’analisi: </th>
					</tr>
					<%
					if(obiettivi!=null && obiettivi.size()>0){
						for(String s:obiettivi){
					%>
								<tr>
									<td><%=s %></td>
								</tr>
					<%
						}
					}				
					%>
				</table>
				<%						
				}				
				%>
			</div>	
		</div>
	</div>
	<br><br><br><br><br>
	<%@ include file="Footer.jsp" %>	
</body>
</html>