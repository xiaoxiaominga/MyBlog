import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.utility.*;
public class handleFileUpload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public handleFileUpload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
   
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       response.setCharacterEncoding("utf-8");
       request.setCharacterEncoding("utf-8");
       
       
		response.setContentType("text/html;chatset=UTF-8");
		//String state=request.getParameter("state");
       
		PrintWriter out = response.getWriter();
		
		String pathString=saveFile(request, response);
		String title=request.getParameter("title");
		String type=request.getParameter("articleType");
		String author=request.getParameter("author");
		String content=request.getParameter("articleContent");
	    insert(pathString, title, type, author, content);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	/**
	 * @param pathString
	 * @param title
	 * @param type
	 * @param author
	 * @param content
	 * @return
	 */
	private int insert(String pathString,String title,String type,String author,String content)
	{
		
		return 0;
	}
    /**
     * @param request   http流
     * @param response  http流
     * @return  path,上传文件的路径
     * @throws IOException 对上传的文件进行io操作时出现错误的调整
     * @throws ServletException
     */
    private String saveFile(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
    {
    	String path=request.getServletContext().getRealPath("/WEB-INF/uploadimages/");//存储路径
    	Part part=request.getPart("files");
    	String header=part.getHeader("Content-Disposition");
    	String fileName=getFileName(header);
    	InputStream inputStream=part.getInputStream();
    	
    	OutputStream outputStream=new FileOutputStream(path+fileName);
    	
    	//String addrString=path+"/"+fileName;
    	byte[] b=new byte[1024];
    	int length=-1;
    	while((length=inputStream.read(b))!=-1)
    	{
    		outputStream.write(b,0,length);
    	}
    	inputStream.close();
    	outputStream.close();
    	return "/WEB-INF/uploadimages/"+fileName;
    }
    private String getFileName(String header)
    {
    	String fileName = header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
    	return fileName;
    }
}
