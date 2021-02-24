package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.MatriceBean;
import ekh.model.MatriceModelDM;

@WebServlet("/ModificaMatriceServlet")
public class ModificaMatriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MatriceModelDM model = new MatriceModelDM();

	public ModificaMatriceServlet() {
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
					if (action.equals("nome") || action.equals("sottotitolo") || action.equals("descrizione")) {
						MatriceBean matrice = (MatriceBean) request.getSession().getAttribute("matrice");
						if(matrice!=null) {
							String dato = request.getParameter("dato");
							if (dato != null) {
								model.doUpdate(action, dato, String.valueOf(matrice.getId()));
								redirectedPage = "/ModificaMatriceAdmin.jsp";
								request.getSession().removeAttribute("matrice");
								request.getSession().setAttribute("matrice", model.doRetrieveByKey(String.valueOf(matrice.getId())));
							} else
								throw new Exception("ERRORE-ModificaMatriceServlet: dati null.");
						}else {
							redirectedPage = "/GestioneMatriciAdmin.jsp";
							throw new Exception("ERRORE-ModificaMatriceServlet: Matrice null");
						}
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
