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
import ekh.model.AmministratoreModelDM;
import ekh.model.ClienteModelDM;
import ekh.strategy.ClienteValidator;
import ekh.support.EncryptionPassword;
import ekh.support.SendEmail;

@WebServlet("/RP")
public class RPControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AmministratoreModelDM modelAdmin = new AmministratoreModelDM();
	ClienteModelDM modelCliente = new ClienteModelDM();
	
	public RPControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/RecuperaPassword.jsp";
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if(action.equals("verifica")) {
					String email = request.getParameter("email");
					if(email!=null) {
						AmministratoreBean admin=modelAdmin.doRetrieveByEmail(email);
						ClienteBean cliente=modelCliente.doRetrieveByEmail(email);
						SendEmail sm = new SendEmail();
						if(!admin.isEmpty()) {
							admin.setCodSicurezza(sm.getRandom()+"P");
							boolean sendEmail = sm.recuperaPassword(null, admin);
							if (sendEmail) {
								request.setAttribute("user", "admin");
								request.getSession().setAttribute("adminRP", admin);
								redirectedPage = "/VerificaCSRP.jsp";
							}else
								throw new Exception("ERRORE-RPControl-verifica: email admin non inviata");			
						}else if(!cliente.isEmpty()) {		
							cliente.setCodSicurezza(sm.getRandom()+"P");
							boolean sendEmail = sm.recuperaPassword(cliente, null);
							if (sendEmail) {
								request.setAttribute("user", "cliente");
								request.getSession().setAttribute("clienteRP", cliente);
								redirectedPage = "/VerificaCSRP.jsp";
							}else
								throw new Exception("ERRORE-RPControl-verifica: email cliente non inviata");			
						}else 
							throw new Exception("ERRORE-RPControl-verifica: email inesistente.");
					}else
						throw new Exception("ERRORE-RPControl-verifica: email null.");	
				}else if (action.equals("sendEmail")) {
					String user=request.getParameter("user");
					if(user!=null) {
						if(user.equals("admin")) {
							AmministratoreBean bean = (AmministratoreBean) request.getSession().getAttribute("adminRP");
							if(bean!=null) {
								SendEmail sm = new SendEmail();						
								bean.setCodSicurezza(sm.getRandom()+"P");

								request.getSession().removeAttribute("adminRP");
								request.getSession().setAttribute("adminRP", bean);
								boolean sendEmail = sm.recuperaPassword(null, bean);

								if (sendEmail) {
									request.setAttribute("user", "admin");
									redirectedPage = "/VerificaCSRP.jsp";
								} else
									throw new Exception("ERRORE-RPControl-sendEmail: invio e-mail");
							}else 
								throw new Exception("ERRORE-RPControl-sendEmail: utente null");
						}else if(user.equals("cliente")) {
							ClienteBean bean = (ClienteBean) request.getSession().getAttribute("clienteRP");
							if(bean!=null) {
								SendEmail sm = new SendEmail();						
								bean.setCodSicurezza(sm.getRandom()+"P");

								request.getSession().removeAttribute("clienteRP");
								request.getSession().setAttribute("clienteRP", bean);
								boolean sendEmail = sm.recuperaPassword(bean, null);

								if (sendEmail) {
									request.setAttribute("user", "cliente");
									redirectedPage = "/VerificaCSRP.jsp";
								} else
									throw new Exception("ERRORE-RPControl-sendEmail: invio e-mail");
							}else 
								throw new Exception("ERRORE-RPControl-sendEmail: utente null");
						}else
							throw new Exception("ERRORE-RPControl-sendEmail: invalid user.");	
					}else
						throw new Exception("ERRORE-RPControl-sendEmail: user null.");		
				}else if(action.equals("codice")) {
					String user=request.getParameter("user");					
					if(user!=null) {
						String codice=request.getParameter("codice");
						if(user.equals("admin")) {
							AmministratoreBean bean=(AmministratoreBean) request.getSession().getAttribute("adminRP");
							if(!bean.isEmpty()) {
								if(codice.length()==7 && codice.equals(bean.getCodSicurezza()) && codice.substring(6).equals("P")) {
									request.setAttribute("user", "admin");
									redirectedPage="/ModificaPassword.jsp";
								}else 
									throw new Exception("ERRORE-RPControl-codice: invalid codice");
							}else
								throw new Exception("ERRORE-RPControl-codice: admin empty.");
						}else if(user.equals("cliente")) {
							ClienteBean bean=(ClienteBean) request.getSession().getAttribute("clienteRP");
							if(!bean.isEmpty()) {
								if(codice.length()==7 && codice.equals(bean.getCodSicurezza()) && codice.substring(6).equals("P")) {
									request.setAttribute("user", "cliente");
									redirectedPage="/ModificaPassword.jsp";
								}else 
									throw new Exception("ERRORE-RPControl-codice: invalid codice");
							}else
								throw new Exception("ERRORE-RPControl-codice: cliente empty.");
						}else
							throw new Exception("ERRORE-RPControl-codice: invalid user.");	
					}else
						throw new Exception("ERRORE-RPControl-codice: user null.");		
				}else if(action.equals("password")){
					String user=request.getParameter("user");	
					if(user!=null) {
						String password=request.getParameter("password");
						String password2=request.getParameter("password2");
						
						ArrayList<String> inputs = new ArrayList<String>();
						inputs.add(password.trim());
						inputs.add(password2.trim());
						
						ClienteValidator cv = new ClienteValidator();
						if (cv.passwordVal(inputs)) {
							redirectedPage="/LoginUser.jsp";
							if(user.equals("admin")) {
								AmministratoreBean bean=(AmministratoreBean) request.getSession().getAttribute("adminRP");
								if(!bean.isEmpty()) {
									modelAdmin.doUpdate("password", EncryptionPassword.MD5(password), bean.getUsername());
								}else 
									throw new Exception("ERRORE-RPControl-password: admin empty.");
							}else if(user.equals("cliente")) {
								ClienteBean bean=(ClienteBean) request.getSession().getAttribute("clienteRP");
								if(!bean.isEmpty()) {
									modelCliente.doUpdate("password", EncryptionPassword.MD5(password), bean.getUsername());
								}else 
									throw new Exception("ERRORE-RPControl-password: cliente empty.");
							}else
								throw new Exception("ERRORE-RP-password: invalid user.");		
						}else
							throw new Exception("ERRORE-RPControl-password: inserimento dati.");		
					}else
						throw new Exception("ERRORE-RPControl-password: user null.");		
				}else
					throw new Exception("ERRORE-RPControl: invalid action.");		
			} else
				throw new Exception("ERRORE-RPControl: action null.");
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
