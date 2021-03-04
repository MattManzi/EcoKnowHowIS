package ekh.strategy;

import java.util.regex.Pattern;

public class ValidatorAllLetterPoint implements InputVal{
	@Override
	public boolean valida(String str, int n) {
		return validator(str, n);
	}
	
	public boolean validator(String str, int n) {
		if(str!=null && !str.equals("") && str.length()<=n) {
			return Pattern.matches("^[A-Z\\.a-z]*$", str);
		}
		return false;
	}
}
