package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorUsername implements InputVal{
	@Override
	public boolean valida(String str, int n) {
		return validaUsername(str, n);
	}
	
	public boolean validaUsername(String str, int n) {
		if(str!=null && !str.equals("") && str.length()<=n) {
			return Pattern.matches("^[a-z0-9_-]{5,15}$", str);
		}
		return false;
	}
}
