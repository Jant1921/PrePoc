/**
 * React Starter Kit (https://www.reactstarterkit.com/)
 *
 * Copyright © 2014-2016 Kriasoft, LLC. All rights reserved.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */

import path from 'path';
import gaze from 'gaze';
import { writeFile, copyFile, makeDir, copyDir, cleanDir } from './lib/fs';
import pkg from '../package.json';

/**
 * Copies static files such as robots.txt, favicon.ico to the
 * output (dist) folder.
 */
async function copy() {
  await makeDir('dist');
  await Promise.all([
    writeFile('dist/package.json', JSON.stringify({
      private: true,
      engines: pkg.engines,
      dependencies: pkg.dependencies,
      scripts: {
        start: 'node server.js',
      },
    }, null, 2)),
    copyFile('LICENSE.txt', 'dist/LICENSE.txt'),
    copyDir('src/content', 'dist/content'),
    copyDir('public', 'dist/public'),
    copyDir('src/messages', 'dist/messages'),
  ]);

  if (process.argv.includes('--watch')) {
    const watcher = await new Promise((resolve, reject) => {
      gaze([
        'src/content/**/*',
        'src/messages/**/*',
        'public/**/*',
      ], (err, val) => (err ? reject(err) : resolve(val)));
    });

    watcher.on('all', async (event, filePath) => {
      const src = path.relative('./', filePath);
      const dist = path.join('dist/', src.startsWith('src') ? path.relative('src', src) : src);
      switch (event) {
        case 'added':
        case 'renamed':
        case 'changed':
          if (filePath.endsWith('/')) return;
          await makeDir(path.dirname(dist));
          await copyFile(filePath, dist);
          break;
        case 'deleted':
          cleanDir(dist, { nosort: true, dot: true });
          break;
        default:
          return;
      }
      console.log(`[file ${event}] ${dist}`);
    });
  }
}

export default copy;
