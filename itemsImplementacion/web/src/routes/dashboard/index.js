
import React from 'react';
import Dashboard from './Dashboard';

const title = 'Dashboard';

export default {

    path: '/',


    async action( ) {

     

        return {
            title,
            //chunk: 'dashboard',
            component: <Dashboard title={title} />,
        };
    },

};
