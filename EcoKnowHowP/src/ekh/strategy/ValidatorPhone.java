package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorPhone implements InputVal{
	@Override
	public boolean valida(String str, int n) {
		return validaPhone(str);
	}
	
	public boolean validaPhone(String str) {
		if(str!=null && !str.equals("")) {
			return Pattern.matches("^\\d{8,15}$", str);
		}
		return false;
	}
}
