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
import ekh.model.ClienteModelDM;

@WebServlet("/SelectClienteAdminServlet")
public class SelectClienteAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM model=new ClienteModelDM();
	
	public SelectClienteAdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				request.getSession().removeAttribute("cliente");
				String username = request.getParameter("username");
				if (username != null) {
					ClienteBean bean = new ClienteBean();
					bean = model.doRetrieveByKey(username);
					if (!bean.isEmpty()) {
						redirectedPage = "/StoricoCliente.jsp";
						request.getSession().setAttribute("cliente", bean);
					} else
						throw new Exception("ERRORE-SelectClienteAdminServlet: Cliente non trovato");
				} else
					throw new Exception("ERRORE-SelectClienteAdminServlet: username null");
			} else {
				redirectedPage = "/LoginAdmin.jsp";
				throw new Exception("ERRORE-SelectClienteAdminServlet: Admin non loggato");
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
