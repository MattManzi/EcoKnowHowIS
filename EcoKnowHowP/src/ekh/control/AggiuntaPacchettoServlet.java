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
import ekh.model.PacchettoModelDM;
import ekh.strategy.AggiungiPacchettoValidator;

@WebServlet("/AggiungiPacchettoServlet")
public class AggiuntaPacchettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PacchettoModelDM model = new PacchettoModelDM();

	public AggiuntaPacchettoServlet() {
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
			if ((admin != null && adminRoles != null && adminRoles.booleanValue())
					|| (user != null || userRoles != null && !userRoles.booleanValue())) {
				String log = "";
				if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
					log = "admin";
				} else {
					log = "user";
				}
				String action = request.getParameter("action");
				if (action != null) {
					if (action.equals("crea")) {
						String idMatrice = request.getParameter("idMatrice");
						String nome = request.getParameter("nome");
						String descrizione = request.getParameter("descrizione");
						String tipo = "";
						String username = "";
						if (log.equals("admin")) {
							tipo = "standard";
							username = admin.getUsername();
						} else {
							tipo = "analitico";
							username = user.getUsername();
						}

						ArrayList<String> inputs = new ArrayList<String>();
						inputs.add(idMatrice);
						inputs.add(nome);
						inputs.add(descrizione);

						AggiungiPacchettoValidator pv = new AggiungiPacchettoValidator();

						if (pv.validazione(inputs)) {
							PacchettoBean bean = new PacchettoBean();
							bean.setIdMatrice(Integer.parseInt(idMatrice));
							bean.setNome(nome);
							bean.setDescrizione(descrizione);
							bean.setTipo(tipo);
							bean.setUsername(username);

							request.getSession().setAttribute("pacchettoTemp", bean);

							redirectedPage = "/ComponiPacchetto.jsp";
						} else
							throw new Exception("ERRORE-AggiuntaPacchettoServlet: inserimento dati.");
					} else if (action.equals("salva")) {
						PacchettoBean pacchettoTemp = (PacchettoBean) request.getSession()
								.getAttribute("pacchettoTemp");
						if (pacchettoTemp != null) {
							if(pacchettoTemp.getContenuto().size()>0) {
								pacchettoTemp.setPrezzo(pacchettoTemp.calcolaPrezzo());
								model.doSave(pacchettoTemp);
								pacchettoTemp.stampContenuto();
							}else {
								redirectedPage = "/ComponiPacchetto.jsp";
								throw new Exception("ERRORE-AggiuntaPacchettoServlet: pacchetto vuoto.");	
							}
						} else {
							redirectedPage = "/CreaPacchetto.jsp";
							throw new Exception("ERRORE-AggiuntaPacchettoServlet: pacchetto null.");
						}
					} else
						throw new Exception("ERRORE-AggiuntaPacchettoServlet: action non valida.");
				} else
					throw new Exception("ERRORE-AggiuntaPacchettoServlet: action vuota o null.");
			} else
				throw new Exception("ERRORE-AggiuntaMatriceServlet: nessun utente loggato.");
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
