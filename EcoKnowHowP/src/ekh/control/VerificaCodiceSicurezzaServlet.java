package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;


@WebServlet("/VerificaCodiceSicurezzaServlet")
public class VerificaCodiceSicurezzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public VerificaCodiceSicurezzaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage = "/VerificaCodiceSicurezza.jsp";
		String codice=request.getParameter("codice");
		String action=request.getParameter("action");
		
		try {
			if(action!=null && codice!=null) {
				if(action.equals("admin")) {
					AmministratoreBean bean=(AmministratoreBean) request.getSession().getAttribute("adminRP");
					if(bean!=null && !bean.isEmpty()) {
						if(codice.equals(bean.getCodSicurezza())) {
							if(codice.substring(6).equals("P")) {
								redirectedPage="/ModificaPassword.jsp";
							}else 
								throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: invalid codice");
						}else
							throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: codice admin errato.");
					}else
						throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: admin null/empty.");
				}else if(action.equals("user")) {
					ClienteBean bean=(ClienteBean) request.getSession().getAttribute("clienteRP");
					if(bean!=null && !bean.isEmpty()) {
						if(codice.equals(bean.getCodSicurezza())) {
							if(codice.substring(6).equals("P")) {
								redirectedPage="/ModificaPassword.jsp";
							}else 
								throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: invalid codice");
						}else
							throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: codice cliente errato.");
					}else
						throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: cliente null/empty.");
				}else
					throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: invalid action.");
			}else
				throw new Exception("ERRORE-VerificaCodiceSicurezzaServlet: action/codice null.");
			
			request.setAttribute("action", action);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
