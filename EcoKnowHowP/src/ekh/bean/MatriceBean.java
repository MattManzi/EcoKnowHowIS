package ekh.bean;

public class MatriceBean {
	private int id;
	private String nome;
	private String sottotitolo;
	private String descrizione;
	
	public MatriceBean() {
		id=0;
		nome = "";
		sottotitolo = "";
		descrizione = "";
	}
	
	public MatriceBean(int id, String nome, String sottotitolo, String descrizione) {
		this.id=id;
		this.nome = nome;
		this.sottotitolo = sottotitolo;
		this.descrizione = descrizione;
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

	public String getSottotitolo() {
		return sottotitolo;
	}

	public void setSottotitolo(String sottotitolo) {
		this.sottotitolo = sottotitolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public boolean isEmpty() {
		if (id == 0) {
			return true;
		}
		return false;
	}
}
