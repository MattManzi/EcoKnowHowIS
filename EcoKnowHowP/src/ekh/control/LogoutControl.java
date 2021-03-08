package ekh.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class LogoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LogoutControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("adminRoles");
		request.getSession().removeAttribute("userRoles");
		request.getSession().removeAttribute("Utente");
		request.getSession().removeAttribute("Admin");
		request.getSession().invalidate();
		
		String redirectedPage="/HomePage.jsp";
		redirectedPage = response.encodeURL(redirectedPage);
		response.sendRedirect(request.getContextPath()+redirectedPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
