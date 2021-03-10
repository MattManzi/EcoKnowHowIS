//MATRICI

function cancellaMatrice(id){
	var scelta=confirm("Sei sicuro di voler eliminare la matrice?");
	if(scelta===true){
		location.href="Matrice?action=delete&id="+id;
	}
}

function selectMatriceMod(id){
	var scelta=confirm("Procedere con la modifica della matrice?");
	if(scelta===true){
		location.href="Matrice?action=select&id="+id;
	}
}

function confermaMatriceMod(action){
	var scelta=confirm("Salvare le modifiche?");
	if(scelta===true){
		a.action="Matrice?action="+action;
	}
}

function cancellaParMatrice(id){
	var scelta=confirm("Eliminare il parametro dalla matrice?");
	if(scelta===true){
		location.href="Parametro?action=delParM&id="+id;
	}
}


//PACCHETTI
function cancellaPacchetto(id){
	var scelta=confirm("Sei sicuro di voler eliminare il pacchetto?");
	if(scelta===true){
		location.href="Pacchetto?action=delete&id="+id;
	}
}

function selectPacchettoMod(id){
	var scelta=confirm("Procedere con la modifica del pacchetto?");
	if(scelta===true){
		location.href="Pacchetto?action=select&id="+id;
	}
}

//ParametriPacchetto
function canellcaParametroPacchetto(id){
	var scelta=confirm("Sei sicuro di voler rimuovere il parametro dal pacchetto?");
	if(scelta===true){
		location.href="Pacchetto?action=remParam&id="+id;
	}
}

//CLIENTI
function storicoClienti(id){
	var scelta=confirm("Sei sicuro di voler vedere lo storico del Cliente?");
	if(scelta===true){
		location.href="User?action=select&username="+id;
	}
}

function cancellaClienti(id){
	var scelta=confirm("Sei sicuro di voler rimuovere il Cliente?");
	if(scelta===true){
		location.href="User?action=delete&username="+id;
	}
}
       