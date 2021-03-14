package ekh.bean;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import ekh.model.ModuloModelDM;

public class ModuloBean {
	protected String tipo;
	protected String ragioneSocialeProd;
	protected String sedeLegaleProd;
	protected String pIvaProd;
	protected String telefonoProd;
	protected String emailProd;
	protected String ragioneSocialeCom;
	protected String sedeLegaleCom;
	protected String pIvaCom;
	protected String telefonoCom;
	protected String emailCom;
	protected String data;
	protected String luogo;
	protected String nomeCampionatore;
	protected String cognomeCampionatore;
	protected String norma;
	protected String quantitaCampione;
	protected boolean rapporto;
	protected ArrayList<String> obiettivi;
	protected String dataConferma;
	
	public ModuloBean() {
		obiettivi=new ArrayList<String>();
		rapporto=false;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRagioneSocialeProd() {
		return ragioneSocialeProd;
	}

	public void setRagioneSocialeProd(String ragioneSocialeProd) {
		this.ragioneSocialeProd = ragioneSocialeProd;
	}

	public String getSedeLegaleProd() {
		return sedeLegaleProd;
	}

	public void setSedeLegaleProd(String sedeLegaleProd) {
		this.sedeLegaleProd = sedeLegaleProd;
	}

	public String getpIvaProd() {
		return pIvaProd;
	}

	public void setpIvaProd(String pIvaProd) {
		this.pIvaProd = pIvaProd;
	}

	public String getTelefonoProd() {
		return telefonoProd;
	}

	public void setTelefonoProd(String telefonoProd) {
		this.telefonoProd = telefonoProd;
	}

	public String getEmailProd() {
		return emailProd;
	}

	public void setEmailProd(String emailProd) {
		this.emailProd = emailProd;
	}

	public String getRagioneSocialeCom() {
		return ragioneSocialeCom;
	}

	public void setRagioneSocialeCom(String ragioneSocialeCom) {
		this.ragioneSocialeCom = ragioneSocialeCom;
	}

	public String getSedeLegaleCom() {
		return sedeLegaleCom;
	}

	public void setSedeLegaleCom(String sedeLegaleCom) {
		this.sedeLegaleCom = sedeLegaleCom;
	}

	public String getpIvaCom() {
		return pIvaCom;
	}

	public void setpIvaCom(String pIvaCom) {
		this.pIvaCom = pIvaCom;
	}

	public String getTelefonoCom() {
		return telefonoCom;
	}

	public void setTelefonoCom(String telefonoCom) {
		this.telefonoCom = telefonoCom;
	}

	public String getEmailCom() {
		return emailCom;
	}

	public void setEmailCom(String emailCom) {
		this.emailCom = emailCom;
	}

	public void stessaPersona() {
		ragioneSocialeCom = ragioneSocialeProd;
		sedeLegaleCom = sedeLegaleProd;
		pIvaCom = pIvaProd;
		telefonoCom = telefonoProd;
		emailCom = emailProd;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getNomeCampionatore() {
		return nomeCampionatore;
	}

	public void setNomeCampionatore(String nomeCampionatore) {
		this.nomeCampionatore = nomeCampionatore;
	}

	public String getCognomeCampionatore() {
		return cognomeCampionatore;
	}

	public void setCognomeCampionatore(String cognomeCampionatore) {
		this.cognomeCampionatore = cognomeCampionatore;
	}

	public String getNorma() {
		return norma;
	}

	public void setNorma(String norma) {
		this.norma = norma;
	}

	public String getQuantitaCampione() {
		return quantitaCampione;
	}

	public void setQuantitaCampione(String quantitaCampione) {
		this.quantitaCampione = quantitaCampione;
	}

	public boolean getRapporto() {
		return rapporto;
	}

	public void setRapporto(Boolean rapporto) {
		this.rapporto = rapporto;
	}

	public ArrayList<String> getObiettivi() {
		return obiettivi;
	}

	public void setObiettivi(ArrayList<String> obiettivi) {
		this.obiettivi = obiettivi;
	}
	
	public String getDataConferma() {
		return dataConferma;
	}

	public void setDataConferma(String dataConferma) {
		this.dataConferma = dataConferma;
	};

	public void stampModulo(int id) {
		String str = "";
		str = str + tipo + "\n" + ragioneSocialeProd + "\n" + ragioneSocialeCom + "\n" + sedeLegaleProd + "\n"
				+ sedeLegaleCom + "\n" + pIvaProd + "\n" + pIvaCom + "\n" + telefonoProd + "\n" + telefonoCom + "\n"
				+ emailProd + "\n" + emailCom + "\n" + data + "\n" + luogo + "\n" + nomeCampionatore + "\n"
				+ cognomeCampionatore + "\n" + norma + "\n" + quantitaCampione + "\n"+ dataConferma+"\n"+rapporto+"\n";

		for (String i : obiettivi) {
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
		if(dati[18].equals("true")) {
			rapporto=true;
		}else {
			rapporto=false;
		}
		String oStr=dati[19];
		
		String[] o=oStr.split(";");
		
		if(o.length>0) {
			for(String x:o) {
				obiettivi.add(x);
			}
		}
	}
	
	public void inizializza(String path) throws IOException {
		readObiettivi(path);
	}
	
	public void readObiettivi(String path) throws IOException{
		obiettivi.clear();
		FileReader fr = new FileReader(path+"Modulo"+tipo+".txt");
		Scanner in = new Scanner(fr);
		
		while (in.hasNextLine()) {
			obiettivi.add(in.nextLine());
		}
		
		in.close();
		fr.close();
	}
}
