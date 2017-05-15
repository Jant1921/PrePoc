import React, { PropTypes } from 'react';

import withStyles from 'isomorphic-style-loader/lib/withStyles';
import { defineMessages, FormattedMessage } from 'react-intl';
import styles from './Dashboard.css';
import {IMAGE_SERVER_URL} from '../../constants/';

const messages = defineMessages({
  categoriesLabel: {
    id: 'Dashboard.categories',
    defaultMessage: 'CATEGORIES',
    description: 'Dashboard category column title header',
  }
});



class Dashboard extends React.Component {
  
    constructor(props) {
        super(props);
        this.state = {
            showGaussOptions: false,
            showBilateralOptions: false,
        };
    } 

    handleGaussChange(){
        this.setState({showGaussOptions:!this.state.showGaussOptions});
    }

    handleBilateralChange(){
        this.setState({showBilateralOptions:!this.state.showBilateralOptions});
    }

    renderGaussOptions(){
            if(this.state.showGaussOptions){
                return(
                    <div className={styles.optionsDiv}>       
                        <p>Tamaño del Kernel </p>
                        <label className={styles.optionsDiv}>
                            <input type="number" name="gaussParams"/>
                            <span>filas x</span>
                            <input type="number" name="gaussParams"/>
                            <span>columnas</span>
                        </label>
                
                        <p>Desviación</p>
                        <label className={styles.optionsDiv}>
                            <input type="text" name="gaussParams" />
                            <span>  Ejemplo: 5.25</span>
                        </label>
                    </div>
                );
            }else{
                return(
                    null
                );
            }
    }

    renderBilateralOptions(){
            if(this.state.showBilateralOptions){
                return(
                    <div className={styles.optionsDiv}>       
                        <p>Tamaño del Kernel </p>
                        <label className={styles.optionsDiv}>
                            <input type="number" name="bilateralParams"/>
                            <span>filas x</span>
                            <input type="number" name="bilateralParams"/>
                            <span>columnas</span>
                        </label>
                
                        <p>Desviacion del dominio de la intensidad</p>
                        <label className={styles.optionsDiv}>
                            <input type="text" name="bilateralParams" />
                            <span>  Ejemplo: 5.25</span>
                        </label>
                        <p>Desviación del dominio del espacio</p>
                        <label className={styles.optionsDiv}>
                            <input type="text" name="bilateralParams" />
                            <span>  Ejemplo: 5.25</span>
                        </label>
                    </div>
                );
            }else{
                return(
                    null
                );
            }
    }

    render() {
        return(
            <div className={styles.home__container}>
                <h1> Prepocesamiento de Imágenes </h1>
                    <form action={IMAGE_SERVER_URL+"/sendfiles"} method="post" encType="multipart/form-data">

                        <h2>Seleccione las imágenes que desea procesar</h2>
                        <input type="file" name="images[]" multiple/><br/>
                        <br/>
                        <div>
                            <span> Nivel de Ruido: </span>
                            <input type="number" name="noise"/> 
                        </div>
                        <h2>Seleccione los filtros que desea aplicar a las imágenes</h2>
                        <div>
                            <input type="checkbox" name="Gauss" onChange={()=>this.handleGaussChange()}/> Filtro Gaussiano<br/>
                            {this.renderGaussOptions()}
                        </div>
                        <div>
                            <input type="checkbox" name="Bilateral" onChange={()=>this.handleBilateralChange()}/> Filtro Bilateral<br/>
                            {this.renderBilateralOptions()}
                        </div>
                        <div>
                            <input type="checkbox" name="Clahe"/> 
                            <span> Clahe</span>
                        </div>
                        <input type="submit" value="Procesar Imágenes"/>

                    </form>
                            
            </div>
        );
    } 

}


export default withStyles(styles)(Dashboard);