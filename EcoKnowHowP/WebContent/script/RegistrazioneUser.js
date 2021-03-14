$(document).ready(function() {
	$.validator.addMethod('email_val', function(value, element) {
		return this.optional(element) || /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value);
	});
	$.validator.addMethod('username_val', function(value, element) {
		return this.optional(element) || /^\w{5,15}$/.test(value);
	});
	$.validator.addMethod('allLetter_val', function(value, element) {
		return this.optional(element) || /^[A-Za-z]+(\s?[A-Za-z])*$/.test(value);
	});
	$.validator.addMethod('phoneNumber_val', function(value, element) {
		return this.optional(element) || /^\d{8,15}$/.test(value);
	});
	$.validator.addMethod('cap_val', function(value, element) {
		return this.optional(element) || /^\d{5}$/.test(value);
	});
	$.validator.addMethod('sdi_val', function(value, element) {
		return this.optional(element) || /^\w+$/.test(value);
	});
	$.validator.addMethod('pIvaCF_val', function(value, element) {
		return this.optional(element) || /^[A-Z]{6}\d{2}[A-Z]\d{2}[A-Z]\d{3}[A-Z]$|(^[0-9]{11})$/.test(value);
	});
	$.validator.addMethod('password_val', function(value, element) {
		return this.optional(element) || /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})/.test(value);
	});

	$("#formRegistrazioneUser").validate({
		rules: {
			'nome': {
				required: true,
				allLetter_val: true
			},
			'cognome': {
				required: true,
				allLetter_val: true
			},
			'settore': {
				required: true
			},
			'telefono': {
				required: true,
				phoneNumber_val: true
			},
			'ragioneSociale': {
				required: true
			},
			'via': {
				required: true
			},
			'civico': {
				required: true
			},
			'cap': {
				required: true,
				cap_val: true
			},
			'comune': {
				required: true,
				allLetter_val: true
			},
			'ivaCF': {
				required: true,
				pIvaCF_val: true
			},
			'pec': {
				email_val: true
			},
			'sdi': {
				required: true,
				sdi_val: true
			},
			'email': {
				required: true,
				email_val: true
			},
			'username': {
				required: true,
				minlength: 5,
				username_val: true
			},
			'password': {
				required: true,
				minlength: 5,
				password_val: true
			},
			'password2': {
				required: true,
				equalTo: '#password'
			}
		},
		messages: {
			'nome': {
				required: "Inserire un nome.",
				allLetter_val: "Nome non valido."
			},
			'cognome': {
				required: "Inserire un cognome.",
				allLetter_val: "Cognome non valido."
			},
			'settore': {
				required: "Selezionare un settore."
			},
			'telefono': {
				required: "Inserire un numero di telefono.",
				phoneNumber_val: "Numero non valido."
			},
			'ragioneSociale': {
				required: "Inserire una Ragione Sociale."
			},
			'via': {
				required: "Inserire una via."
			},
			'civico': {
				required: "Inserire un civico."
			},
			'cap': {
				required: "Inserire un CAP.",
				cap_val: "CAP non valido."
			},
			'comune': {
				required: "Inserire un comune.",
				allLetter_val: "Comune non valido."
			},
			'ivaCF': {
				required: "Inserire una p.Iva o un CF.",
				pIvaCF_val: "P.IVA o CF non valido."
			},
			'sdi': {
				required: "Inserire un SDI.",
				sdi_val: "SDI non valido."
			},
			'pec': {
				email_val: "PEC non valida."
			},
			'email': {
				required: "Inserire una e-mail.",
				email_val: "E-mail non valida."
			},
			'username': {
				required: "Inserire un username.",
				minlength: "Inserire almeno 5 caratteri.",
				username_val: "Username non valido."
			},
			'password': {
				required: "Inserire una password.",
				minlength: "Inserire almeno 5 caratteri.",
				password_val: "Password non valida."
			},
			'password2': {
				required: "Confermare la password.",
				equalTo: "Password diversa."
			}
		}
	});
});