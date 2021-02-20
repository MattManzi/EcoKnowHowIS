package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorAlphaNumeric implements InputVal{
	@Override
	public boolean valida(String str, int n) {
		return validaEmail(str, n);
	}
	
	public boolean validaEmail(String str, int n) {
		if(str!=null && !str.equals("") && str.length()<=n) {
			return Pattern.matches("^\\w+(\\s?\\w+)*$", str);
		}
		return false;
	}
}
