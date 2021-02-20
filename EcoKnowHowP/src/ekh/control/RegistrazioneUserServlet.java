package ekh.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.ClienteBean;
import ekh.model.ClienteModelDM;
import ekh.strategy.RegistrazioneValidator;
import ekh.support.SendEmail;

@WebServlet("/RegistrazioneUserServlet")
public class RegistrazioneUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM model = new ClienteModelDM();

	public RegistrazioneUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String redirectedPage = "/jsp/RegistrazioneUser.jsp";

		String action = request.getParameter("action");
		try {
			if (action != null) {
				if (action.equals("inserimentoDati")) {
					String nome = request.getParameter("nome");
					String cognome = request.getParameter("cognome");
					String funzioneAziendale = request.getParameter("funzioneAziendale");
					String telefono = request.getParameter("telefono");
					String ragioneSociale = request.getParameter("ragioneSociale");
					String via = request.getParameter("via");
					String civico = request.getParameter("civico");
					String cap = request.getParameter("cap");
					String comune = request.getParameter("comune");
					String pIva = request.getParameter("pIva");
					String cf = request.getParameter("cf");
					String pec = request.getParameter("pec");
					String sdi = request.getParameter("sdi");
					String email = request.getParameter("email");
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String password2 = request.getParameter("password2");

					ArrayList<String> inputs=new ArrayList<String>();
					inputs.add(nome);
					inputs.add(cognome);
					inputs.add(funzioneAziendale);
					inputs.add(ragioneSociale);
					inputs.add(comune);
					inputs.add(via);
					inputs.add(civico);
					inputs.add(pIva);
					inputs.add(cf);
					inputs.add(sdi);
					inputs.add(cap);
					inputs.add(telefono);
					inputs.add(pec);
					inputs.add(email);
					inputs.add(username);
					inputs.add(password);
					inputs.add(password2);
					
					RegistrazioneValidator rv=new RegistrazioneValidator();
					
					if(rv.validazione(inputs)) {
						SendEmail sm = new SendEmail();
						String codSicurezza = sm.getRandom();

						String indirizzo = via + ", " + civico + ", " + comune + ", " + cap;

						ClienteBean bean = new ClienteBean(username, nome, cognome, funzioneAziendale, telefono,
								ragioneSociale, indirizzo, pIva, cf, pec, sdi, email, password, codSicurezza);
						request.getSession().setAttribute("ClienteTemp", bean);

						boolean sendEmail = sm.sendEmail(bean);

						if (sendEmail) {
							redirectedPage = "/jsp/VerificaCodiceRegistrazione.jsp";
						} else
							throw new Exception("ERRORE-RegistrazioneUserServlet: invio e-mMail");
					} else
							throw new Exception("ERRORE-RegistrazioneUserServlet: inserimento dati");
				} else if (action.equals("sendEmail")) {
					SendEmail sm = new SendEmail();
					String codSicurezza = sm.getRandom();

					ClienteBean bean = (ClienteBean) request.getSession().getAttribute("ClienteTemp");
					bean.setCodSicurezza(codSicurezza);

					request.getSession().removeAttribute("ClienteTemp");
					request.getSession().setAttribute("ClienteTemp", bean);

					boolean sendEmail = sm.sendEmail(bean);

					if (sendEmail) {
						redirectedPage = "/jsp/VerificaCodice.jsp";
					} else
						throw new Exception("ERRORE-RegistrazioneUserServlet: invio e-mail");
				} else if (action.equals("registra")) {
					ClienteBean bean = (ClienteBean) request.getSession().getAttribute("ClienteTemp");
					try {
						model.doSave(bean);
					} catch (SQLException e) {
						System.out.println(e.toString());
					}
					redirectedPage = "/jsp/LoginUser.jsp";
				} else
					throw new Exception("ERRORE-RegistrazioneUserServlet: invalid action");
			} else
				throw new Exception("ERRORE-RegistrazioneUserServlet: action null");
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

	public boolean valEmail(String str) {
		if(str!=null && !str.equals("") && str.length()<=50) {
			return Pattern.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", str);
		}
		return false;
	}
}
