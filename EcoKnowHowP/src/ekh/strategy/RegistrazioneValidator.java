package ekh.strategy;

import java.util.ArrayList;

public class RegistrazioneValidator {
	public boolean validazione(ArrayList<String> inputs) {
		InputValidator validator=new InputValidator();
		boolean pIva=false;
		boolean cf=false;		
		
		try {
			validator.setValidatorStrategy(new ValidatorAllLetter());			
			validator.setString(inputs.get(0)); //Nome
			if(!validator.validator(30))
				throw new Exception("ERRORE-RegistrazioneValidator: Nome");
			
			validator.setString(inputs.get(1)); //Cognome
			if(!validator.validator(30))
				throw new Exception("ERRORE-RegistrazioneValidator: Cognome");
			
			validator.setString(inputs.get(2)); //FunzioneAziendale
			if(!validator.validator(50))
				throw new Exception("ERRORE-RegistrazioneValidator: Funzione Aziendale");
			
			validator.setString(inputs.get(3)); //RagioneSociale
			if(!validator.validator(50))
				throw new Exception("ERRORE-RegistrazioneValidator: Ragione Sociale");
			
			validator.setString(inputs.get(4)); //Comune
			if(!validator.validator(30))
				throw new Exception("ERRORE-RegistrazioneValidator: Comune");
			
			validator.setValidatorStrategy(new ValidatorAlphaNumeric());			
			validator.setString(inputs.get(5)); //Via	
			if(!validator.validator(40))
				throw new Exception("ERRORE-RegistrazioneValidator: Via");
			
			validator.setString(inputs.get(6)); //Civico
			if(!validator.validator(10))
				throw new Exception("ERRORE-RegistrazioneValidator: Civico");
			
			if(inputs.get(7)!=null && inputs.get(7).length()>0 ) {
				validator.setString(inputs.get(7)); //pIva
				if(!validator.validator(11)) {
					throw new Exception("ERRORE-RegistrazioneValidator: PIVA");
				}else {
					pIva=true;
				}				
			}
			
			if(inputs.get(8)!=null && inputs.get(8).length()>0 ) {
				validator.setString(inputs.get(8)); //CF				
				if(!validator.validator(16)) {
					throw new Exception("ERRORE-RegistrazioneValidator: CF");
				}else {
					cf=true;
				}
			}
			
			validator.setString(inputs.get(9)); //SDI
			if(!validator.validator(6))
				throw new Exception("ERRORE-RegistrazioneValidator: SDI");
			
			validator.setValidatorStrategy(new ValidatorNumber());			
			validator.setString(inputs.get(10)); //CAP
			if(!validator.validator(5))
				throw new Exception("ERRORE-RegistrazioneValidator: CAP");
			
			validator.setString(inputs.get(11)); //Telefono			
			if(!validator.validator(10))
				throw new Exception("ERRORE-RegistrazioneValidator: Telefono");
			
			validator.setValidatorStrategy(new ValidatorEmail());	
			if(inputs.get(12)!=null && inputs.get(12).length()>0 ) {
				validator.setString(inputs.get(12)); //PEC
				if(!validator.validator(50))
					throw new Exception("ERRORE-RegistrazioneValidator: PEC");
			}
			
			validator.setString(inputs.get(13)); //EMAIL
			if(!validator.validator(50))
				throw new Exception("ERRORE-RegistrazioneValidator: EMAIL");
			
			validator.setValidatorStrategy(new ValidatorUserPass());			
			validator.setString(inputs.get(14)); //Username			
			if(!validator.validator(20) || inputs.get(14).length()<5)
				throw new Exception("ERRORE-RegistrazioneValidator: Username");
			
			validator.setString(inputs.get(15)); //Password
			if(!validator.validator(15))
				throw new Exception("ERRORE-RegistrazioneValidator: Password");
			
			validator.setString(inputs.get(16)); //Password2
			if(!validator.validator(15))
				throw new Exception("ERRORE-RegistrazioneValidator: Conferma Password");
			
			if(!inputs.get(15).equals(inputs.get(16))) 
				throw new Exception("ERRORE-RegistrazioneValidator: Password Diverse");			
			
			if(!pIva && !cf)
				throw new Exception("ERRORE-RegistrazioneValidator: cf e pIva assenti");
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}
