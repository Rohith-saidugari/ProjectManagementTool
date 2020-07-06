import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT } from "./types";

export const createProject = (project, history) => async (dispatch) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/projects",
      project
    );
    //On success redirect users to dashboard
    history.push("/dashboard");
    // Bug Fix To clear errors
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (error) {
    // On Error dispatch an action with error details
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  const response = await axios.get("http://localhost:8080/api/projects");
  dispatch({
    type: GET_PROJECTS,
    payload: response.data,
  });
};

export const getProject = (projectIdentifier, history) => async (dispatch) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/projects/${projectIdentifier}`
    );
    dispatch({
      type: GET_PROJECT,
      payload: response.data,
    });
  } catch (error) {
    history.push("/dashboard");
  }
};
