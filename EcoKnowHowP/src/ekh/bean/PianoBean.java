package ekh.bean;

public class PianoBean {
	private int id;
	private int idPacchetto;
	private String username;
	private double prezzo;
	private String stato;
	
	public PianoBean() {
		id = 0;
		idPacchetto = 0;
		username =  "";
		prezzo = 0;
		stato =  "";
	}
	
	public PianoBean(int id, int idPacchetto, String username, double prezzo, String stato) {
		this.id = id;
		this.idPacchetto = idPacchetto;
		this.username = username;
		this.prezzo = prezzo;
		this.stato = stato;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPacchetto() {
		return idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public boolean isEmpty() {
		if (id == 0) {
			return true;
		}
		return false;
	}
}
