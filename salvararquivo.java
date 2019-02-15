package servlets;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Decoder.BinaryStream;

import java.io.InputStream;

import org.apache.catalina.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoArquivo;
import beans.Arquivos;
import buscaemarquivo.extralager;

/**
 * Servlet implementation class salvararquivo
 */
@WebServlet("/salvararquivo")
@MultipartConfig
public class salvararquivo extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String url="";
	public salvararquivo() {
		super();
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
       
		extralager ext = new extralager();
		HttpSession session = request.getSession();
		String idusuario = (String) session.getAttribute("idcadastroarquivo");
		String type = (String) session.getAttribute("type");
		String nomearq = (String) session.getAttribute("nomearq");
		String base64bBLob = ext.blobaplication(request);

		DaoArquivo arquivo = new DaoArquivo();
        Arquivos ar = new Arquivos();
        
        ar.setBlob(base64bBLob); 
        ar.setIdUsuario(Integer.parseInt(idusuario));
        ar.setTipo(type); 
        ar.setNomearquivo(nomearq);
        
        try {
			  request.setAttribute("dados", arquivo.salvar(ar));
			  url="/cadastroarquivo.jsp";
			  
			  RequestDispatcher dis=request.getRequestDispatcher(url);
			  dis.forward(request,response);
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}

}

/*
 * String url="/cadastroarquivo.jsp";
 * response.setContentType("text/html;charset=ISO-8859-1"); Part img=
 * request.getPart("arq");
 * 
 * // Create path components to save the file final String path =
 * "C:/Users/MAQUINA/Documents/GitHub/curso_jsp-001/WebContent/tmp"; final Part
 * filePart = request.getPart("arq"); final String fileName =
 * getFileName(filePart); System.out.println(filePart); OutputStream out = null;
 * InputStream filecontent = null; final PrintWriter writer =
 * response.getWriter();
 * 
 * try{
 * 
 * out = new FileOutputStream(new File(path + File.separator+ fileName));
 * 
 * filecontent = filePart.getInputStream(); System.out.println(filecontent); int
 * read = 0; final byte[] bytes = new byte[1024]; System.out.println(bytes);
 * while ((read = filecontent.read(bytes)) != -1) { out.write(bytes, 0, read); }
 * writer.println("New file " + fileName + " created at " + path); //
 * LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", // new
 * Object[]{fileName, path});
 * 
 * 
 * }catch (FileNotFoundException fne){
 * 
 * writer.println("You either did not specify a file to upload or are " +
 * "trying to upload a file to a protected or nonexistent " + "location.");
 * writer.println("<br/> ERROR: " + fne.getMessage());
 * 
 * // LOGGE.log(Level.SEVERE, "Problems during file upload. Error: {0}", // new
 * Object[]{fne.getMessage()});
 * 
 * 
 * }finally { if (out != null) { out.close(); } if (filecontent != null) {
 * filecontent.close(); } if (writer != null) { writer.close(); } }
 * 
 * 
 * 
 * try {
 * 
 * ServletFileUpload file=new ServletFileUpload(new DiskFileItemFactory());
 * List<FileItem> fileitem=file.parseRequest(request);
 * 
 * for(FileItem item:fileitem){ System.out.println(fileitem);
 * System.out.println(item); item.write(new
 * File("/curso_jsp-001/WebContent/tmp"+item.getName()));
 * 
 * }
 * 
 * System.out.println("passou");
 * 
 * if(ServletFileUpload.isMultipartContent(request)){ /*
 * if(request.getPart("arq").getSize()<=428472){ Arquivos ar = new Arquivos();
 * DaoArquivo arquivo=new DaoArquivo();
 * 
 * String id=request.getParameter("idarquivo"); Part img=
 * request.getPart("arq"); String nomearquivo=request.getParameter("nomearq");
 * String fotoBase64 =
 * Base64.encodeBase64String(converteStremParabyte(img.getInputStream()));
 * ar.setBlob(fotoBase64); ar.setIdUsuario(Integer.parseInt(id));
 * ar.setTipo(img.getContentType()); ar.setNomearquivo(nomearquivo);
 * request.setAttribute("dados", arquivo.salvar(ar));
 * url="/cadastroarquivo.jsp"; }
 * 
 * }
 * 
 * RequestDispatcher dis=request.getRequestDispatcher(url); dis.forward(request,
 * response);
 * 
 * 
 * } catch (Exception e) {
 * 
 * }
 */

// }

/*
 * private String getFileName(final Part part) { final String partHeader =
 * part.getHeader("content-disposition"); // LOGGER.log(Level.INFO,
 * "Part Header = {0}", partHeader); for (String content :
 * part.getHeader("content-disposition").split(";")) { if
 * (content.trim().startsWith("filename")) { return content.substring(
 * content.indexOf('=') + 1).trim().replace("\"", ""); } } return null; }
 */

