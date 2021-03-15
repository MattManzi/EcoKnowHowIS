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
import ekh.bean.PacchettoBean;
import ekh.model.AmministratoreModelDM;
import ekh.model.ClienteModelDM;
import ekh.model.PacchettoModelDM;
import ekh.strategy.AmministratoreValidator;
import ekh.support.EncryptionPassword;

@WebServlet("/Admin")
public class AmministratoreControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AmministratoreModelDM modelAdmin = new AmministratoreModelDM();
	ClienteModelDM modelCliente = new ClienteModelDM();
	PacchettoModelDM modelPacchetto = new PacchettoModelDM();
	
	public AmministratoreControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String redirectedPage = "/HomePage.jsp";
		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		try {
			String action = request.getParameter("action");
			if (action != null) {
				if (action.equals("passwordRP")) {
					redirectedPage = "/ModificaPassword.jsp";
					String password = request.getParameter("password");
					String password2 = request.getParameter("password2");

					ArrayList<String> inputs = new ArrayList<String>();
					inputs.add(password);
					inputs.add(password2);

					AmministratoreValidator av = new AmministratoreValidator();
					if (av.passwordVal(inputs)) {
						AmministratoreBean bean = (AmministratoreBean) request.getSession().getAttribute("adminRP");
						if (bean != null) {
							modelAdmin.doUpdate("password", EncryptionPassword.MD5(password), bean.getUsername());
							redirectedPage = "/LoginAdmin.jsp";
						} else {
							redirectedPage = "/RecuperaPassword.jsp";
							throw new Exception("ERRORE-AmministratoreControl-passwordRP: cliente null");
						}
					} else
						throw new Exception("ERRORE-AmministratoreControl-passwordRP: inserimento dati");
				} else {
					/* Controllo Login */
					if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
						if (action.equals("username") || action.equals("email") || action.equals("password")) {
							redirectedPage = "/AreaPersonaleAdmin.jsp";
							if (action.equals("email")) {
								String dato = request.getParameter("dato");
								if (dato != null) {
									AmministratoreValidator av = new AmministratoreValidator();
									if (av.modificaProfiloVal(action, dato)) {
										if (modelCliente.controlloDato("email", dato)
												&& modelAdmin.controlloDato("email", dato)) {
											modelAdmin.doUpdate(action, dato, admin.getUsername());
										} else
											throw new Exception(
													"ERRORE-AmministratoreControl-admin-email: email gia associata ad un account.");
									} else
										throw new Exception(
												"ERRORE-AmministratoreControl-admin-email: inserimento dati");
								} else
									throw new Exception("ERRORE-AmministratoreControl-admin-dato: dato null.");
							} else if (action.equals("password")) {
								String password = request.getParameter("password");
								String password2 = request.getParameter("password2");
								ArrayList<String> inputs = new ArrayList<String>();
								inputs.add(password);
								inputs.add(password2);
								AmministratoreValidator av = new AmministratoreValidator();
								if (av.passwordVal(inputs)) {
									modelAdmin.doUpdate("password", EncryptionPassword.MD5(password),
											admin.getUsername());
								} else
									throw new Exception(
											"ERRORE--AmministratoreControl-admin-password: inserimento dati");
							} else if (action.equals("username")) {
								String dato = request.getParameter("dato");
								if (dato != null) {
									AmministratoreValidator av = new AmministratoreValidator();
									if (av.modificaProfiloVal(action, dato)) {
										if (modelCliente.controlloDato("username", dato)
												&& modelAdmin.controlloDato("username", dato)) {
											ArrayList<PacchettoBean> pacchetti= new ArrayList<PacchettoBean>();
											pacchetti=modelPacchetto.doRetrieveForUser(admin.getUsername(), "standard", "");
											for(PacchettoBean bean:pacchetti){
												modelPacchetto.doUpdate("username", dato, bean.getId());
											}
											modelAdmin.doUpdate(action, dato, admin.getUsername());
										} else
											throw new Exception(
													"ERRORE-AmministratoreControl-admin-username: username gia associato ad un account.");
									} else
										throw new Exception(
												"ERRORE-AmministratoreControl-admin-username: inserimento dati");
								} else
									throw new Exception("ERRORE-AmministratoreControl-admin-dato: dato null.");
							}
						}
					} else
						throw new Exception("ERRORE-AmministratoreControl: invalid action for admin.");
				}
			} else
				throw new Exception("ERRORE-AmministratoreControl: action null");
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
