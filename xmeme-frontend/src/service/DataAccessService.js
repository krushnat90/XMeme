import axios from 'axios';

//This is the service class to make REST requests to backend

const BACKEND_API_URL = process.env.REACT_APP_BACKEND_API_URL;
const FIND_BY_NAME = 'findByName';

class DataAccessService{

    // Retreive lates memes from backend
    getLatestMemes(){
        return axios.get(`${BACKEND_API_URL}`);
    }

    // Retreive lates memes from backend based on name
    getLatestMemesByName(name){
        return axios.get(`${BACKEND_API_URL}/${FIND_BY_NAME}/${name}`);
    }

    //Add a meme to the backend
    addMeme(meme){
        return axios.post(`${BACKEND_API_URL}`,meme);
    }

    //Update a meme on the backend
    updateMeme(meme,id){
        return axios.patch(`${BACKEND_API_URL}/${id}`,meme);
    }

}

export default new DataAccessService()