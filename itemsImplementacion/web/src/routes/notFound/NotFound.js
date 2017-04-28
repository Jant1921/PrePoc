/**
 * React Starter Kit (https://www.reactstarterkit.com/)
 *
 * Copyright © 2014-2016 Kriasoft, LLC. All rights reserved.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

import React, { PropTypes } from 'react';
import { defineMessages, FormattedMessage } from 'react-intl';
import withStyles from 'isomorphic-style-loader/lib/withStyles';
import s from './NotFound.css';

const messages = defineMessages({
  notFoundLabel: {
    id: 'notFound.notFoundMessage',
    defaultMessage: 'Sorry, the page you were trying to view does not exist.',
    description: 'Message to show in "Not Found" page',
  },
});

class NotFound extends React.Component {
  static propTypes = {
    title: PropTypes.string.isRequired,
  };

  render() {
    return (
      <div className={s.root}>
        <div className={s.container}>
          <h1>{this.props.title}</h1>
          <FormattedMessage {...messages.notFoundLabel}/>
        </div>
      </div>
    );
  }
}

export default withStyles(s)(NotFound);
