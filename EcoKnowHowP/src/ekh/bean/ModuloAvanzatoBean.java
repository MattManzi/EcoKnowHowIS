package ekh.bean;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import ekh.model.ModuloModelDM;

public class ModuloAvanzatoBean extends ModuloBean {
	private ArrayList<String> hp;
	private String cer;
	private String statoFisico;
	private String descrizione;
	private boolean schedaDS;

	public ModuloAvanzatoBean() {
		super();
		hp=new ArrayList<String>();
	}	
	public ArrayList<String> getHp() {
		return hp;
	}

	public void setHp(ArrayList<String> hp) {
		this.hp = hp;
	}

	public String getCer() {
		return cer;
	}

	public void setCer(String cer) {
		this.cer = cer;
	}

	public String getStatoFisico() {
		return statoFisico;
	}

	public void setStatoFisico(String statoFisico) {
		this.statoFisico = statoFisico;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public boolean isSchedaDS() {
		return schedaDS;
	}
	public void setSchedaDS(boolean schedaDS) {
		this.schedaDS = schedaDS;
	}
	public void stampModulo(int id) {
		String str = "";
		str = str + tipo + "\n" + ragioneSocialeProd + "\n" + ragioneSocialeCom + "\n" + sedeLegaleProd + "\n"
				+ sedeLegaleCom + "\n" + pIvaProd + "\n" + pIvaCom + "\n" + telefonoProd + "\n" + telefonoCom + "\n"
				+ emailProd + "\n" + emailCom + "\n" + data + "\n" + luogo + "\n" + nomeCampionatore + "\n"
				+ cognomeCampionatore + "\n" + norma + "\n" + quantitaCampione + "\n"+ dataConferma
				+ "\n" + cer + "\n" + statoFisico + "\n" + descrizione + "\n"+rapporto+ "\n"+schedaDS+ "\n";

		for (String i : obiettivi) {
			str = str + i + ";";
		}
		str = str + "\n";

		for (String i : hp) {
			str = str + i + ";";
		}
		str = str + "\n";
		try {
			ModuloModelDM.updateFile(id, str);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readModulo(int id) {
		byte[] bt = null;

		try {
			bt = ModuloModelDM.loadFile(id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		String str = new String(bt);
		String[] dati = str.split("\n");

		tipo = dati[0];
		ragioneSocialeProd = dati[1];
		ragioneSocialeCom = dati[2];
		sedeLegaleProd = dati[3];
		sedeLegaleCom = dati[4];
		pIvaProd = dati[5];
		pIvaCom = dati[6];
		telefonoProd = dati[7];
		telefonoCom = dati[8];
		emailProd = dati[9];
		emailCom = dati[10];
		data = dati[11];
		luogo = dati[12];
		nomeCampionatore = dati[13];
		cognomeCampionatore = dati[14];
		norma = dati[15];
		quantitaCampione = dati[16];
		dataConferma = dati[17];
		cer = dati[18];
		statoFisico = dati[19];
		descrizione = dati[20];
		if(dati[21].equals("true")) {
			rapporto=true;
		}else {
			rapporto=false;
		}
		if(dati[22].equals("true")) {
			schedaDS=true;
		}else {
			schedaDS=false;
		}
		String oStr=dati[23];
		String hStr=dati[24];
		
		String[] o=oStr.split(";"); 
		String[] h=hStr.split(";"); 
		
		if(o.length>0) {
			for(String x:o) {
				obiettivi.add(x);
			}
		}
		
		if(h.length>0) {
			for(String x:h) {
				hp.add(x);
			}
		}
	}
	
	public void inizializza(String path) throws IOException {
		super.inizializza(path);
		readHp(path);
	}
	
	public void readHp(String path) throws IOException{
		FileReader fr = new FileReader(path+"HP.txt");
		Scanner in = new Scanner(fr);
		
		while (in.hasNextLine()) {
			hp.add(in.nextLine());
		}
		in.close();
		fr.close();
	}
}
