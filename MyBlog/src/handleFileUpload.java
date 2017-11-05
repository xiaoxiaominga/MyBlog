import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bll.ArticleGet;
import com.entity.Article;
import com.utility.Util;
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
			throws ServletException, IOException {}	
   Part part=null;
   String path="";
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       response.setCharacterEncoding("utf-8");
       request.setCharacterEncoding("utf-8");
       
       
		response.setContentType("text/html;chatset=UTF-8");
		//String state=request.getParameter("state");
       part=request.getPart("files");
       path=request.getServletContext().getRealPath("/uploadimages/");
		
		PrintWriter out = response.getWriter();
		
		String pathString=saveFile();
		String title=request.getParameter("title");
		String type=request.getParameter("articleType");
	    type=getType(Integer.parseInt(type));
		String author=request.getParameter("author");
		String content=request.getParameter("input");
		System.out.println(content);
	    System.out.println(title);
	    System.out.println(type);
	    System.out.println(author);
	    System.out.println(content);
	    System.out.print(pathString);
	    String returnMEssage="{";
	    ArticleGet get=new ArticleGet();	   
	    boolean flag= get.insertArticle(getArticle(title, type, author, content, pathString));
	    if(flag)
	    {
	    	returnMEssage+="\"state\":1,\"Message\":\"发布成功！\"";
	    }
	    else {
	    	returnMEssage+="\"state\":0,\"Message\":\"发布失败！\"";
		}
	    returnMEssage +="}";
	    out.print(returnMEssage);
	}
	
	private Article getArticle(String title,String type,String author,String content,String imageurl)
	{
		Article article=new Article(title, content, Util.getSysTime(), type,0, imageurl, 0);
	    return article;
	}
    private String getType(int type)
    {
    	String typeString="日记";
    	switch (type) {
		case 0:
			typeString="日记";
			break;
		case 1:
			typeString="技术";
			break;
		case 2:
			typeString="经验";
			break;
		case 3:
			typeString="心情";
			break;
		case 4:
			typeString="算法";
			break;
		case 5:
			typeString="网站";
			break;
		default:
			break;
		}
    	return typeString;
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
     * @param request   http流
     * @param response  http流
     * @return  path,上传文件的路径
     * @throws IOException 对上传的文件进行io操作时出现错误的调整
     * @throws ServletException
     */
    private String saveFile() throws IOException, ServletException
    {   	
    	//会出现问题
    	String header=part.getHeader("Content-Disposition");
    	String fileName=getFileName(header);
    	InputStream inputStream=part.getInputStream();
    	
    	OutputStream outputStream=new FileOutputStream(path+"\\"+fileName);
    	
    	//String addrString=path+"/"+fileName;
    	byte[] b=new byte[1024];
    	int length=-1;
    	while((length=inputStream.read(b))!=-1)
    	{
    		outputStream.write(b,0,length);
    	}
    	inputStream.close();
    	outputStream.close();
    	return fileName;
    }
    private String getFileName(String header)
    {
    	String fileName = header.substring(header.indexOf("filename=\"") + 10,
                header.lastIndexOf("\""));
    	return fileName;
    }
}
