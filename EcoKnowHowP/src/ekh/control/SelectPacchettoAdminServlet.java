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
import ekh.model.PacchettoModelDM;

@WebServlet("/SelectPacchettoAdminServlet")
public class SelectPacchettoAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PacchettoModelDM model=new PacchettoModelDM();
	
	public SelectPacchettoAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				request.getSession().removeAttribute("pacchetto");
				String id = request.getParameter("id");
				if (id != null) {
					PacchettoBean bean = new PacchettoBean();
					bean = model.doRetrieveByKey(id);
					if (!bean.isEmpty()) {
						bean.readContenuto();
						redirectedPage = "/ModificaPacchettoAdmin.jsp";
						request.getSession().setAttribute("pacchetto", bean);
					} else
						throw new Exception("ERRORE-SelectPacchettoAdminServlet: Pacchetto non trovata.");
				} else
					throw new Exception("ERRORE-SelectPacchettoAdminServlet: id null.");
			} else {
				redirectedPage = "/LoginAdmin.jsp";
				throw new Exception("ERRORE-SelectPacchettoAdminServlet: Admin non loggato.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
