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
import ekh.model.AmministratoreModelDM;
import ekh.model.ClienteModelDM;
import ekh.support.EncryptionPassword;

@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AmministratoreModelDM modelAdmin = new AmministratoreModelDM();
	ClienteModelDM modelCliente = new ClienteModelDM();
	
	public LoginControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("userRoles");
		request.getSession().removeAttribute("Utente");
		request.getSession().removeAttribute("adminRoles");
		request.getSession().removeAttribute("Admin");
		request.getSession().invalidate();

		String redirectedPage = "/LoginFormUser.jsp";
		String action = request.getParameter("action");

		try {
			if (action != null) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if (username != null && password != null) {
					if (action.equals("admin")) {
						AmministratoreBean bean=new AmministratoreBean();
						bean = modelAdmin.verificaLogin(username, EncryptionPassword.MD5(password));
						if (!bean.isEmpty()) {
							request.getSession().setAttribute("adminRoles", true);
							request.getSession().setAttribute("Admin", bean);
							redirectedPage = "/HomePageAdmin.jsp";
						} else {
							request.getSession().setAttribute("adminRoles", false);
							throw new Exception("ERRORE-LoginControl-admin: Account non trovato.");	
						}
					} else if (action.equals("user")) {
						ClienteBean bean = new ClienteBean();
						bean = modelCliente.verificaLogin(username, EncryptionPassword.MD5(password));
						if (!bean.isEmpty() && bean.getAttivo()==1) {
							request.getSession().setAttribute("userRoles", true);
							request.getSession().setAttribute("Utente", bean);
							redirectedPage = "/HomePage.jsp";
						} else {
							request.getSession().setAttribute("userRoles", false);
							throw new Exception("ERRORE-LoginControl-user: Account non trovato.");
						}
					} else
						throw new Exception("ERRORE-LoginControl: invalid action.");
				}else
					throw new Exception("ERRORE-LoginControl: Dati null");
			} else
				throw new Exception("ERRORE-LoginControl: action null.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			request.getSession().setAttribute("adminRoles", false);
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

}
