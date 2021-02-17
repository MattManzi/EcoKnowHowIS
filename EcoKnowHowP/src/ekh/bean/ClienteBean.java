package ekh.bean;

public class ClienteBean {
	private String username;
	private String nome;
	private String cognome;
	private String funzioneAziendale;
	private String telefono;
	private String ragioneSociale;
	private String indirizzo;
	private String pIva;
	private String cf;
	private String pec;
	private String sdi;

	public ClienteBean() {
		username = "";
		nome = "";
		cognome = "";
		funzioneAziendale = "";
		telefono = "";
		ragioneSociale = "";
		indirizzo = "";
		pIva = "";
		cf = "";
		pec = "";
		sdi = "";
	}

	public ClienteBean(String username, String nome, String cognome, String funzioneAziendale, String telefono,
			String ragioneSociale, String indirizzo, String pIva, String cf, String pec, String sdi) {
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.funzioneAziendale = funzioneAziendale;
		this.telefono = telefono;
		this.ragioneSociale = ragioneSociale;
		this.indirizzo = indirizzo;
		this.pIva = pIva;
		this.cf = cf;
		this.pec = pec;
		this.sdi = sdi;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getFunzioneAziendale() {
		return funzioneAziendale;
	}

	public void setFunzioneAziendale(String funzioneAziendale) {
		this.funzioneAziendale = funzioneAziendale;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
	}

	public String getSdi() {
		return sdi;
	}

	public void setSdi(String sdi) {
		this.sdi = sdi;
	}

	public boolean isEmpty() {
		if (username.length() < 0) {
			return true;
		}
		return false;
	}
}
