import React, { PropTypes } from 'react';

import withStyles from 'isomorphic-style-loader/lib/withStyles';
import { defineMessages, FormattedMessage } from 'react-intl';
import styles from './Results.css';
import Link from '../../components/Link';
import {IMAGE_SERVER_URL} from '../../constants/';

const messages = defineMessages({
  categoriesLabel: {
    id: 'Results.categories',
    defaultMessage: 'CATEGORIES',
    description: 'Results category column title header',
  }
});

const imageVariantsList = {
    'Original':'/',
    'Escala de Grises':'/gris_',
    'Imagen con Ruido':'/noise_',
    'Filtro Bilateral':'/bilateral_',
    'Clahe':'/clahe_',
    'Clahe Gauss':'/claheGauss_',
    'Clahe Escala de Grises' : '/claheGris_',
    'Filtro Gaussiano':'/gauss_',
};


class Results extends React.Component {
  
  constructor(props) {
    super(props);
    this.state = {
      customFieldEmpty: false,
      imgTags: [],
      testId: '',
      serverMsg: 'nada :('
    };

  } 

  componentDidMount() {
      this.getImages();
  }

  /**
   * Retrieved from 
   * http://www.netlobo.com/url_query_string_javascript.html
   */
  gup( name ){
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    let regexS = "[\\?&]"+name+"=([^&#]*)";
    let regex = new RegExp( regexS );
    let results = regex.exec( window.location.href );
    if( results == null )
      return null;
    else
      return results[1];
  }

  removerComillas(pPalabra){
    let palabra = '';
    for(let letra in pPalabra){
      if(pPalabra[letra]==='\\'){
        palabra+='/';
      }else if(pPalabra[letra]!=='\"' && letra > 2){
        palabra+=pPalabra[letra];
      }
    }
    return palabra;
  }

  removerComillasNombre(pPalabra){
    let palabra = '';
    for(let letra in pPalabra){
      if(pPalabra[letra]!=='\"'){
        palabra+=pPalabra[letra];
      }
    }
    return palabra;
  }

    getImageVariants(pToken,pImgName){
        let imgVariants = [];
        Object.keys(imageVariantsList).forEach((variant)=>{
            let imageRoute = IMAGE_SERVER_URL+'/uploaded_images/'+pToken+imageVariantsList[variant]+pImgName;
            imgVariants.push((
                <a href={imageRoute} target="_blank">
                    <img className={styles.uploaded_image} src={imageRoute} />       
                    <p>{variant}</p>
                </a>
            ));
          //console.log(variant+'->'+imageVariantsList[variant]);
        });
        return imgVariants;
    }  
    getImageFromParams(){
        return (decodeURI(this.gup('images'))).split(',');
    }

    getTokenFromParams(){
        return this.gup('token');
    }

    getMetricasFromParams(){
        return this.gup('metricas');
    }

    getImageNameFromRoute(pRoute){
        let imgName = pRoute.split('\\');
        imgName = imgName[imgName.length-1];
        imgName = this.removerComillasNombre(imgName);
        return imgName;
    }

    getImages(){
        const images = this.getImageFromParams();
        const tokenID = this.getTokenFromParams();
        const metricas = this.getMetricasFromParams().split("+");
        this.setState({testId:tokenID}); //updates the testID
        let imgTags = images.map((route,key)=>{
            const imgName = this.getImageNameFromRoute(route);
            let metricasArray = (metricas[key].substring(1,metricas[key].length-1)).split(',');
            return (
                <div className={styles.resultContainer} key={key}>
                    <h2>Imagen: {imgName}</h2>
                    <div className={styles.imageVariants}>
                        {this.getImageVariants(tokenID,imgName)}
                    </div>
                    <h3> Valores de Métricas </h3>
                    <div>
                        <p> MSE: {metricasArray[0]}</p>
                        <p> AD: {metricasArray[1]}</p>
                        <p> MAE: {metricasArray[2]}</p>
                        <p> MAE: {metricasArray[3]}</p>
                        <p> PSNR: {metricasArray[4]}</p>

                    </div>
                </div>
            )
        }); 
        this.setState({imgTags:imgTags});
    }
   
    render() {
        return(
            <div className={styles.page_two__container}>
                <h1> Imágenes cargadas </h1>
                <p> Las imagenes han sido cargadas exitosamente, presione el boton para iniciar el procesamiento</p>
                <button onClick={()=>this.getImages()}>Cargar Resultado</button>
                
                <h2>Id de la prueba: {this.state.testId}</h2>
                <div className={styles.images_container}>
                    {this.state.imgTags}
                </div>

            
                <button>
                  <Link to={'/'}>Regresar a la página principal</Link>
                </button>
            </div>
        );
    }

}


export default withStyles(styles)(Results);