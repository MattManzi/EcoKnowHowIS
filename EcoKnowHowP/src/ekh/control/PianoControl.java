package ekh.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;
import ekh.bean.MatriceBean;
import ekh.bean.ModuloAvanzatoBean;
import ekh.bean.ModuloBean;
import ekh.bean.PacchettoBean;
import ekh.bean.PianoBean;
import ekh.model.InfoModelDM;
import ekh.model.PacchettoModelDM;
import ekh.model.PianoModelDM;
import ekh.model.RefertoModelDM;
import ekh.model.SchedaSicurezzaModelDM;
import ekh.strategy.PianoValidator;
import ekh.support.SendEmail;

@WebServlet("/Piano")

public class PianoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;
	
	PianoModelDM modelPiano = new PianoModelDM();
	PacchettoModelDM modelPacchetto = new PacchettoModelDM();
	InfoModelDM modelInfo=new InfoModelDM();
	SchedaSicurezzaModelDM model = new SchedaSicurezzaModelDM();

	public PianoControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
		try {
			/* Controllo Login */
			if ((admin != null && adminRoles != null && adminRoles.booleanValue())
					|| (user != null && userRoles != null && userRoles.booleanValue())) {
				String action = request.getParameter("action");
				if (action != null) {
					if (action.equals("pianiCliente")) {
						String username = request.getParameter("username");
						if (username != null) {
							ArrayList<PianoBean> piani = new ArrayList<PianoBean>();
							piani = modelPiano.doRetrieveByUsername(username);
							request.setAttribute("piani", piani);
							redirectedPage = "/StoricoCliente.jsp";
						} else
							throw new Exception("ERRORE-PianoControl: username null.");
					} else if (action.equals("select")) {
						request.getSession().removeAttribute("pianoAdmin");
						String id = request.getParameter("id");
						if (id != null) {
							PianoBean piano = new PianoBean();
							piano = modelPiano.doRetrieveByKey(id);
							if (!piano.isEmpty()) {
								piano.readContenuto();
								ModuloBean modulo = new ModuloBean();
								modulo.readModulo(piano.getId());
								piano.setModulo(modulo);
								request.setAttribute("piano", piano);
								request.getSession().setAttribute("pianoAdmin", piano);
								redirectedPage = "/DettagliPianoCliente.jsp";
							} else
								throw new Exception("ERRORE-SelectPianoClienteServlet: piano non trovato.");
						} else {
							redirectedPage = "/StoricoCliente.jsp";
							throw new Exception("ERRORE-SelectPianoClienteServlet: id null.");
						}
					} else if (action.equals("dlReferto")) {
						String idPiano = request.getParameter("idPiano");
						if (idPiano != null) {
							Blob blob = RefertoModelDM.downloadFile(idPiano);
							InputStream inputStream = blob.getBinaryStream();
							int fileLength = inputStream.available();
							ServletContext context = getServletContext();
							// sets MIME type for the file download
							String mimeType = context.getMimeType("referto");
							if (mimeType == null) {
								mimeType = "application/octet-stream";
							}
							// set content properties and header attributes for the response
							response.setContentType(mimeType);
							response.setContentLength(fileLength);
							String headerKey = "Content-Disposition";
							String headerValue = String.format("attachment; filename=\"%s\"", "referto.pdf");
							response.setHeader(headerKey, headerValue);
							// writes the file to the client
							OutputStream outStream = response.getOutputStream();
							byte[] buffer = new byte[BUFFER_SIZE];
							int bytesRead = -1;

							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outStream.write(buffer, 0, bytesRead);
							}

							inputStream.close();
							outStream.close();
						} else
							throw new Exception("ERRORE-PianoControl-dlReferto: idPiano null.");
					} else if (action.equals("dlSDS")) {
						String idPiano = request.getParameter("codice");
						if (idPiano != null) {
							Blob blob = SchedaSicurezzaModelDM.downloadFile(idPiano);
							InputStream inputStream = blob.getBinaryStream();
							int fileLength = inputStream.available();

							ServletContext context = getServletContext();

							// sets MIME type for the file download
							String mimeType = context.getMimeType("schedaDatiSicurezza");
							if (mimeType == null) {
								mimeType = "application/octet-stream";
							}

							// set content properties and header attributes for the response
							response.setContentType(mimeType);
							response.setContentLength(fileLength);
							String headerKey = "Content-Disposition";
							String headerValue = String.format("attachment; filename=\"%s\"",
									"Scheda_Dati_Sicurezza.pdf");
							response.setHeader(headerKey, headerValue);

							// writes the file to the client
							OutputStream outStream = response.getOutputStream();

							byte[] buffer = new byte[BUFFER_SIZE];
							int bytesRead = -1;

							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outStream.write(buffer, 0, bytesRead);
							}

							inputStream.close();
							outStream.close();
						} else
							throw new Exception("ERRORE-PianoControl-dlSDS: idPiano null.");
					} else {
						if (admin != null) {
							PianoBean bean = (PianoBean) request.getSession().getAttribute("pianoAdmin");
							if (bean != null) {
								if (action.equals("prezzo")) {
									String prezzo = request.getParameter("prezzo");
									PianoValidator pv = new PianoValidator();
									if (pv.modificaPianoVal("prezzo", prezzo)) {
										modelPiano.doUpdate("prezzo", prezzo, String.valueOf(bean.getId()));
										redirectedPage = "/DettagliPianoCliente.jsp";
									}
								} else if (action.equals("stato")) {

								} else
									throw new Exception("ERRORE-PianoControl-admin: invalid action for admin.");
							} else {
								redirectedPage = "/StoricoCliente.jsp";
								throw new Exception("ERRORE-PianoControl: piano null.");
							}
						} else {
							if (action.equals("crea")) {
								MatriceBean matrice = (MatriceBean) request.getSession().getAttribute("SelectMatrice");
								if (matrice != null) {
									PacchettoBean pacchetto = (PacchettoBean) request.getSession()
											.getAttribute("SelectPacchetto");
									ModuloBean modulo = (ModuloBean) request.getSession().getAttribute("modulo");
									if (pacchetto != null && modulo != null) {
										if (modulo.getTipo().equals("B")) {
											modulo = (ModuloAvanzatoBean) request.getSession()
													.getAttribute("modulo");
										}
										ArrayList<String> produttoreInputs = new ArrayList<String>();
										ArrayList<String> committenteInputs = new ArrayList<String>();
										ArrayList<String> datiInputs = new ArrayList<String>();
										ArrayList<String> datiBInputs = new ArrayList<String>();
										ArrayList<String> obiettivi = new ArrayList<String>();
										ArrayList<String> hp = new ArrayList<String>();
										String check = request.getParameter("check");
										String ragioneSocialeProd = request.getParameter("ragioneSocialeProd");
										String sedeLegaleProd = request.getParameter("sedeLegaleProd");
										String pIvaProd = request.getParameter("pIvaProd");
										String telefonoProd = request.getParameter("telefonoProd");
										String emailProd = request.getParameter("emailProd");
										produttoreInputs.add(ragioneSocialeProd);
										produttoreInputs.add(sedeLegaleProd);
										produttoreInputs.add(pIvaProd);
										produttoreInputs.add(telefonoProd);
										produttoreInputs.add(emailProd);

										String ragioneSocialeCom = null;
										String sedeLegaleCom = null;
										String pIvaCom = null;
										String telefonoCom = null;
										String emailCom = null;
										if (check == null || !check.equals("true")) {
											ragioneSocialeCom = request.getParameter("ragioneSocialeCom");
											sedeLegaleCom = request.getParameter("sedeLegaleCom");
											pIvaCom = request.getParameter("pIvaCom");
											telefonoCom = request.getParameter("telefonoCom");
											emailCom = request.getParameter("emailCom");
											committenteInputs.add(ragioneSocialeCom);
											committenteInputs.add(sedeLegaleCom);
											committenteInputs.add(pIvaCom);
											committenteInputs.add(telefonoCom);
											committenteInputs.add(emailCom);
										}

										String data = request.getParameter("data");
										String dataConferma = request.getParameter("dataConferma");
										String luogo = request.getParameter("luogo");
										String nomeCampionatore = request.getParameter("nomeCampionatore");
										String cognomeCampionatore = request.getParameter("cognomeCampionatore");
										String norma = request.getParameter("norma");
										String quantitaCampione = request.getParameter("quantitaCampione");
										datiInputs.add(data);
										datiInputs.add(dataConferma);
										datiInputs.add(luogo);
										datiInputs.add(nomeCampionatore);
										datiInputs.add(cognomeCampionatore);
										datiInputs.add(norma);
										datiInputs.add(quantitaCampione);

										String[] obInput = request.getParameterValues("obiettivi");
										for(String o:obInput) {
											obiettivi.add(o);
										}
										
										String cer = null;
										String statoFisico = null;
										String descrizione = null;

										String[] hpInput = null;
										if (modulo.getTipo().equals("B")) {
											cer = request.getParameter("cer");
											statoFisico = request.getParameter("statoFisico");
											descrizione = request.getParameter("descrizione");
											hpInput = request.getParameterValues("hp");

											for(String h:hpInput) {
												hp.add(h);
											}
											datiBInputs.add(cer);
											datiBInputs.add(statoFisico);
											datiBInputs.add(descrizione);
										}

										PianoValidator pv = new PianoValidator();
										if (pv.dati1PianoVal(produttoreInputs)
												&& ((check == null || !check.equals("true"))
														&& pv.dati2PianoVal(committenteInputs))
												|| (check != null && check.equals("true"))
														&& pv.dati3PianoVal(datiInputs)
														&& pv.obiettiviPianoVal(obiettivi, modulo)
														&& ((modulo.getTipo().equals("B") && pv.hpPianoVal(hp, (ModuloAvanzatoBean)modulo))
																|| !modulo.getTipo().equals("B"))) {
											modulo.setRagioneSocialeProd(ragioneSocialeProd);
											modulo.setSedeLegaleProd(sedeLegaleProd);
											modulo.setpIvaProd(pIvaProd);
											modulo.setTelefonoProd(telefonoProd);
											modulo.setEmailProd(emailProd);

											if (check != null && check.equals("true")) {
												modulo.stessaPersona();
											} else {
												modulo.setRagioneSocialeCom(ragioneSocialeCom);
												modulo.setSedeLegaleCom(sedeLegaleCom);
												modulo.setpIvaCom(pIvaCom);
												modulo.setTelefonoCom(telefonoCom);
												modulo.setEmailCom(emailCom);
											}

											modulo.setData(data);
											modulo.setDataConferma(dataConferma);
											modulo.setLuogo(luogo);
											modulo.setNomeCampionatore(nomeCampionatore);
											modulo.setCognomeCampionatore(cognomeCampionatore);
											modulo.setNorma(norma);
											modulo.setQuantitaCampione(quantitaCampione);

											if (modulo.getTipo().equals("B")) {
												((ModuloAvanzatoBean) modulo).setCer(cer);
												((ModuloAvanzatoBean) modulo).setStatoFisico(statoFisico);
												((ModuloAvanzatoBean) modulo).setDescrizione(descrizione);
												((ModuloAvanzatoBean) modulo).setHp(hp);
											}
											
											modulo.setObiettivi(obiettivi);
											
										}else {
											redirectedPage = "/CompilaModuloCliente.jsp";
											throw new Exception("ERRORE-PianoControl-user-crea: inserimento Dati.");
										}
										
										PianoBean piano=new PianoBean();
										piano.generaId();
										piano.setContenuto(pacchetto.getContenuto());
										piano.setUsername(user.getUsername());
										piano.setPrezzo(pacchetto.calcolaPrezzo());
										
										String SAVE_DIR = "uploadTemp";
										String appPath = request.getServletContext().getRealPath("");
										String savePath = appPath + SAVE_DIR;

										File fileSaveDir = new File(savePath);
										if (!fileSaveDir.exists()) {
											fileSaveDir.mkdir();
										}
										
										modelPiano.doSave(piano);
										piano.stampContenuto(savePath+File.separator);
										modulo.stampModulo(piano.getId());
										modelInfo.setIdPiano(piano.getId()-1, piano.getId());
										SendEmail sm=new SendEmail();
										boolean sendEmail=sm.confermaPiano(user);
										if (sendEmail) {
											redirectedPage = "/StoricoCliente.jsp";
										} else
											throw new Exception("ERRORE-PianoControl-user-crea: invio e-mail");
									} else {
										redirectedPage = "/SceltaTipoPacchettoCliente.jsp";
										throw new Exception("ERRORE-PianoControl-user-crea: pacchetto null.");
									}
								} else {
									redirectedPage = "/SceltaMatriceCliente.jsp";
									throw new Exception("ERRORE-PianoControl-user-crea: matrice null.");
								}
							}
						}
					}
				} else
					throw new Exception("ERRORE-PianoControl: action null.");
			} else
				throw new Exception("ERRORE-PianoControl: nessun utente loggato.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
