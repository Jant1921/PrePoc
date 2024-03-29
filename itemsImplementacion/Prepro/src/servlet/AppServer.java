package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AppServer
 */
/**
 * Servlet implementation class AppServer
 * Provee una interfaz que se encarga de manejar peticiones externas a la aplicacion
 * @author  Edward Rodriguez, Jose Ruiz
 * @version 1.0.2, 23 Abril 2017
 */
@WebServlet("/AppServer")
public class AppServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppServer() {
        super();
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        response.getWriter().append("Served at: \n").append(request.getContextPath()+"\n");
		String token = request.getParameter("token");
		String rutaImagenes = request.getParameter("images");
		String gauss = request.getParameter("gauss");
		String bilateral = request.getParameter("bilateral");
		int noiseRatio = 250;
		if(request.getParameter("noise")!=null){
			noiseRatio = Integer.parseInt(request.getParameter("noise"));
		}
		//TODO
		//String clahe = request.getParameter("clahe");
		
		if(rutaImagenes!=null && token!=null ){
			String[] imagenes = rutaImagenes.split(",");
			
			String[] tmp ;
			String tmpName;
			int[] matrizGauss = {0,0};
			double desviacionGauss = 0.0;
			int[] matrizBilateral = {0,0};
			double[] desviacionesBilateral = {0.0,0.0};
			
			if(gauss!= null){

				String[] gaussParamsArray = gauss.split(",");
				response.getWriter().append("gauss="+gaussParamsArray[0]+"\n");
				if(gaussParamsArray.length==3){
					matrizGauss[0] = Integer.parseInt(gaussParamsArray[0]);
					matrizGauss[1] = Integer.parseInt(gaussParamsArray[1]);
					desviacionGauss = Double.parseDouble(gaussParamsArray[2]);
					imageHandler.Main.setGaussValues(matrizGauss,desviacionGauss);
				}else{
					response.getWriter().append("Error: Debe Rellenar todos los parámetros para el filtro Gaussiano\n");
					return;
				}
			}else{
				imageHandler.Main.doGauss = false;
			}
			if(bilateral!= null){
				String[] bilateralParamsArray = bilateral.split(",");
				if(bilateralParamsArray.length==4){
					matrizBilateral[0] = Integer.parseInt(bilateralParamsArray[0]);
					matrizBilateral[1] = Integer.parseInt(bilateralParamsArray[1]);
					desviacionesBilateral[0] = Double.parseDouble(bilateralParamsArray[2]);
					desviacionesBilateral[1] = Double.parseDouble(bilateralParamsArray[3]);
					imageHandler.Main.setBilateralValues(matrizBilateral,desviacionesBilateral);
				}else{
					response.getWriter().append("Error: Debe Rellenar todos los parámetros para el filtro Bilateral\n");
					return;
				}
			}else{
				imageHandler.Main.doBilateral = false;
			}
			//TODO
			/*
			if(clahe!= null){
				
			}else{
				else{
					imageHandler.Main.doClahe = false;
				}
			}*/
			String valoresMetricas = "";
			for(int i = 0; i<imagenes.length;i++){
				tmp = imagenes[i].split(token);
				tmpName = tmp[tmp.length-1];
				tmpName = tmpName.substring(1, tmpName.length()-1);
				
				double[] resultados = imageHandler.Main.procesar(token,tmpName,noiseRatio);
				for(double resultado : resultados){
					valoresMetricas += resultado+",";
				}
				valoresMetricas+="+";
			}
			valoresMetricas = valoresMetricas.substring(0, valoresMetricas.length()-1);
			response.getWriter().append("Procesando...");
			
			response.sendRedirect("http://localhost:3001/results?token="+token+"&images="+request.getParameter("images")+"&metricas="+valoresMetricas);
		}else{
			response.getWriter().append("Error: Debe seleccionar la menos una imagen para procesar\n");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
