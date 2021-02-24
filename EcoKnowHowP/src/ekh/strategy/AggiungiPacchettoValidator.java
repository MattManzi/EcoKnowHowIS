package ekh.strategy;

import java.util.ArrayList;

public class AggiungiPacchettoValidator {
	public boolean validazione(ArrayList<String> inputs) {
		InputValidator validator=new InputValidator();
		try {
			validator.setValidatorStrategy(new ValidatorAlphaNumeric());	
			validator.setString(inputs.get(0)); //Nome
			if(!validator.validator(30))
				throw new Exception("ERRORE-AggiuntaPacchettoValidator: Nome");
	
			validator.setString(inputs.get(1)); //Descrizione
			if(!validator.validator(250))
				throw new Exception("ERRORE-AggiuntaPacchettoValidator: Descrizione");
	
			validator.setValidatorStrategy(new ValidatorNumber());			
			validator.setString(inputs.get(2)); //idMatrice
			if(!validator.validator(10))
				throw new Exception("ERRORE-AggiuntaPacchettoValidator: idMatrice");
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
