package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorDouble implements InputVal {

	@Override
	public boolean valida(String str, int n) {
		return validaDouble(str, n);
	}
	
	public boolean validaDouble(String str, int n) {
		if(str!=null && !str.equals("")) {
			return Pattern.matches("^\\d+(\\.\\d+)?$", str);
		}
		return false;
	}
}

