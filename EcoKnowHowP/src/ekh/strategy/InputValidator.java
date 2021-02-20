package ekh.strategy;

public class InputValidator {
	private InputVal valStrategy;
	private String input;
	
	public InputValidator() {
		
	}
	
	public InputValidator(String input) {
		this.input=input;
	}
	
	public void setString(String input) {
		this.input=input;
	}

	public void setValidatorStrategy(InputVal valStrategy) {
		this.valStrategy=valStrategy;
	}

	public boolean validator(int n) {
		return valStrategy.valida(input, n);
	}
	

}