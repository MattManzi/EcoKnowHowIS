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
import ekh.model.PacchettoModelDM;

/**
 * Servlet implementation class RimuoviPacchettoServlet
 */
@WebServlet("/RimuoviPacchettoServlet")
public class RimuoviPacchettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	PacchettoModelDM model=new PacchettoModelDM();
	
    public RimuoviPacchettoServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage = "/jsp/HomePageAdmin.jsp";
		
		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		
		if (admin == null || adminRoles == null || !adminRoles.booleanValue()){
			redirectedPage = "/jsp/HomePage.jsp";
		}else {
			String id=request.getParameter("id");
			try {
				if (id != null) {
					try {
						model.doDelete(id);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
				}else
					throw new Exception("ERRORE-RimuoviPacchettoServlet: id vuoto o null.");
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
