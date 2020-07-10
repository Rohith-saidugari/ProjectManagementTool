import axios from "axios";
import { GET_ERRORS, SET_CURRENT_USER } from "./types";
import setJwtToken from "../SecurityUtils/SetJwtToken"
import jwt_decode from "jwt-decode"
export const registerUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post(`api/auth/signup`, newUser);
    history.push(`/login`);
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};

export const login = (login) => async (dispatch) => {
  try {
    const response = await axios.post(`api/auth/login`, login);
    const {token} = response.data
    localStorage.setItem("JwtToken",token);
    setJwtToken(token);
    const decoded = jwt_decode(token)
    dispatch({
        type : SET_CURRENT_USER,
        payload : decoded
    })
    dispatch({
        type: GET_ERRORS,
        payload: {},
      });
  }catch(error){
    dispatch({
        type: GET_ERRORS,
        payload: error.response.data,
      });
  }
};
