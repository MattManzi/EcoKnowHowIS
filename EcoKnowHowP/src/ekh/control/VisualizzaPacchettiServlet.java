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
import ekh.bean.MatriceBean;
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
		String redirectedPage="/HomePage.jsp";
		
		/*Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");*/
		
		try {
			/*if ((admin != null && adminRoles != null && adminRoles.booleanValue()) || (user != null && userRoles != null && userRoles.booleanValue()) ) {
				String log = "";
				if (admin != null && adminRoles != null && adminRoles.booleanValue()) {
					log = "admin";
				} else {
					log = "user";
				}*/
				
			String log="user";
				ArrayList<PacchettoBean> pacchetti=new ArrayList<PacchettoBean>();
				
					//if(log!=null) {
						if(log.equals("admin")) {
							pacchetti=model.doRetrieveAll("id");
							redirectedPage="GestionePacchettiAdmin.jsp";
						}else if(log.equals("user")) {
							MatriceBean bean=(MatriceBean) request.getSession().getAttribute("SelectMatrice");
							String idMatrice=String.valueOf(bean.getId());
							String tipo=request.getParameter("tipo");
							if(tipo.equals("standard")) {
								pacchetti=model.doRetrieveForUser("", tipo, idMatrice);
							}else {
								//String username=user.getUsername();
								//pacchetti=model.doRetrieveForUser(username, tipo, idMatrice);
							}						
							redirectedPage="/SceltaPacchetto.jsp";
							request.setAttribute("pacchetti", pacchetti);		
						}else
							throw new Exception("Errore action Visualizza Pacchetti");
					/*}else 
						throw new Exception("Errore action Visualizza Pacchetti");		*/	
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
