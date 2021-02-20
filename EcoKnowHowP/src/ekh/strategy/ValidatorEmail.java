package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorEmail implements InputVal{

	@Override
	public boolean valida(String str, int n) {
		return validaEmail(str, n);
	}
	
	public boolean validaEmail(String str, int n) {
		if(str!=null && !str.equals("") && str.length()<=n) {
			return Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", str);
		}
		return false;
	}
}
