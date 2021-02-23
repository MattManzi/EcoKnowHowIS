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
import ekh.model.AmministratoreModelDM;
import ekh.model.ClienteModelDM;


@WebServlet("/ModificaProfiloServlet")
public class ModificaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	AmministratoreModelDM modelAdmin = new AmministratoreModelDM();
	ClienteModelDM modelCliente = new ClienteModelDM();
    
    public ModificaProfiloServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage = "/ModificaPassword.jsp";
		String action=request.getParameter("action");
		
		try {
			if(action!=null) {
				if(action.equals("password")) {
					String user=request.getParameter("user");
					if(user!=null) {
						String password=request.getParameter("user");
						String password2=request.getParameter("user");
						//validazione
						
						if(user.equals("admin")) {
							AmministratoreBean bean=(AmministratoreBean) request.getSession().getAttribute("adminRP");
							modelAdmin.doUpdate("password", password, bean.getUsername());
							redirectedPage = "/LoginAdmin.jsp";
						}else if(user.equals("cliente")) {
							ClienteBean bean=(ClienteBean) request.getSession().getAttribute("clienteRP");
							modelCliente.doUpdate("password", password, bean.getUsername());
							redirectedPage = "/LoginUser.jsp";
						}else
							throw new Exception("ERRORE-RecuperoPasswordServlet: invalid user.");	
					}else
						throw new Exception("ERRORE-ModificaProfiloServlet: user null.");		
				}else
					throw new Exception("ERRORE-ModificaProfiloServlet: invalid action.");		
			}else
				throw new Exception("ERRORE-ModificaProfiloServlet: action null.");			
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
