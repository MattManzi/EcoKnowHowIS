package ekh.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import ekh.model.ContenutoPacchettoModelDM;
import ekh.model.ParametroModelDM;

public class PacchettoBean {
	private int id;
	private String nome;
	private String descrizione;
	private String tipo;
	private String username;
	private ArrayList<ParametroBean> contenuto;
	private double prezzo;

	public PacchettoBean() {
		id = 0;
		nome = "";
		descrizione = "";
		tipo = "";
		username = "";
		contenuto=new ArrayList<ParametroBean>();
		prezzo = 0;
	}

	public PacchettoBean(int id, String nome, String descrizione, String tipo, String username, ArrayList<ParametroBean> contenuto, double prezzo) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.username = username;
		this.contenuto=contenuto;
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

	public ArrayList<ParametroBean> getContenuto() {
		return contenuto;
	}

	public void setContenuto(ArrayList<ParametroBean> contenuto) {
		this.contenuto = contenuto;
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
	
	public void readContenuto() {
		ParametroModelDM model=new ParametroModelDM();
		contenuto.clear();
		byte[] bt=null;
		try {
			bt = ContenutoPacchettoModelDM.loadFile(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		String parametri=new String(bt);
		String[] param=parametri.split("\n");
		
		for(String id:param) {
			try {
				contenuto.add(model.doRetrieveByKey(id));
			} catch (SQLException e) {
				System.out.println("Errore PacchettoBean readContenuto: "+e.getMessage());
			}
		}
	}
	
	public String stampContenuto() {
		String str="";
		for(ParametroBean i:contenuto) {
			str=str+i.getId()+"\n";
		}
		return str;
	}
}
