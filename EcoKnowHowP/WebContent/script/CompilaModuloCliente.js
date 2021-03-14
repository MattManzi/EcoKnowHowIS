function validate() {
	var rsc = document.getElementById("rsc");
	var rsc2 = document.getElementById("rsc2");
	var slc = document.getElementById("slc");
	var slc2 = document.getElementById("slc2");	
	var pic = document.getElementById("pic");
	var pic2 = document.getElementById("pic2");
	var tfc = document.getElementById("tfc");
	var tfc2 = document.getElementById("tfc2");
	var emc = document.getElementById("emc");
	var emc2 = document.getElementById("emc2");
	
	var rscTD = document.getElementById("rscTD");
	var slcTD = document.getElementById("slcTD");
	var picTD = document.getElementById("picTD");
	var tfcTD = document.getElementById("tfcTD");
	var emcTD = document.getElementById("emcTD");
	var check = document.getElementById("check");

	var rs=false;
	var sl=false;
	var iva = false;
	var tel = false;
	var email = false;
	
	if((rsc.value == "" || rsc.value == "undefined") || ((rsc2.value == "" || rsc2.value == "undefined")&& !check.checked)){
		rscTD.style.color = "red";
		rscTD.innerHTML = "Ragione Sociale non inserità.";
	}else{
		rscTD.style.color = "black";
		rscTD.innerHTML = "Ragione Sociale:";
		rs=true;
	}
	
	if((slc.value == "" || slc.value == "undefined") || ((slc2.value == "" || slc2.value == "undefined")&& !check.checked)){
		slcTD.style.color = "red";
		slcTD.innerHTML = "Sede legale non inserità.";
	}else{
		slcTD.style.color = "black";
		slcTD.innerHTML = "Sede legale o operativa:";
		sl=true;
	}

	if ((pic.value == "" || pic.value == "undefined" || !pIva(pic.value)) || ((pic2.value == "" || pic2.value == "undefined" || !pIva(pic2.value)) && !check.checked)) {
		picTD.style.color = "red";
		if (pic.value == "" || pic.value == "undefined" || pic2.value == "" || pic2.value == "undefined" ) {
			picTD.innerHTML = "P.IVA non inserità.";
		} else {
			picTD.innerHTML = "P.IVA non valida.";
		}
	} else {
		picTD.style.color = "black";
		picTD.innerHTML = "P.IVA:";
		iva = true;
	}

	if ((tfc.value == "" || tfc.value == "undefined" || !phoneNumber(tfc.value)) || ((tfc2.value == "" || tfc2.value == "undefined" || !phoneNumber(tfc2.value)) && !check.checked)) {
		tfcTD.style.color = "red";
		if (tfc.value == "" || tfc.value == "undefined" || tfc2.value == "" || tfc2.value == "undefined") {
			tfcTD.innerHTML = "Telefono non inserito.";
		} else {
			tfcTD.innerHTML = "Telefono non valido.";
		}
	} else {
		tfcTD.style.color = "black";
		tfcTD.innerHTML = "Telefono:";
		tel = true;
	}

	if ((emc.value == "" || emc.value == "undefined" || !validateEmail(emc.value))|| ((emc2.value == "" || emc2.value == "undefined" || !validateEmail(emc2.value)) && !check.checked)) {
		emcTD.style.color = "red";
		if (emc.value == "" || emc.value == "undefined" || emc2.value == "" || emc2.value == "undefined") {
			emcTD.innerHTML = "E-mail non inserita.";
		} else {
			emcTD.innerHTML = "E-mail non valida.";
		}
	}else {
		emcTD.style.color = "black";
		emcTD.innerHTML = "E-mail:";
		email = true;
	}

	if (rs && sl && iva && tel && email) {
		document.getElementById("formCompilaModulo").submit();
	} else {
		return false;
	}
}


function validateEmail(text) {
	var letters = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if (text.match(letters)) {
		return true;
	} else {
		return false;
	}
}

function phoneNumber(text) {
	var letters = /^\d{5,15}$/;
	if (text.match(letters)) {
		return true;
	} else {
		return false;
	}
}

function pIva(text) {
	var letters = /(^[0-9]{11})$/;
	if (text.match(letters)) {
		return true;
	} else {
		return false;
	}
}