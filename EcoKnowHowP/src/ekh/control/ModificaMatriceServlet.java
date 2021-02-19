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
import ekh.model.MatriceModelDM;

@WebServlet("/ModificaMatriceServlet")
public class ModificaMatriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MatriceModelDM model=new MatriceModelDM();
	
    public ModificaMatriceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage = "/jsp/HomePageAdmin.jsp";
		
		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		
		if (admin == null || adminRoles == null || !adminRoles.booleanValue()) {
			redirectedPage = "/jsp/LoginAdmin.jsp";
		} else {
			String action=request.getParameter("action");
			String id=request.getParameter("id");
			String dato=request.getParameter("dato");
			try {
				if(action!=null) {				
					if(action.equals("nome") || action.equals("sottotitolo") || action.equals("descrizione")) {
						try {
							model.doUpdate(action, dato, id);
						} catch (SQLException e) {
							System.out.println(e.getMessage());
						}
						redirectedPage = "/jsp/GestioneMatriciAdmin.jsp";
					}else
						throw new Exception("Errore action Modifica Matrice");			
				}else
					throw new Exception("Errore action Modifica Matrice");				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
