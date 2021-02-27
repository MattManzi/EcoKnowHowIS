package ekh.strategy;

public class MatriceValidator {

	public boolean modificaMatriceVal(String dato, String input) {
		InputValidator validator = new InputValidator();
		int n=0;
		try {
			if (dato.equals("nome") || dato.equals("sottotitolo") || dato.equals("descrizione")) {
				validator.setValidatorStrategy(new ValidatorAlphaNumeric());
				if(dato.equals("nome")) {
					n=20;
				}else if(dato.equals("sottotitolo")) {
					n=50;
				}else if(dato.equals("descrizione")) {
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
