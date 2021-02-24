package ekh.strategy;

import java.util.ArrayList;

public class AggiungiMatriceValidator {
	public boolean validazione(ArrayList<String> inputs) {
		InputValidator validator=new InputValidator();
		try {
			validator.setValidatorStrategy(new ValidatorAlphaNumeric());	
			validator.setString(inputs.get(0)); //Nome
			if(!validator.validator(20))
				throw new Exception("ERRORE-AggiuntaMatriceValidator: Nome");
			
			validator.setString(inputs.get(1)); //Sottotitolo
			if(!validator.validator(50))
				throw new Exception("ERRORE-AggiuntaMatriceValidator: Sottotitolo");
			
			validator.setString(inputs.get(2)); //Descrizione
			if(!validator.validator(250))
				throw new Exception("ERRORE-AggiuntaMatriceValidator: Descrizione");	
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
