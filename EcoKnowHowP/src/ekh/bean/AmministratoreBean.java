package ekh.bean;

public class AmministratoreBean {
	private String email;

	public AmministratoreBean() {
		email = "";
	}

	public AmministratoreBean(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	public boolean isEmpty() {
		if (email.length() < 0) {
			return true;
		}
		return false;
	}
}
