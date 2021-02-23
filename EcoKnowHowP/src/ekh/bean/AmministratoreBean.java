package ekh.bean;

public class AmministratoreBean {
	private String username;
	private String email;
	private String password;
	private String codSicurezza;
	

	public AmministratoreBean() {
		username="";
		email = "";
		password="";
		codSicurezza="";
	}

	public AmministratoreBean(String username, String email, String password, String codSicurezza) {
		this.username=username;
		this.email = email;
		this.password=password;
		this.codSicurezza=codSicurezza;		
	}	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}		

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodSicurezza() {
		return codSicurezza;
	}

	public void setCodSicurezza(String codSicurezza) {
		this.codSicurezza = codSicurezza;
	}

	public boolean isEmpty() {
		return username.equals("");
	}
}
