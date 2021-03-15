package ekh.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;
import ekh.bean.PianoBean;
import ekh.model.PianoModelDM;
import ekh.model.RefertoModelDM;
import ekh.model.SchedaSicurezzaModelDM;


@WebServlet("/Upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PianoModelDM model=new PianoModelDM();
   
    public UploadControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String redirectedPage = "/GestioneClientiAdmin.jsp";
		Boolean adminRoles = (Boolean) request.getSession().getAttribute("adminRoles");
		AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("Admin");
		Boolean userRoles = (Boolean) request.getSession().getAttribute("userRoles");
		ClienteBean user = (ClienteBean) request.getSession().getAttribute("User");
		try {
			String action = request.getParameter("action");
			if (action != null) {
				if ((admin != null && adminRoles != null && adminRoles.booleanValue())
						|| (user != null || userRoles != null && !userRoles.booleanValue())) {
					if (admin != null) {
						if (action.equals("ulReferto")) {
							PianoBean bean = (PianoBean) request.getSession().getAttribute("pianoAdmin");
							if (bean != null) {
								String SAVE_DIR = "uploadTemp";
								String appPath = request.getServletContext().getRealPath("");
								String savePath = appPath + SAVE_DIR;

								File fileSaveDir = new File(savePath);
								if (!fileSaveDir.exists()) {
									fileSaveDir.mkdir();
								}

								for (Part part : request.getParts()) {
									String fileName = extractFileName(part);
									System.out.println(fileName);
									if (fileName != null && fileName.length() > 0) {
										part.write(savePath + File.separator + fileName);
										RefertoModelDM.uploadReferto(String.valueOf(bean.getId()),
												savePath + File.separator + fileName);
										request.getSession().removeAttribute("pianoAdmin");
										request.getSession().setAttribute("pianoAdmin",
												model.doRetrieveByKey(String.valueOf(bean.getId())));
									}
								}
								redirectedPage = "/DettagliPianoCliente.jsp";
							} else {
								redirectedPage = "/StoricoCliente.jsp";
								throw new Exception("ERRORE-UploadControl-ulReferto: Piano null.");
							}
						} else
							throw new Exception("ERRORE-UploadControl: invalid action POST for admin.");
					} else {
						if (action.equals("ulSDS")) {
							String idPiano = request.getParameter("idPiano");
							if (idPiano != null) {
								String SAVE_DIR = "uploadTemp";
								String appPath = request.getServletContext().getRealPath("");
								String savePath = appPath + SAVE_DIR;

								File fileSaveDir = new File(savePath);
								if (!fileSaveDir.exists()) {
									fileSaveDir.mkdir();
								}

								for (Part part : request.getParts()) {
									String fileName = extractFileName(part);
									System.out.println(fileName);
									if (fileName != null && fileName.length() > 0) {
										SchedaSicurezzaModelDM.uploadSDS(idPiano, savePath + File.separator + fileName);
										request.getSession().removeAttribute("pianoAdmin");
										request.getSession().setAttribute("pianoAdmin",
												model.doRetrieveByKey(idPiano));
									}
								}
								redirectedPage = "/DettagliPianoCliente.jsp";
							} else
								throw new Exception("ERRORE-UploadControl-ulSDC: idPiano null.");
						} else
							throw new Exception("ERRORE-UploadControl: invalid action POST for user.");
					}
				} else
					throw new Exception("ERRORE-UploadControl: nessun utente loggato.");
			} else
				throw new Exception("ERRORE-UploadControl: action null.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

	private String extractFileName(Part part) {
		// form-data; name="file"; filename="C:\file1.zip"
		// form-data; name="file"; filename="C:\Note\file2.zip"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				// C:\file1.zip
				// C:\Note\file2.zip
				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');
				// file1.zip
				// file2.zip
				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}
}
