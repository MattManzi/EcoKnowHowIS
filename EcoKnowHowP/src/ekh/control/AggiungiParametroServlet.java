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
import ekh.bean.ParametroBean;
import ekh.model.ParametroModelDM;

@WebServlet("/AggiungiParametroServlet")
public class AggiungiParametroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ParametroModelDM model = new ParametroModelDM();

	public AggiungiParametroServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "jsp/HomePageAdmin.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		if (admin == null || adminRoles == null || !adminRoles.booleanValue()) {
			redirectedPage = "jsp/LoginAdmin.jsp";
		} else {
			String idMatrice = request.getParameter("idMatrice");
			String nome = request.getParameter("nome");
			String sku = request.getParameter("sku");
			String campione = request.getParameter("campione");
			String campionamento = request.getParameter("campionamento");
			String limiteEmissione = request.getParameter("limiteEmissione");
			String uMisura = request.getParameter("uMisura");
			String prezzo = request.getParameter("prezzo");
			
			try {
				if (!idMatrice.equals("") && idMatrice != null && !nome.equals("") && nome != null && !sku.equals("")
						&& sku != null && !campione.equals("") && campione != null && !campionamento.equals("")
						&& campionamento != null && !limiteEmissione.equals("") && limiteEmissione != null
						&& !uMisura.equals("") && uMisura != null && !prezzo.equals("") && prezzo != null) {
					
					ParametroBean bean=new ParametroBean();
					bean.setIdMatrice(Integer.parseInt(idMatrice));
					bean.setNome(nome);
					bean.setSku(sku);
					bean.setCampione(campione);
					bean.setCampionamento(campionamento);
					bean.setLimiteEmissione(limiteEmissione);
					bean.setuMisura(uMisura);
					bean.setPrezzo(Double.parseDouble(prezzo));
					
					try {
						model.doSave(bean);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					
					redirectedPage="jsp/PaginaMatriceAdmin.jsp";
				}else 
					throw new Exception("Errore inserimento dati Aggiunta Parametro");
			}catch(Exception e) {
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
