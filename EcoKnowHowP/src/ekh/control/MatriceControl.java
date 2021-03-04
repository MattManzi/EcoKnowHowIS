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
import ekh.model.MatriceModelDM;
import ekh.model.ParametroModelDM;
import ekh.strategy.MatriceValidator;

@WebServlet("/Matrice")
public class MatriceControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MatriceModelDM modelMatrice = new MatriceModelDM();
	ParametroModelDM modelParametro= new ParametroModelDM();
	
	public MatriceControl() {
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
					if (admin != null) {
						redirectedPage = "/GestioneMatriciAdmin.jsp";
						if (action.equals("visualizza")) {
							ArrayList<MatriceBean> matrici = new ArrayList<MatriceBean>();
							matrici = modelMatrice.doRetrieveAll("id");
							request.setAttribute("matrici", matrici);
							String jsp = request.getParameter("jsp");
							if (jsp != null) {
								if (jsp.equals("pacchetto")) {
									redirectedPage = "/AggiungiPacchetto.jsp";
								} else
									throw new Exception("ERRORE-MatriceControl-admin-visualizza: invalid jsp.");
							}
						} else if (action.equals("nome") || action.equals("sottotitolo")
								|| action.equals("descrizione")) {
							MatriceBean matrice = (MatriceBean) request.getSession().getAttribute("matrice");
							if (matrice != null) {
								String dato = request.getParameter("dato");
								if (dato != null) {
									MatriceValidator mv = new MatriceValidator();
									if (mv.modificaMatriceVal(action, dato)) {
										modelMatrice.doUpdate(action, dato, String.valueOf(matrice.getId()));
										redirectedPage = "/ModificaMatriceAdmin.jsp";
										request.getSession().removeAttribute("matrice");
										request.getSession().setAttribute("matrice",
												modelMatrice.doRetrieveByKey(String.valueOf(matrice.getId())));
									} else
										throw new Exception(
												"ERRORE-MatriceControl-admin-nome/sottotitolo/descrizione: inserimento dati.");
								} else
									throw new Exception(
											"ERRORE-MatriceControl-admin-nome/sottotitolo/descrizione: dati null.");
							} else
								throw new Exception("ERRORE-MatriceControl-admin-nome/sottotitolo/descrizione: Matrice null");
						} else if (action.equals("select")) {
							request.getSession().removeAttribute("matrice");
							String id = request.getParameter("id");
							if (id != null) {
								MatriceBean bean = new MatriceBean();
								bean = modelMatrice.doRetrieveByKey(id);
								if (!bean.isEmpty()) {
									redirectedPage = "/ModificaMatriceAdmin.jsp";
									request.getSession().setAttribute("matrice", bean);
								} else 
									throw new Exception("ERRORE-MatriceControl-admin-select: Matrice non trovata.");
							} else 
								throw new Exception("ERRORE-MatriceControl-admin-select: id null.");
						} else if (action.equals("aggiungi")) {
							String nome = request.getParameter("nome");
							String sottotitolo = request.getParameter("sottotitolo");
							String descrizione = request.getParameter("descrizione");

							ArrayList<String> inputs = new ArrayList<String>();
							inputs.add(nome);
							inputs.add(sottotitolo);
							inputs.add(descrizione);

							MatriceValidator mv = new MatriceValidator();

							if (mv.aggiuntaVal(inputs)) {
								MatriceBean bean = new MatriceBean();
								bean.setNome(nome);
								bean.setSottotitolo(sottotitolo);
								bean.setDescrizione(descrizione);
								modelMatrice.doSave(bean);
							} else
								throw new Exception("ERRORE-MatriceControl-admin-aggiungi: inserimento dati.");
						} else if (action.equals("delete")) {
							String id = request.getParameter("id");
							if (id != null) {
								modelMatrice.doDelete(id);
							} else
								throw new Exception("ERRORE-MatriceControl-admin-delete: id null");
						} else
							throw new Exception("ERRORE-MatriceControl-admin: invalid action for admin.");
					} else {
						if (action.equals("visualizza")) {
							ArrayList<MatriceBean> matrici = new ArrayList<MatriceBean>();
							matrici = modelMatrice.doRetrieveAll("id");
							request.setAttribute("matrici", matrici);
							redirectedPage = "/SceltaMatriceCliente.jsp";
						} else if (action.equals("select")) {
							request.getSession().removeAttribute("SelectMatrice");
							String id = request.getParameter("id");
							if (id != null) {
								MatriceBean bean = modelMatrice.doRetrieveByKey(id);
								if (!bean.isEmpty()) {
									request.getSession().setAttribute("SelectMatrice", bean);
									redirectedPage = "/SceltaTipoPacchettoCliente.jsp";
								} else
									throw new Exception("ERRORE-MatriceControl-user-select: matrice non trovata.");
							} else
								throw new Exception("ERRORE-MatriceControl-user-select: idMatrice null.");
						} else
							throw new Exception("ERRORE-MatriceControl-user: invalid action for user.");
					}
				} else
					throw new Exception("ERRORE-MatriceControl: action null");
			} else
				throw new Exception("ERRORE-MatriceControl: nessun utente loggato.");
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