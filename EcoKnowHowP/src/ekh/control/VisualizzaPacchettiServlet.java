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

@WebServlet("/VisualizzaPacchettiServlet")
public class VisualizzaPacchettiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	PacchettoModelDM model=new PacchettoModelDM();

    public VisualizzaPacchettiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage="jsp/HomePage.jsp";
		
		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
		
		if ((admin == null || adminRoles == null || !adminRoles.booleanValue()) && (user == null || userRoles == null || !userRoles.booleanValue()) ) {
			redirectedPage = "jsp/HomePage.jsp";
		}else {
			String action=request.getParameter("action");
			
			ArrayList<PacchettoBean> pacchetti=new ArrayList<PacchettoBean>();
			try {
				if(action!=null) {
					if(action.equals("admin")) {
						pacchetti=model.doRetrieveAll("id");
						redirectedPage="jsp/GestionePacchettiAdmin.jsp";
					}else if(action.equals("user")) {
						String tipo=request.getParameter("tipo");
						String idMatrice=request.getParameter("idMatrice");
						if(tipo.equals("standard")) {
							pacchetti=model.doRetrieveForUser("", tipo, idMatrice);
						}else {
							String username=user.getUsername();
							pacchetti=model.doRetrieveForUser(username, tipo, idMatrice);
						}						
						redirectedPage="jsp/SceltaPacchetto.jsp";
					}else
						throw new Exception("Errore action Visualizza Pacchetti");
				}else 
					throw new Exception("Errore action Visualizza Pacchetti");			
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}			
			request.setAttribute("pacchetti", pacchetti);			
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
