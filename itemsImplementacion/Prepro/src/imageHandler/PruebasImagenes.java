package imageHandler;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class PruebasImagenes {
	
	private String direc = "C:\\Users\\jruiz\\Desktop\\";
	  
	  @Test
	  public void probarguardadocarga() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      imgh.setDir(direc);
	      imgh.setImgname("testguardado.tif");
	      imgh.guardarImg(img);
	      Mat img2 = imgh.cargarImg(direc, "testguardado.tif");
	      assert (true);
	    } catch (Exception e) {
	      assert (false);
	    }
	    
	  }





	  @Test
	  public void pruebamse() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    ImageHandler imgh = new ImageHandler();
	    //AGREGAR NOISE 
	    String imgname = "input.tif";
	    Mat img = new Mat(); 
	    img = imgh.cargarImg(direc, imgname);
	    Mat imgaux = img.clone();
	    Mat img1 = imgh.imgToGrayScale(img);
	    Mat img2 = imgh.addNoise(imgaux, 128);
	    
	    double mse = imgh.getMse(img1, img2);
	    int mseint = (int)mse;
	    // 1940 es el entero esperado para el mse
	    assertEquals(mseint,1940);
	    
	  }
	  
	  
	  @Test
	  public void pruebapsnr() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    ImageHandler imgh = new ImageHandler();
	    String imgname = "input.tif";
	    Mat img = new Mat(); 
	    img = imgh.cargarImg(direc, imgname);
	    Mat imgaux = img.clone();
	    Mat img1 = imgh.imgToGrayScale(img);
	    Mat img2 = imgh.addNoise(imgaux, 128);
	    double mse = imgh.getMse(img1, img2);
	    double psnr = imgh.getPsnr(mse);
	    int psnrint = (int)psnr;
	    //15 es el entero esperado como respuesta referente al psnr
	    assertEquals(psnrint,15);
	    
	  }
	  
	  @Test
	  public void pruebaclahe() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      //define el directorio donde guardara las imagenes
	      
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      imgh.setDir(direc);
	      imgname = "pruebaclahe.jpg";    
	      imgh.setImgname(imgname);
	      Mat prueba = imgh.imgToGrayScale(img); 
	      imgh.guardarImg(prueba);
	      Mat test = imgh.clahe(prueba);
	      
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }
	    
	    
	  }
	  
	  @Test
	  public void pruebaescalagrises() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      
	      
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      
	      Mat prueba = imgh.imgToGrayScale(img);
	      
	      imgname = "pruebagrayscale.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarImg(prueba);
	      
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }
	    
	  }
	  
	  @Test
	  public void pruebaaddnoise() {
	    try {
	      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	      ImageHandler imgh = new ImageHandler();
	      //AGREGAR NOISE 
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgToGrayScale(img);
	      Mat img2 = imgh.addNoise(imgaux, 100);
	      imgname = "pruebaimgnoise.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarImg(img2);
	      assert (true);
	    } catch (Error e) {
	      assert (false);
	    }
	  }
	  
	  
	  @Test
	  public void pruebagauss() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	     
	      
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      
	      Mat prueba = imgh.imgToGrayScale(img);
	      Mat gaus = imgh.filtroGauss(prueba, 8, 8, 1.5);
	      imgname = "pruebagauss.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarImg(gaus);
	      
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }   
	  }
	  
	  @Test
	  public void pruebabilateral() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	     
	      
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      
	      Mat prueba = imgh.imgToGrayScale(img);
	      Mat gaus = imgh.filtroBilateral(prueba, 8, 8, 1.5,8);
	      imgname = "pruebagauss.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarImg(gaus);
	      
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }
	  }
	  
	  @Test
	  public void pruebaae() {
	    try {
	      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	      ImageHandler imgh = new ImageHandler();
	      //AGREGAR NOISE 
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgToGrayScale(img);
	      Mat img2 = imgh.addNoise(imgaux, 128);
	    
	      double ae = imgh.getMse(img1, img2);
	      int aeint = (int)ae;
	 
	      assert (true);
	    } catch (Error e) {
	      assert (false);
	    }
	  }
	  
	  @Test
	  public void pruebamae() {
	    try {
	      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	      ImageHandler imgh = new ImageHandler();
	      //AGREGAR NOISE 
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgToGrayScale(img);
	      Mat img2 = imgh.addNoise(imgaux, 128);
	    
	      double mae = imgh.getMae(img1, img2);
	      int maeint = (int)mae;
	      
	      assert (true);
	    } catch (Error e) {
	      assert (false);
	    }
	  }
	  
	  @Test
	  public void pruebaad() {
	    try {
	      System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	      ImageHandler imgh = new ImageHandler();
	      //AGREGAR NOISE 
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgToGrayScale(img);
	      Mat img2 = imgh.addNoise(imgaux, 128);
	      
	      double ad = imgh.getAd(img1, img2);
	      int adint = (int)ad;
	      assert (true);
	    } catch (Error e) {
	      assert (false);
	    }
	  }
	  
	  @Test
	  public void pruebatiempo() {
	    final long startTime = System.currentTimeMillis();
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    ImageHandler imgh = new ImageHandler();
	    String imgname = "input.tif";
	    Mat img = new Mat(); 
	    img = imgh.cargarImg(direc, imgname);
	    Mat imgaux = img.clone();
	    Mat img1 = imgh.imgToGrayScale(img);
	    imgh.filtroBilateral(img1, 5, 5, 1.5, 12);
	    imgh.filtroGauss(img1, 5, 5, 1.5);
	    Mat img2 = imgh.addNoise(imgaux, 128);
	    double mse = imgh.getMse(img1, img2);
	    double psnr = imgh.getPsnr(mse);
	    double mae = imgh.getMae(img1, img2);
	    Mat clahe = imgh.clahe(img1);
	    int psnrint = (int)psnr;
	    long endTime   = System.currentTimeMillis();
	    long totalTime = (endTime - startTime) / 1000;
	    
	    //acepta si el tiempo de procesado es menor a 8
	    if ((int)totalTime < 8) {
	      
	      assert (true);
	    } else {
	      assert (false);
	    }

	  }
	
	  @Test
	  public void pruebabilateralclahe() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	    	ImageHandler imgh = new ImageHandler();
	     
	      
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      
	      Mat prueba = imgh.imgToGrayScale(img);
	      Mat bila = imgh.filtroBilateral(prueba, 8, 8, 1.5,8);
	      imgname = "pruebagauss.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarImg(bila);
	      Mat test = imgh.clahe(bila);
	      
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }
	  }
	  
	  @Test
	  public void pruebagaussclahe(){
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	     
	      
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      
	      Mat prueba = imgh.imgToGrayScale(img);
	      Mat gaus = imgh.filtroGauss(prueba, 8, 8, 1.5);
	      imgname = "pruebagauss.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarImg(gaus);
	      Mat test = imgh.clahe(gaus);
	      
	      assert (true);
	      
	    } catch (Exception e) {
	      assert (false);
	    }  
	  }
	  
	  @Test
	  public void pruebapsnrunitaria() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    ImageHandler imgh = new ImageHandler();
	    String imgname = "input.tif";
	    Mat img = new Mat(); 
	    img = imgh.cargarImg(direc, imgname);
	    Mat imgaux = img.clone();
	    Mat img1 = imgh.imgToGrayScale(img);
	    Mat img2 = imgh.addNoise(imgaux, 128);
	   
	    double psnr = imgh.getPsnr(1940.0);
	    int psnrint = (int)psnr;
	    //15 es el entero esperado como respuesta referente al psnr
	    assertEquals(psnrint,15);
	  }
	  
	  @Test
	  public void probarguardado() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      imgh.setDir(direc);
	      imgh.setImgname("testguardado.tif");
	      imgh.guardarImg(img);
	      assert (true);
	    } catch (Exception e) {
	      assert (false);
	    }
	    
	  }
	  
	  @Test
	  public void probarcarga() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    try {
	      ImageHandler imgh = new ImageHandler();
	      String imgname = "input.tif";
	      Mat img = new Mat(); 
	      img = imgh.cargarImg(direc, imgname);
	      assert (true);
	    } catch (Exception e) {
	      assert (false);
	    }
	    
	  }
	
	

}
