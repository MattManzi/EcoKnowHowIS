package ekh.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.bean.ClienteBean;
import ekh.support.SendEmail;


@WebServlet("/EmailControl")
public class EmailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmailControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String redirectedPage = "/HomePage.jsp";
		String action = request.getParameter("action");
		try {
			if (action != null) {
				if (action.equals("sendEmail")) {
					ClienteBean bean = (ClienteBean) request.getSession().getAttribute("ClienteTemp");
					if(bean!=null) {
						SendEmail sm = new SendEmail();						
						bean.setCodSicurezza(sm.getRandom());

						request.getSession().removeAttribute("ClienteTemp");
						request.getSession().setAttribute("ClienteTemp", bean);
						boolean sendEmail = sm.sendEmail(bean);

						if (sendEmail) {
							redirectedPage = "/VerificaCodiceSicurezzaRegistrazione.jsp";
						} else
							throw new Exception("ERRORE-RegistrazioneUserServlet: invio e-mail");
					}else {
						redirectedPage = "/RegistrazioneUser.jsp";
						throw new Exception("ERRORE-RegistrazioneUserServlet: utente null");
					}
				}
			} else
				throw new Exception("ERRORE-RegistrazioneUserServlet: action null");
		} catch (Exception e) {
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
