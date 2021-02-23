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

/**
 * Servlet implementation class SelectMatriceAdminServlet
 */
@WebServlet("/SelectMatriceAdminServlet")
public class SelectMatriceAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MatriceModelDM model = new MatriceModelDM();

	public SelectMatriceAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePageAdmin.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");

		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				request.getSession().removeAttribute("matrice");
				String id = request.getParameter("id");
				if (id != null) {
					MatriceBean bean = new MatriceBean();
					bean = model.doRetrieveByKey(id);
					if (!bean.isEmpty()) {
						redirectedPage = "/ModificaMatriceAdmin.jsp";
						request.getSession().setAttribute("matrice", bean);
					} else
						throw new Exception("ERRORE-SelectMatriceAdminServlet: Matrice non trovato");
				} else
					throw new Exception("ERRORE-SelectMatriceAdminServlet: id null");
			} else {
				redirectedPage = "/LoginAdmin.jsp";
				throw new Exception("ERRORE-SelectMatriceAdminServlet: Admin non loggato");
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
