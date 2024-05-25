package upload;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.missiondata.fileupload.MonitoredDiskFileItemFactory;

public class UploadServlet extends HttpServlet {
	public static final String UPLOAD_STATUS = "uploadStatus";
	public static final String FILE_UPLOAD_STATS = "fileUploadStats";
	public static final String FILES_UPLOADED = "FILES_UPLOADED";
	public static final String UNIQUE_IDENTIFIER = "uniqueFileIdentifier";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		UploadStatus status = new UploadStatus();
		session.setAttribute("uploadStatus", null);
		try {
			FileUploadListener listener = new FileUploadListener(request.getContentLength());
			session.setAttribute("fileUploadStats", listener.getFileUploadStats());
			FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			boolean hasError = false;
			
			/*
			
			
			
			for (Iterator i = items.iterator(); i.hasNext();) {
				FileItem fileItem = (FileItem) i.next();
				if (!fileItem.isFormField()) {
					System.out.println("fileItem "+fileItem);
					
					InputStreamReader isr = new InputStreamReader(fileItem.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					
					String digitado = br.readLine();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(digitado != null){
						list.add(digitado.split(";"));
						//System.out.println("Texto Digitado = "+digitado);
						digitado = br.readLine();
					}
					
					/*
					for (int x = 0; x < list.size(); x++) {
						System.out.println("lista -- " + list.get(x).length + " " + list.get(x)[1] );
					}*/
					
					
					/*
					List<Table> table = new ArrayList<Table>();
					Table t = new Table();
					
					for (int x = 0; x < list.size(); x++) {
						//System.out.println("lista -- " + list.get(x).length + " " + list.get(x)[1] );
						t.carregaDados(list.get(x));
						table.add(t);
					}
					
					System.out.println("table.size: "+table.size());
					
				}
			}
	        */
			
			/*
			for (int i = 0; i < items.size(); i++) {
				System.out.println(items.get(i));
				FileItem fileItem = items.get(i);
				
				BufferedReader br = new BufferedReader(new FileReader(items.get(i)));
				while (br.ready()) {
					String linha = br.readLine();
					System.out.println(linha);
				}
				br.close();

			} */
			storeFilesOnSession(request, items);
			if (!hasError) {
				status.setStatus(2);
			} else {
				status.setStatus(3);
				status.setMessage("Could not process uploaded file. Please see log for details.");
			}
		} catch (Exception e) {
			status.setStatus(3);
			status.setMessage(e.getMessage());
		}
		session.setAttribute("uploadStatus", status);
	}

	private void storeFilesOnSession(HttpServletRequest request, List items) throws IOException {
		/*
		String uniqueIdentifier = request.getParameter("uniqueFileIdentifier");
		if (uniqueIdentifier != null) {
			List<FileItem> files = (List) request.getSession().getAttribute(uniqueIdentifier);
			if (files == null) {
				files = new ArrayList();
			}
			for (Iterator i = items.iterator(); i.hasNext();) {
				FileItem fileItem = (FileItem) i.next();
				if (!fileItem.isFormField()) {
					files.add(fileItem);
					System.out.println(fileItem);
				}
			}
			request.getSession().setAttribute("FILES_UPLOADED", files);
		}*/
		
		for (Iterator i = items.iterator(); i.hasNext();) {
			FileItem fileItem = (FileItem) i.next();
			if (!fileItem.isFormField()) {
				System.out.println("fileItem "+fileItem);
				
				InputStreamReader isr = new InputStreamReader(fileItem.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				
				String digitado = br.readLine();
				ArrayList<String[]> list = new ArrayList<String[]>();
				while(digitado != null){
					list.add(digitado.split(";"));
					/*System.out.println("Texto Digitado = "+digitado);*/
					digitado = br.readLine();
				}
				
				
				for (int x = 0; x < list.size(); x++) {
					System.out.println("lista -- " + list.get(x).length + " " + list.get(x)[1] );
				}
				
				
				/*
				List<Table> table = new ArrayList<Table>();
				Table t = new Table();
				
				for (int x = 0; x < list.size(); x++) {
					//System.out.println("lista -- " + list.get(x).length + " " + list.get(x)[1] );
					t.carregaDados(list.get(x));
					table.add(t);
				}
				
				System.out.println("table.size: "+table.size());
				*/
				
			}
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
