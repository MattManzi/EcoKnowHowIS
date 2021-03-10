package ekh.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;
import ekh.bean.PacchettoBean;
import ekh.model.AmministratoreModelDM;
import ekh.model.ClienteModelDM;
import ekh.model.PacchettoModelDM;
import ekh.strategy.ClienteValidator;
import ekh.support.EncryptionPassword;
import ekh.support.SendEmail;

@WebServlet("/User")
public class ClienteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ClienteModelDM modelCliente = new ClienteModelDM();
	AmministratoreModelDM modelAdmin = new AmministratoreModelDM();
	PacchettoModelDM modelPacchetto = new PacchettoModelDM();

	public ClienteControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

		try {
			String action = request.getParameter("action");
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
					String ivaCF = request.getParameter("ivaCF");
					String pec = request.getParameter("pec");
					String sdi = request.getParameter("sdi");
					String email = request.getParameter("email");
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String password2 = request.getParameter("password2");

					System.out.println(nome + " " + ragioneSociale);
					ArrayList<String> inputs = new ArrayList<String>();

					inputs.add(nome.trim());
					inputs.add(cognome.trim());
					inputs.add(funzioneAziendale.trim());
					inputs.add(ragioneSociale.trim());
					inputs.add(comune.trim());
					inputs.add(via.trim());
					inputs.add(civico.trim());
					inputs.add(ivaCF.trim());
					inputs.add(sdi.trim());
					inputs.add(cap.trim());
					inputs.add(telefono.trim());
					inputs.add(pec.trim());
					inputs.add(email.trim());
					inputs.add(username.trim());
					inputs.add(password.trim());
					inputs.add(password2.trim());

					ClienteValidator cv = new ClienteValidator();

					if (cv.registrazioneVal(inputs)) {
						if (modelCliente.controlloDato("email", email)
								&& modelCliente.controlloDato("username", username)
								&& modelAdmin.controlloDato("email", email)
								&& modelAdmin.controlloDato("username", username)) {

							SendEmail sm = new SendEmail();
							String codSicurezza = sm.getRandom();

							String indirizzo = via + ", " + civico + ", " + comune + ", " + cap;

							ClienteBean bean = new ClienteBean(username, nome, cognome, funzioneAziendale, telefono,
									ragioneSociale, indirizzo, ivaCF, pec, sdi, email, password, codSicurezza);

							request.getSession().setAttribute("ClienteTemp", bean);

							boolean sendEmail = sm.sendEmail(bean);

							if (sendEmail) {
								redirectedPage = "/VerificaCSRegistrazione.jsp";
							} else
								throw new Exception("ERRORE-ClienteControl-inserimentoDati: invio e-mMail");
						} else
							throw new Exception("ERRORE-ClienteControl-inserimentoDati: email/username esistente.");
					} else
						throw new Exception("ERRORE-ClienteControl-inserimentoDati: inserimento dati");
				} else if (action.equals("registra")) {
					redirectedPage = "/RegistrazioneUser.jsp";
					String codice = request.getParameter("codice");
					if (codice != null) {
						ClienteBean bean = (ClienteBean) request.getSession().getAttribute("ClienteTemp");
						if (bean != null) {
							if (codice.length() == 6 && codice.equals(bean.getCodSicurezza())
									&& bean.getAttivo() == 0) {
								bean.setAttivo(1);
								bean.setPassword(EncryptionPassword.MD5(bean.getPassword()));
								modelCliente.doSave(bean);
								redirectedPage = "/LoginUser.jsp";
							} else {
								redirectedPage = "/VerificaCSRegistrazione.jsp";
								throw new Exception("ERRORE-ClienteControl-registra: codice errato");
							}
						} else
							throw new Exception("ERRORE-ClienteControl-registra: cliente null");
					} else
						throw new Exception("ERRORE-ClienteControl-registra: codice null");
				} else {
					/* Controllo Login */
					if ((admin != null && adminRoles != null && adminRoles.booleanValue())
							|| (user != null || userRoles != null && !userRoles.booleanValue())) {
						if (admin != null) {
							redirectedPage = "/GestioneClientiAdmin.jsp";
							if (action.equals("visualizza")) {
								ArrayList<ClienteBean> clienti = new ArrayList<ClienteBean>();
								clienti = modelCliente.doRetrieveAll("username");
								request.setAttribute("clienti", clienti);
							} else if (action.equals("select")) {
								request.getSession().removeAttribute("cliente");
								String username = request.getParameter("username");
								if (username != null) {
									ClienteBean bean = new ClienteBean();
									bean = modelCliente.doRetrieveByKey(username);
									if (!bean.isEmpty()) {
										redirectedPage = "/StoricoCliente.jsp";
										request.getSession().setAttribute("cliente", bean);
									} else
										throw new Exception("ERRORE-ClienteControl-admin-select: Cliente non trovato");
								} else
									throw new Exception("ERRORE-ClienteControl-admin-select: username null");
							} else if (action.equals("delete")) {
								String username = request.getParameter("username");
								if (username != null) {
									ArrayList<PacchettoBean> pacchetti = new ArrayList<PacchettoBean>();
									pacchetti = modelPacchetto.doRetrieveForUser(username, "analitico", "");
									for (PacchettoBean p : pacchetti) {
										modelPacchetto.doDelete(p.getId());
									}
									modelCliente.doDelete(username);
								} else
									throw new Exception("ERRORE-ClienteControl-admin-delete: username null");
							} else
								throw new Exception("ERRORE-ClienteControl-admin: invalid action for admin.");
						} else {
							if (action.equals("nome") || action.equals("cognome") || action.equals("funzioneAziendale")
									|| action.equals("telefono") || action.equals("ragioneSociale")
									|| action.equals("indirizzo") || action.equals("pIva") || action.equals("pec")
									|| action.equals("sdi") || action.equals("email") || action.equals("password")) {
								redirectedPage = "/AreaPersonaleCliente.jsp";
								if (action.equals("email")) {
									String dato = request.getParameter("dato");
									if (dato != null) {
										ClienteValidator cv = new ClienteValidator();
										if (cv.modificaProfiloVal(action, dato)) {
											if (modelCliente.controlloDato("email", dato)
													&& modelAdmin.controlloDato("email", dato)) {
												modelCliente.doUpdate(action, dato, user.getUsername());
											} else
												throw new Exception(
														"ERRORE-ClienteControl-user-email: email gia associata ad un account.");
										} else
											throw new Exception(
													"ERRORE-ClienteControl-user-password: inserimento dati");
									} else
										throw new Exception("ERRORE-ClienteControl-user-dato: dato null.");
								} else if (action.equals("password")) {
									String password = request.getParameter("password");
									String password2 = request.getParameter("password2");
									ArrayList<String> inputs = new ArrayList<String>();
									inputs.add(password);
									inputs.add(password2);
									ClienteValidator cv = new ClienteValidator();
									if (cv.passwordVal(inputs)) {
										modelCliente.doUpdate("password", EncryptionPassword.MD5(password),
												user.getUsername());
									} else
										throw new Exception("ERRORE-ClienteControl-user-password: inserimento dati");
								} else {
									String dato = request.getParameter("dato");
									if (dato != null) {
										ClienteValidator cv = new ClienteValidator();
										if (cv.modificaProfiloVal(action, dato)) {
											modelCliente.doUpdate(action, dato, user.getUsername());
										} else
											throw new Exception(
													"ERRORE-ClienteControl-user-password: inserimento dati");
									} else
										throw new Exception("ERRORE-ClienteControl-user-dato:dati null.");
								}
							} else if (action.equals("passwordRP")) {
								redirectedPage = "/ModificaPassword.jsp";
								String password = request.getParameter("password");
								String password2 = request.getParameter("password2");

								ArrayList<String> inputs = new ArrayList<String>();
								inputs.add(password);
								inputs.add(password2);

								ClienteValidator cv = new ClienteValidator();
								if (cv.passwordVal(inputs)) {
									ClienteBean bean = (ClienteBean) request.getSession().getAttribute("clienteRP");
									if (bean != null) {
										modelCliente.doUpdate("password", EncryptionPassword.MD5(password),
												bean.getUsername());
										redirectedPage = "/LoginUser.jsp";
									} else {
										redirectedPage = "/RecuperaPassword.jsp";
										throw new Exception("ERRORE-ClienteControl-user-passwordRP: cliente null");
									}
								} else
									throw new Exception("ERRORE-ClienteControl-user-passwordRP: inserimento dati");
							} else
								throw new Exception("ERRORE-ClienteControl-user: invalid action for user.");
						}
					} else
						throw new Exception("ERRORE-ClienteControl: nessun utente loggato.");
				}
			} else
				throw new Exception("ERRORE-ClienteControl: action null");
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
