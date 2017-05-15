package imageHandler;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Clase que se encarga de realizar todos los procesamientos
 * relacionados con el manejo de imagenes y filtros
 * @author  Daniel Estrada
 * @version 1.0.2, 14 Mayo 2017
 */

public class ImageHandler {

  private String dir = "F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
  private String imgName = "prueba2.tif";




  /**
   * guarda la matriz como una imagen
   * @param img matriz que contiene la informacion de los pixeles de la imagen
   * @param dir string referente a la direccion del folder
   * @param imgname string referente al nombre de la imagen
  */

    public void guardarImg(Mat img, String dir, String imgname) {
      Imgcodecs.imwrite(dir + imgname, img);
    }

  /**
   * guarda la matriz como una imagen
   * @param img matriz que contiene la informacion de los pixeles de la imagen
   */
    public void guardarImg(Mat img) {
      Imgcodecs.imwrite(dir + imgName, img);
    }

  /**
   * genera la matriz de la imagen ubicada segun el folder y nombre
   * @param dir string referente a la direccion del folder
   * @param imgname string referente al nombre de la imagen
   * @return la matriz referente a la imagen cargada
   */
    public Mat cargarImg(String dir,String imgname) {
      Mat img = Imgcodecs.imread(dir + imgname);
      return img;
    }

  /**
   * genera la matriz de la imagen ubicada segun el folder y nombre
   * @return la matriz referente a la imagen cargada
   */
    public Mat cargarImg() {
      Mat img = Imgcodecs.imread(dir + imgName,0);
      return img;
    }

  /**
   * Aplica Contrast Limited Adaptive Histogram Equalization a la matriz referente a una imagen
   * @see http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/CLAHE.html
   * @param img matriz de una imagen
   * @return la matriz de imagen modificada 
   */
    public Mat clahe(Mat img) {
      Mat resultado = new Mat();
      Imgproc.createCLAHE().apply(img, resultado);
      return resultado;
    }



  /**
   * Retorna una matriz con la imagen transformada a escala de grises
   * 
   * @param  img  corresponde a la matriz de la imagen  
   * @return      la matriz de la imagen en formato de escala de grises
   */
    public Mat imgToGrayScale(Mat img) {
      Mat resultado = new Mat();
      Imgproc.cvtColor(img, resultado, Imgproc.COLOR_RGB2GRAY);
      return resultado;

    }



