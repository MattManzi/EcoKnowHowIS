package ekh.strategy;

import java.util.ArrayList;

import ekh.bean.ModuloBean;

public class PianoValidator {
	public boolean dati1PianoVal(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		try {
			if(inputs.get(0)==null || inputs.get(0).equals("") || inputs.get(0).length()>50)	// RagioneSocialeProd
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: Ragione Sociale");

			if(inputs.get(1)==null || inputs.get(1).equals("") || inputs.get(1).length()>100)	// sedeLegaleProd
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: sedeLegaleProd");

			validator.setValidatorStrategy(new ValidatorPIvaCF());
			validator.setString(inputs.get(2)); // pIvaProd
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: pIvaProd");

			validator.setValidatorStrategy(new ValidatorPhone());
			validator.setString(inputs.get(3)); // telefonoProd
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: telefonoProd");

			validator.setValidatorStrategy(new ValidatorEmail());
			validator.setString(inputs.get(4)); // emailProd
			if (!validator.validator(50))
				throw new Exception("ERRORE-PianoValidator-dati1PianoVal: emailProd");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean dati2PianoVal(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		try {
			if(inputs.get(0)==null || inputs.get(0).equals("") || inputs.get(0).length()>50)	// RagioneSocialeComm
				throw new Exception("ERRORE-PianoValidator-dati2PianoVal: Ragione Sociale");

			if(inputs.get(1)==null || inputs.get(1).equals("") || inputs.get(1).length()>100)	// sedeLegaleComm
				throw new Exception("ERRORE-PianoValidator-dati2PianoVal: sedeLegaleProd");

			validator.setValidatorStrategy(new ValidatorPIvaCF());
			validator.setString(inputs.get(2)); // pIvaComm
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati2PianoVal: pIvaProd");

			validator.setValidatorStrategy(new ValidatorPhone());
			validator.setString(inputs.get(3)); // telefonoComm
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati2PianoVal: telefonoProd");

			validator.setValidatorStrategy(new ValidatorEmail());
			validator.setString(inputs.get(4)); // emailComm
			if (!validator.validator(50))
				throw new Exception("ERRORE-PianoValidator-dati2PianoVal: emailProd");
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean dati3PianoVal(ArrayList<String> inputs) {
		InputValidator validator = new InputValidator();
		try {
			if(inputs.get(2)==null || inputs.get(2).equals("") || inputs.get(2).length()>100)	// Luogo
				throw new Exception("ERRORE-PianoValidator-dati3PianoVal: Luogo");
			
			validator.setValidatorStrategy(new ValidatorDate());
			validator.setString(inputs.get(0)); // data
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati3PianoVal: data");

			validator.setString(inputs.get(1)); // data
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-dati3PianoVal: data Conferma");

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean obiettiviPianoVal(ArrayList<String> input, ModuloBean modulo) {	
		try {
			ArrayList<String> obiettivi=new ArrayList<String>();
			obiettivi=modulo.getObiettivi();
		
			int i=0;
			for(String s:input) {
				for(String o:obiettivi) {
					if(s.equals(o)) {
						i++;
						break;
					}
				}
			}
			
			if(input.size()==i) {
				return true;
			}else 
				throw new Exception("ERRORE-PianoValidator-obiettiviPianoVal: obiettivi");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return false;		
	}
	
	public boolean hpPianoVal(ArrayList<String> input, ModuloBean modulo) {	
		try {
			ArrayList<String> hp=new ArrayList<String>();
			hp=modulo.getHp();
			
			int i=0;
			for(String s:input) {
				String[] split1=s.split("-");
				for(String h:hp) {
					String[] split2=h.split(";");
					if(split1[0].equals(split2[0])) {
						i++;
					}
				}
			}
			
			if(input.size()==i) {
				return true;
			}else 
				throw new Exception("ERRORE-PianoValidator-hpPianoVal: hp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return false;		
	}
	
	public boolean modificaPianoVal(String dato, String prezzo) {	
		InputValidator validator = new InputValidator();
		try {
			
			validator.setValidatorStrategy(new ValidatorDouble());
			validator.setString(prezzo); // prezzo
			if (!validator.validator(0))
				throw new Exception("ERRORE-PianoValidator-modificaPianoVal: prezzo");
			
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return false;		
	}
}
