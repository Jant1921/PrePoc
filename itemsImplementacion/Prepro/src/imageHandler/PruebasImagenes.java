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
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      imgh.setDir(direc);
	      imgh.setImgname("testguardado.tif");
	      imgh.guardarimg(img);
	      Mat img2 = imgh.cargarimg(direc, "testguardado.tif");
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
	    String imgname = "prueba.png";
	    Mat img = new Mat(); 
	    img = imgh.cargarimg(direc, imgname);
	    Mat imgaux = img.clone();
	    Mat img1 = imgh.imgtograyscale(img);
	    Mat img2 = imgh.addnoise(imgaux, 128);
	    
	    double mse = imgh.getmse(img1, img2);
	    int mseint = (int)mse;
	    // 1940 es el entero esperado para el mse
	    assertEquals(mseint,2045);
	    
	  }
	  
	  
	  @Test
	  public void pruebapsnr() {
	    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	    ImageHandler imgh = new ImageHandler();
	    String imgname = "prueba.png";
	    Mat img = new Mat(); 
	    img = imgh.cargarimg(direc, imgname);
	    Mat imgaux = img.clone();
	    Mat img1 = imgh.imgtograyscale(img);
	    Mat img2 = imgh.addnoise(imgaux, 128);
	    double mse = imgh.getmse(img1, img2);
	    double psnr = imgh.getpsnr(mse);
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
	      
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      imgh.setDir(direc);
	      imgname = "pruebaclahe.jpg";    
	      imgh.setImgname(imgname);
	      Mat prueba = imgh.imgtograyscale(img); 
	      imgh.guardarimg(prueba);
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
	      
	      
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      
	      Mat prueba = imgh.imgtograyscale(img);
	      
	      imgname = "pruebagrayscale.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarimg(prueba);
	      
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
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgtograyscale(img);
	      Mat img2 = imgh.addnoise(imgaux, 100);
	      imgname = "pruebaimgnoise.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarimg(img2);
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
	     
	      
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      
	      Mat prueba = imgh.imgtograyscale(img);
	      Mat gaus = imgh.filtrogaus(prueba, 8, 8, 1.5);
	      imgname = "pruebagauss.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarimg(gaus);
	      
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
	     
	      
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      
	      Mat prueba = imgh.imgtograyscale(img);
	      Mat gaus = imgh.filtrobilateral(prueba, 8, 8, 1.5,8);
	      imgname = "pruebagauss.jpg";
	      imgh.setImgname(imgname);
	      imgh.guardarimg(gaus);
	      
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
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgtograyscale(img);
	      Mat img2 = imgh.addnoise(imgaux, 128);
	    
	      double ae = imgh.getmse(img1, img2);
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
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgtograyscale(img);
	      Mat img2 = imgh.addnoise(imgaux, 128);
	    
	      double mae = imgh.getmae(img1, img2);
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
	      String imgname = "prueba.png";
	      Mat img = new Mat(); 
	      img = imgh.cargarimg(direc, imgname);
	      Mat imgaux = img.clone();
	      Mat img1 = imgh.imgtograyscale(img);
	      Mat img2 = imgh.addnoise(imgaux, 128);
	      
	      double ad = imgh.getad(img1, img2);
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
	    String imgname = "prueba.png";
	    Mat img = new Mat(); 
	    img = imgh.cargarimg(direc, imgname);
	    Mat imgaux = img.clone();
	    Mat img1 = imgh.imgtograyscale(img);
	    imgh.filtrobilateral(img1, 5, 5, 1.5, 12);
	    imgh.filtrogaus(img1, 5, 5, 1.5);
	    Mat img2 = imgh.addnoise(imgaux, 128);
	    double mse = imgh.getmse(img1, img2);
	    double psnr = imgh.getpsnr(mse);
	    double mae = imgh.getmae(img1, img2);
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
	
	
	
	

}
