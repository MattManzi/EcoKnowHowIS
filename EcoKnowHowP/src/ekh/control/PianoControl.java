package ekh.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;
import ekh.bean.MatriceBean;
import ekh.bean.ModuloBean;
import ekh.bean.PacchettoBean;
import ekh.bean.PianoBean;
import ekh.model.PacchettoModelDM;
import ekh.model.PianoModelDM;
import ekh.model.RefertoModelDM;
import ekh.model.SchedaSicurezzaModelDM;
import ekh.strategy.PianoValidator;

@WebServlet("/Piano")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class PianoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PianoModelDM modelPiano = new PianoModelDM();
	PacchettoModelDM modelPacchetto = new PacchettoModelDM();
	private static final int BUFFER_SIZE = 4096;
	SchedaSicurezzaModelDM model = new SchedaSicurezzaModelDM();

	public PianoControl() {
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
			/* Controllo Login */
			if ((admin != null && adminRoles != null && adminRoles.booleanValue())
					|| (user != null || userRoles != null && !userRoles.booleanValue())) {
				String action = request.getParameter("action");
				if (action != null) {
					if (action.equals("pianiCliente")) {
						String username = request.getParameter("username");
						if (username != null) {
							ArrayList<PianoBean> piani = new ArrayList<PianoBean>();
							piani = modelPiano.doRetrieveByUsername(username);
							request.setAttribute("piani", piani);
							redirectedPage = "/StoricoCliente.jsp";
						} else
							throw new Exception("ERRORE-PianoControl: username null.");
					} else if (action.equals("select")) {
						request.getSession().removeAttribute("pianoAdmin");
						String id = request.getParameter("id");
						if (id != null) {
							PianoBean piano = new PianoBean();
							piano = modelPiano.doRetrieveByKey(id);
							if (!piano.isEmpty()) {									
									piano.readContenuto();								
									ModuloBean modulo = new ModuloBean();
									modulo.readModulo(piano.getId());
									piano.setModulo(modulo);
									request.setAttribute("piano", piano);
									request.getSession().setAttribute("pianoAdmin", piano);
									redirectedPage = "/DettagliPianoCliente.jsp";
							} else
								throw new Exception("ERRORE-SelectPianoClienteServlet: piano non trovato.");
						} else {
							redirectedPage = "/StoricoCliente.jsp";
							throw new Exception("ERRORE-SelectPianoClienteServlet: id null.");
						}
					} else if (action.equals("dlReferto")) {
						String idPiano = request.getParameter("idPiano");
						if (idPiano != null) {
							Blob blob = RefertoModelDM.downloadFile(idPiano);
							InputStream inputStream = blob.getBinaryStream();
							int fileLength = inputStream.available();
							ServletContext context = getServletContext();
							// sets MIME type for the file download
							String mimeType = context.getMimeType("referto");
							if (mimeType == null) {
								mimeType = "application/octet-stream";
							}
							// set content properties and header attributes for the response
							response.setContentType(mimeType);
							response.setContentLength(fileLength);
							String headerKey = "Content-Disposition";
							String headerValue = String.format("attachment; filename=\"%s\"", "referto.pdf");
							response.setHeader(headerKey, headerValue);
							// writes the file to the client
							OutputStream outStream = response.getOutputStream();
							byte[] buffer = new byte[BUFFER_SIZE];
							int bytesRead = -1;

							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outStream.write(buffer, 0, bytesRead);
							}

							inputStream.close();
							outStream.close();
						} else
							throw new Exception("ERRORE-PianoControl-dlReferto: idPiano null.");
					} else if (action.equals("dlSDS")) {
						String idPiano = request.getParameter("codice");
						if (idPiano != null) {
							Blob blob = SchedaSicurezzaModelDM.downloadFile(idPiano);
							InputStream inputStream = blob.getBinaryStream();
							int fileLength = inputStream.available();

							ServletContext context = getServletContext();

							// sets MIME type for the file download
							String mimeType = context.getMimeType("schedaDatiSicurezza");
							if (mimeType == null) {
								mimeType = "application/octet-stream";
							}

							// set content properties and header attributes for the response
							response.setContentType(mimeType);
							response.setContentLength(fileLength);
							String headerKey = "Content-Disposition";
							String headerValue = String.format("attachment; filename=\"%s\"",
									"Scheda_Dati_Sicurezza.pdf");
							response.setHeader(headerKey, headerValue);

							// writes the file to the client
							OutputStream outStream = response.getOutputStream();

							byte[] buffer = new byte[BUFFER_SIZE];
							int bytesRead = -1;

							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outStream.write(buffer, 0, bytesRead);
							}

							inputStream.close();
							outStream.close();
						} else
							throw new Exception("ERRORE-PianoControl-dlSDS: idPiano null.");
					} else {
						if (admin != null) {
							PianoBean bean=(PianoBean) request.getSession().getAttribute("pianoAdmin");
							if(bean!=null) {
								if(action.equals("prezzo")) {
									String prezzo=request.getParameter("prezzo");
									PianoValidator pv=new PianoValidator();
									if(pv.modificaPianoVal("prezzo", prezzo)) {
										modelPiano.doUpdate("prezzo", prezzo, String.valueOf(bean.getId()));
										redirectedPage = "/DettagliPianoCliente.jsp";
									}
								} else if (action.equals("stato")) {
									
								} else
									throw new Exception("ERRORE-PianoControl-admin: invalid action for admin.");
							}else {
								redirectedPage="/StoricoCliente.jsp";
								throw new Exception("ERRORE-PianoControl: piano null.");
							}							
						}else {
							if(action.equals("crea")) {
								MatriceBean matrice=(MatriceBean) request.getSession().getAttribute("SelectMatrice");
								if(matrice!=null) {
									PacchettoBean pacchetto=(PacchettoBean) request.getSession().getAttribute("SelectPacchetto");
									if(pacchetto!=null) {
										//obbligatorio
										String ragioneSocialeProd=request.getParameter("ragioneSocialeProd");
										String sedeLegaleProd=request.getParameter("sedeLegaleProd");
										String pIvaProd=request.getParameter("pIvaProd");
										String telefonoProd=request.getParameter("telefonoProd");
										String emailProd=request.getParameter("emailProd");		
										
										String check=request.getParameter("check");
										
										String ragioneSocialeCom=request.getParameter("ragioneSocialeCom");
										String sedeLegaleCom=request.getParameter("sedeLegaleCom");
										String pIvaCom=request.getParameter("pIvaCom");
										String telefonoCom=request.getParameter("telefonoCom");
										String emailCom=request.getParameter("emailCom");
										
										String data=request.getParameter("data");
										String luogo=request.getParameter("luogo");
										String quantitaCampione=request.getParameter("quantitaCampione");										
										String dataConferma=request.getParameter("dataConferma");
										
										//facoltativo
										String nomeCampionatore=request.getParameter("nomeCampionatore");
										String cognomeCampionatore=request.getParameter("cognomeCampionatore");
										String norma=request.getParameter("norma");
										String note=request.getParameter("note");
										
										PianoValidator pv=new PianoValidator();
										
										ArrayList<String> inputs=new ArrayList<String>();
										inputs.add(ragioneSocialeProd);
										inputs.add(sedeLegaleProd);
										inputs.add(pIvaProd);
										inputs.add(telefonoProd);
										inputs.add(emailProd);
										if(check==null || !check.equals("true")) {
											inputs.add(ragioneSocialeCom);
											inputs.add(sedeLegaleCom);
											inputs.add(pIvaCom);
											inputs.add(telefonoCom);
											inputs.add(emailCom);
										}
										
										ArrayList<String> inputs2=new ArrayList<String>();
										inputs2.add(data);
										inputs2.add(luogo);
										inputs2.add(quantitaCampione);
										inputs2.add(dataConferma);
										
										
									}else {
										redirectedPage="/SceltaTipoPacchettoCliente.jsp";
										throw new Exception("ERRORE-PianoControl-user-crea: matrice null.");
									}
								}else{
									redirectedPage="/SceltaMatriceCliente.jsp";
									throw new Exception("ERRORE-PianoControl-user-crea: matrice null.");
								}
							}
						}
					}
				} else
					throw new Exception("ERRORE-PianoControl: action null.");
			} else
				throw new Exception("ERRORE-PianoControl: nessun utente loggato.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		redirectedPage = response.encodeURL(redirectedPage);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(redirectedPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
												modelPiano.doRetrieveByKey(String.valueOf(bean.getId())));
									}
								}
								redirectedPage = "/DettagliPianoCliente.jsp";
							} else {
								redirectedPage = "/StoricoCliente.jsp";
								throw new Exception("ERRORE-PianoControl-ulReferto: Piano null.");
							}
						} else
							throw new Exception("ERRORE-PianoControl: invalid action for admin.");
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
												modelPiano.doRetrieveByKey(idPiano));
									}
								}
								redirectedPage = "/DettagliPianoCliente.jsp";
							} else
								throw new Exception("ERRORE-PianoControl-ulSDC: idPiano null.");
						} else
							throw new Exception("ERRORE-PianoControl: invalid action for user.");
					}
				} else
					throw new Exception("ERRORE-PianoControl: nessun utente loggato.");
			} else
				throw new Exception("ERRORE-PianoControl: action null.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			doGet(request, response);
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
