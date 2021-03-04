package ekh.strategy;

import java.util.ArrayList;

public class PianoValidator {
	public boolean dati1PianoVal(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		try {
			validator.setValidatorStrategy(new ValidatorAllLetterPoint());
			validator.setString(inputs.get(0)); // ragioneSocialeProd
			if (!validator.validator(6))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: ragioneSocialeProd");

			validator.setValidatorStrategy(new ValidatorAlphaNumeric());
			validator.setString(inputs.get(1)); // sedeLegaleProd
			if (!validator.validator(100))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: sedeLegaleProd");

			validator.setValidatorStrategy(new ValidatorPIvaCF());
			validator.setString(inputs.get(2)); // pIvaProd
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: pIvaProd");

			validator.setValidatorStrategy(new ValidatorNumber());
			validator.setString(inputs.get(3)); // telefonoProd
			if (!validator.validator(10))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: telefonoProd");

			validator.setValidatorStrategy(new ValidatorEmail());
			validator.setString(inputs.get(4)); // emailProd
			if (!validator.validator(50))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: emailProd");

			if (inputs.size() > 5) {
				validator.setValidatorStrategy(new ValidatorAllLetterPoint());
				validator.setString(inputs.get(5)); // ragioneSocialeCom
				if (!validator.validator(6))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: ragioneSocialeCom");

				validator.setValidatorStrategy(new ValidatorAlphaNumeric());
				validator.setString(inputs.get(6)); // sedeLegaleCom
				if (!validator.validator(100))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: sedeLegaleCom");

				validator.setValidatorStrategy(new ValidatorPIvaCF());
				validator.setString(inputs.get(7)); // pIvaCom
				if (!validator.validator(0))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: pIvaCom");

				validator.setValidatorStrategy(new ValidatorNumber());
				validator.setString(inputs.get(8)); // telefonoCom
				if (!validator.validator(10))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: telefonoCom");

				validator.setValidatorStrategy(new ValidatorEmail());
				validator.setString(inputs.get(9)); // emailCom
				if (!validator.validator(50))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: emailCom");
			}

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean dati2PianoVal(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		try {
			validator.setValidatorStrategy(new ValidatorAllLetterPoint());
			validator.setString(inputs.get(0)); // ragioneSocialeProd
			if (!validator.validator(6))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: ragioneSocialeProd");

			validator.setValidatorStrategy(new ValidatorAlphaNumeric());
			validator.setString(inputs.get(1)); // sedeLegaleProd
			if (!validator.validator(100))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: sedeLegaleProd");

			validator.setValidatorStrategy(new ValidatorPIvaCF());
			validator.setString(inputs.get(2)); // pIvaProd
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: pIvaProd");

			validator.setValidatorStrategy(new ValidatorNumber());
			validator.setString(inputs.get(3)); // telefonoProd
			if (!validator.validator(10))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: telefonoProd");

			validator.setValidatorStrategy(new ValidatorEmail());
			validator.setString(inputs.get(4)); // emailProd
			if (!validator.validator(50))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: emailProd");

			if (inputs.size() > 5) {
				validator.setValidatorStrategy(new ValidatorAllLetterPoint());
				validator.setString(inputs.get(5)); // ragioneSocialeCom
				if (!validator.validator(6))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: ragioneSocialeCom");

				validator.setValidatorStrategy(new ValidatorAlphaNumeric());
				validator.setString(inputs.get(6)); // sedeLegaleCom
				if (!validator.validator(100))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: sedeLegaleCom");

				validator.setValidatorStrategy(new ValidatorPIvaCF());
				validator.setString(inputs.get(7)); // pIvaCom
				if (!validator.validator(0))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: pIvaCom");

				validator.setValidatorStrategy(new ValidatorNumber());
				validator.setString(inputs.get(8)); // telefonoCom
				if (!validator.validator(10))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: telefonoCom");

				validator.setValidatorStrategy(new ValidatorEmail());
				validator.setString(inputs.get(9)); // emailCom
				if (!validator.validator(50))
					throw new Exception("ERRORE-PianoValidator-dati1PianoVal: emailCom");
			}

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean modificaPianoVal(String dato, String input) {
		InputValidator validator = new InputValidator();
		try {
			if (dato.equals("prezzo")) {
				validator.setValidatorStrategy(new ValidatorDouble());
			} else
				throw new Exception("ERRORE-PianoValidator-modificaPianoVal: invalid dato");

			validator.setString(input);
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator: prezzo");

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
