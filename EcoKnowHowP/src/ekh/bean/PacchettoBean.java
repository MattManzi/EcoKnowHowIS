package ekh.bean;

public class PacchettoBean {
	private int id;
	private String nome;
	private String descrizione;
	private String tipo;
	private String username;
	private double prezzo;

	public PacchettoBean() {
		id = 0;
		nome = "";
		descrizione = "";
		tipo = "";
		username = "";
		prezzo = 0;
	}

	public PacchettoBean(int id, String nome, String descrizione, String tipo, String username, double prezzo) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.username = username;
		this.prezzo = prezzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public boolean isEmpty() {
		if (id == 0) {
			return true;
		}
		return false;
	}
}
