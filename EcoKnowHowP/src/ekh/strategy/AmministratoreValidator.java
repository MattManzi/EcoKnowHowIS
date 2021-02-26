package ekh.strategy;

import java.util.ArrayList;

public class AmministratoreValidator {
	public boolean modificaProfiloVal(String dato, String input) {
		InputValidator validator = new InputValidator();
		int n=0;
		try {
			if (dato.equals("email")) {
				validator.setValidatorStrategy(new ValidatorEmail());
				n=50;
			}else 
				throw new Exception("ERRORE-AmministratoreValidator-modificaProfiloVal: invalid dato for admin");

			validator.setString(input);
			if (!validator.validator(n))
				throw new Exception("ERRORE-AmministratoreValidator-modificaProfiloVal: " + dato);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean passwordVal(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		try {
			validator.setValidatorStrategy(new ValidatorUsername());
			validator.setString(inputs.get(0)); // Password
			if (!validator.validator(15))
				throw new Exception("ERRORE-AmministratoreValidator-passwordVal: Password");

			validator.setString(inputs.get(1)); // Password2
			if (!validator.validator(15))
				throw new Exception("ERRORE-AmministratoreValidator-passwordVal: Conferma Password");

			if (!inputs.get(0).equals(inputs.get(1)))
				throw new Exception("ERRORE-AmministratoreValidator-passwordVal: Password Diverse");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
