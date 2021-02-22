package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.support.EncryptionPassword;
import ekh.model.AmministratoreModelDM;

@WebServlet("/LoginAdminServlet")
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AmministratoreModelDM model = new AmministratoreModelDM();
	
    public LoginAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("userRoles");
		request.getSession().removeAttribute("Utente");
		request.getSession().invalidate();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String redirectedPage = "/LoginAdmin.jsp";
		System.out.println("1");
		try {
			if (!username.equals("") && username != null && !password.equals("") && password != null) {
				AmministratoreBean bean=new AmministratoreBean();
				bean = model.verificaLogin(username, EncryptionPassword.MD5(password));
				if (!bean.isEmpty()) {
					request.getSession().setAttribute("adminRoles", true);
					request.getSession().setAttribute("Admin", bean);
					redirectedPage = "/HomePageAdmin.jsp";
				} else 
					throw new Exception("ERRORE-LoginAdmin: Utente non trovato.");				
			} else
				throw new Exception("ERRORE-LoginAdmi: Dati Errati");
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			request.getSession().setAttribute("adminRoles", false);
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
