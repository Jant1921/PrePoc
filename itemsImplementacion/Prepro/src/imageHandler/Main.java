package imageHandler;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import org.opencv.imgproc.Imgproc;

/*
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.core.CvType;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
*/

//import javax.imageio.ImageIO;

public class Main {
	static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	private static int[] matrizGauss = {8,8};
	private static double desviacionGauss = 0.0;
	private static int[] matrizBilateral = {10,10};
	private static double[] desviacionesBilateral = {2.0,8.0};
	public static boolean doGauss = false;
	public static boolean doBilateral = false;
	
	public static void setGaussValues(int[] matriz,double desviacion){
		matrizGauss = matriz;
		desviacionGauss = desviacion;
		doGauss = true;
	}
	
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
		Mat prueba= new Mat();
		
		//define el directorio donde guardara las imagenes	
		//direc="F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
		//imgname="01.png";
		
		direc="C:\\Users\\jruiz\\Documents\\PrePoc\\itemsImplementacion\\uploaded_images\\"+pToken+"\\";
		imgname=pName;
		
		
		 //carga matriz de imagen en img
		 img = imgh.cargarimg(direc, imgname);
		 //escribre la imagen en escala de grises
		 Imgproc.cvtColor(img, prueba, Imgproc.COLOR_RGB2GRAY);
		 prueba=img.clone();
		 prueba=imgh.imgtograyscale(prueba);
		 imgh.setDir(direc);
		 imgh.setImgname("gris_"+imgname);
		 imgh.guardarimg(prueba);

		 
		 
		 //copia el estado actual de variable prueba(matriz en escala de grises)
		 Mat respaldo = prueba.clone();
		 
		 if(doGauss){
			 //aplica el filtro gaussiano a la imagen
			 Mat respaldo2 = prueba.clone();		 
			 Mat gaus=imgh.filtrogaus(respaldo2, matrizGauss[0], matrizGauss[1], desviacionGauss);
			 imgh.setDir(direc);
			 imgh.setImgname("gauss_"+imgname);
			 imgh.guardarimg(gaus);
			 
			 //crea una matriz para apoyo
			 Mat clahe_gauss= prueba.clone();
			 //utilizacion del algoritmo clahe para el bilateral
			 clahe_gauss=imgh.clahe(gaus);
			 //guardar la imagen modificada por clahe
			 imgh.setImgname("claheGauss_"+imgname);
			 imgh.guardarimg(clahe_gauss);
		 }
		 
		 //clahe a imagen en escala de grises
		//crea una matriz para apoyo
		 Mat clahe_gris= prueba.clone();
		 //utilizacion del algoritmo clahe para el bilateral
		 clahe_gris=imgh.clahe(clahe_gris);
		 //guardar la imagen modificada por clahe
		 imgh.setImgname("claheGris_"+imgname);
		 imgh.guardarimg(clahe_gris);
		 
		 
		 Mat respaldo7 = prueba.clone();
		 Mat image = respaldo7.clone();
		 image=imgh.addnoise(image,noiseRatio);
	     imgh.setDir(direc);
	     imgh.setImgname("noise_"+imgname);
	     imgh.guardarimg(image);  
		 
	     
	     
		if(doBilateral){
			 //aplica el filtro bilateral a la imagen
		     Mat respaldo3 = prueba.clone();
			 Mat bilateral=imgh.filtrobilateral(respaldo3, matrizBilateral[0], matrizBilateral[1], desviacionesBilateral[0], desviacionesBilateral[1]); 
			 imgh.setDir(direc);
			 imgh.setImgname("bilateral_"+imgname);
			 imgh.guardarimg(bilateral);	
				
			 
					
			 //crea una matriz para apoyo
			 Mat img2= prueba.clone();
			 //utilizacion del algoritmo clahe para el bilateral
			 img2=imgh.clahe(bilateral);
			 //guardar la imagen modificada por clahe
			 imgh.setImgname("clahe_"+imgname);
			 imgh.guardarimg(img2);
		}
		 
		 

		
		 //llamado de la funcion para calcular el mse
		 double mse=imgh.getmse(respaldo, image);
		 
		 System.out.println("mse " + mse);
		 
		 double ad=imgh.getad(respaldo, image);
         
         System.out.println("ad "+ ad);
         
         double mae=imgh.getmae(respaldo, image);
         
         System.out.println("mae " + mae);
         
         double ae=imgh.getmse(respaldo, image);
         
         System.out.println("ae " + ae);
		 
		 //llamado de la funcion para calcular el psnr
		 double psnr = imgh.getpsnr(mse);
		 
		 System.out.println("psnr "+psnr);
		 
		double[] results = {mse,ad,mae,ae,psnr};
		return results;
		 
		 
		
	       
		
	}
		
	
}