  /**
   * calcula el  mean squared error
   * Basado en la formula descrita en el punto 2.1 del documento Proyecto2.pdf
   * https://www.dropbox.com/sh/ih439k2p0szpxh0/AAAwJBWTF4sX2eshm-v_rfyYa/Proyectos?dl=0&preview=Proyecto2.pdf
   * @param img  Mat de la imagen original
   * @param ruido Mat de la imagen con ruido
   * @return el mse calculado en base a las 2 imagenes
   */
    public double getMse(Mat img, Mat ruido) {
      double mse;
      int sum = 0;
      double[] val1 = new double[1];
      double[] val2 = new double[1];
      int w = img.width();
      int h = img.height();

      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w;j++) {
          val1[0] = img.get(i,j)[0];
          val2[0] = ruido.get(i,j)[0];
          double error = val1[0] - val2[0];
          sum += (error * error);

        }

      }
      mse = sum / (h * w);
      return mse;

    }

  /**
   * calcula el peak signal to noise ratio
   * Basado en la formula descrita en el punto 2.1 del documento Proyecto2.pdf
   * https://www.dropbox.com/sh/ih439k2p0szpxh0/AAAwJBWTF4sX2eshm-v_rfyYa/Proyectos?dl=0&preview=Proyecto2.pdf
   * @param mse es el mean quared error
   * @return un double con el valor del psnr
   */
    public double getPsnr(double mse) {
      int max = 255;
      double psnr = (20 * Math.log10(max)) - (10 * Math.log10(mse));
      
      return psnr;
    }
    
    /**
    * calcula el mean absolute error
    * @param img imagen sin ruido
    * @param ruido imagen contaminada
    * @return calculo del mae
    */
    public double getMae(Mat img, Mat ruido) { //mean absolute error
      double mae;
      int sum = 0;
      double[] val1 = new double[1];
      double[] val2 = new double[1];
      int w = img.width();
      int h = img.height();

      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w;j++) {
          val1[0] = img.get(i,j)[0];
          val2[0] = ruido.get(i,j)[0];
          double error = Math.abs(val1[0] - val2[0]);
          sum += (error);

        }

      }
      mae = sum / (h * w);
      return mae;

    }

    /**
    * calcula el  average difference
    * @param img imagen sin ruido
    * @param ruido imagen contaminada
    * @return calculo del ad
    */
    public double getAd(Mat img, Mat ruido) { 
      double ad;
      int sum = 0;
      double[] val1 = new double[1];
      double[] val2 = new double[1];
      int w = img.width();
      int h = img.height();

      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w;j++) {
          val1[0] = img.get(i,j)[0];
          val2[0] = ruido.get(i,j)[0];
          double error = val1[0] - val2[0];
          sum += (error);

        }

      }
      ad = sum / (h * w);
      return ad;

    }
    
    
    
    /**
     * calcula el  absolute error
     * @param img imagen sin ruido
     * @param ruido imagen contaminada
     * @return calculo del ae
     */  
    public double getAe(Mat img, Mat ruido) { //absolute error
      double ae;
      int sum = 0;
      double[] val1 = new double[1];
      double[] val2 = new double[1];
      int w = img.width();
      int h = img.height();

      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w;j++) {
          val1[0] = img.get(i,j)[0];
          val2[0] = ruido.get(i,j)[0];
          double error = Math.abs(val1[0] - val2[0]);
          sum += error;

        }

      }
      ae = sum ;
      return ae;

    }

  /**
   * Retorna el valor de dir (direccion del forlder)
   * 
   * @return el string con la direccion del folder
   */
    public String getDir() {
      return dir;
    }

  /**
   * setea el parametro de direccion del folder
   * @param dir string que hace referencia a la ruta del folder
   */
    public void setDir(String dir) {
      this.dir = dir;
    }

    /** 
   * retorna el nombre del archivo a crear
   * @return string con el nombre del archivo a generar
   */
    public String getImgname() {
      return imgName;
    }

    /**
   * Setea el valor del nombre de la imagen a crear
   * @param imgName string para setear el nombre del archivo a crear
   */
    public void setImgname(String imgName) {
      this.imgName = imgName;
    }
    
    /**
   * Toma un mat, de la imagen cargada y le aplica el filtro gaussiano
   * Basado en la formula descrita en el punto 2 del documento Proyecto2.pdf
   * https://www.dropbox.com/sh/ih439k2p0szpxh0/AAAwJBWTF4sX2eshm-v_rfyYa/Proyectos?dl=0&preview=Proyecto2.pdf
   * @param img matriz con los datos de imagen    
   * @param kernelSizeRow filas del kernel
   * @param kernelSizeColumn columnas del kernel
   * @param desv desviacion estandar
   * @return matriz con el filtro aplicado
   */
    public Mat filtroGauss(Mat img,int kernelSizeRow,int kernelSizeColumn,double desv) {
      Mat result = img;
     
      int w;
      int h;
      double valorMat;
      w = img.width();
      h = img.height();

      for (int i = 0; i < h;i++) {
        for (int j = 0; j < w;j++) {

          valorMat = img.get(i, j)[0];
          result = gaussAuxiliar(i,j,kernelSizeRow,kernelSizeColumn,desv,valorMat,result);

        }
      }

      return result;
      
    }

    /**
     * funcion auxiliar de la funcion filtroGauss
     * Basado en la formula descrita en el punto 2 del documento Proyecto2.pdf
     * https://www.dropbox.com/sh/ih439k2p0szpxh0/AAAwJBWTF4sX2eshm-v_rfyYa/Proyectos?dl=0&preview=Proyecto2.pdf
     * @param x valor de referencia para el kernel
     * @param y valor de referencia para el kernel
     * @param kernelSizeR filas del kernel
     * @param kernelSizeColumn columnas del kernel
     * @param desv desviacion estandar
     * @param valormat valor en el pixel actual
     * @param img matriz de imagen
     * @return imagen con el kernel aplicado para el pixel
     */
    public Mat gaussAuxiliar(int x, int y, int kernelSizeR,int kernelSizeColumn,double desv,
                             double valormat,Mat img) {

      int uMin = (x + 1) - ((kernelSizeR - 1) / 2);
      int uMax = (x + 1) + ((kernelSizeR - 1) / 2);
      int vMax = (y + 1) + ((kernelSizeColumn - 1) / 2);
      int vMin = (y + 1) - ((kernelSizeColumn - 1) / 2);
      double[] val1 = new double[1];
      Mat resultado = img;
      double suma = 0;
      double alfa = 0;

      for (int i = uMin;i < uMax;i++) {
        for (int j = vMin;j < vMax;j++) {

          if (x - i < 0 || y - j < 0 || i < 0 || j < 0) {
            suma += 0;
          } else {
            double kernelVal = getKernel(x - i,y - j,desv);
            alfa += kernelVal;
            val1[0] = img.get(i,j)[0];
            suma += kernelVal * val1[0];
          }


        }
      }

      double[] data = img.get(x, y);

      data[0] = suma * (1 / alfa);


      resultado.put(x,y,data);

      return resultado;
    }

  /**
   * Obtiene el kertel en un punto especifico
   * @param x punto de referencia
   * @param y punto de referencia
   * @param desv desviacion estandar
   * @return valor del kernel en el punto especifico
   */
    public double getKernel(int x,int y,double desv) {
      double val = Math.exp(- (((x * x) + (y * y)) / (2 * (desv * desv))));
      return val;
    }
    
    
    
  /**
   * obtiene el alfa de una imagen
   * @param matriz matriz de imagen
   * @param h height
   * @param w weight
   * @return entero con el valor de alfa
   */
    public int getAlfa(Mat matriz,int h, int w) {
      int alfa = 0;
      double[] val1 = new double[1];
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w;j++) {
          val1[0] = matriz.get(i,j)[0]; 
          alfa += val1[0];

        }

      }
      return alfa;

    }




  /**
   * aplica el filtro bilateral de una imagen
   * Basado en la formula descrita en el punto 2 del documento Proyecto2.pdf
   * https://www.dropbox.com/sh/ih439k2p0szpxh0/AAAwJBWTF4sX2eshm-v_rfyYa/Proyectos?dl=0&preview=Proyecto2.pdf
   * @param img matriz de imagen
   * @param kernelSizeR filas del kernel
   * @param kernelSizeColumn columnas del kernel
   * @param desv desviacion del dominio de la intensidad
     * @param desvr desviacion del dominio del espacio
   * @return matriz con filtro bilateral aplicado
   */
    public Mat filtroBilateral(Mat img,int kernelSizeR,int kernelSizeColumn,double desv,double desvr) {
      Mat result = img;
    
      int w;
      int h;
     
      double valorMat;
      w = img.width();
      h = img.height();
      
      for (int i = 0; i < h;i++) {
        for (int j = 0; j < w;j++) {

          valorMat = img.get(i, j)[0];
          result = bilateralAuxiliar(i,j,kernelSizeR,kernelSizeColumn,desv,desvr,valorMat,result);

        }
      }


      return result;

    }

  /**
   *  funcion auxiliar de filtroBilateral()
   * Basado en la formula descrita en el punto 2 del documento Proyecto2.pdf
   * https://www.dropbox.com/sh/ih439k2p0szpxh0/AAAwJBWTF4sX2eshm-v_rfyYa/Proyectos?dl=0&preview=Proyecto2.pdf
   * @param x  valor en pixeles de referencia para el kernel
   * @param y  valor en pixeles de referencia para el kernel
   * @param kernelSizeR filas del kernel en pixeles
   * @param kernelSizeColumn columnas del kernel en pixeles
   * @param desv desviacion del dominio de la intensidad
     * @param desvr desviacion del dominio del espacio
   * @param valorMat valor en el punto actual de la matriz imagen
   * @param img matriz imagen
   * @return matriz con el kernel aplicado al pixel actual
   */
    public Mat bilateralAuxiliar(int x, int y, int kernelSizeR,int kernelSizeColumn,
                                 double desv,double desvr,double valorMat,Mat img) {

      int uMin = (x + 1) - ((kernelSizeR - 1) / 2);
      int uMax = (x + 1) + ((kernelSizeR - 1) / 2);
      int vMax = (y + 1) + ((kernelSizeColumn - 1) / 2);
      int vmin = (y + 1) - ((kernelSizeColumn - 1) / 2);
      double[] val1 = new double[1];
      Mat resultado = img;
      double suma = 0;
      double alfa = 0;

      for (int i = uMin; i < uMax;i++) {
        for (int j = vmin; j < vMax;j++) {

          if (x - i < 0 || y - j < 0 || i < 0 || j < 0) {
            suma += 0;
          } else {
            val1[0] = img.get(i,j)[0];
            double kernelVal = getKernelBilateral(x - i,y - j,desv,desvr,valorMat,val1[0]);
            alfa += kernelVal;
            suma += kernelVal * val1[0];
          }
          
          
        }
      }

      double[] data = img.get(x, y);

      data[0] = suma / alfa;


      resultado.put(x,y,data);
      return resultado;
    }


    /**
     *  obtiene el kernel en una ubicacion especifica
     * @param x valor de referencia para el kernel
     * @param y valor de referencia para el kernel
     * @param desv desviacion del dominio de la intensidad
     * @param desvr desviacion del dominio del espacio
     * @param valPixel valor del pixel actual
     * @param pixelRelatKernel valor del pixel en la matriz actual del kernel
     * @return valor del kernel en el punto
     */
    public double getKernelBilateral(int x,int y,double desv,double desvr,
                                     double valPixel,double pixelRelatKernel) {
    
      double val = Math.pow(Math.E, (-1 * ((x * x + y * y) / (2 * desv * desv)) 
          -  (Math.pow((pixelRelatKernel - valPixel), 2) / (2 * desvr * desvr)))); 
       
      
      return val;
    }

    /**
     * Agrega ruido artificial a una imagen
     * @param image matriz de imagen
     * @param noise double con el factor de ruido
     * @return matriz con ruido
     */
    public Mat addNoise(Mat image, double noise) {
      Mat grayRnd = new Mat(image.rows(), image.cols(), image.type());
      grayRnd.setTo(new Scalar(noise / 2, noise / 2, noise / 2));
      Core.subtract(image, grayRnd, image);
      Core.randu(grayRnd, 0, noise);
      Core.add(image, grayRnd, image);
      return image;
      
    }




}