package ekh.bean;

public class ParametroBean {
	private int id;
	private int idMatrice;
	private String nome;
	private String sku;
	private String limiteEmissione;
	private String uMisura;
	private double prezzo;
	
	public ParametroBean() {
		id = 0;
		idMatrice = 0;
		nome = "";
		sku = "";
		limiteEmissione = "";
		uMisura = "";
		prezzo = 0;
	}
	
	public ParametroBean(int id, int idMatrice, String nome, String sku, String limiteEmissione, String uMisura, double prezzo) {
		this.id = id;
		this.idMatrice = idMatrice;
		this.nome = nome;
		this.sku = sku;
		this.limiteEmissione = limiteEmissione;
		this.uMisura = uMisura;
		this.prezzo = prezzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdMatrice() {
		return idMatrice;
	}

	public void setIdMatrice(int idMatrice) {
		this.idMatrice = idMatrice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getLimiteEmissione() {
		return limiteEmissione;
	}

	public void setLimiteEmissione(String limiteEmissione) {
		this.limiteEmissione = limiteEmissione;
	}

	public String getuMisura() {
		return uMisura;
	}

	public void setuMisura(String uMisura) {
		this.uMisura = uMisura;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	public boolean isEmpty() {
		return id==0;
	}
}
