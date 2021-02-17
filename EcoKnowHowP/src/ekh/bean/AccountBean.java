package ekh.bean;

public class AccountBean {
	private String username;
	private String email;
	private String password;
	private String codSicurezza;
	private String tipo;

	public AccountBean() {
		username = "";
		email = "";
		password = "";
		codSicurezza = "";
		tipo = "";
	}

	public AccountBean(String username, String email, String password, String codSicurezza, String tipo) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.codSicurezza = codSicurezza;
		this.tipo = tipo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isEmpty() {
		if (username.length() < 0) {
			return true;
		}
		return false;
	}
}
