package PruebasJUnit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import imageHandler.ImageHandler;
import imageHandler.Main;

/**
 * Clase con las pruebas para JUnit - Auditoria dinámica.
 * @author  Kevin Ramírez
 * @version 1.0.0, 04 Mayo 2017
 */

public class Pruebas {
	
	private String direc = "C:\\Users\\L45-B4208FL\\Desktop\\";
	  
	
	/**
	   * Metodo que prueba que el metodo toGrayScale no puede tener como parametro el valor NULL.
	  */
	
	  @Test
	  public void pruebaToGrayScale() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      Mat img = imgh.imgToGrayScale(null);
	      assert (true);
	    } catch (Exception e) {
	      assert (false);
	    } 
	  }




	  /**
	   * Método que prueba el método GetMae, con el parametro Ruido en NULL. Debe dar error.
	  */
	  @Test
	  public void pruebaGetMaeRuido() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try{
		    ImageHandler imgh = new ImageHandler();
		    //AGREGAR NOISE 
		    String imgname = "prueba.png";
		    Mat img = new Mat(); 
		    img = imgh.cargarImg(direc, imgname);
		    Mat imgaux = img.clone();
		    Mat img1 = imgh.imgToGrayScale(img);
		    imgh.getMae(img1, null);
		    assert(true);
	    }catch(Exception e){
	    	assert(false);
	    }
	  }
	  
	  /**
	   * Método que prueba el método GaussAuxiliar, con los valores Height y Width mayores a la matriz de la imagen
	  */
	  @Test
	  public void pruebaWHGaussAuxiliar() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try{
		    ImageHandler imgh = new ImageHandler();
		    String imgname = "prueba.png";
		    Mat img = new Mat(); 
		    img = imgh.cargarImg(direc, imgname);
		    Mat imgaux = img.clone();
		    Mat img1 = imgh.imgToGrayScale(img);
		    Mat img2 = imgh.addNoise(imgaux, 128);
		    int h = img1.height()+10;
		    int w = img1.width()+10;
		    double valorMat;
		    Mat result = img;
		    for (int i = 0; i < h;i++) {
		      for (int j = 0; j < w;j++) {
		        valorMat = img.get(i, j)[0];
		        result = imgh.gaussAuxiliar(i,j,w,h,5,valorMat,result);
		      }
		    }
		    assert(true);
	    }catch(Exception e){
	    	assert(false);
	    }
	  }
	  
	  /**
	   * Método que prueba el método KernelBilateral, Con el valor Pixel y otros no definidos correctamente en la matriz de valores.
	  */
	  @Test
	  public void pruebaPixelKernelBilateral() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      //define el directorio donde guardara las imagenes
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      imgh.setDir(direc);
	      imgname = "pruebaclahe.jpg";    
	      imgh.setImgname(imgname);
	      int x=30;
	      int y=30;
	      int kernelSizeR=30;
	      int kernelSizeColumn=30;
	      int uMin = (x + 1) - ((kernelSizeR - 1) / 2);
	      int uMax = (x + 1) + ((kernelSizeR - 1) / 2);
	      int vMax = (y + 1) + ((kernelSizeColumn - 1) / 2);
	      int vmin = (y + 1) - ((kernelSizeColumn - 1) / 2);
	      double[] val1 = new double[1];
	      double valorMat=-30.0;
	      Mat resultado = img;
	      double suma = 0;
	      double alfa = 0;
	      for (int i = uMin; i < uMax;i++) {
	          for (int j = vmin; j < vMax;j++) {
	              val1[0] = img.get(i,j)[0];
	              double kernelVal = imgh.getKernelBilateral(x - i,y - j,2,2,valorMat,val1[0]);
	          }
	      }
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }
	    
	    
	  }
	  
	  /**
	   * Prueba de integridad de la clase Main con respecto a la función toGrayScale de ImageHandler pasandole una imagen nula.
	  */
	  @Test
	  public void pruebaProcesarToGrayScale() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	    	ImageHandler imgh = new ImageHandler();
	    	Mat imgOriginal=imgh.imgToGrayScale(null);
	    	assert (true);
	    } catch (Exception e) {
	      assert (false);
	    }
	  }
	  
	  /**
	   * Prueba de integridad de la clase Main con respecto a la función FiltroGauss de ImageHandler pasandole valores incorrectos.
	  */
	  @Test
	  public void pruebaProcesarFiltroGauss() {
	    try {
	      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	      ImageHandler imgh = new ImageHandler();
	      //AGREGAR NOISE 
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgToGrayScale(img);
	      imgh.filtroGauss(img1, -10, 0, 3);
	      assert (true);
	    } catch (Error e) {
	      assert (false);
	    }
	  }
	  
	  /**
	   * Prueba de integridad de la clase Main con respecto a la función addNoise de ImageHandler, dandole un valor negativo menor al permitido.
	  */
	  @Test
	  public void pruebaProcesarNoise() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      Mat img = new Mat(); 
	      String imgname = "prueba.png";
	      img = imgh.cargarImg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgToGrayScale(img);
	      imgh.addNoise(img1, -15.0);
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }   
	  }
	  
	  /**
	   * Prueba de integridad de la clase Main con respecto a la función getAe de ImageHandler, dandole como parámetro un valor null y uno correcto en el ruido.
	  */
	  @Test
	  public void pruebaGetAe() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	    	ImageHandler imgh = new ImageHandler();
		    String imgname = "prueba.png";
		    Mat img = new Mat(); 
		    img = imgh.cargarImg(direc, imgname);
		    Mat imgaux = img.clone();
		    Mat img1 = imgh.imgToGrayScale(img);
		    Mat img2 = imgh.addNoise(imgaux, 128);
		    imgh.getAd(null, img2);
		    assert(true);
	    } catch (Exception e) {
	      assert (false);
	    }
	  }
}
