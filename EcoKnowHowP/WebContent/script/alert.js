//MATRICI
function cancellaMatrice(id){
	var scelta=confirm("Sei sicuro di voler eliminare la matrice?");
	if(scelta===true){
		location.href="MatriceControl?action=delete&id="+id;
	}
}

function selectMatriceMod(id){
	var scelta=confirm("Procedere con la modifica della matrice?");
	if(scelta===true){
		location.href="MatriceControl?action=select&id="+id;
	}
}

function confermaMatriceMod(action){
	var scelta=confirm("Salvare le modifiche?");
	if(scelta===true){
		a.action="MatriceControl?action="+action;
	}
}

function cancellaParMatrice(id){
	var scelta=confirm("Eliminare il parametro dalla matrice?");
	if(scelta===true){
		location.href="ParametroControl?action=delParM&id="+id;
	}
}


//PACCHETTI
function cancellaPacchetto(id){
	var scelta=confirm("Sei sicuro di voler eliminare il pacchetto?");
	if(scelta===true){
		location.href="PacchettoControl?action=delete&id="+id;
	}
}

function selectPacchettoMod(id){
	var scelta=confirm("Procedere con la modifica del pacchetto?");
	if(scelta===true){
		location.href="PacchettoControl?action=select&id="+id;
	}
}
