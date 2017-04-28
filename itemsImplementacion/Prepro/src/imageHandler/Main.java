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
	public static void procesar(String pToken,String pName){
		
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
		
		direc="C:\\Users\\jruiz\\Documents\\workspace\\Prepro\\uploaded_images\\"+pToken+"\\";
		imgname=pName;
		
		
		//carga matriz de imagen en img
		img = imgh.cargarimg(direc, imgname);
	
	 
		 //escribe la imagen recien leida
		 //imgh.setDir(direc);
		 //imgh.setImgname("imagtest1.tif");
		 //imgh.guardarimg(img);
		 
		 
		
		
		 //escribre la imagen en escala de grises
		//
		 Imgproc.cvtColor(img, prueba, Imgproc.COLOR_RGB2GRAY);
		 
		 prueba=img.clone();
		 
		 prueba=imgh.imgtograyscale(prueba);
		 //imgname= "pruebagrayscale.tif";	 

		 imgh.setDir(direc);
		 imgh.setImgname("gris_"+imgname);
		 imgh.guardarimg(prueba);

		 
		 
		 //copia el estado actual de variable prueba(matriz en escala de grises)
		 Mat respaldo = prueba.clone();
		 Mat respaldo2 = prueba.clone();		 
		 Mat respaldo3 = prueba.clone();
		 Mat respaldo4 = prueba.clone();
		 Mat respaldo5 = prueba.clone();
		 //Mat respaldo6 = prueba.clone();
		 Mat respaldo7 = prueba.clone();

		 
		 //aplica el filtro gaussiano a la imagen
		 Mat gaus=imgh.filtrogaus(respaldo2, 8, 8, 2);
		 //imgname= "pruebagauss1.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname("gauss_"+imgname);
		 imgh.guardarimg(gaus);
		 
		 
		 Mat image = respaldo7.clone();
		 
		 image=imgh.addnoise(image,28);
	        
	     //imgname= "pruebanoise.tif";  
	     imgh.setDir(direc);
	     imgh.setImgname("noise_"+imgname);
	     imgh.guardarimg(image);  
		 
		
		 //aplica el filtro bilateral a la imagen
		 Mat bilateral=imgh.filtrobilateral(respaldo3, 10, 10, 2, 8);
		 //imgname= "pruebabilateral1.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname("bilateral_"+imgname);
		 imgh.guardarimg(bilateral);	
			
		 //bilateral de opencv
		 /*
		 Imgproc.bilateralFilter ( respaldo4, respaldo5, 10, 15, 8 );
		 imgname= "pruebabilateral2opencv.tif";	 
		 imgh.setDir(direc);
		 imgh.setImgname(imgname);
		 imgh.guardarimg(respaldo5);
		 */
		 
		
				 
		
		 //crea una matriz para apoyo
		 Mat img2= prueba;
		 
		 
		 //utilizacion del algoritmo clahe
		 img2=imgh.clahe(bilateral);
		
		
		 
		 //guardar la imagen modificada por clahe
		 //imgname="pruebaclahe2.tif";
		 imgh.setImgname("clahe_"+imgname);
		 imgh.guardarimg(img2);
		 
		 

		
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
		 
		
		 
		 
		
	       
		
	}
		
	
}
