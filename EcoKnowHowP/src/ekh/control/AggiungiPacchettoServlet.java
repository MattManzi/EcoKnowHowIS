package ekh.control;

import java.io.IOException;
import java.sql.SQLException;

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

@WebServlet("/AggiungiPacchettoServlet")
public class AggiungiPacchettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PacchettoModelDM model=new PacchettoModelDM();
	
	public AggiungiPacchettoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/jsp/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

		if ((admin == null || adminRoles == null || !adminRoles.booleanValue())
				&& (user == null || userRoles == null || !userRoles.booleanValue())) {
			redirectedPage = "/jsp/HomePage.jsp";
		} else {
			String log = "";
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				log = "admin";
			} else {
				log = "user";
			}

			String action = request.getParameter("action");
			try {
				if (action != null) {
					if (action.equals("crea")) {
						String idMatrice = request.getParameter("idMatrice");
						String nome = request.getParameter("nome");
						String descrizione = request.getParameter("descrizione");
						String tipo = "";
						String username = "";
						if (log.equals("admin")) {
							tipo = "Standard";
							username = admin.getUsername();
						} else {
							tipo = "analitico";
							username = user.getUsername();
						}

						if (!idMatrice.equals("") && idMatrice != null && !nome.equals("") && nome != null
								&& !descrizione.equals("") && descrizione != null && !tipo.equals("") && tipo != null
								&& !username.equals("") && username != null) {
							PacchettoBean bean = new PacchettoBean();
							bean.setIdMatrice(Integer.parseInt(idMatrice));
							bean.setNome(nome);
							bean.setDescrizione(descrizione);
							bean.setTipo(tipo);
							bean.setUsername(username);

							request.getSession().setAttribute("pacchettoTemp", bean);

							redirectedPage = "/jsp/ComponiPacchetto.jsp";
						}
					} else if (action.equals("salva")) {
						PacchettoBean pacchettoTemp = (PacchettoBean) request.getSession().getAttribute("pacchettoTemp");
						if(pacchettoTemp!=null) {
							try {
								pacchettoTemp.setPrezzo(pacchettoTemp.calcolaPrezzo());
								model.doSave(pacchettoTemp);
								PacchettoModelDM.updateContenuto(String.valueOf(pacchettoTemp.getId()), pacchettoTemp.stampContenuto());
							}catch(SQLException e) {
								System.out.println(e.getMessage());
							}
						}
					} else
						throw new Exception("ERRORE - AggiuntaPacchettoServlet: action non valida.");
				} else
					throw new Exception("ERRORE - AggiuntaPacchettoServlet: action vuota o null.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

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
