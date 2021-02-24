package ekh.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import ekh.model.ModuloModelDM;

public class ModuloBean {
	private String tipo;
	private String ragioneSocialeProd;
	private String sedeLegaleProd;
	private String pIvaProd;
	private String telefonoProd;
	private String emailProd;
	private String ragioneSocialeCom;
	private String sedeLegaleCom;
	private String pIvaCom;
	private String telefonoCom;
	private String emailCom;
	private String data;
	private String luogo;
	private String nomeCampionatore;
	private String cognomeCampionatore;
	private String norma;
	private String quantitaCampione;
	private String note;
	private ArrayList<String> obiettivi;
	private String dataConferma;
	// moduloAvanzato
	private ArrayList<String> hp;
	private String cer;
	private String statoFisico;
	private String descrizione;

	public ModuloBean() {
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ArrayList<String> getObiettivi() {
		return obiettivi;
	}

	public void setObiettivi(ArrayList<String> obiettivi) {
		this.obiettivi = obiettivi;
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

	public String getDataConferma() {
		return dataConferma;
	}

	public void setDataConferma(String dataConferma) {
		this.dataConferma = dataConferma;
	};

	public void stampModuloBase(int id) {
		String str = "";
		str = str + tipo + "\n" + ragioneSocialeProd + "\n" + ragioneSocialeCom + "\n" + sedeLegaleProd + "\n"
				+ sedeLegaleCom + "\n" + pIvaProd + "\n" + pIvaCom + "\n" + telefonoProd + "\n" + telefonoCom + "\n"
				+ emailProd + "\n" + emailCom + "\n" + data + "\n" + luogo + "\n" + nomeCampionatore + "\n"
				+ cognomeCampionatore + "\n" + norma + "\n" + quantitaCampione + "\n" + note + "\n" + dataConferma
				+ "\n" + " \n" + " \n" + " \n";

		for (String i : obiettivi) {
			str = str + "Obiettivo:" + i + "\n";
		}
		try {
			ModuloModelDM.updateFile(id, str);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void stampModuloAvanzato(int id) {
		String str = "";
		str = str + tipo + "\n" + ragioneSocialeProd + "\n" + ragioneSocialeCom + "\n" + sedeLegaleProd + "\n"
				+ sedeLegaleCom + "\n" + pIvaProd + "\n" + pIvaCom + "\n" + telefonoProd + "\n" + telefonoCom + "\n"
				+ emailProd + "\n" + emailCom + "\n" + data + "\n" + luogo + "\n" + nomeCampionatore + "\n"
				+ cognomeCampionatore + "\n" + norma + "\n" + quantitaCampione + "\n" + note + "\n" + dataConferma
				+ "\n" + cer + "\n" + statoFisico + "\n" + descrizione + "\n";

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
		note = dati[17];
		dataConferma = dati[18];
		cer = dati[19];
		statoFisico = dati[20];
		descrizione = dati[21];
		String oStr=dati[22];
		String hStr=dati[23];
		
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

}
