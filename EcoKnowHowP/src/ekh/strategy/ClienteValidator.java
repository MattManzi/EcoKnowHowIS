package ekh.strategy;

import java.util.ArrayList;

public class ClienteValidator {
	public boolean registrazioneVal(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();

		try {

			if (inputs.get(2)!=null && !inputs.get(2).equals("")) { 	// Settore
				String s = inputs.get(2);
				if (!s.equals("Prova1") && !s.equals("Prova2") && !s.equals("Prova3") && !s.equals("Prova4")
						&& !s.equals("Prova5"))
					throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Settore");
			}
			
			if(inputs.get(3)==null || inputs.get(3).equals("") || inputs.get(3).length()>50)	// RagioneSociale
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Ragione Sociale");
			

			validator.setValidatorStrategy(new ValidatorAllLetter());
			validator.setString(inputs.get(0)); // Nome
			if (!validator.validator(30))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Nome");

			validator.setString(inputs.get(1)); // Cognome
			if (!validator.validator(30))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Cognome");

			validator.setString(inputs.get(4)); // Comune
			if (!validator.validator(30))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Comune");

			validator.setValidatorStrategy(new ValidatorAlphaNumeric());
			validator.setString(inputs.get(5)); // Via
			if (!validator.validator(40))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Via");

			validator.setString(inputs.get(6)); // Civico
			if (!validator.validator(10))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Civico");

			validator.setString(inputs.get(8)); // SDI
			if (!validator.validator(6))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: SDI");

			validator.setValidatorStrategy(new ValidatorPIvaCF());
			validator.setString(inputs.get(7)); // pIva o CF
			if (!validator.validator(inputs.get(7).length()))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: PIVA CF");

			validator.setValidatorStrategy(new ValidatorNumber());
			validator.setString(inputs.get(9)); // CAP
			if (!validator.validator(5))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: CAP");

			validator.setString(inputs.get(10)); // Telefono
			if (!validator.validator(inputs.get(10).length()))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Telefono");

			validator.setValidatorStrategy(new ValidatorEmail());
			if (inputs.get(11) != null && inputs.get(11).length() > 0) {
				validator.setString(inputs.get(11)); // PEC
				if (!validator.validator(50))
					throw new Exception("ERRORE-ClienteValidator-registrazioneVal: PEC");
			}

			validator.setString(inputs.get(12)); // EMAIL
			if (!validator.validator(50))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: EMAIL");

			validator.setValidatorStrategy(new ValidatorUsername());
			validator.setString(inputs.get(13)); // Username
			if (!validator.validator(20))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Username");

			validator.setValidatorStrategy(new ValidatorPassword());
			validator.setString(inputs.get(14)); // Password
			if (!validator.validator(15))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Password");

			if (!inputs.get(14).equals(inputs.get(15)))
				throw new Exception("ERRORE-ClienteValidator-registrazioneVal: Password Diverse");

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
				throw new Exception("ERRORE-ClienteValidator-passwordVal: Password");

			if (!inputs.get(0).equals(inputs.get(1)))
				throw new Exception("ERRORE-ClienteValidator-passwordVal: Password Diverse");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean modificaProfiloVal(String dato, String input) {
		InputValidator validator = new InputValidator();
		int n = 0;
		try {
			if (dato.equals("nome") || dato.equals("cognome") || dato.equals("funzioneAziendale")
					|| dato.equals("ragioneSociale") || dato.equals("comune")) {
				validator.setValidatorStrategy(new ValidatorAllLetter());
				if (dato.equals("nome") || dato.equals("cognome") || dato.equals("comune")) {
					n = 30;
				} else {
					n = 50;
				}
			} else if (dato.equals("via") || dato.equals("civico") || dato.equals("sdi")) {
				validator.setValidatorStrategy(new ValidatorAlphaNumeric());
				if (dato.equals("via")) {
					n = 40;
				} else if (dato.equals("civico")) {
					n = 10;
				} else if (dato.equals("pIva")) {
					n = 11;
				} else if (dato.equals("cf")) {
					n = 16;
				} else if (dato.equals("sdi")) {
					n = 6;
				}
			} else if (dato.equals("ivaCF")) {
				validator.setValidatorStrategy(new ValidatorPIvaCF());
			} else if (dato.equals("cap") || dato.equals("telefono")) {
				validator.setValidatorStrategy(new ValidatorNumber());
				if (dato.equals("cap")) {
					n = 5;
				} else if (dato.equals("telefono")) {
					n = 10;
				}
			} else if (dato.equals("pec") || dato.equals("email")) {
				validator.setValidatorStrategy(new ValidatorEmail());
				n = 50;
			} else
				throw new Exception("ERRORE-ClienteValidator-modificaProfiloVal: invalid dato for user");

			validator.setString(input);
			if (!validator.validator(n))
				throw new Exception("ERRORE-ClienteValidator-modificaProfiloVal: " + dato);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
