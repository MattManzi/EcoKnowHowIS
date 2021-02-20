$(document).ready(function(){
	
	$.validator.addMethod('email_val', function(value, element){
		return this.optional(element) || /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value);
	});
	$.validator.addMethod('userPass_val', function(value, element){
		return this.optional(element) || /^\w+([\.-]?\w+)*$/.test(value);
	});
	$.validator.addMethod('allLetter_val', function(value, element){
		return this.optional(element) || /^[A-Za-z]+(\s?[A-Za-z])*$/.test(value);
	});
	$.validator.addMethod('phoneNumber_val', function(value, element){
		return this.optional(element) || /^\d{10}$/.test(value);
	});
	$.validator.addMethod('cap_val', function(value, element){
		return this.optional(element) || /^\d{5}$/.test(value);
	});
	$.validator.addMethod('alphaNumeric_val', function(value, element){
		return this.optional(element) || /^\w+(\s?\w+)*$/.test(value);
	});
	
	$("#formRegistrazioneUser").validate({
		rules:{
			'nome':{
				required:true,
				allLetter_val:true
			},
			'cognome':{
				required:true,
				allLetter_val:true
			},
			'funzioneAziendale':{
				required:true,
				allLetter_val:true
			},
			'telefono':{
				required:true,
				phoneNumber_val:true
			},
			'ragioneSociale':{
				required:true,
				allLetter_val:true
			},
			'via':{
				required:true,
				alphaNumeric_val:true
			},
			'civico':{
				required:true,
				alphaNumeric_val:true
			},
			'cap':{
				required:true,
				cap_val:true
			},
			'comune':{
				required:true,
				allLetter_val:true
			},
			'pIva':{
				alphaNumeric_val:true
			},
			'cf':{
				alphaNumeric_val:true
			},
			'pec':{
				email_val:true
			},
			'sdi':{
				required:true,
				alphaNumeric_val:true
			},
			'email':{
				required:true,
				email_val:true
			},
			'username':{
				required:true,
				minlength:5,
				userPass_val:true
			},
			'password':{
				required:true,
				userPass_val:true
			},
			'password2':{
				required:true,
				equalTo: '#password'
			}
		},
		messages:{
			'nome':{
				required:"Inserire un nome.",
				allLetter_val:"Nome non valido."
			},
			'cognome':{
				required:"Inserire un cognome.",
				allLetter_val:"Cognome non valido."
			},
			'funzioneAziendale':{
				required:"Inserire una Funzione Aziendale.",
				allLetter_val:"Funzione Aziendale non valida."
			},
			'telefono':{
				required:"Inserire un numero di telefono.",
				phoneNumber_val:"Numero non valido."
			},
			'ragioneSociale':{
				required:"Inserire una Ragione Sociale.",
				allLetter_val:"Ragione Sociale non valida."
			},
			'via':{
				required:"Inserire una via.",
				alphaNumeric_val:"Via non valida."
			},
			'civico':{
				required:"Inserire un civico.",
				alphaNumeric_val:"Civico non valido."
			},
			'cap':{
				required:"Inserire un CAP.",
				cap_val:"CAP non valido."
			},
			'comune':{
				required:"Inserire un comune.",
				allLetter_val:"Comune non valido."
			},
			'pIva':{
				alphaNumeric_val:"P.IVA non valida."
			},
			'cf':{
				alphaNumeric_val:"CF non valido."
			},
			'sdi':{
				required:"Inserire un SDI.",
				alphaNumeric_val:"SDI non valido."
			},
			'pec':{
				email_val:"PEC non valida."
			},
			'email':{
				required:"Inserire una e-mail.",
				email_val:"E-mail non valida."
			},
			'username':{
				required:"Inserire un username.",
				minlength:"Inserire almeno 5 caratteri.",
				userPass_val:"Username non valido."
			},
			'password':{
				required:"Inserire una password.",
				userPass_val:"Password non valida."
			},
			'password2':{
				required:"Confermare la password.",
				equalTo:"Password diversa."
			}
		}			
	});	
});