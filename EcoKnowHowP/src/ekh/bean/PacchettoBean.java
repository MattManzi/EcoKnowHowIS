package ekh.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import ekh.model.PacchettoModelDM;
import ekh.model.ParametroModelDM;
import ekh.support.GeneraRandom;

public class PacchettoBean {
	private String id;
	private int idMatrice;
	private String nome;
	private String descrizione;
	private String tipo;
	private String username;
	private ArrayList<ParametroBean> contenuto;
	private double prezzo;
	
	public PacchettoBean() {
		id = "";
		idMatrice=0;
		nome = "";
		descrizione = "";
		tipo = "";
		username = "";
		contenuto=new ArrayList<ParametroBean>();
		prezzo = 0;
	}

	public PacchettoBean(String id, int idMatrice, String nome, String descrizione, String tipo, String username, ArrayList<ParametroBean> contenuto, double prezzo) {
		this.id = id;
		this.idMatrice=idMatrice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.username = username;
		this.contenuto=contenuto;
		this.prezzo = prezzo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void generaId() throws SQLException {
		PacchettoModelDM model=new PacchettoModelDM();
		GeneraRandom gr=new GeneraRandom();		
		String idTemp=gr.getRandom();
		
		if(model.controlloId(idTemp)) {
			id=idTemp;
		}else {
			generaId();
		}		
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
		return id.equals("");
	}
	
	public void addParametro(ParametroBean bean) {
		boolean chiave=true;
		try {
			if(!bean.isEmpty() && bean!=null) {
				if(contenuto.size()>0) {
					for(ParametroBean p:contenuto) {
						if(p.getId()==bean.getId()) {
							chiave=false;
							throw new Exception("ERRORE - PacchettoBean.addParametro: Parametro già presente nel pacchetto.");
						}
					}
					if(chiave) {
						contenuto.add(bean);
					}
				}else {
					contenuto.add(bean);
				}
			}else
				throw new Exception("ERRORE - PacchettoBean.addParametro: Parametro non valido");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public void remParametro(ParametroBean bean) {
		try {
			if(!bean.isEmpty() && bean!=null) {
				for(ParametroBean p:contenuto) {
					if(p.getId()==bean.getId()) {
						contenuto.remove(p);
						break;
					}
				}
			}else
				throw new Exception("ERRORE - PacchettoBean.remParametro: Parametro non valido");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public int getSize() {
		return contenuto.size();
	}
	
	public void deleteContenuto() {
		contenuto.clear();		
	}
	
	public void readContenuto() {
		ParametroModelDM model=new ParametroModelDM();
		contenuto.clear();
		byte[] bt=null;
		try {
			bt = PacchettoModelDM.loadContenuto(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		String parametri=new String(bt);
		String[] param=parametri.split("\n");
		
		for(String id:param) {
			try {
				contenuto.add(model.doRetrieveByKey(id));
			} catch (SQLException e) {
				System.out.println("ERORRE-PacchettoBean: readContenuto: "+e.getMessage());
			}
		}
	}
	
	public void stampContenuto(String path){
		String str="";
		for(ParametroBean i:contenuto) {
			str=str+i.getId()+"\n";
		}
		try {
			PacchettoModelDM.updateContenuto(id,str, path);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public double calcolaPrezzo() {		
		for(ParametroBean bean:contenuto) {
			prezzo=prezzo+bean.getPrezzo();
		}	
		
		return prezzo;
	}
}
