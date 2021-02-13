import React,{Component} from 'react';
import DataAccessService from '../service/DataAccessService';
import AddMemeComponent from './AddMemeComponent';
import UpdateMemeComponent from './UpdateMemeComponent';
import { BsFillEyeFill } from "react-icons/bs";

// This component will retrieve latest memes and render on the webpage

class ShowMemesComponent extends Component{

    constructor(props){
        super(props);
        this.state = {
            memes: [],
            message: null,
            editClick : false,
            meme : null,
            showAll : true,
            showMessage : 'show more memes from this user'
        }
        this.loadLatestMemes = this.loadLatestMemes.bind(this);
        this.editButtonClick = this.editButtonClick.bind(this);
        this.editButtonClose = this.editButtonClose.bind(this);
        this.loadLatestMemesByName = this.loadLatestMemesByName.bind(this);
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

    loadLatestMemesByName(name){
        
        if(!this.state.showAll){
            this.state.showAll  =true
            this.state.showMessage  ='show more memes from this user'
            this.loadLatestMemes()
        }
        else
        {
            DataAccessService.getLatestMemesByName(name).then(
                resp => {
                    this.setState({memes:resp.data,
                    showMessage : 'show all memes',
                    showAll : false
                    })
                }
            )
        }
    }

    editButtonClick(thisMeme){

        this.setState({
            editClick : true,
            meme : thisMeme
        })
    }

    editButtonClose(){
        this.setState({editClick : false})
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
                                    <button type="button" class="btn btn-primary float-right" onClick={() => this.editButtonClick(meme)}>Edit</button>
                                        <h5 class="card-title">by : {meme.name}</h5>
                                        <p class="card-text">Caption : {meme.caption}</p>
                                    </div>
                                    <img class="card-img-bottom img-responsive" src={meme.url} alt="INVALID IMAGE"/>
                                    <div class="card-footer">
                                    <button type="button" class="btn btn-info" onClick={() => this.loadLatestMemesByName(meme.name)}>
                                    {this.state.showMessage}
                                    </button>
                                    </div>
                                </div>
                            
                            )
                        }
                    </div>
                </center>
                {this.state.editClick && <UpdateMemeComponent editClose={this.editButtonClose} memeToUpdate={this.state.meme} parentMethod={this.loadLatestMemes}/>}
            </div>
        )
      }
}

export default ShowMemesComponent