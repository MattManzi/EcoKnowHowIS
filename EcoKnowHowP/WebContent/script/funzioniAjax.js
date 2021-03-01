//Controllo Registrazione user
function testUser(username){
	var http = new XMLHttpRequest();
	var url = "ajaxRegistrazioneUser";
	var params = "action=username&dato="+ username;
	http.open("GET", url+"?"+params, true);
	http.onreadystatechange = function() {
		var txt=this.responseText;
		if(txt.length>0){
			$("#username").css("color","red");
			$("#username").css("border-color","red");
			$('#usernameTd').css("color", "red")
			$('#usernameTd').html('<td id="usernameTd"><label for="username">Username: gi&#224; esistente.</label></td>');			
		}else{
			$("#username").css("color","black");
			$("#username").css("border-color","#c0c0c0");
			$('#usernameTd').css("color", "black")
			$('#usernameTd').html('<td id="usernameTd"><label for="username">Username:</label></td>');
		}
  	};
	http.send();
}

//Controllo Registrazione email
function testEmail(email){
	var http = new XMLHttpRequest();
	var url = "ajaxRegistrazioneUser";
	var params = "action=email&dato="+ email;
	http.open("GET", url+"?"+params, true);
	http.onreadystatechange = function() {
		var txt=this.responseText;
		if(txt.length>0){
			$("#email").css("color","red");
			$("#emial").css("border-color","red");
			$('#emailTd').css("color", "red")
			$('#emailTd').html('<td id="emailTd"><label for="email">Email: gi&#224; esistente.</label></td>');
		}else{
			$("#email").css("color","black");
			$("#email").css("border-color","#c0c0c0");
			$('#emailTd').css("color", "black")
			$('#emialTd').html('<td id="emailTd"><label for="email">E-mail:</label></td>');
		}
  	};
	http.send();
}
