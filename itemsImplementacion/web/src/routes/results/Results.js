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

  getImages(){
    //console.log(((decodeURI(this.gup('images'))).split(','))[0]);
    const images = (decodeURI(this.gup('images'))).split(',');

    //const images = [];
    this.setState({testId:this.gup('token')});
    let tokenID = this.gup('token');
    let imgTags = images.map((route,key)=>{
      let imgName = route.split('\\');
      imgName = imgName[imgName.length-1];
      console.log(route);
      return (
        <div className={styles.resultContainer} key={key}>
          <h2>Imagen: {imgName}</h2>
          <div>
            <div>
              <p>Original</p>
              <img className={styles.uploaded_image} src={IMAGE_SERVER_URL+this.removerComillas(route)} />     
            </div>
            <div>
              <p>Clahe</p>
              <img className={styles.uploaded_image} src={IMAGE_SERVER_URL+'/uploaded_images/'+tokenID+'/clahe_'+this.removerComillasNombre(imgName)} />       
            </div>
            <div>
              <p>Filtro Gaussiano</p>
              <img className={styles.uploaded_image} src={IMAGE_SERVER_URL+'/uploaded_images/'+tokenID+'/gauss_'+this.removerComillasNombre(imgName)} />       
            </div>
            <div>
              <p>Escala de Grises</p>
              <img className={styles.uploaded_image} src={IMAGE_SERVER_URL+'/uploaded_images/'+tokenID+'/gris_'+this.removerComillasNombre(imgName)} />       
            </div>
            <div>
              <p>Imagen con Ruido</p>
              <img className={styles.uploaded_image} src={IMAGE_SERVER_URL+'/uploaded_images/'+tokenID+'/noise_'+this.removerComillasNombre(imgName)} />       
            </div>
            <div>
              <p>Filtro Bilateral</p>
              <img className={styles.uploaded_image} src={IMAGE_SERVER_URL+'/uploaded_images/'+tokenID+'/bilateral_'+this.removerComillasNombre(imgName)} />       
            </div>
          </div>
          <div>
            <p> Timestamp: {Date.now()}</p>
            <p> Info 2</p>
            <p> Info 3</p>
          </div>
        </div>
      )
    });
    this.setState({imgTags:imgTags});
  }
  
  llamarAJava(){
    // application/x-www-form-urlencoded

    let details = {
        usuario:'joseA',
        clave:'123355'
    };

    var formBody = [];
    for (var property in details) {
      var encodedKey = encodeURIComponent(property);
      var encodedValue = encodeURIComponent(details[property]);
      formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");


    fetch("http://localhost:8080/PreprocesamientoImagenes/IngresoUsuario",
        {
            method: "POST",
            headers: { 
                "Content-type": "application/x-www-form-urlencoded; charset=UTF-8"  
            },
            body: formBody
        })
        .then((res)=>{
            console.log(res.json());

          })
        .catch((error)=>{console.log(error);}) 
  }

  /**
 * Send a email when the user log in a diferent device
 * @param  {string} pEmail - user email
 
export function sendLoginEmail(pEmail, pSubject, pEmailBody){
    let emailData = {
        email:pEmail,
        subject:pSubject,
        text:pEmailBody
    };
    fetch("https://www.akurey.com/akurey/vaultemail",
        {
            method: "POST",
            headers: {  
                "Content-type": "application/json; charset=UTF-8"  
            },
            body: JSON.stringify(emailData)
        })
        .then((res)=>{
            console.log(res.json());

          })
        .catch((error)=>{console.log(error);})           
}
*/

  render() {
    
    return(
      <div className={styles.page_two__container}>
        <h1 > Imágenes cargadas </h1>
        <p> Las imagenes han sido cargadas exitosamente, presione el boton para iniciar el procesamiento</p>
        <button onClick={()=>this.getImages()}>Cargar Resultado</button>
        <button onClick={()=>this.llamarAJava()}>Cargar Mensaje de Tomcat</button>
        
        <h2>Mensaje de ApplicationServer: {this.state.serverMsg}</h2>
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