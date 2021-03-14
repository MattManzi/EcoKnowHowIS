package ekh.strategy;

import java.util.ArrayList;

public class MatriceValidator {
	public boolean aggiuntaVal(ArrayList<String> inputs) {
		InputValidator validator=new InputValidator();
		try {
			validator.setValidatorStrategy(new ValidatorSDI());	
			validator.setString(inputs.get(0)); //Nome
			if(!validator.validator(20))
				throw new Exception("ERRORE-MatriceValidator-aggiuntaVal: Nome");
			
			validator.setString(inputs.get(1)); //Sottotitolo
			if(!validator.validator(50))
				throw new Exception("ERRORE-MatriceValidator-aggiuntaVal: Sottotitolo");
			
			validator.setString(inputs.get(2)); //Descrizione
			if(!validator.validator(250))
				throw new Exception("ERRORE-MatriceValidator-aggiuntaVal: Descrizione");	
			
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean modificaMatriceVal(String dato, String input) {
		InputValidator validator = new InputValidator();
		int n=0;
		try {
			if (dato.equals("nome") || dato.equals("sottotitolo") || dato.equals("nota")) {
				validator.setValidatorStrategy(new ValidatorSDI());
				if(dato.equals("nome")) {
					n=20;
				}else if(dato.equals("sottotitolo")) {
					n=50;
				}else if(dato.equals("nota")) {
					n=250;
				}
			}else
				throw new Exception("ERRORE-ClienteValidator-modificaProfiloVal: invalid dato for user");

			validator.setString(input);
			if (!validator.validator(n))
				throw new Exception("ERRORE-MatriceValidator-modificaMatriceVal: " + dato);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
