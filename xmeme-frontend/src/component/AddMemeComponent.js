import React,{Component} from 'react';

import { Formik, Form, Field, ErrorMessage } from 'formik';
import validator from 'validator'

import DataAccessService from '../service/DataAccessService';
import { Redirect } from "react-router-dom";

class AddMemeComponent extends Component{

    constructor(props){
        super(props);

        this.state={
            name:'',
            url:'',
            caption:'',
            message : '',
            errorMessage : '',
        }

        this.validate = this.validate.bind(this)
        this.submit = this.submit.bind(this);
    }

    //Basic validation
    validate(val){
        this.state.errorMessage ='';

        if (!val.memerName) {
            this.state.errorMessage = 'Name of meme poster cannot be blank'
            this.state.message=''
            return this.state.errorMessage;
        } else if (/[^0-9a-zA-Z]/.test(val.memerName)) {
            this.state.errorMessage = 'Name can only contain alphabets and numbers'
            this.state.message=''
            return this.state.errorMessage;
        }

        if(!val.memeUrl){
            this.state.errorMessage='URL of the meme is mandatory. It should point to a public image'
            this.state.message=''
            return this.state.errorMessage;
        }

        else if(!validator.isURL(val.memeUrl)){
            this.state.errorMessage='URL should be a valid image URL'
            this.state.message=''
            return this.state.errorMessage;
        }

        else if(!val.memeCaption){
            this.state.errorMessage='Caption for the meme is mandatory'
            this.state.message=''
            return this.state.errorMessage;
        }

        return this.state.errorMessage;
    }

    //close button functionality for error
    hideErrorAlert(){
        this.setState({
            errorMessage: false,
        });
    }

    //close button functionality for message
    hideMessageAlert(){
        this.setState({
            message: false,
        });
    }

    //submit form data and make rest call to add api
    submit(val){
        let Meme = {
            name: val.memerName,
            url: val.memeUrl,
            caption: val.memeCaption
        }
        DataAccessService.addMeme(Meme).then(resp => {
            this.state.message='Meme added successfully'
            val.memerName='';
            val.memeUrl='';
            val.memeCaption='';
        }).catch(error => {
            console.log(error.response.status)
            if(error.response.status == 409){
                this.state.errorMessage= 'This meme already exists'
            }
            else{
            this.state.errorMessage= 'Meme could not be added due to an internal error'
            }
            
            
        }).then(() =>{ 
            console.log('ending submit')
            this.props.parentMethod()})
        }

    render(){
        let { memerName, memeUrl, memeCaption } = this.state;

        return (
            <div className="container">
                <Formik
                    initialValues={{ memerName, memeUrl, memeCaption }}
                    onSubmit={this.submit}
                    validateOnChange={false}
                    validateOnBlur={false}
                    validate = {this.validate}
                    enableReinitialize={true}
                >
                    {
                        (props) => (
                            <Form>
                                {this.state.message && <div className="alert alert-success" id="site-message">{this.state.message}
                                                            <button type="button" 
                                                                className="close" 
                                                                data-dismiss="alert" 
                                                                aria-label="Close"
                                                                onClick={() => this.hideMessageAlert()}>
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>}
                                {this.state.errorMessage && <div className="alert alert-warning" role="alert">{this.state.errorMessage}
                                                            <button type="button" 
                                                                className="close" 
                                                                data-dismiss="alert" 
                                                                aria-label="Close"
                                                                onClick={() => this.hideErrorAlert()}>
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                            </div>}
                                <div class="row">
                                    <div class="col">
                                    <Field className="form-control" type="text" name="memerName"  placeholder="Name of meme poster"/>
                                    </div>
                                    <div class="col">
                                    <Field className="form-control" type="text" name="memeUrl"  placeholder="URL of the Meme"/>
                                    </div>
                                    <div class="col">
                                    <Field className="form-control" type="text" name="memeCaption"  placeholder="Caption"/>
                                    </div>
                                    <div class="col">
                                    <button className="btn btn-success" type="submit">Add your meme</button>
                                    </div>
                                </div>
                                
                            </Form>
                        )
                    }
                </Formik>

            </div>
        );
    }
}

export default AddMemeComponent;