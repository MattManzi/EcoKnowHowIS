package ekh.bean;

public class ParametroBean {
	private int id;
	private int idMatrice;
	private String nome;
	private String sku;
	private String campione;
	private String campionamento;
	private String limiteEmissione;
	private String uMisura;
	private double prezzo;
	
	public ParametroBean() {
		id = 0;
		idMatrice = 0;
		nome = "";
		sku = "";
		campione = "";
		campionamento = "";
		limiteEmissione = "";
		uMisura = "";
		prezzo = 0;
	}
	
	public ParametroBean(int id, int idMatrice, String nome, String sku, String campione, String campionamento,
			String limiteEmissione, String uMisura, double prezzo) {
		this.id = id;
		this.idMatrice = idMatrice;
		this.nome = nome;
		this.sku = sku;
		this.campione = campione;
		this.campionamento = campionamento;
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

	public String getCampione() {
		return campione;
	}

	public void setCampione(String campione) {
		this.campione = campione;
	}

	public String getCampionamento() {
		return campionamento;
	}

	public void setCampionamento(String campionamento) {
		this.campionamento = campionamento;
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
		if (id == 0) {
			return true;
		}
		return false;
	}
}
