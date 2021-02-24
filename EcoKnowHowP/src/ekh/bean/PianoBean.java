package ekh.bean;

public class PianoBean {
	private int id;
	private int idPacchetto;
	private PacchettoBean pacchetto;
	private String username;
	private double prezzo;
	private String stato;
	private boolean referto;
	private boolean schedaDS;
	private ModuloBean modulo;
	
	public PianoBean() {
		id = 0;
		idPacchetto = 0;
		username =  "";
		prezzo = 0;
		stato =  "";
		referto=false;
		schedaDS=false;
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
		
	public boolean isReferto() {
		return referto;
	}

	public void setReferto(boolean referto) {
		this.referto = referto;
	}

	public boolean isSchedaDS() {
		return schedaDS;
	}

	public void setSchedaDS(boolean schedaDS) {
		this.schedaDS = schedaDS;
	}

	public PacchettoBean getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoBean pacchetto) {
		this.pacchetto = pacchetto;
	}

	public ModuloBean getModulo() {
		return modulo;
	}

	public void setModulo(ModuloBean modulo) {
		this.modulo = modulo;
	}

	public boolean isEmpty() {
		if (id == 0) {
			return true;
		}
		return false;
	}
}
