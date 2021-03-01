package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorPIvaCF implements InputVal {

	@Override
	public boolean valida(String str, int n) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean validaIvaCF(String str, int n) {
		if(str!=null && !str.equals("")) {
			return Pattern.matches("(^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z])|(^[0-9]{11})$", str);
		}
		return false;
	}

}
