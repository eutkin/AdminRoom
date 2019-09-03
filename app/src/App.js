import React, { Component } from 'react';
import './App.css';
import Home from './component/Home';
import {
  BrowserRouter as Router,
  Route,
  Switch
} from 'react-router-dom';
import Redirects from './component/Redirects';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/redirects' exact={true} component={Redirects}/>
          </Switch>
        </Router>
    )
  }
}

export default App;
