package ekh.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.ClienteBean;
import ekh.model.AmministratoreModelDM;
import ekh.model.ClienteModelDM;
import ekh.strategy.RegistrazioneValidator;
import ekh.support.SendEmail;

@WebServlet("/RegistrazioneUserServlet")
public class RegistrazioneUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM modelCliente = new ClienteModelDM();
	AmministratoreModelDM modelAdmin = new AmministratoreModelDM();

	public RegistrazioneUserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String redirectedPage = "/RegistrazioneUser.jsp";

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

					ArrayList<String> inputs = new ArrayList<String>();
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

					RegistrazioneValidator rv = new RegistrazioneValidator();

					if (rv.validazione(inputs)) {
						if (modelCliente.controlloDato("email", email)
								&& modelCliente.controlloDato("username", username)
								&& modelAdmin.controlloDato("email", email)
								&& modelAdmin.controlloDato("username", username)) {
							
							SendEmail sm = new SendEmail();
							String codSicurezza = sm.getRandom();

							String indirizzo = via + ", " + civico + ", " + comune + ", " + cap;

							ClienteBean bean = new ClienteBean(username, nome, cognome, funzioneAziendale, telefono,
									ragioneSociale, indirizzo, pIva, cf, pec, sdi, email, password, codSicurezza);
							
							request.getSession().setAttribute("ClienteTemp", bean);

							boolean sendEmail = sm.sendEmail(bean);

							if (sendEmail) {
								redirectedPage = "/VerificaCodiceSicurezzaRegistrazione.jsp";
							} else
								throw new Exception("ERRORE-RegistrazioneUserServlet: invio e-mMail");
						}else
							throw new Exception("ERRORE-RegistrazioneUserServlet: email/username esistente.");
					} else
						throw new Exception("ERRORE-RegistrazioneUserServlet: inserimento dati");
				} else if (action.equals("sendEmail")) {
					ClienteBean bean = (ClienteBean) request.getSession().getAttribute("ClienteTemp");
					if(bean!=null) {
						SendEmail sm = new SendEmail();						
						bean.setCodSicurezza(sm.getRandom());

						request.getSession().removeAttribute("ClienteTemp");
						request.getSession().setAttribute("ClienteTemp", bean);
						boolean sendEmail = sm.sendEmail(bean);

						if (sendEmail) {
							redirectedPage = "/VerificaCodiceSicurezzaRegistrazione.jsp";
						} else
							throw new Exception("ERRORE-RegistrazioneUserServlet: invio e-mail");
					}else {
						redirectedPage = "/RegistrazioneUser.jsp";
						throw new Exception("ERRORE-RegistrazioneUserServlet: utente null");
					}
				} else if (action.equals("registra")) {
					String codice=request.getParameter("codice");
					if(codice!=null) {
						ClienteBean bean = (ClienteBean) request.getSession().getAttribute("ClienteTemp");
						if(bean!=null) {
							if(codice.equals(bean.getCodSicurezza()) && bean.getAttivo()==0) {
								bean.setAttivo(1);
								modelCliente.doSave(bean);
								redirectedPage = "/LoginUser.jsp";
							}else {
								redirectedPage = "/VerificaCodiceSicurezzaRegistrazione.jsp";
								throw new Exception("ERRORE-RegistrazioneUserServlet: codice errato");
							}
						}else {
							redirectedPage = "/RegistrazioneUser.jsp";
							throw new Exception("ERRORE-RegistrazioneUserServlet: codice null");
						}							
					}else
						throw new Exception("ERRORE-RegistrazioneUserServlet: codice null");
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
}
