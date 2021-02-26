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
import ekh.model.ParametroModelDM;

@WebServlet("/ParametroControl")
public class ParametroControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ParametroModelDM model = new ParametroModelDM();

	public ParametroControl() {
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
					if (action.equals("componi")) {
						PacchettoBean pacchetto = (PacchettoBean) request.getSession().getAttribute("creaPacchetto");
						if (pacchetto != null) {
							ArrayList<ParametroBean> parametri = new ArrayList<ParametroBean>();
							parametri = model.doRetrieveByMatrix(String.valueOf(pacchetto.getIdMatrice()));
							redirectedPage = "/ComponiPacchetto.jsp";
							request.setAttribute("parametri", parametri);
						} else {
							redirectedPage = "/CreaPacchetto.jsp";
							throw new Exception("ERRORE-ParametroControl-componi: Pacchetto null.");
						}
					}
					if (admin != null) {
						if (action.equals("visualizza")) {
							String jsp = request.getParameter("jsp");
							if (jsp != null) {
								if (jsp.equals("matrice") || jsp.equals("pacchetto")) {
									ArrayList<ParametroBean> parametri = new ArrayList<ParametroBean>();
									if (jsp.equals("matrice")) {
										MatriceBean matrice = (MatriceBean) request.getSession()
												.getAttribute("matrice");
										if (matrice != null) {
											parametri = model.doRetrieveByMatrix(String.valueOf(matrice.getId()));
											redirectedPage = "/ModificaMatriceAdmin.jsp";
										} else {
											redirectedPage = "/GestioneMatriciAdmin.jsp";
											throw new Exception(
													"ERRORE-ParametroControl-matrice/pacchetto: Matrice null");
										}
									} else {
										PacchettoBean pacchetto = (PacchettoBean) request.getSession()
												.getAttribute("pacchetto");
										if (pacchetto != null) {
											parametri = model
													.doRetrieveByMatrix(String.valueOf(pacchetto.getIdMatrice()));
											redirectedPage = "/ModificaPacchettoAdmin.jsp";
										} else {
											redirectedPage = "/GestionePacchettiAdmin.jsp";
											throw new Exception(
													"ERRORE-ParametroControl-matrice/pacchetto: Matrice null");
										}
									}
									request.setAttribute("parametri", parametri);
								} else
									throw new Exception("ERRORE-ParametroControl: invalid jsp.");
							} else
								throw new Exception("ERRORE-ParametroControl: jsp null.");
						} else if (action.equals("addParM") || action.equals("delParM")) {
							MatriceBean matrice = (MatriceBean) request.getSession().getAttribute("matrice");
							if (matrice != null) {
								if (action.equals("aggiungiPar")) {
									String nome = request.getParameter("nome");
									String sku = request.getParameter("sku");
									String campione = request.getParameter("campione");
									String campionamento = request.getParameter("campionamento");
									String limiteEmissione = request.getParameter("limiteEmissione");
									String uMisura = request.getParameter("uMisura");
									String prezzo = request.getParameter("prezzo");

									/*
									 * ArrayList<String> inputs = new ArrayList<String>(); inputs.add(nome);
									 * inputs.add(sku); inputs.add(campione); inputs.add(campionamento);
									 * inputs.add(limiteEmissione); inputs.add(uMisura); inputs.add(prezzo);
									 * 
									 * AggiungiParametroValidator ps = new AggiungiParametroValidator(); if
									 * (ps.validazione(inputs)) {
									 */
									ParametroBean bean = new ParametroBean();
									bean.setIdMatrice(matrice.getId());
									bean.setNome(nome);
									bean.setSku(sku);
									bean.setCampione(campione);
									bean.setCampionamento(campionamento);
									bean.setLimiteEmissione(limiteEmissione);
									bean.setuMisura(uMisura);
									bean.setPrezzo(Double.parseDouble(prezzo));

									model.doSave(bean);
									/*
									 * } else throw new
									 * Exception("ERRORE-AggiuntaParametroServlet: inseriemento Dati.");
									 */
								} else {
									String idParametro = request.getParameter("idParametro");
									if (idParametro != null) {
										model.doDelete(idParametro);
									} else {
										redirectedPage = "/ModificaMatriceAdmin.jsp";
										throw new Exception(
												"ERRORE-ParametroControl-addParM/delParM: idParametro null");
									}
								}
								redirectedPage = "/ModificaMatriceAdmin.jsp";
							}else {
								redirectedPage = "/GestioneMatriciAdmin.jsp";
								throw new Exception("ERRORE-MatriceControl-aggiungiPar/rimuoviPar: Matrice null");
							}
						}
					} else {

					}
				} else
					throw new Exception("ERRORE-ParametroControl: action null.");
			} else
				throw new Exception("ERRORE-ParametroControl: nessun utente loggato.");
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