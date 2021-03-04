package ekh.strategy;

import java.util.ArrayList;

public class ModuloValidator {
	public boolean datiPrincipaliValidator(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		
		try {
			
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
}
