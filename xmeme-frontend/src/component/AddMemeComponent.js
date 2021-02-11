import React,{Component} from 'react';

import { Formik, Form, Field, ErrorMessage } from 'formik';
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

        else if(!val.memeCaption){
            this.state.errorMessage='Caption for the meme is mandatory'
            this.state.message=''
            return this.state.errorMessage;
        }

        return this.state.errorMessage;
    }

    submit(val){
        let Meme = {
            name: val.memerName,
            url: val.memeUrl,
            caption: val.memeCaption
        }
        DataAccessService.addMeme(Meme).then(resp => {
            this.state.message='Meme added successfully'
        }).catch(error => {
            this.state.message='Meme could not be added due to an error'
            
        }).then(() => this.props.parentMethod())
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
                                {this.state.message && <div className="alert alert-success">{this.state.message}</div>}
                                {this.state.errorMessage && <div className="alert alert-warning">{this.state.errorMessage}</div>}
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