/**
 * React Starter Kit (https://www.reactstarterkit.com/)
 *
 * Copyright © 2014-2016 Kriasoft, LLC. All rights reserved.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

import React, { PropTypes } from 'react';
import withStyles from 'isomorphic-style-loader/lib/withStyles';
import s from './Layout.css';



class Layout extends React.Component {

    static propTypes = {
        children: PropTypes.node.isRequired,
    };

    render() {
        return (
            <div className={s.container}>

                    {this.props.children}

          </div>
        );
      }
}

export default withStyles(s)(Layout);
