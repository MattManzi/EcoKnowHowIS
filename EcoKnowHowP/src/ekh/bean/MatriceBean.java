package ekh.bean;

public class MatriceBean {
	private int id;
	private String nome;
	private String sottotitolo;
	private String nota;
	
	public MatriceBean() {
		id=0;
		nome = "";
		sottotitolo = "";
		nota = "";
	}
	
	public MatriceBean(int id, String nome, String sottotitolo, String nota) {
		this.id=id;
		this.nome = nome;
		this.sottotitolo = sottotitolo;
		this.nota = nota;
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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public boolean isEmpty() {
		return id==0;
	}
}
