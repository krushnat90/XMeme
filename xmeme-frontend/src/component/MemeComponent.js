import React,{Component} from 'react';
import {BrowserRouter as Router, Route,Switch } from 'react-router-dom';
import { createHashHistory } from 'history'

import ShowMemesComponent from './ShowMemesComponent';
import AddMemeComponent from './AddMemeComponent';
// export const history = createHashHistory();
// This is the master component which will render and route through other components

class MemeComponent extends Component{

    constructor(props){
        super(props);
    }

    render(){
        return (
            <Router>
                <>
                    <center><h1> X-Meme </h1><h5>A meme sharing site.</h5></center>
                    <Switch>
                        <Route path="/" exact component={ShowMemesComponent} />
                    </Switch>
                </>
            </Router>
        );
    }
}
export default MemeComponent;