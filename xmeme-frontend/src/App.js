import React,{Component} from 'react';
import './App.css';
import MemeComponent from './component/MemeComponent';
import ShowMemesComponent from './component/ShowMemesComponent';

class App extends Component {
  render() {
    return (
      <div className="container-fluid">
        <MemeComponent />
      </div>
    );
  }
}

export default App;
