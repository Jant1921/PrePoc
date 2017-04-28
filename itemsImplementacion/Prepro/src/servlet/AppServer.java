package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.core.CvType;

/**
 * Servlet implementation class AppServer
 */
@WebServlet("/AppServer")
public class AppServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: \n").append(request.getContextPath());
		
		if(request.getParameter("images")!=null && request.getParameter("token")!=null ){
			
			String token = request.getParameter("token");
			response.getWriter().append("\n"+token+"\n\n\n"+request.getParameter("images"));
			
			String[] imagenes = request.getParameter("images").split(",");
			response.getWriter().append("\n"+imagenes[0]);
			String[] tmp ;
			String tmpName;
			 
			for(int i = 0; i<imagenes.length;i++){
				tmp = imagenes[i].split(token);
				tmpName = tmp[tmp.length-1];
				tmpName = tmpName.substring(1, tmpName.length()-1);
				response.getWriter().append("\n"+tmpName);
				imageHandler.Main.procesar(token,tmpName);
			}
			response.sendRedirect("http://192.168.43.218:3001/results?token="+token+"&images="+request.getParameter("images"));
		}
		
		//imageHandler.Main.procesar("Tfup","cmamo.jpg");
		//imageHandler.Main.procesar("uno.jpg");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//imageHandler.Main.procesar("uno.jpg");
		doGet(request, response);
	}

}
