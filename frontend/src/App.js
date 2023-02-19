import React, { Component } from 'react';
import './App.css';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import StationList from './StationList';
import StationEdit from './StationEdit';
class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
          <Route path='/stations' exact={true} component={StationList}/>
            <Route path='/' exact={true} component={StationList}/>
            <Route path='/stations/:id' component={StationEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;