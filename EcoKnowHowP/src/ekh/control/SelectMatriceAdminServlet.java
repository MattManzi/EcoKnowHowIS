package ekh.control;

import java.io.IOException;

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
 * Servlet implementation class SelectMatriceAdminServlet
 */
@WebServlet("/SelectMatriceAdminServlet")
public class SelectMatriceAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	MatriceModelDM model=new MatriceModelDM();
	
    public SelectMatriceAdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage="jsp/HomePageAdmin.jsp";
		
		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		
		if (admin == null || adminRoles == null || !adminRoles.booleanValue()) {
			redirectedPage = "jsp/LoginAdmin.jsp";
		}else {
			request.getSession().removeAttribute("matrice");		
		
			MatriceBean bean=new MatriceBean();	
			String id=request.getParameter("id");
			try {
				bean=model.doRetrieveByKey(id);
				if(!bean.isEmpty()) {
					redirectedPage="PaginaMatriceAdmin.jsp";
				}else 
					throw new Exception("Errore Select Matrice Admin");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			request.getSession().setAttribute("matrice", bean);
		}		
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
