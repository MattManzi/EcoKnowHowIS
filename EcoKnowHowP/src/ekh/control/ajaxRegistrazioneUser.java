package ekh.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.model.AmministratoreModelDM;
import ekh.model.ClienteModelDM;

@WebServlet("/ajaxRegistrazioneUser")
public class ajaxRegistrazioneUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM modelCliente = new ClienteModelDM();
	AmministratoreModelDM modelAdmin = new AmministratoreModelDM();

	public ajaxRegistrazioneUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String txt = "";
		try {

			if (action != null) {
				if (action.equals("username")) {
					String user = request.getParameter("dato");
					if (!modelCliente.controlloDato("username", user))
						throw new Exception("ERRORE-ajaxRegistrazioneUser-username in cliente gia esistente.");
							
					if (!modelAdmin.controlloDato("username", user)) 
						throw new Exception("ERRORE-ajaxRegistrazioneUser-username in amministratore gia esistente.");

				} else if (action.equals("email")) {
					String email = request.getParameter("dato");
					if (!modelCliente.controlloDato("email", email))
						throw new Exception("ERRORE-ajaxRegistrazioneUser-email in cliente gia esistente.");
							
					if (!modelAdmin.controlloDato("email", email)) 
						throw new Exception("ERRORE-ajaxRegistrazioneUser-email in amministratore gia esistente.");


				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			txt = "0";
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
