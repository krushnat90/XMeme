import React,{Component} from 'react';
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'

import { Formik, Form, Field, ErrorMessage } from 'formik';
import DataAccessService from '../service/DataAccessService';

class UpdateMemeComponent extends Component{

    constructor(props){
        super(props);

        this.state={
            id:props.memeToUpdate.id,
            name:props.memeToUpdate.name,
            url:props.memeToUpdate.url,
            caption:props.memeToUpdate.caption,
            message : '',
            errorMessage : '',
            show : true,
            hide :false
        }

        this.validate = this.validate.bind(this)
        this.show = this.show.bind(this)
        this.handleClose = this.handleClose.bind(this)
        this.submitForUpdate = this.submitForUpdate.bind(this)
    }

    //Basic validation
    validate(val){
        this.state.errorMessage ='';

        if (!val.name) {
            this.state.errorMessage = 'Name of meme poster cannot be blank'
            this.state.message=''
            return this.state.errorMessage;
        } else if (/[^0-9a-zA-Z]/.test(val.name)) {
            this.state.errorMessage = 'Name can only contain alphabets and numbers'
            this.state.message=''
            return this.state.errorMessage;
        }

        if(!val.url){
            this.state.errorMessage='URL of the meme is mandatory. It should point to a public image'
            this.state.message=''
            return this.state.errorMessage;
        }

        else if(!val.caption){
            this.state.errorMessage='Caption for the meme is mandatory'
            this.state.message=''
            return this.state.errorMessage;
        }

        return this.state.errorMessage;
    }

    submitForUpdate(val){
        console.log(this.state.id);
        let Meme = {
            name: val.name,
            url: val.url,
            caption: val.caption
        }
        DataAccessService.updateMeme(Meme,this.state.id).then(
            resp =>{
                this.state.message='Meme updated successfully'
                val.name='';
                val.url='';
                val.caption='';
            }
        ).catch(err =>{
            if(err.response.status == 404){
                this.state.errorMessage= 'This meme does not exists'
            }
            else if(err.response.status == 409){
                this.state.errorMessage= 'This meme already exists'
            }
            else{
            this.state.errorMessage= 'Meme could not be added due to an internal error'
            }
        }).then(()=>{
            console.log('ending submit')
            this.props.parentMethod()
            }
        )
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

    handleClose(){
        this.props.editClose();
    }

    show(){
        this.state.show=true;
    }

    render(){
        let { name, url, caption } = this.state;
        return (
                <Modal show={this.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Update Meme</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Formik
                            initialValues={{ name, url,caption }}
                            validate = {this.validate}
                            onSubmit ={this.submitForUpdate}
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
                                    <fieldset className="form-group">
                                        <label>Name</label>
                                        <Field className="form-control" type="text" name="name" disabled />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>URL</label>
                                        <Field className="form-control" type="text" name="url" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Caption</label>
                                        <Field className="form-control" type="text" name="caption" />
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Update</button>
                                </Form>
                            )
                        }
                        </Formik>
                    </Modal.Body>
                    
                </Modal>);
    }

}

export default UpdateMemeComponent;