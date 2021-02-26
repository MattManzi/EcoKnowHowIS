package ekh.strategy;

public class PianoValidator {
	public boolean modificaPianoVal(String dato, String input) {
		InputValidator validator = new InputValidator();
		try {
			validator.setValidatorStrategy(new ValidatorDouble());
			validator.setString(input);
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator: prezzo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
