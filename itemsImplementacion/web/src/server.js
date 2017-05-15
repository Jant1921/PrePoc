/**
 * React Starter Kit (https://www.reactstarterkit.com/)
 *
 * Copyright © 2014-2016 Kriasoft, LLC. All rights reserved.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

import 'babel-polyfill';
import path from 'path';
import express from 'express';
import cookieParser from 'cookie-parser';
import requestLanguage from 'express-request-language';
import bodyParser from 'body-parser';
import expressJwt from 'express-jwt';
import expressGraphQL from 'express-graphql';
import jwt from 'jsonwebtoken';
import React from 'react';
import ReactDOM from 'react-dom/server';
import UniversalRouter from 'universal-router';
import PrettyError from 'pretty-error';
import { IntlProvider } from 'react-intl';
import createBrowserHistory from 'history/createBrowserHistory';

import './serverIntlPolyfill';
import App from './components/App';
import Html from './components/Html';
import { ErrorPageWithoutStyle } from './routes/error/ErrorPage';
import errorPageStyle from './routes/error/ErrorPage.css';
import passport from './core/passport';
import models from './data/models';
import schema from './data/schema';
import routes from './routes';
import assets from './assets'; // eslint-disable-line import/no-unresolved
import configureStore from './store/configureStore';
import { setRuntimeVariable } from './actions/runtime';
import { setLocale } from './actions/intl';
import { port, auth, locales, currentEnvironment } from './config';
import {APP_SERVER_URL} from './constants/';

const multer = require('multer');
const fs = require('fs');
const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
const tokenLength = 4;
let testName = 'test001';
let uploadedImagesRoute = []
const proxy = require('express-http-proxy');
const app = express();

//
// Tell any CSS tooling (such as Material UI) to use all vendor prefixes if the
// user agent is not known.
// -----------------------------------------------------------------------------
global.navigator = global.navigator || {};
global.navigator.userAgent = global.navigator.userAgent || 'all';

//
// Register Node.js middleware
// -----------------------------------------------------------------------------
app.use(express.static(path.join(__dirname, 'public')));
app.use(cookieParser());
app.use(requestLanguage({
  languages: locales,
  queryName: 'lang',
  cookie: {
    name: 'lang',
    options: {
      path: '/',
      maxAge: 3650 * 24 * 3600 * 1000, // 10 years in miliseconds
    },
    url: '/lang/{language}',
  },
}));
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

//
// Authentication
// -----------------------------------------------------------------------------
app.use(expressJwt({
  secret: auth.jwt.secret,
  credentialsRequired: false,
  getToken: req => req.cookies.id_token,
}));
app.use(passport.initialize());

if (process.env.NODE_ENV !== 'production') {
  app.enable('trust proxy');
}


//
// Register API middleware
// -----------------------------------------------------------------------------
app.use('/graphql', expressGraphQL(req => ({
  schema,
  graphiql: process.env.NODE_ENV !== 'production',
  rootValue: { request: req },
  pretty: process.env.NODE_ENV !== 'production',
})));

  /**
     * Landing page route validation
  */
 app.enable('trust proxy');

/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/*
 
app.use('/proxy', proxy('localhost:8080/PreprocesamientoImagenes/IngresoUsuario', {
  filter: function(req, res) {
     return req.method == 'POST';
  }
}));

*/
function removerComillasNombre(pPalabra){
    let palabra = '';
    for(let letra in pPalabra){
        if(pPalabra[letra]!=='\"'){
            palabra+=pPalabra[letra];
        }
    }
    return palabra;
}


/**
 * Generates a random token
 * @param  {integer} pLength - token length
 * @return {string} generatedToken - a new random token
 */
function generateRandomToken(pLength){
    let generatedToken = '';
    let selectedChar = '';
    for(let i=0;i<pLength;i++){
        selectedChar = Math.floor(Math.random() * (chars.length));
        generatedToken += chars.charAt(selectedChar);
    }
    return generatedToken;
};

testName = generateRandomToken(tokenLength);

app.get('/uploaded_images/*', function (req, res) {
    res.sendFile(path.join(__dirname +'/../..'+decodeURI(req.url)));
  });


const storage = multer.diskStorage({
  destination: function(req, file, cb){
    const dir = '../uploaded_images/'+testName;
    if (!fs.existsSync(dir)){
      fs.mkdirSync(dir);
    }
    cb(null,dir)
  },
  filename: function(req, file, cb){
    cb(null, file.originalname);
  }
});

const upload = multer({storage:storage});

