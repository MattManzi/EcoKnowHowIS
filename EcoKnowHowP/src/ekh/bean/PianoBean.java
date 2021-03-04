package ekh.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import ekh.model.InfoModelDM;
import ekh.model.PianoModelDM;

public class PianoBean {
	private int id;
	private ArrayList<ParametroBean> contenuto;
	private String username;
	private double prezzo;
	private String stato;
	private boolean referto;
	private boolean schedaDS;
	private ModuloBean modulo;
	
	public PianoBean() {
		id = 0;
		username =  "";
		prezzo = 0;
		stato =  "";
		referto=false;
		schedaDS=false;
		contenuto=new ArrayList<ParametroBean>();
	}
	
	public PianoBean(int id, String username, double prezzo, String stato) {
		this.id = id;
		this.username = username;
		this.prezzo = prezzo;
		this.stato = stato;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public void generaId() throws SQLException{
		InfoModelDM model=new InfoModelDM();
		String info=model.getInfo();
		int idPiano=Integer.parseInt(info);
		id=idPiano+1;
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
	public ModuloBean getModulo() {
		return modulo;
	}

	public void setModulo(ModuloBean modulo) {
		this.modulo = modulo;
	}
	
	public ArrayList<ParametroBean> getContenuto() {
		return contenuto;
	}

	public void setContenuto(ArrayList<ParametroBean> contenuto) {
		this.contenuto = contenuto;
	}

	public void readContenuto() {
		contenuto.clear();
		byte[] bt=null;
		try {
			bt = PianoModelDM.loadContenuto(String.valueOf(id));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		String parametri=new String(bt);
		String[] param=parametri.split("\n");
		
		for(String p:param) {
			ParametroBean bean=new ParametroBean();
			String[] dati=p.split(";");
			bean.setNome(dati[0]);
			bean.setSku(dati[1]);
			bean.setPrezzo(Double.parseDouble(dati[2]));
			contenuto.add(bean);
		}
	}
	
	public void stampContenuto(String path){
		String str="";
		for(ParametroBean i:contenuto) {
			str=str+i.getNome()+";"+i.getSku()+";"+i.getPrezzo()+"\n";
		}
		try {
			PianoModelDM.updateContenuto(String.valueOf(id),str, path);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean isEmpty() {
		return id==0; 
	}
}
