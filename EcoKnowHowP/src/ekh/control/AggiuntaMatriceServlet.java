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
import ekh.bean.MatriceBean;
import ekh.model.MatriceModelDM;

/**
 * Servlet implementation class AggiuntaMatriceServlet
 */
@WebServlet("/AggiuntaMatriceServlet")
public class AggiuntaMatriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MatriceModelDM model = new MatriceModelDM();

	public AggiuntaMatriceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/jsp/HomePageAdmin.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");

		if (admin == null || adminRoles == null || !adminRoles.booleanValue()) {
			redirectedPage = "/jsp/LoginAdmin.jsp";
		} else {
			String nome = request.getParameter("nome");
			String sottotitolo = request.getParameter("sottotitolo");
			String descrizione = request.getParameter("descrizione");

			try {
				if (!nome.equals("") && nome != null && !sottotitolo.equals("") && sottotitolo != null
						&& !descrizione.equals("") && descrizione != null) {
					try {
						MatriceBean bean = new MatriceBean();
						bean.setNome(nome);
						bean.setSottotitolo(sottotitolo);
						bean.setDescrizione(descrizione);
						model.doSave(bean);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					redirectedPage = "/jsp/GestioneMatriciAdmin.jsp";
				} else
					throw new Exception("Errore inserimento dati Aggiunta Matrice");
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
