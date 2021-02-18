package ekh.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekh.model.SchedaSicurezzaModelDM;

@WebServlet("/DownloadRefertoServlet")
public class DownloadRefertoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 4096;

	public DownloadRefertoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codice = (String) request.getParameter("codice");

		try {
			Blob blob = SchedaSicurezzaModelDM.downloadFile(codice);
			InputStream inputStream = blob.getBinaryStream();
			int fileLength = inputStream.available();
			System.out.println("fileLength = " + fileLength);

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
			String headerValue = String.format("attachment; filename=\"%s\"", "Scheda_Dati_Sicurezza.pdf");
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
