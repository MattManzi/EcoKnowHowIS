function cancellaMatrice(id){
	var scelta=confirm("Sei sicuro di voler eliminare la matrice?");
	if(scelta===true){
		location.href="RimuoviMatriceServlet?id="+id;
	}
}

function selectMatriceMod(id){
	var scelta=confirm("Procedere con la modifica della matrice?");
	if(scelta===true){
		location.href="SelectMatriceAdminServlet?id="+id;
	}
}

function confermaMatriceMod(id){
	var scelta=confirm("Salvare le modifiche?");
	if(scelta===true){
		location.href="ModificaMatriceServlet?id="+id;
	}
}

function annullaModifica(){
	var scelta=confirm("Annullare?");
	if(scelta===true){
		location.href="ViniliControl?action=annullaModifica";
	}
}


function resetRicerca(){
	var scelta=confirm("Eliminare i filtri di ricerca?");
	if(scelta===true){
		location.href="RicercaAvanzata?action=reset";
	}
}

function aggiuntaGenere(){
	var scelta=confirm("Aggiungere genere?");
	if(scelta===true){
		location.href="GenereArtistaControl?action=aggiungi&opzione=genere";
	}
}

function cancellaAdmin(nome){
	var scelta=confirm("Sei sicuro?");
	if(scelta===true){
		location.href="AdminControl?action=cancella&username="+nome;
	}
}