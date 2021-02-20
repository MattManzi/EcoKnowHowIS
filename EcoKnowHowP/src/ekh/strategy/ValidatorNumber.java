package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorNumber implements InputVal {
	@Override
	public boolean valida(String str, int n) {
		return validaEmail(str, n);
	}
	
	public boolean validaEmail(String str, int n) {
		if(str!=null && !str.equals("")) {
			return Pattern.matches("^\\d{"+n+"}$", str);
		}
		return false;
	}
}
