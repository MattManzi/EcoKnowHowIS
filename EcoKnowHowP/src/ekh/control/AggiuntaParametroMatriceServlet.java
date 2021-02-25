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
import ekh.bean.MatriceBean;
import ekh.bean.ParametroBean;
import ekh.model.ParametroModelDM;
import ekh.strategy.AggiungiParametroValidator;

@WebServlet("/AggiungiParametroMatriceServlet")
public class AggiuntaParametroMatriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ParametroModelDM model = new ParametroModelDM();

	public AggiuntaParametroMatriceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePageAdmin.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		try {
			if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
				MatriceBean matrice = (MatriceBean) request.getSession().getAttribute("matrice");
				if (matrice != null) {
					String nome = request.getParameter("nome");
					String sku = request.getParameter("sku");
					String campione = request.getParameter("campione");
					String campionamento = request.getParameter("campionamento");
					String limiteEmissione = request.getParameter("limiteEmissione");
					String uMisura = request.getParameter("uMisura");
					String prezzo = request.getParameter("prezzo");

					ArrayList<String> inputs = new ArrayList<String>();
					inputs.add(nome);
					inputs.add(sku);
					inputs.add(campione);
					inputs.add(campionamento);
					inputs.add(limiteEmissione);
					inputs.add(uMisura);
					inputs.add(prezzo);

					AggiungiParametroValidator ps = new AggiungiParametroValidator();
					if (ps.validazione(inputs)) {
						ParametroBean bean = new ParametroBean();
						bean.setIdMatrice(matrice.getId());
						bean.setNome(nome);
						bean.setSku(sku);
						bean.setCampione(campione);
						bean.setCampionamento(campionamento);
						bean.setLimiteEmissione(limiteEmissione);
						bean.setuMisura(uMisura);
						bean.setPrezzo(Double.parseDouble(prezzo));

						model.doSave(bean);
						redirectedPage = "/ModificaMatriceAdmin.jsp";
					} else
						throw new Exception("ERRORE-AggiuntaParametroServlet: inseriemento Dati.");
				}else {
					redirectedPage = "/GestioneMatriciAdmin.jsp";
					throw new Exception("ERRORE-AggiuntaParametroServlet: Matrice null");
				}
			}else {
				redirectedPage = "/LoginAdmin.jsp";
				throw new Exception("ERRORE-AggiuntaParametroServlet: Admin non loggato.");
			}
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
