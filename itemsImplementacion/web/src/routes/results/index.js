
import React from 'react';
import Results from './Results';

const title = 'Results';

export default {

    path: '/results',


    async action( ) {

     

        return {
            title,
            //chunk: 'dashboard',
            component: <Results title={title} />,
        };
    },

};
