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
import ekh.model.ClienteModelDM;

@WebServlet("/VisualizzaClientiServlet")
public class VisualizzaClientiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM model = new ClienteModelDM();

	public VisualizzaClientiServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				ArrayList<ClienteBean> clienti = new ArrayList<ClienteBean>();
				clienti=model.doRetrieveAll("username");
				request.setAttribute("clienti", clienti);
				redirectedPage = "/GestioneClientiAdmin.jsp";
			} else
				throw new Exception("ERRORE-VisualizzaClientiServlet: admin non loggato");
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
