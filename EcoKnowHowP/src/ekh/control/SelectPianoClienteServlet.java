package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;
import ekh.bean.PianoBean;
import ekh.model.PianoModelDM;

@WebServlet("/SelectPianoClienteServlet")
public class SelectPianoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PianoModelDM model = new PianoModelDM();

	public SelectPianoClienteServlet() {
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
			if ((admin != null && adminRoles != null && adminRoles.booleanValue())
					|| (user != null && userRoles != null && userRoles.booleanValue())) {
				String id = request.getParameter("id");
				if(id!=null) {
					PianoBean piano = new PianoBean();
					piano=model.doRetrieveByKey(id);
					if(!piano.isEmpty()) {
						request.setAttribute("piano", piano);
						redirectedPage = "/DettagliPianoCliente.jsp";
					}else					
						throw new Exception("ERRORE-SelectPianoClienteServlet: piano non trovato.");	
				}else
					throw new Exception("ERRORE-SelectPianoClienteServlet: id null.");				
			} else
				throw new Exception("ERRORE-SelectPianoClienteServlet: nessun utente loggato.");
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
