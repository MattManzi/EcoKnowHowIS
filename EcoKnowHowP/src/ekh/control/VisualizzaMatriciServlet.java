package ekh.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;
import ekh.bean.MatriceBean;
import ekh.model.MatriceModelDM;

@WebServlet("/VisualizzaMatriciServlet")
public class VisualizzaMatriciServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MatriceModelDM model=new MatriceModelDM();
			
    public VisualizzaMatriciServlet() {
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
			
			ArrayList<MatriceBean> matrici=new ArrayList<MatriceBean>();	
			
			try {
				if(action!=null) {
					try {
						matrici=model.doRetrieveAll("id");
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					if(action.equals("admin")) {
						redirectedPage="jsp/GestioneMatriciAdmin.jsp";
					}else if(action.equals("user")) {
						redirectedPage="jsp/SceltaMatrice.jsp";
					}else
						throw new Exception("Errore action Visualizza Matrici");
				}else 
					throw new Exception("Errore action Visualizza Matrici");			
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}			
			request.setAttribute("matrici", matrici);
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
