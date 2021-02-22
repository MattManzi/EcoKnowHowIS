package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.ClienteBean;
import ekh.bean.MatriceBean;
import ekh.model.MatriceModelDM;

@WebServlet("/SceltaMatriceUser")
public class SceltaMatriceUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MatriceModelDM model = new MatriceModelDM();

	public SceltaMatriceUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

	/*	Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");*/

		try {
			//if (user != null && userRoles != null && userRoles.booleanValue()) {*/
				String idMatrice = request.getParameter("idMatrice");
				if (idMatrice != null) {
					MatriceBean bean = model.doRetrieveByKey(idMatrice);
					if (!bean.isEmpty()) {
						request.getSession().setAttribute("SelectMatrice", bean);
						redirectedPage= "/SceltaTipoPacchettoUser.jsp";
					}else
						throw new Exception("ERRORE-SceltaMatriceUser: matrice non trovata.");
				} else
					throw new Exception("ERRORE-SceltaMatriceUser: idMatrice null.");
			/*} else
				throw new Exception("ERRORE-SceltaMatriceUser: Nessun Utente loggato.");*/
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
