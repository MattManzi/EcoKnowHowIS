package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.model.MatriceModelDM;

@WebServlet("/RimuoviMatriceServlet")
public class RimuoviMatriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MatriceModelDM model = new MatriceModelDM();

	public RimuoviMatriceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePageAdmin.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {

				String id = request.getParameter("id");
				if (id != null) {
					model.doDelete(id);
					redirectedPage = "/GestioneMatriciAdmin.jsp";
				} else
					throw new Exception("ERRORE-RimuoviMatriceServlet: id null");
			} else {
				redirectedPage = "/LoginAdmin.jsp";
				throw new Exception("ERRORE-RimuoviMatriceServlet: Admin non loggato");
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
