package ekh.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.model.ClienteModelDM;


/**
 * Servlet implementation class ajaxRegistrazioneUser
 */
@WebServlet("/ajaxRegistrazioneUser")
public class ajaxRegistrazioneUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM model = new ClienteModelDM();

	public ajaxRegistrazioneUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String action = request.getParameter("action");
		String txt = "";
		if (action != null) {
			if (action.equals("user")) {
				String user = request.getParameter("user");
				try {
					if(model.controlloDato("username", user)) {
						txt="xxx";
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else if (action.equals("email")) {
				String email = request.getParameter("email");
				try {
					if(model.controlloDato("email", email)) {
						txt="xxx";
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(txt);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
