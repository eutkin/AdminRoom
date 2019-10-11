import React, {Component} from 'react';
import './App.css';
import Home from './component/Home';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Redirects from './component/Redirects';
import RedirectEdit from "./component/RedirectEdit";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/redirects' exact={true} component={Redirects}/>
                    <Route path='/redirects/:path' component={RedirectEdit}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
