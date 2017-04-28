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
      customFieldEmpty: false
    };

  } 

  loadImages(pEvent){
    let files = document.getElementById('files_input').value;
      if (files) {
          console.log(files);
          //console.log("Name: " + file.name + "\n" + "Last Modified Date :" + file.lastModifiedDate);
      }else{
        console.log('no file');
      }
  }
  

  render() {
    return(
      <div className={styles.home__container}>
        <h1> Prepocesamiento de Imágenes </h1>
        <div>
          <h2>Por favor seleccione las imágenes que desea procesar</h2>
          <br/>
          <label>
            Parametro 1: &nbsp; 
            <input type="text" name="param1"/>
          </label>
          <br />
          <label>
            Parametro 2: &nbsp; 
            <input type="text" name="param2"/>
          </label>
          <br/>
          <br/>
          <form action="http://192.168.43.129:3001/sendfiles" method="post" encType="multipart/form-data">
            <input type="file" name="images[]" multiple/><br/>
            <br/>
            
            <input type="submit" name="submit"/>
            
          </form>
        </div>

        <br/>
        
        {/*<Link className="link_to_page_two" to={'/page_two'}>Go to page 2</Link>*/}
      </div>
    );
  }

}


export default withStyles(styles)(Dashboard);