app.post('/sendfiles',upload.any(),function(req,res){
    const lastUsedToken = testName;
    testName = generateRandomToken(tokenLength);
    let redirectRoute = APP_SERVER_URL+'/Prepro/AppServer?token='+lastUsedToken;
    
    uploadedImagesRoute = req.files.map(function(image){
        return '"'+image.path+'"';
    });

    if(uploadedImagesRoute!=''){
        redirectRoute += '&images='+encodeURI( uploadedImagesRoute );
    }
    if(req.body.gaussParams){
        let gauss = JSON.stringify(req.body.gaussParams);
        gauss = removerComillasNombre(gauss.substring(1,gauss.length-1));
        redirectRoute += '&gauss='+gauss;
    }
    if(req.body.bilateralParams){
        let bilateral = JSON.stringify(req.body.bilateralParams);
        bilateral = removerComillasNombre(bilateral.substring(1,bilateral.length-1));
        redirectRoute += '&bilateral='+bilateral;
    }
    if(req.body.clahe){
        redirectRoute += '&clahe=true';
    }
    if(req.body.noise){
        let noise = JSON.stringify(req.body.noise);
        noise = removerComillasNombre(noise.substring(1,noise.length-1));
        redirectRoute += '&noise='+noise;
    }
    res.redirect(redirectRoute);
},upload.any());


/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/
/***************/




//
// Register server-side rendering middleware
// -----------------------------------------------------------------------------
app.get('*', async (req, res, next) => {
  try {
    if(req.secure || currentEnvironment === 'local'){

      const store = configureStore({
        user: req.user || null,
      }, {
        cookie: req.headers.cookie,
      });

      store.dispatch(setRuntimeVariable({
        name: 'initialNow',
        value: Date.now(),
      }));

      store.dispatch(setRuntimeVariable({
        name: 'availableLocales',
        value: locales,
      }));

      const locale = req.language;
      await store.dispatch(setLocale({
        locale,
      }));

      const css = new Set();

      // Global (context) variables that can be easily accessed from any React component
      // https://facebook.github.io/react/docs/context.html
      const context = {
        // Enables critical path CSS rendering
        // https://github.com/kriasoft/isomorphic-style-loader
        insertCss: (...styles) => {
          // eslint-disable-next-line no-underscore-dangle
          styles.forEach(style => css.add(style._getCss()));
        },
        // Initialize a new Redux store
        // http://redux.js.org/docs/basics/UsageWithReact.html
        store,
      };

      const route = await UniversalRouter.resolve(routes, {
        ...context,
        path: req.path,
        query: req.query,
        locale,
      });

      if (route.redirect) {
        res.redirect(route.status || 302, route.redirect);
        return;
      }

      const data = { ...route };
      data.children = ReactDOM.renderToString(<App context={context}>{route.component}</App>);
      data.style = [...css].join('');
      data.scripts = [
        assets.vendor.js,
        assets.client.js,
      ];
      if (assets[route.chunk]) {
        data.scripts.push(assets[route.chunk].js);
      }
      data.state = context.store.getState();
      data.lang = locale;

      const html = ReactDOM.renderToStaticMarkup(<Html {...data} />);
      res.status(route.status || 200);
      res.send(`<!doctype html>${html}`);
      
    }else{
      res.redirect('https://' + req.headers.host + req.url);
    }
  } catch (err) {
    next(err);
  }
});

//
// Error handling
// -----------------------------------------------------------------------------
const pe = new PrettyError();
pe.skipNodeFiles();
pe.skipPackage('express');

app.use((err, req, res, next) => { // eslint-disable-line no-unused-vars
  console.log(pe.render(err)); // eslint-disable-line no-console
  const locale = req.language;
  const html = ReactDOM.renderToStaticMarkup(
    <Html
      title="Internal Server Error"
      description={err.message}
      style={errorPageStyle._getCss()} // eslint-disable-line no-underscore-dangle
      lang={locale}
    >
      {ReactDOM.renderToString(
        <IntlProvider locale={locale}>
          <ErrorPageWithoutStyle error={err} />
        </IntlProvider>,
      )}
    </Html>,
  );
  res.status(err.status || 500);
  res.send(`<!doctype html>${html}`);
});

//
// Launch the server
// -----------------------------------------------------------------------------
/* eslint-disable no-console */
models.sync().catch(err => console.error(err.stack)).then(() => {
  app.listen(port, () => {
    console.log(`The server is running at http://localhost:${port}/`);
  });
});
/* eslint-enable no-console */
