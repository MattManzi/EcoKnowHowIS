package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorPassword implements InputVal{

	@Override
	public boolean valida(String str, int n) {
		return validaPassword(str, n);
	}
	public boolean validaPassword(String str, int n) {
		if(str!=null && !str.equals("") && str.length()<=n) {
			return Pattern.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,15})", str);
		}
		return false;
	}
}
