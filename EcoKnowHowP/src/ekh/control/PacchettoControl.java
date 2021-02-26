package ekh.control;

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
import ekh.bean.PacchettoBean;
import ekh.bean.ParametroBean;
import ekh.model.PacchettoModelDM;
import ekh.model.ParametroModelDM;
import ekh.strategy.AggiungiPacchettoValidator;

@WebServlet("/ModificaPacchettoAdminServlet")
public class PacchettoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PacchettoModelDM modelPacchetto = new PacchettoModelDM();
	ParametroModelDM modelParametro = new ParametroModelDM();

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
							inputs.add(idMatrice);
							inputs.add(nome);
							inputs.add(descrizione);
							AggiungiPacchettoValidator pv = new AggiungiPacchettoValidator();
							if (pv.validazione(inputs)) {
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
									bean.setPrezzo(bean.calcolaPrezzo());
									modelPacchetto.doSave(bean);
									bean.stampContenuto();
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
							if (action.equals("visualizza")) {
								ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
								pacchetti = modelPacchetto.doRetrieveAll("id");
								redirectedPage = "/GestionePacchettiAdmin.jsp";
								request.setAttribute("pacchetti", pacchetti);
							} else if (action.equals("nome") || action.equals("prezzo")
									|| action.equals("descrizione")) {
								String id = request.getParameter("id");
								String dato = request.getParameter("dato");
								if (id != null && dato != null) {
									modelPacchetto.doUpdate(action, dato, id);
									redirectedPage = "/ModificaPacchettoAdmin.jsp";
									request.getSession().removeAttribute("pacchetto");
									request.getSession().setAttribute("pacchetto", modelPacchetto.doRetrieveByKey(id));
								} else
									throw new Exception("ERRORE-PacchettoControl-admin-visualizza: dati null.");
							} else if (action.equals("aggiungiPar") || action.equals("rimuoviPar")) {
								PacchettoBean pacchetto = (PacchettoBean) request.getSession()
										.getAttribute("pacchetto");
								if (pacchetto != null) {
									String idParametro = request.getParameter("idParametro");
									if (idParametro != null) {
										ParametroBean bean = new ParametroBean();
										bean = modelParametro.doRetrieveByKey(idParametro);
										if (!bean.isEmpty()) {
											if (action.equals("aggiungiPar")) {
												pacchetto.addParametro(bean);
												pacchetto.stampContenuto();
											} else {
												pacchetto.remParametro(bean);
												pacchetto.stampContenuto();
											}
											pacchetto.stampContenuto();
											request.getSession().removeAttribute("pacchetto");
											request.getSession().setAttribute("pacchetto", pacchetto);
											redirectedPage = "/ModificaPacchettoAdmin.jsp";
										} else
											throw new Exception(
													"ERRORE-PacchettoControl-admin-aggiungiPar/rimuoviPar: Parametro non trovato");
									} else {
										redirectedPage = "/ModificaPacchettoAdmin.jsp";
										throw new Exception(
												"ERRORE-PacchettoControl-admin-aggiungiPar/rimuoviPar: idParametro null");
									}
								} else {
									redirectedPage = "/GestionePacchettiAdmin.jsp";
									throw new Exception(
											"ERRORE-PacchettoControl-admin-aggiungiPar/rimuoviPar: pacchetto null");
								}
							} else if (action.equals("delete")) {
								String id = request.getParameter("id");
								if (id != null) {
									modelPacchetto.doDelete(id);
								} else
									throw new Exception("ERRORE-PacchettoControl-admin-delete: id null.");
							} else if (action.equals("select")) {
								request.getSession().removeAttribute("pacchetto");
								String id = request.getParameter("id");
								if (id != null) {
									PacchettoBean bean = new PacchettoBean();
									bean = modelPacchetto.doRetrieveByKey(id);
									if (!bean.isEmpty()) {
										bean.readContenuto();
										redirectedPage = "/ModificaPacchettoAdmin.jsp";
										request.getSession().setAttribute("pacchetto", bean);
									} else
										throw new Exception("ERRORE-PacchettoControl-select: Pacchetto non trovata.");
								} else
									throw new Exception("ERRORE-PacchettoControl-select: id null.");
							} else
								throw new Exception("ERRORE-PacchettoControl-admin: invalid action for admin.");
							/* Funzioni action per admin - FINE */
						} else {
							/* Funzioni action per user - INIZIO */
							if (action.equals("visualizza")) {
								ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
								MatriceBean bean = (MatriceBean) request.getSession().getAttribute("SelectMatrice");
								if (bean != null) {
									String tipo = request.getParameter("tipo");
									if (tipo != null) {
										if (tipo.equals("standard")) {
											pacchetti = modelPacchetto.doRetrieveForUser("", tipo,
													String.valueOf(bean.getId()));
											redirectedPage = "/SceltaPacchettoCliente.jsp";
											request.setAttribute("pacchetti", pacchetti);
										} else if (tipo.equals("analitico")) {
											String username = user.getUsername();
											pacchetti = modelPacchetto.doRetrieveForUser(username, tipo,
													String.valueOf(bean.getId()));
											redirectedPage = "/SceltaPacchettoCliente.jsp";
											request.setAttribute("pacchetti", pacchetti);
										} else
											throw new Exception(
													"ERRORE-PacchettoControl-user-visualizza: invalid tipo");
									} else
										throw new Exception("ERRORE-PacchettoControl-user-visualizza: tipo null");
								} else
									throw new Exception("ERRORE-PacchettoControl-user-visualizza: matrice null");
							} else
								throw new Exception("ERRORE-PacchettoControl-user: invalid action for user");
						}
						/* Funzioni action per user - FINE */
					}
				} else
					throw new Exception("ERRORE-PacchettoControl: action null");
			} else
				throw new Exception("ERRORE-PacchettoControl: nessun utente loggato.");
		} catch (

		Exception e) {
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