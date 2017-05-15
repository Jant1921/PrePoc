package imageHandler;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import org.opencv.imgproc.Imgproc;


/**
 * Clase intermedia que se encarga de comunicar la clase ImageHandler
 * con AppServer
 * @author  Daniel Estrada, Jose Ruiz
 * @version 1.0.2, 14 Mayo 2017
 */

public class Main {
	static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	private static int[] matrizGauss = {8,8};
	private static double desviacionGauss = 0.0;
	private static int[] matrizBilateral = {10,10};
	private static double[] desviacionesBilateral = {2.0,8.0};
	public static boolean doGauss = false;
	public static boolean doBilateral = false;
	
	/**
	 * Configura los valores con los que se va a aplicar el filtro gaussiano
	 * @param matriz contiene tamaños para el kernel 
	 * @param desviacion desviacion con la que aplica el filtro
	 */
	public static void setGaussValues(int[] matriz,double desviacion){
		matrizGauss = matriz;
		desviacionGauss = desviacion;
		doGauss = true;
	}
	
	/**
	 * Configura los valores con los que se va a aplicar el filtro bilateral
	 * @param matriz contiene tamaños para el kernel
	 * @param desviacion grado de desviacion que se aplica, contiene 2 valores
	 */
	public static void setBilateralValues(int[] matriz,double[] desviacion){
		matrizBilateral = matriz;
		desviacionesBilateral = desviacion;
		doBilateral = true;
	}
	
	public static double[] procesar(String pToken,String pName, int noiseRatio){
		
		//se carga la libreria para evitar errores 
		
				
		//instancia la clase de manejo de imagenes
		ImageHandler imgh= new ImageHandler();
		//instancias de variables base a utilizar
		String direc="";
		String imgname="";
		Mat img= new Mat();
		Mat imgOriginal= new Mat();
		
		//define el directorio donde guardara las imagenes
		
		direc="C:\\Users\\jruiz\\Documents\\PrePoc\\itemsImplementacion\\uploaded_images\\"+pToken+"\\";
		imgname=pName;
		
		
		 //carga matriz de imagen en img
		 img = imgh.cargarImg(direc, imgname);
		 //escribre la imagen en escala de grises
		 Imgproc.cvtColor(img, imgOriginal, Imgproc.COLOR_RGB2GRAY);
		 imgOriginal=img.clone();
		 imgOriginal=imgh.imgToGrayScale(imgOriginal);
		 imgh.setDir(direc);
		 imgh.setImgname("gris_"+imgname);
		 imgh.guardarImg(imgOriginal);

		 
		 
		 //copia el estado actual de variable prueba(matriz en escala de grises)
		 Mat respaldo = imgOriginal.clone();
		 
		 if(doGauss){
			 //aplica el filtro gaussiano a la imagen
			 Mat respaldo2 = imgOriginal.clone();		 
			 Mat gaus=imgh.filtroGauss(respaldo2, matrizGauss[0], matrizGauss[1], desviacionGauss);
			 imgh.setDir(direc);
			 imgh.setImgname("gauss_"+imgname);
			 imgh.guardarImg(gaus);
			 
			 //crea una matriz para apoyo
			 Mat clahe_gauss= imgOriginal.clone();
			 //utilizacion del algoritmo clahe para el bilateral
			 clahe_gauss=imgh.clahe(gaus);
			 //guardar la imagen modificada por clahe
			 imgh.setImgname("claheGauss_"+imgname);
			 imgh.guardarImg(clahe_gauss);
		 }
		 
		 //clahe a imagen en escala de grises
		//crea una matriz para apoyo
		 Mat clahe_gris= imgOriginal.clone();
		 //utilizacion del algoritmo clahe para el bilateral
		 clahe_gris=imgh.clahe(clahe_gris);
		 //guardar la imagen modificada por clahe
		 imgh.setImgname("claheGris_"+imgname);
		 imgh.guardarImg(clahe_gris);
		 
		 
		 Mat respaldo7 = imgOriginal.clone();
		 Mat image = respaldo7.clone();
		 image=imgh.addNoise(image,noiseRatio);
	     imgh.setDir(direc);
	     imgh.setImgname("noise_"+imgname);
	     imgh.guardarImg(image);  
		 
	     
	     
		if(doBilateral){
			 //aplica el filtro bilateral a la imagen
		     Mat respaldo3 = imgOriginal.clone();
			 Mat bilateral=imgh.filtroBilateral(respaldo3, matrizBilateral[0], matrizBilateral[1], desviacionesBilateral[0], desviacionesBilateral[1]); 
			 imgh.setDir(direc);
			 imgh.setImgname("bilateral_"+imgname);
			 imgh.guardarImg(bilateral);	
				
			 
					
			 //crea una matriz para apoyo
			 Mat img2= imgOriginal.clone();
			 //utilizacion del algoritmo clahe para el bilateral
			 img2=imgh.clahe(bilateral);
			 //guardar la imagen modificada por clahe
			 imgh.setImgname("clahe_"+imgname);
			 imgh.guardarImg(img2);
		}
		 
		 

		
		 //llamado de la funcion para calcular el mse
		 double mse=imgh.getMse(respaldo, image);
		 
		
		 double ad=imgh.getAd(respaldo, image);
         
        
         double mae=imgh.getMae(respaldo, image);
         
          
         double ae=imgh.getMse(respaldo, image);
         
        
		 //llamado de la funcion para calcular el psnr
		 double psnr = imgh.getPsnr(mse);
		 
		  
		double[] results = {mse,ad,mae,ae,psnr};
		return results;
		 
		 
		
	       
		
	}
		
	
}
