import axios from 'axios';

//This is the service class to make REST requests to backend

const BACKEND_API_URL = process.env.REACT_APP_BACKEND_API_URL;

class DataAccessService{

    // Retreive lates memes from backend
    getLatestMemes(){
        return axios.get(`${BACKEND_API_URL}`);
    }

    //Add a meme to the backend
    addMeme(meme){
        return axios.post(`${BACKEND_API_URL}`,meme);
    }

}

export default new DataAccessService()