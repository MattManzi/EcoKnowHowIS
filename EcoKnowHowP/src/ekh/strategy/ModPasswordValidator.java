package ekh.strategy;

import java.util.ArrayList;

public class ModPasswordValidator {
	public boolean validazione(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		try {
			validator.setString(inputs.get(0)); //Password
			if(!validator.validator(15))
				throw new Exception("ERRORE-ModPasswordValidator: Password");
			
			validator.setString(inputs.get(1)); //Password2
			if(!validator.validator(15))
				throw new Exception("ERRORE-ModPasswordValidator: Conferma Password");
			
			if(!inputs.get(0).equals(inputs.get(1))) 
				throw new Exception("ERRORE-ModPasswordValidator: Password Diverse");		
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
