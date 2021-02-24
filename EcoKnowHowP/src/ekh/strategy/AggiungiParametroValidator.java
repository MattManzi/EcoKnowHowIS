package ekh.strategy;

import java.util.ArrayList;

public class AggiungiParametroValidator {
	public boolean validazione(ArrayList<String> inputs) {
		InputValidator validator=new InputValidator();
				
		try {
			validator.setValidatorStrategy(new ValidatorNumber());			
			validator.setString(inputs.get(0)); //idMatrice
			if(!validator.validator(10))
				throw new Exception("ERRORE-AggiungiParametroValidator: idMatrice");
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}
