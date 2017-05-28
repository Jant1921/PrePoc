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

const VALID_INPUT_CHARACTERS = "0123456789";

class Dashboard extends React.Component {
  
    constructor(props) {
        super(props);
        this.state = {
            showGaussOptions: false,
            showBilateralOptions: false,
            inputError: [false,false,false,false,false,false,false,false],
        };
    } 

    checkInputState(pInputNumber){
        if(this.state.inputError[pInputNumber]){
            return styles.error_input;
        }else{
            return null;
        }
    }

    isSomethingWrong(){
        let inputStates = this.state.inputError;
        let lenght = inputStates.length;
        for (let i = 0; i < lenght; i++) {
            if(inputStates[i]){
                return true;
            }
        }
        return false;
    }


    handleInputChange(event){
        let inputValue = event.target.value;
        console.log(inputValue)
        let lenght = inputValue.length;
        let inputStates = this.state.inputError;
        if(!event.target.value){
            inputStates[event.target.id] = true;
            this.setState({inputError:inputStates});
            return;
        }
        let validCharacters = VALID_INPUT_CHARACTERS;
        if(event.target.type!=='number'){
            validCharacters = VALID_INPUT_CHARACTERS+'.';
        }
        for (let i = 0; i < lenght; i++) {
            if(!validCharacters.includes(inputValue[i])){
                inputStates[event.target.id] = true;
                this.setState({inputError:inputStates});
                return;
            }
        }

        inputStates[event.target.id] = false;
        this.setState({inputError:inputStates});
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
                        <p className={styles.option_title}>Tamaño del Kernel </p>
                        <label className={styles.optionsDiv}>
                            <input 
                                className={this.checkInputState(1)} 
                                type="number" 
                                defaultValue = "10"
                                name="gaussParams"
                                id = "1"
                                onChange={(event)=>this.handleInputChange(event)}
                                />
                            <span>filas x</span>
                            <input 
                                className={this.checkInputState(2)}
                                type="number"
                                defaultValue = "10"
                                name="gaussParams"
                                id = "2"
                                onChange={(event)=>this.handleInputChange(event)}
                                />
                            <span>columnas</span>
                        </label>
                
                        <p className={styles.option_title}>Desviación</p>
                        <label className={styles.optionsDiv}>
                            <input 
                                className={this.checkInputState(3)} 
                                type="text" 
                                defaultValue = "5"
                                name="gaussParams"
                                id = "3"
                                onChange={(event)=>this.handleInputChange(event)} 
                                />
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
                        <p className={styles.option_title}>Tamaño del Kernel </p>
                        <label className={styles.optionsDiv}>
                            <input 
                                className={this.checkInputState(4)} 
                                type="number" 
                                defaultValue = "10"
                                name="bilateralParams"
                                id = "4"
                                onChange={(event)=>this.handleInputChange(event)}
                                />
                            <span>filas x</span>
                            <input 
                                className={this.checkInputState(5)} 
                                type="number" 
                                defaultValue = "10"
                                name="bilateralParams"
                                id = "5"
                                onChange={(event)=>this.handleInputChange(event)}    
                                />
                            <span>columnas</span>
                        </label>
                
                        <p className={styles.option_title}>Desviación del dominio de la intensidad</p>
                        <label className={styles.optionsDiv}>
                            <input 
                                className={this.checkInputState(6)} 
                                type="text" 
                                defaultValue = "0.3"
                                name="bilateralParams" 
                                id = "6"
                                onChange={(event)=>this.handleInputChange(event)}
                                />
                            <span>  Ejemplo: 5.25</span>
                        </label>
                        <p className={styles.option_title}>Desviación del dominio del espacio</p>
                        <label className={styles.optionsDiv}>
                            <input 
                                className={this.checkInputState(7)} 
                                type="text" 
                                defaultValue = "10"
                                name="bilateralParams" 
                                id = "7"
                                onChange={(event)=>this.handleInputChange(event)}
                                />
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
                <div className={styles.center__container}>
                    <div className={styles.header_title}>
                        <h1> Prepocesamiento de Imágenes </h1>
                    </div>
                    <form action={IMAGE_SERVER_URL+"/sendfiles"} method="post" encType="multipart/form-data">

                        <h2>Seleccione las imágenes que desea procesar</h2>
                        <input type="file" name="images[]" multiple/><br/>
                        <br/>
                        <div>
                            <span> Nivel de Ruido: </span>
                            <input 
                                className={this.checkInputState(0)} 
                                type="number" 
                                defaultValue = "10"
                                name="noise"
                                id = "0"
                                onChange={(event)=>this.handleInputChange(event)} 
                                
                                /> 
                        </div>
                        <h2>Seleccione los filtros que desea aplicar a las imágenes</h2>
                        <div>
                            <input type="checkbox" name="Gauss" onChange={()=>this.handleGaussChange()}/>
                                <span className={styles.cb_style}>
                                    Filtro Gaussiano
                                </span>
                            {this.renderGaussOptions()}
                        </div>
                        <div>
                            <input type="checkbox" name="Bilateral" onChange={()=>this.handleBilateralChange()}/>
                                <span className={styles.cb_style}>
                                    Filtro Bilateral
                                </span>
                            {this.renderBilateralOptions()}
                        </div>
                        {
                            (this.isSomethingWrong())?(
                                <input type="submit" value="Procesar Imágenes" disabled/>
                            ):(
                                <input type="submit" value="Procesar Imágenes"/>
                            )
                        }

                    </form>
                    
                    {
                        (this.isSomethingWrong())?(
                            <p className={styles.error_message} >
                                *Algunos campos contienen valores no válidos*
                            </p>
                        ):(
                            null
                        )
                    }
                    
                </div>   
            </div>
        );
    } 

}


export default withStyles(styles)(Dashboard);