package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.PacchettoBean;
import ekh.bean.ParametroBean;
import ekh.model.PacchettoModelDM;
import ekh.model.ParametroModelDM;

@WebServlet("/ModificaParametroPacchettoServlet")
public class ModificaParametroPacchettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PacchettoModelDM modelPacchetto = new PacchettoModelDM();
	ParametroModelDM modelParametro = new ParametroModelDM();

	public ModificaParametroPacchettoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePageAdmin.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				String action = request.getParameter("action");
				if (action != null) {
					PacchettoBean pacchetto = (PacchettoBean) request.getSession().getAttribute("pacchetto");
					if (pacchetto != null) {
						String idParametro = request.getParameter("idParametro");
						if (idParametro != null) {
							ParametroBean bean = new ParametroBean();
							bean = modelParametro.doRetrieveByKey(idParametro);
							if (!bean.isEmpty()) {
								if (action.equals("aggiungi")) {
									pacchetto.addParametro(bean);
								} else if (action.equals("rimuovi")) {
									pacchetto.remParametro(bean);
								} else
									throw new Exception("ERRORE-ModificaParametroPacchettoServlet: invalid action.");
								pacchetto.stampContenuto();
								request.getSession().removeAttribute("pacchetto");
								request.getSession().setAttribute("pacchetto", pacchetto);
								redirectedPage = "/ModificaPacchettoAdmin.jsp";
							} else
								throw new Exception("ERRORE-ModificaParametroPacchettoServlet: Parametro non trovato");
						} else
							throw new Exception("ERRORE-ModificaParametroPacchettoServlet: idParametro null");
					} else {
						redirectedPage = "/GestionePacchettiAdmin.jsp";
						throw new Exception("ERRORE-ModificaParametroPacchettoServlet: pacchetto null");					
					}	
				} else
						throw new Exception("ERRORE-ModificaParametroPacchettoServlet: action null.");
			} else {
				redirectedPage = "/LoginAdmin.jsp";
				throw new Exception("ERRORE-ModificaParametroPacchettoServlet: Admin non loggato.");
			}
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
