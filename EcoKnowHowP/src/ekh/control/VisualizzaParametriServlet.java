package ekh.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;
import ekh.bean.ParametroBean;
import ekh.model.ParametroModelDM;

@WebServlet("/VisualizzaParametriServlet")
public class VisualizzaParametriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ParametroModelDM model = new ParametroModelDM();

	public VisualizzaParametriServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/jsp/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

		try {
			if ((admin != null && adminRoles != null && adminRoles.booleanValue())
					|| (user != null && userRoles != null && userRoles.booleanValue())) {	
				String log="";
				if(admin!=null) {
					log="admin";
				}else {
					log="cliente";
				}
				ArrayList<ParametroBean> parametri = new ArrayList<ParametroBean>();				

				String idMatrice = request.getParameter("idMatrice");
				
				parametri = model.doRetrieveByMatrix(idMatrice);
				
				if (log.equals("admin")) {
					String action = request.getParameter("action");					
					if (action != null) {
						request.setAttribute("parametri", parametri);
						if (action.equals("matrice")) {
							redirectedPage = "/ModificaMatriceAdmin.jsp";
						} else if (action.equals("pacchetto")) {
							redirectedPage = "/ComponiPacchetto.jsp";
						} else
							throw new Exception("ERRORE - VisualizzaParametriServlet: invalid action.");
					} else
						throw new Exception("ERRORE - VisualizzaParametriServlet: action null.");
				} else {
					redirectedPage = "/ComponiPacchetto.jsp";
				}
			}else
				throw new Exception("ERRORE-VisualizzaParametriServlet: nessun utente loggato");
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
