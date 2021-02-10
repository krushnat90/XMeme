import React,{Component} from 'react';
import DataAccessService from '../service/DataAccessService';
import AddMemeComponent from './AddMemeComponent';

// This component will retrieve latest memes and render on the webpage

class ShowMemesComponent extends Component{

    constructor(props){
        super(props);
        this.state = {
            memes: [],
            message: null
        }
        this.loadLatestMemes = this.loadLatestMemes.bind(this);
    }

    componentDidMount() {
        this.loadLatestMemes();
    }

    loadLatestMemes(){
        DataAccessService.getLatestMemes().then(
            resp => {
                this.setState({memes:resp.data})
            }
        )
    }

    render() {
        return (
            <div>
                <AddMemeComponent parentMethod={this.loadLatestMemes}/>
                <hr/>
                <center>
                    <div className="container-fluid container-sm vertical-scrollable">
                        {
                            this.state.memes.map(
                                meme =>
                                <div class="card text-left" style={{ width: '30em',marginBottom: 12 }}>
                                    
                                    <div class="card-body">
                                        <h5 class="card-title">{meme.memeName}</h5>
                                        <p class="card-text">{meme.memeCaption}</p>
                                    </div>
                                    <img class="card-img-bottom img-responsive" src={meme.memeUrl} alt="Card image cap"/>
                                </div>
                            
                            )
                        }
                    </div>
                </center>
            </div>
        )
      }
}

export default ShowMemesComponent