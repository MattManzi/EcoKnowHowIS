package ekh.strategy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class ValidatorDate implements InputVal{
	public boolean valida(String str, int n) {
		return validaData(str);
	}
	
	public boolean validaData(String str) {
		LocalDate today = LocalDate.now();	
		String data = ""+today;
		if(str!=null && !str.equals("") && str.compareTo(data)<=0) {
			return Pattern.matches("((19|20)[0-9]{2})\\-(0?[1-9]|1[012])\\-([12][0-9]|3[01]|0?[1-9])", str);
		}
		return false;
	}
}
