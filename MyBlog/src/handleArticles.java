import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Entity;
import com.utility.JsonUtil;
import com.bll.ArticleGet;

public class handleArticles extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public handleArticles() {
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
         response.setContentType("Utf-8");
		 PrintWriter out=response.getWriter();
		 out.print("[{\"title\":\"马腾飞\"}]");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //使用此post方法老是报错500
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chatset=UTF-8");
		PrintWriter out = response.getWriter();
		String type=request.getParameter("type");
		System.out.print(type);
		ArticleGet get=new ArticleGet();
        String actualString=get.getAllArticles();
		//System.out.print(sendString);
		out.print(actualString);
		
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
