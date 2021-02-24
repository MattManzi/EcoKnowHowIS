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
import ekh.bean.ModuloBean;
import ekh.bean.PacchettoBean;
import ekh.bean.PianoBean;
import ekh.model.PacchettoModelDM;
import ekh.model.PianoModelDM;

@WebServlet("/SelectPianoClienteServlet")
public class SelectPianoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PianoModelDM modelPiano = new PianoModelDM();
	PacchettoModelDM modelPacchetto=new PacchettoModelDM();
	
	public SelectPianoClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";

		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");

		try {
			if ((admin != null && adminRoles != null && adminRoles.booleanValue())
					|| (user != null && userRoles != null && userRoles.booleanValue())) {
				String id = request.getParameter("id");
				if(id!=null) {
					PianoBean piano = new PianoBean();
					piano=modelPiano.doRetrieveByKey(id);
					if(!piano.isEmpty()) {
						PacchettoBean pacchetto=new PacchettoBean();
						pacchetto=modelPacchetto.doRetrieveByKey(String.valueOf(piano.getIdPacchetto()));
						if(!pacchetto.isEmpty()) {
							pacchetto.readContenuto();
							piano.setPacchetto(pacchetto);
							ModuloBean modulo=new ModuloBean();
							modulo.readModulo(piano.getId());
							piano.setModulo(modulo);
							request.setAttribute("piano", piano);
							request.getSession().setAttribute("pianoAdmin", piano);
							redirectedPage = "/DettagliPianoCliente.jsp";
						}else					
							throw new Exception("ERRORE-SelectPianoClienteServlet: pacchetto non trovato.");	
					}else					
						throw new Exception("ERRORE-SelectPianoClienteServlet: piano non trovato.");	
				}else {
					redirectedPage = "/StoricoCliente.jsp";
					throw new Exception("ERRORE-SelectPianoClienteServlet: id null.");			
				}		
			} else
				throw new Exception("ERRORE-SelectPianoClienteServlet: nessun utente loggato.");
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
