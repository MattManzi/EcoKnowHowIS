package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorUserPass implements InputVal{
	@Override
	public boolean valida(String str, int n) {
		return validaUsername(str, n);
	}
	
	public boolean validaUsername(String str, int n) {
		if(str!=null && !str.equals("") && str.length()<=n) {
			return Pattern.matches("^\\w+([\\.-]?\\w+)*$", str);
		}
		return false;
	}
}
