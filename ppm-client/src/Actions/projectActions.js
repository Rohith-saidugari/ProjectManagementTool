import axios from 'axios'
import {GET_ERRORS} from './types'

export const createProject = (project,history) => async dispatch =>{
    try{
        const response = await axios.post("http://localhost:8080/api/projects",project)
        //On success redirect users to dashboard
        history.push("/dashboard")
    }catch(error){
        // On Error dispatch an action with error details
        dispatch({
            type : GET_ERRORS,
            payload:error.response.data
        })

    }
}
