package ekh.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.ClienteBean;
import ekh.support.EncryptionPassword;
import ekh.model.ClienteModelDM;

@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM model = new ClienteModelDM();

	public LoginUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("adminRoles");
		request.getSession().removeAttribute("Admin");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		   
		String redirectedPage = "/jsp/LoginFormUser.jsp";

		ClienteBean bean = new ClienteBean();

		try {
			if (!username.equals("") && username != null && !password.equals("") && password != null) {
				bean = model.verificaLogin(username, EncryptionPassword.MD5(password));
				if (!bean.isEmpty()) {
					request.getSession().setAttribute("userRoles", true);
					request.getSession().setAttribute("Utente", bean);
					redirectedPage = "/jsp/HomePage.jsp";
				} else 
					throw new Exception("Accesso Negato");
			} else
				throw new Exception("Accesso Negato");

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			request.getSession().setAttribute("userRoles", false);
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

}
