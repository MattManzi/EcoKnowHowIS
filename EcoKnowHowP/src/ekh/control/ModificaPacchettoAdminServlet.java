package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.model.PacchettoModelDM;

@WebServlet("/ModificaPacchettoAdminServlet")
public class ModificaPacchettoAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PacchettoModelDM model = new PacchettoModelDM();

	public ModificaPacchettoAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");

		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				String action = request.getParameter("action");
				if (action != null) {
					if (action.equals("nome") || action.equals("prezzo") || action.equals("descrizione")) {
						String id = request.getParameter("id");
						String dato = request.getParameter("dato");
						if (id != null && dato != null) {
							model.doUpdate(action, dato, id);
							redirectedPage = "/ModificaPacchettoAdmin.jsp";
							request.getSession().removeAttribute("pacchetto");
							request.getSession().setAttribute("pacchetto", model.doRetrieveByKey(id));
						} else
							throw new Exception("ERRORE-ModificaMatriceServlet: dati null.");
					} else
						throw new Exception("ERRORE-ModificaMatriceServlet: invalid action.");
				} else
					throw new Exception("ERRORE-ModificaMatriceServlet: action null.");
			} else {
				redirectedPage = "/LoginAdmin.jsp";
				throw new Exception("ERRORE-SelectMatriceAdminServlet: Admin non loggato.");
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
