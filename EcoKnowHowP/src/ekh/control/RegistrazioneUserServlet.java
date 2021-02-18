package ekh.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.ClienteBean;
import ekh.model.ClienteModelDM;
import ekh.bean.SendEmail;

@WebServlet("/RegistrazioneUserServlet")
public class RegistrazioneUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM model = new ClienteModelDM();

	public RegistrazioneUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String redirectedPage = "jsp/RegistrazioneUser.jsp";

		String username = request.getParameter("username").trim();
		String nome = request.getParameter("nome").trim();
		String cognome = request.getParameter("cognome").trim();
		String funzioneAziendale = request.getParameter("funzioneAziendale").trim();
		String telefono = request.getParameter("telefono").trim();
		String ragioneSociale = request.getParameter("ragioneSociale").trim();
		String via = request.getParameter("via").trim();
		String civico = request.getParameter("civico").trim();
		String cap = request.getParameter("cap").trim();
		String comune = request.getParameter("comune").trim();
		String pIva = request.getParameter("pIva").trim();
		String cf = request.getParameter("cf").trim();
		String pec = request.getParameter("pec").trim();
		String sdi = request.getParameter("sdi").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();

		try {
			if (!username.equals("") && username != null && !email.equals("") && email != null
					&& model.controlloDato("username", username) && model.controlloDato("email", email)
					&& !nome.equals("") && nome != null && !cognome.equals("") && cognome != null
					&& !funzioneAziendale.equals("") && funzioneAziendale != null && !telefono.equals("")
					&& telefono != null && !ragioneSociale.equals("") && ragioneSociale != null && !via.equals("")
					&& via != null && !civico.equals("") && civico != null && !cap.equals("") && cap != null
					&& !comune.equals("") && comune != null && ((!pIva.equals("") && pIva != null) || (!cf.equals("")
					&& cf != null)) && !pec.equals("") && pec != null && !sdi.equals("") && sdi != null
					&& !email.equals("") && email != null && !password.equals("") && password != null) {
				
					SendEmail sm=new SendEmail();
					String codSicurezza=sm.getRandom();
					
					String indirizzo=via+", "+civico+", "+comune+", "+cap;
					
					ClienteBean bean=new ClienteBean(username, nome, cognome, funzioneAziendale, telefono, ragioneSociale, indirizzo, pIva, cf, pec, sdi, email, password, codSicurezza);
					request.getSession().setAttribute("ClienteTemp", bean);
					
					boolean sendEmail=sm.sendEmail(bean);
			}
		} catch (SQLException e) {
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
