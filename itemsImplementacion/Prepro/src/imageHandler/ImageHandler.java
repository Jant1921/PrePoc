package imageHandler;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageHandler {

  private String dir = "F:\\eclipseworkspace\\workspace\\OpenCV Testing\\";
  private String imgname = "prueba2.tif";




  /**
   * guarda la matriz como una imagen
   * @param img matriz que contiene la informacion de los pixeles de la imagen
   * @param dir string referente a la direccion del folder
   * @param imgname string referente al nombre de la imagen
  */

    public void guardarimg(Mat img, String dir, String imgname) {
      Imgcodecs.imwrite(dir + imgname, img);
    }

  /**
   * guarda la matriz como una imagen
   * @param img matriz que contiene la informacion de los pixeles de la imagen
   */
    public void guardarimg(Mat img) {
      Imgcodecs.imwrite(dir + imgname, img);
    }

  /**
   * genera la matriz de la imagen ubicada segun el folder y nombre
   * @param dir string referente a la direccion del folder
   * @param imgname string referente al nombre de la imagen
   * @return la matriz referente a la imagen cargada
   */
    public Mat cargarimg(String dir,String imgname) {
      Mat img = Imgcodecs.imread(dir + imgname);
      return img;
    }

  /**
   * genera la matriz de la imagen ubicada segun el folder y nombre
   * @return la matriz referente a la imagen cargada
   */
    public Mat cargarimg() {
      Mat img = Imgcodecs.imread(dir + imgname,0);
      return img;
    }

  /**
   * Aplica Contrast Limited Adaptive Histogram Equalization a la matriz referente a una imagen
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
    public Mat imgtograyscale(Mat img) {
      Mat resultado = new Mat();
      Imgproc.cvtColor(img, resultado, Imgproc.COLOR_RGB2GRAY);
      return resultado;

    }



  /**
   * calcula el  mean squared error
   * @param img  Mat de la imagen original
   * @param ruido Mat de la imagen con ruido
   * @return el mse calculado en base a las 2 imagenes
   */
    public double getmse(Mat img, Mat ruido) {
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
   * @param mse es el mean quared error
   * @return un double con el valor del psnr
   */
    public double getpsnr(double mse) {
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
    public double getmae(Mat img, Mat ruido) { //mean absolute error
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
    public double getad(Mat img, Mat ruido) { 
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
    public double getae(Mat img, Mat ruido) { //absolute error
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
      return imgname;
    }

    /**
   * Setea el valor del nombre de la imagen a crear
   * @param imgname string para setear el nombre del archivo a crear
   */
    public void setImgname(String imgname) {
      this.imgname = imgname;
    }
    
    /**
   * 
   * @param img matriz con los datos de imagen    
   * @param ksizer filas del kernel
   * @param ksizec columnas del kernel
   * @param desv desviacion estandar
   * @return matriz con el filtro aplicado
   */
    public Mat filtrogaus(Mat img,int ksizer,int ksizec,double desv) {
      Mat result = img;
     
      int w;
      int h;
      double valormat;
      w = img.width();
      h = img.height();

      for (int i = 0; i < h;i++) {
        for (int j = 0; j < w;j++) {

          valormat = img.get(i, j)[0];
          result = gaussauxiliar(i,j,ksizer,ksizec,desv,valormat,result);

        }
      }

      return result;
      
    }

    /**
     * 
     * @param x valor de referencia para el kernel
     * @param y valor de referencia para el kernel
     * @param ksizer filas del kernel
     * @param ksizec columnas del kernel
     * @param desv desviacion estandar
     * @param valormat valor en el pixel actual
     * @param img matriz de imagen
     * @return imagen con el kernel aplicado para el pixel
     */
    public Mat gaussauxiliar(int x, int y, int ksizer,int ksizec,double desv,
                             double valormat,Mat img) {

      int umin = (x + 1) - ((ksizer - 1) / 2);
      int umax = (x + 1) + ((ksizer - 1) / 2);
      int vmax = (y + 1) + ((ksizec - 1) / 2);
      int vmin = (y + 1) - ((ksizec - 1) / 2);
      double[] val1 = new double[1];
      Mat resultado = img;
      double suma = 0;
      double alfa = 0;

      for (int i = umin;i < umax;i++) {
        for (int j = vmin;j < vmax;j++) {

          if (x - i < 0 || y - j < 0 || i < 0 || j < 0) {
            suma += 0;
          } else {
            double kernelval = getkernel(x - i,y - j,desv);
            alfa += kernelval;
            val1[0] = img.get(i,j)[0];
            suma += kernelval * val1[0];
          }


        }
      }

      double[] data = img.get(x, y);

      data[0] = suma * (1 / alfa);


      resultado.put(x,y,data);

      return resultado;
    }

  /**
   * 
   * @param x punto de referencia
   * @param y punto de referencia
   * @param desv desviacion estandar
   * @return valor del kernel en el punto especifico
   */
    public double getkernel(int x,int y,double desv) {
      double val = Math.exp(- (((x * x) + (y * y)) / (2 * (desv * desv))));
      return val;
    }
    
    
    
  /**
   * 
   * @param matriz matriz de imagen
   * @param h height
   * @param w weight
   * @return entero con el valor de alfa
   */
    public int getalfa(Mat matriz,int h, int w) {
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
   * 
   * @param img matriz de imagen
   * @param ksizer filas del kernel
   * @param ksizec columnas del kernel
   * @param desv desviacion del dominio de la intensidad
     * @param desvr desviacion del dominio del espacio
   * @return matriz con filtro bilateral aplicado
   */
    public Mat filtrobilateral(Mat img,int ksizer,int ksizec,double desv,double desvr) {
      Mat result = img;
    
      int w;
      int h;
     
      double valormat;
      w = img.width();
      h = img.height();
      
      for (int i = 0; i < h;i++) {
        for (int j = 0; j < w;j++) {

          valormat = img.get(i, j)[0];
          result = bilateralauxiliar(i,j,ksizer,ksizec,desv,desvr,valormat,result);

        }
      }


      return result;

    }

  /**
   *   
   * @param x  valor de referencia para el kernel
   * @param y  valor de referencia para el kernel
   * @param ksizer filas del kernel
   * @param ksizec columnas del kernel
   * @param desv desviacion del dominio de la intensidad
     * @param desvr desviacion del dominio del espacio
   * @param valormat valor en el punto actual de la matriz imagen
   * @param img matriz imagen
   * @return matriz con el kernel aplicado al pixel actual
   */
    public Mat bilateralauxiliar(int x, int y, int ksizer,int ksizec,
                                 double desv,double desvr,double valormat,Mat img) {

      int umin = (x + 1) - ((ksizer - 1) / 2);
      int umax = (x + 1) + ((ksizer - 1) / 2);
      int vmax = (y + 1) + ((ksizec - 1) / 2);
      int vmin = (y + 1) - ((ksizec - 1) / 2);
      double[] val1 = new double[1];
      Mat resultado = img;
      double suma = 0;
      double alfa = 0;

      for (int i = umin; i < umax;i++) {
        for (int j = vmin; j < vmax;j++) {

          if (x - i < 0 || y - j < 0 || i < 0 || j < 0) {
            suma += 0;
          } else {
            val1[0] = img.get(i,j)[0];
            double kernelval = getkernelbilateral(x - i,y - j,desv,desvr,valormat,val1[0]);
            alfa += kernelval;
            suma += kernelval * val1[0];
          }
          
          
        }
      }

      double[] data = img.get(x, y);

      data[0] = suma / alfa;


      resultado.put(x,y,data);
      return resultado;
    }


    /**
     * 
     * @param x valor de referencia para el kernel
     * @param y valor de referencia para el kernel
     * @param desv desviacion del dominio de la intensidad
     * @param desvr desviacion del dominio del espacio
     * @param valpixel valor del pixel actual
     * @param pixelrelatkernel valor del pixel en la matriz actual del kernel
     * @return valor del kernel en el punto
     */
    public double getkernelbilateral(int x,int y,double desv,double desvr,
                                     double valpixel,double pixelrelatkernel) {
    

      //double val = Math.pow(Math.E, -1 * ((x * x + y * y) / (2 * desvr * desvr))) 
      //  * Math.pow(Math.E , -1 * (Math.pow((pixelrelatkernel - valpixel), 2) / (2 * desv * desv))); 

      double val = Math.pow(Math.E, (-1 * ((x * x + y * y) / (2 * desv * desv)) 
          -  (Math.pow((pixelrelatkernel - valpixel), 2) / (2 * desvr * desvr)))); 
       
      
      return val;
    }

    /**
     * 
     * @param image matriz de imagen
     * @param noise double con el factor de ruido
     * @return matriz con ruido
     */
    public Mat addnoise(Mat image, double noise) {
      Mat grayRnd = new Mat(image.rows(), image.cols(), image.type());
      grayRnd.setTo(new Scalar(noise / 2, noise / 2, noise / 2));
      Core.subtract(image, grayRnd, image);
      Core.randu(grayRnd, 0, noise);
      Core.add(image, grayRnd, image);
      return image;
      
    }




}