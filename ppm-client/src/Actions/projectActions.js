import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async (dispatch) => {
  try {
    const response = await axios.post("/api/projects", project);
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
  const response = await axios.get("/api/projects");
  dispatch({
    type: GET_PROJECTS,
    payload: response.data,
  });
};

export const getProject = (projectIdentifier, history) => async (dispatch) => {
  try {
    const response = await axios.get(`/api/projects/${projectIdentifier}`);
    dispatch({
      type: GET_PROJECT,
      payload: response.data,
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

export const deleteProject = (projectIdentifier, history) => async (
  dispatch
) => {
  if (
    window.confirm(
      "Are you Sure? This will delete the project and all the data"
    )
  ) {
    await axios.delete(`/api/projects/${projectIdentifier}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: projectIdentifier,
    });
  }
};
