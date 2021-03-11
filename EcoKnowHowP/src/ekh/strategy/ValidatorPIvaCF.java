package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorPIvaCF implements InputVal {

	@Override
	public boolean valida(String str, int n) {
		return validator(str, n);
	}
	
	public boolean validator(String str, int n) {
		if(str!=null && !str.equals("")) {			
			return Pattern.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$|(^[0-9]{11})$", str);			
		}
		return false;
	}

}
