package ekh.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
import ekh.bean.ParametroBean;
import ekh.model.MatriceModelDM;
import ekh.model.PacchettoModelDM;
import ekh.model.ParametroModelDM;
import ekh.strategy.PacchettoValidator;

@WebServlet("/Pacchetto")
public class PacchettoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PacchettoModelDM modelPacchetto = new PacchettoModelDM();
	ParametroModelDM modelParametro = new ParametroModelDM();
	MatriceModelDM modelMatrice = new MatriceModelDM();

	public PacchettoControl() {
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
					|| (user != null || userRoles != null && !userRoles.booleanValue())) {
				String action = request.getParameter("action");
				if (action != null) {
					/* Funzioni action per admin e per user - INIZIO */
					if (action.equals("crea") || action.equals("salva") || action.equals("componi")) {
						if (action.equals("crea")) {
							request.getSession().removeAttribute("creaPacchetto");
							String idMatrice = request.getParameter("idMatrice");
							String nome = request.getParameter("nome");
							String descrizione = request.getParameter("descrizione");
							String tipo = "";
							String username = "";
							if (admin != null) {
								tipo = "standard";
								username = admin.getUsername();
							} else {
								tipo = "analitico";
								username = user.getUsername();
							}
							ArrayList<String> inputs = new ArrayList<String>();
							inputs.add(idMatrice.trim());
							inputs.add(nome.trim());
							inputs.add(descrizione.trim());
							PacchettoValidator pv = new PacchettoValidator();
							if (pv.aggiuntaVal(inputs)) {
								PacchettoBean bean = new PacchettoBean();
								bean.setIdMatrice(Integer.parseInt(idMatrice));
								bean.setNome(nome);
								bean.setDescrizione(descrizione);
								bean.setTipo(tipo);
								bean.setUsername(username);
								request.getSession().setAttribute("creaPacchetto", bean);
								redirectedPage = "/ComponiPacchetto.jsp";
							} else
								throw new Exception("ERRORE-PacchettoControl-crea: inserimento dati.");
						} else if (action.equals("componi")) {
							PacchettoBean pacchetto = (PacchettoBean) request.getSession()
									.getAttribute("creaPacchetto");
							if (pacchetto != null) {
								redirectedPage = "/ComponiPacchetto.jsp";
								String function = request.getParameter("function");
								String id = request.getParameter("id");
								if (function != null && id != null) {
									ParametroBean bean = new ParametroBean();
									bean = modelParametro.doRetrieveByKey(id);
									if (!bean.isEmpty()) {
										if (function.equals("aggiungi") || function.equals("rimuovi")
												|| function.equals("svuota")) {
											if (function.equals("aggiungi")) {
												pacchetto.addParametro(bean);
											} else if (function.equals("rimuovi")) {
												pacchetto.remParametro(bean);
											} else {
												pacchetto.deleteContenuto();
											}
											request.getSession().removeAttribute("creaPacchetto");
											request.getSession().setAttribute("creaPacchetto", pacchetto);
										} else
											throw new Exception("ERRORE-PacchettoControl-componi: invalid action.");
									} else
										throw new Exception("ERRORE-PacchettoControl-componi: Parametro non trovato.");
								} else
									throw new Exception("ERRORE-PacchettoControl-componi: function/id null.");
							} else {
								redirectedPage = "/CreaPacchetto.jsp";
								throw new Exception("ERRORE-PacchettoControl-componi: Pacchetto null.");
							}
						} else {
							PacchettoBean bean = (PacchettoBean) request.getSession().getAttribute("creaPacchetto");
							if (bean != null) {
								if (bean.getContenuto().size() > 0) {
									bean.generaId();
									bean.setPrezzo(bean.calcolaPrezzo());
									String SAVE_DIR = "uploadTemp";
									String appPath = request.getServletContext().getRealPath("");
									String savePath = appPath + SAVE_DIR;
									File fileSaveDir = new File(savePath);
									if (!fileSaveDir.exists()) {
										fileSaveDir.mkdir();
									}
									modelPacchetto.doSave(bean);
									bean.stampContenuto(savePath + File.separator);
									if (admin != null) {
										redirectedPage = "/GestionePacchettiAdmin.jsp";
									} else {
										redirectedPage = "/SceltaTipoPacchettoCliente.jsp";
									}
								} else {
									redirectedPage = "/ComponiPacchetto.jsp";
									throw new Exception("ERRORE-PacchettoControl-salva: pacchetto vuoto.");
								}
							} else {
								redirectedPage = "/CreaPacchetto.jsp";
								throw new Exception("ERRORE-PacchettoControl-salva: pacchetto null.");
							}
						}
						/* Funzioni action per admin e per user - FINE */
					} else {
						/* Funzioni action per admin - INIZIO */
						if (admin != null) {
							redirectedPage = "/GestionePacchettiAdmin.jsp";
							if (action.equals("visualizza")) {
								ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
								pacchetti = modelPacchetto.doRetrieveAll("id");
								request.setAttribute("pacchetti", pacchetti);
							} else if (action.equals("nome") || action.equals("prezzo")
									|| action.equals("descrizione")) {
								PacchettoBean pacchetto = (PacchettoBean) request.getSession()
										.getAttribute("pacchetto");
								if (pacchetto != null) {
									redirectedPage = "/ModificaPacchettoAdmin.jsp";
									String dato = request.getParameter("dato");
									if (dato != null) {
										modelPacchetto.doUpdate(action, dato, pacchetto.getId());
										request.getSession().removeAttribute("pacchetto");
										request.getSession().setAttribute("pacchetto",
												modelPacchetto.doRetrieveByKey(pacchetto.getId()));
									} else
										throw new Exception("ERRORE-PacchettoControl-admin-visualizza: dati null.");
								} else
									throw new Exception(
											"ERRORE-PacchettoControl-admin-nome/prezzo/descrizione: pacchetto null");
							} else if (action.equals("addParam") || action.equals("remParam")) {
								PacchettoBean pacchetto = (PacchettoBean) request.getSession()
										.getAttribute("pacchetto");
								if (pacchetto != null) {
									String id = request.getParameter("id");
									if (id != null && !id.equals("-")) {
										redirectedPage = "/ModificaPacchettoAdmin.jsp";
										String SAVE_DIR = "uploadTemp";
										String appPath = request.getServletContext().getRealPath("");
										String savePath = appPath + SAVE_DIR;
										File fileSaveDir = new File(savePath);
										if (!fileSaveDir.exists()) {
											fileSaveDir.mkdir();
										}
										ParametroBean bean = new ParametroBean();
										bean = modelParametro.doRetrieveByKey(id);
										if (!bean.isEmpty()) {
											if (action.equals("addParam")) {
												pacchetto.addParametro(bean);
											} else {
												pacchetto.remParametro(bean);
											}
											pacchetto.stampContenuto(savePath+ File.separator);
											request.getSession().removeAttribute("pacchetto");
											request.getSession().setAttribute("pacchetto", pacchetto);
										} else
											throw new Exception(
													"ERRORE-PacchettoControl-admin-aggiungiPar/rimuoviPar: Parametro non trovato");
									} else
										throw new Exception(
												"ERRORE-PacchettoControl-admin-aggiungiPar/rimuoviPar: id Parametro null");
								} else
									throw new Exception(
											"ERRORE-PacchettoControl-admin-aggiungiPar/rimuoviPar: pacchetto null");
							} else if (action.equals("delete")) {
								redirectedPage = "/GestionePacchettiAdmin.jsp";
								String id = request.getParameter("id");
								if (id != null) {
									modelPacchetto.doDelete(id);
								} else
									throw new Exception("ERRORE-PacchettoControl-admin-delete: id null.");
							} else if (action.equals("select")) {
								request.getSession().removeAttribute("pacchetto");
								request.getSession().removeAttribute("matrice");
								request.getSession().removeAttribute("parametri");
								String id = request.getParameter("id");
								if (id != null) {
									PacchettoBean bean = new PacchettoBean();
									bean = modelPacchetto.doRetrieveByKey(id);
									if (!bean.isEmpty()) {
										bean.readContenuto();
										redirectedPage = "/ModificaPacchettoAdmin.jsp";
										request.getSession().setAttribute("pacchetto", bean);
										request.getSession().setAttribute("matrice",
												modelMatrice.doRetrieveByKey(String.valueOf(bean.getIdMatrice())));
										request.getSession().setAttribute("parametri",
												modelParametro.doRetrieveByMatrix(String.valueOf(bean.getIdMatrice())));
									} else
										throw new Exception(
												"ERRORE-PacchettoControl-admin-select: Pacchetto non trovata.");
								} else
									throw new Exception("ERRORE-PacchettoControl-admin-select: id null.");
							} else
								throw new Exception("ERRORE-PacchettoControl-admin: invalid action for admin.");
							/* Funzioni action per admin - FINE */
						} else {
							/* Funzioni action per user - INIZIO */
							if (action.equals("visualizza")) {
								MatriceBean bean = (MatriceBean) request.getSession().getAttribute("SelectMatrice");
								if (bean != null) {
									String tipo = request.getParameter("tipo");
									if (tipo != null) {
										if (tipo.equals("standard") || tipo.equals("analitico")) {
											ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
											if (tipo.equals("standard")) {
												pacchetti = modelPacchetto.doRetrieveForUser("", tipo,
														String.valueOf(bean.getId()));
											} else {
												String username = user.getUsername();
												pacchetti = modelPacchetto.doRetrieveForUser(username, tipo,
														String.valueOf(bean.getId()));
											}
											redirectedPage = "/SelezionaPacchettoCliente.jsp";
											request.setAttribute("pacchetti", pacchetti);
										} else
											throw new Exception(
													"ERRORE-PacchettoControl-user-visualizza: invalid tipo");
									} else
										throw new Exception("ERRORE-PacchettoControl-user-visualizza: tipo null");
								} else {
									redirectedPage = "/SceltaMatriceCliente.jsp";
									throw new Exception("ERRORE-PacchettoControl-user-visualizza: matrice null");
								}
							} else if (action.equals("select")) {
								request.getSession().removeAttribute("SelectPacchetto");
								MatriceBean matrice = (MatriceBean) request.getSession().getAttribute("SelectMatrice");
								if (matrice != null) {
									String id = request.getParameter("id");
									if (id != null) {
										PacchettoBean pacchetto = new PacchettoBean();
										pacchetto = modelPacchetto.doRetrieveByKey(id);
										pacchetto.readContenuto();
										request.getSession().setAttribute("SelectPacchetto", pacchetto);									
										
										ModuloBean modulo=null;
										if(matrice.getModulo().equals("B")) {
											modulo=new ModuloAvanzatoBean();
										}else {
											modulo=new ModuloBean();
										}
										modulo.setTipo(matrice.getModulo());
										String SAVE_DIR = "txt";
										String appPath = request.getServletContext().getRealPath("");
										String savePath = appPath + SAVE_DIR;
										File fileSaveDir = new File(savePath);
										if (!fileSaveDir.exists()) {
											fileSaveDir.mkdir();
										}
										modulo.inizializza(savePath+File.separator);
										request.getSession().setAttribute("modulo", modulo);
										redirectedPage = "/CompilaModuloCliente.jsp";
									} else {
										redirectedPage = "/SceltaPacchettoCliente.jsp";
										throw new Exception("ERRORE-PacchettoControl-user: id null.");
									}
								} 
							} else
								throw new Exception("ERRORE-PacchettoControl-user: invalid action for user");
						}
						/* Funzioni action per user - FINE */
					}
				} else
					throw new Exception("ERRORE-PacchettoControl: action null");
			} else
				throw new Exception("ERRORE-PacchettoControl: nessun utente loggato.");
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