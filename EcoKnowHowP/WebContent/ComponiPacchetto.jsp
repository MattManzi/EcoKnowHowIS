<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ekh.bean.*, java.util.*"%>
<%
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
Collection<?> parametri=(Collection<?>) request.getAttribute("parametri");
PacchettoBean pacchetto=(PacchettoBean) request.getSession().getAttribute("creaPacchetto");

if((adminRoles != null && adminRoles.booleanValue()) 
		|| (userRoles!= null && userRoles.booleanValue())){
	if(parametri == null){
		response.sendRedirect(response.encodeRedirectURL("./Parametro?action=componi"));
		return;
	}
}else{
	response.sendRedirect("./HomePage.jsp");
	return;
}
%>       
<!DOCTYPE html>
<html>
<head>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/Template.css" rel="stylesheet">
<link href="css/ComponiPacchetto.css" rel="stylesheet">
<title>Insert title here</title>
<script>
function myFunction() {
  var x = document.getElementById("nav");
  if (x.className === "nav") {
    x.className += " responsive";
  } else {
    x.className = "nav";
  }
}
</script>
</head>
<body>
	<%if(adminRoles != null){%>
		<%@ include file="NavAdmin.jsp" %>
	<%}else{%>
		<%@ include file="NavUser.jsp" %>
	<%}%>
	
	<div class="divTable">
		<div class="divRow">
			<div class="contenitore_parametri">
				<%
				if ( parametri.size() > 0) {
					Iterator<?> it = parametri.iterator();
					while (it.hasNext()) {
						ParametroBean bean = (ParametroBean) it.next();
				%>
						<div class="prodottoCatalogo">
							<h2 class="nome__prodotto" > <a href="" > <%=bean.getNome()%> </a> </h2> 
							<h3 class="nome__prodotto" > <a href="" > <%=bean.getSku()%> </a> </h3>
							<h3 class="nome__prodotto" > <a href="" > <%=bean.getPrezzo()%> </a> </h3>
							<button>Dettagli</button>
							<a href="<%=response.encodeURL("Pacchetto?action=componi&function=aggiungi&id="+bean.getId())%>">Aggiungi </a>
						</div>			
				<%	}
				}%>
			</div >
			<div class="carrello ">
				<%
					if(pacchetto.getContenuto().size()== 0){
				%>				
						<p>Il tuo pacchetto è vuoto. Aggiungi i parametri per comporre il pacchetto</p>
				<%
					}else{
						Iterator<?> it = pacchetto.getContenuto().iterator();
						while (it.hasNext()) {
							ParametroBean bean = (ParametroBean) it.next();
				%>	
							<div>
							<a href="<%=response.encodeURL("Pacchetto?action=componi&function=rimuovi&id="+bean.getId())%>">X</a>
								<a href="" > <%=bean.getNome()%>  </a> 	
								<a href="" >   <%=bean.getPrezzo()%> </a> 		
							</div>		
				<%	}  
				%>
					<p> Prezzo:<%=pacchetto.calcolaPrezzo()%> €</p>
					<a href="<%=response.encodeURL("Pacchetto?action=salva")%>">Procedi</a>
				<%		
					}
				%>
			</div>
		</div>
	</div>
	
	<%@ include file="Footer.jsp" %>
</body>
</html>