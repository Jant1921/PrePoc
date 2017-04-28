# Preprocesamiento de Imgenes #

## Requirements ##
* NodeJS
* NPM
* Firebase tools
* [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)

## Instructions to run the app
1. Run command "$ npm install"
2. Run command "$ npm start"


## React Starter Kit — "[isomorphic](http://nerds.airbnb.com/isomorphic-javascript-future-web-apps/)" web app boilerplate

> [React Starter Kit](https://www.reactstarterkit.com) is an opinionated
> boilerplate for web development built on top of [Node.js](https://nodejs.org/),
> [Express](http://expressjs.com/), [GraphQL](http://graphql.org/) and
> [React](https://facebook.github.io/react/), containing modern web development
> tools such as [Webpack](http://webpack.github.io/), [Babel](http://babeljs.io/)
> and [Browsersync](http://www.browsersync.io/). Helping you to stay productive
> following the best practices. A solid starting point for both professionals
> and newcomers to the industry.

**See** [getting started](./docs/getting-started.md) guide,
[demo](http://demo.reactstarterkit.com), [docs](https://github.com/kriasoft/react-starter-kit/tree/master/docs),
[to-do list](https://waffle.io/kriasoft/react-starter-kit) &nbsp;|&nbsp;
**Join** [#react-starter-kit](https://gitter.im/kriasoft/react-starter-kit) chat room to stay up to date &nbsp;|&nbsp;
**Visit our sponsors**:<br><br>

[![Rollbar - Full-stack error tracking for all apps in any language](https://dl.dropboxusercontent.com/u/16006521/react-starter-kit/rollbar.png)](https://rollbar.com/?utm_source=reactstartkit(github)&utm_medium=link&utm_campaign=reactstartkit(github)) &nbsp;&nbsp;
[![Localize - Translate your web app in minutes](https://dl.dropboxusercontent.com/u/16006521/react-starter-kit/localize.png)](https://localizejs.com/?cid=802&utm_source=rsk)


### Getting Started

  * Follow the [getting started guide](./docs/getting-started.md) to download and run the project
    ([node](https://nodejs.org/) >= 5,
    **[node-gyp](https://github.com/nodejs/node-gyp#readme)**
    and **[prerequisites](https://github.com/nodejs/node-gyp#installation)**)
  * Check the [code recipes](./docs/recipes) used in this boilerplate, or share yours


### Customization

The `master` branch of React Starter Kit doesn't include a Flux implementation or any other
advanced integrations. Nevertheless, we have some integrations available to you in *feature*
branches that you can use either as a reference or merge into your project:

  * [feature/redux](https://github.com/kriasoft/react-starter-kit/tree/feature/redux) — isomorphic
    Redux by [Pavel Lang](https://github.com/langpavel)
    (see [how to integrate Redux](./docs/recipes/how-to-integrate-redux.md)) (based on `master`)
  * [feature/react-intl](https://github.com/kriasoft/react-starter-kit/tree/feature/react-intl) —
    isomorphic Redux and React Intl by [Pavel Lang](https://github.com/langpavel)
    (see [how to integrate React Intl](./docs/recipes/how-to-integrate-react-intl.md)) (based on `feature/redux`)
  * [feature/bootstrap3](https://github.com/kriasoft/react-starter-kit/tree/feature/bootstrap3) —
    Simplest possible integration of [react-bootstrap](https://react-bootstrap.github.io/)
    by [Pavel Lang](https://github.com/langpavel) (based on `master`)

If you think that any of these features should be on `master`, or vice versa, some features should
removed from the `master` branch, please [let us know](https://gitter.im/kriasoft/react-starter-kit).
We love your feedback!


### Comparison

<table width="100%">

<tbody>

<tr>

<th> </th>

<th>

React Starter Kit

[![](https://img.shields.io/github/stars/kriasoft/react-starter-kit.svg?style=social&label=~react-starter-kit)](https://github.com/kriasoft/react-starter-kit) [![](https://img.shields.io/twitter/follow/ReactStarter.svg?style=social&label=@ReactStarter)](https://twitter.com/ReactStarter)</th>

<th>

React Static Boilerplate

[![](https://img.shields.io/github/stars/kriasoft/react-static-boilerplate.svg?style=social&label=~react-static-boilerplate)](https://github.com/kriasoft/react-static-boilerplate) [![](https://img.shields.io/twitter/follow/ReactStatic.svg?style=social&label=@ReactStatic)](https://twitter.com/ReactStatic)</th>

<th>

ASP.NET Core Starter Kit

[![](https://img.shields.io/github/stars/kriasoft/aspnet-starter-kit.svg?style=social&label=~aspnet-starter-kit)](https://github.com/kriasoft/aspnet-starter-kit) [![](https://img.shields.io/twitter/follow/dotnetreact.svg?style=social&label=@dotnetreact)](https://twitter.com/dotnetreact)</th>

</tr>

<tr>

<th align="right">App type</th>

<td align="center">[Isomorphic](http://nerds.airbnb.com/isomorphic-javascript-future-web-apps/) (universal)</td>

<td align="center">[Single-page application](https://en.wikipedia.org/wiki/Single-page_application)</td>

<td align="center">[Single-page application](https://en.wikipedia.org/wiki/Single-page_application)</td>

</tr>

<tr>

<th colspan="4">Frontend</th>

</tr>

<tr>

<th align="right">Language</th>

<td align="center">JavaScript (ES2015+, JSX)</td>

<td align="center">JavaScript (ES2015+, JSX)</td>

<td align="center">JavaScript (ES2015+, JSX)</td>

</tr>

<tr>

<th align="right">Libraries</th>

<td align="center">[React](https://github.com/facebook/react), [History](https://github.com/ReactJSTraining/history), [Universal Router](https://github.com/kriasoft/universal-router)</td>

<td align="center">[React](https://github.com/facebook/react), [History](https://github.com/ReactJSTraining/history), [Redux](https://github.com/reactjs/redux)</td>

<td align="center">[React](https://github.com/facebook/react), [History](https://github.com/ReactJSTraining/history), [Redux](https://github.com/reactjs/redux)</td>

</tr>

<tr>

<th align="right">Routes</th>

<td align="center">Imperative (functional)</td>

<td align="center">Declarative</td>

<td align="center">Declarative, cross-stack</td>

</tr>

<tr>

<th colspan="4">Backend</th>

</tr>

<tr>

<th align="right">Language</th>

<td align="center">JavaScript (ES2015+, JSX)</td>

<td align="center">n/a</td>

<td align="center">C#, F#</td>

</tr>

<tr>

<th align="right">Libraries</th>

<td align="center">[Node.js](https://nodejs.org), [Express](http://expressjs.com/), [Sequelize](http://docs.sequelizejs.com/en/latest/),  
[GraphQL](https://github.com/graphql/graphql-js)</td>

<td align="center">n/a</td>

<td align="center">[ASP.NET Core](https://docs.asp.net/en/latest/), [EF Core](https://ef.readthedocs.io/en/latest/),  
[ASP.NET Identity](https://docs.asp.net/en/latest/security/authentication/identity.html)</td>

</tr>

<tr>

<th align="right">[SSR](https://www.quora.com/What-are-the-tradeoffs-of-client-side-rendering-vs-server-side-rendering)</th>

<td align="center">Yes</td>

<td align="center">n/a</td>

<td align="center">n/a</td>

</tr>

<tr>

<th align="right">Data API</th>

<td align="center">[GraphQL](http://graphql.org/)</td>

<td align="center">n/a</td>

<td align="center">[Web API](https://docs.asp.net/en/latest/tutorials/first-web-api.html)</td>

</tr>

</tbody>

</table>


### Backers

♥ React Starter Kit? Help us keep it alive by donating funds to cover project
expenses via [OpenCollective](https://opencollective.com/react-starter-kit) or
[Bountysource](https://salt.bountysource.com/teams/react-starter-kit)!

<a href="http://www.nekst.me/" target="_blank" title="lehneres">
  <img src="https://github.com/lehneres.png?size=64" width="64" height="64" alt="lehneres">
</a>
<a href="http://www.vidpanel.com/" target="_blank" title="Tarkan Anlar">
  <img src="https://github.com/tarkanlar.png?size=64" width="64" height="64" alt="Tarkan Anlar">
</a>
<a href="https://morten.olsen.io/" target="_blank" title="Morten Olsen">
  <img src="https://github.com/mortenolsendk.png?size=64" width="64" height="64" alt="Morten Olsen">
</a>
<a href="https://twitter.com/adamthomann" target="_blank" title="Adam">
  <img src="https://github.com/athomann.png?size=64" width="64" height="64" alt="Adam">
</a>
<a href="http://dsernst.com/" target="_blank" title="David Ernst">
  <img src="https://github.com/dsernst.png?size=64" width="64" height="64" alt="David Ernst">
</a>
<a href="http://zanehitchcox.com/" target="_blank" title="Zane Hitchcox">
  <img src="https://github.com/zwhitchcox.png?size=64" width="64" height="64" alt="Zane Hitchcox">
</a>
<a href="https://opencollective.com/react-starter-kit" target="_blank">
  <img src="https://opencollective.com/static/images/become_backer.svg" width="64" height="64" alt="">
</a>


### How to Contribute

Anyone and everyone is welcome to [contribute](CONTRIBUTING.md) to this project. The best way to
start is by checking our [open issues](https://github.com/kriasoft/react-starter-kit/issues),
[submit a new issues](https://github.com/kriasoft/react-starter-kit/issues/new?labels=bug) or
[feature request](https://github.com/kriasoft/react-starter-kit/issues/new?labels=enhancement),
participate in discussions, upvote or downvote the issues you like or dislike, send [pull
requests](CONTRIBUTING.md#pull-requests).


### Learn More

  * [Getting Started with React.js](http://facebook.github.io/react/)
  * [Getting Started with GraphQL and Relay](https://quip.com/oLxzA1gTsJsE)
  * [React.js Questions on StackOverflow](http://stackoverflow.com/questions/tagged/reactjs)
  * [React.js Discussion Board](https://discuss.reactjs.org/)
  * [Flux Architecture for Building User Interfaces](http://facebook.github.io/flux/)
  * [Enzyme — JavaScript Testing utilities for React](http://airbnb.io/enzyme/)
  * [Flow — A static type checker for JavaScript](http://flowtype.org/)
  * [The Future of React](https://github.com/reactjs/react-future)
  * [Learn ES6](https://babeljs.io/docs/learn-es6/), [ES6 Features](https://github.com/lukehoban/es6features#readme)


### Related Projects

  * [Membership Database](https://github.com/membership/membership.db) — SQL schema boilerplate for user accounts, profiles, roles, and auth claims
  * [Babel Starter Kit](https://github.com/kriasoft/babel-starter-kit) — Boilerplate for authoring JavaScript/React.js libraries


### Support

  * [#react-starter-kit](http://stackoverflow.com/questions/tagged/react-starter-kit) on Stack Overflow — Questions and answers
  * [#react-starter-kit](https://gitter.im/kriasoft/react-starter-kit) on Gitter — Watch announcements, share ideas and feedback
  * [GitHub issues](https://github.com/kriasoft/react-starter-kit/issues), or [Scrum board](https://waffle.io/kriasoft/react-starter-kit) — File issues, send feature requests
  * [appear.in/react](https://appear.in/react) — Open hours! Exchange ideas and experiences (React, GraphQL, startups and pet projects)
  * [@koistya](https://twitter.com/koistya) on [Codementor](https://www.codementor.io/koistya), or [Skype](http://hatscripts.com/addskype?koistya) — Private consulting

### License

Copyright © 2014-2016 Kriasoft, LLC. This source code is licensed under the MIT
license found in the [LICENSE.txt](https://github.com/kriasoft/react-starter-kit/blob/master/LICENSE.txt)
file. The documentation to the project is licensed under the
[CC BY-SA 4.0](http://creativecommons.org/licenses/by-sa/4.0/) license.

---
Made with ♥ by Konstantin Tarkus ([@koistya](https://twitter.com/koistya)) and [contributors](https://github.com/kriasoft/react-starter-kit/graphs/contributors)
