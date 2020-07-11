import {
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
  GET_ERRORS,
} from "./types";
import axios from "axios";

export const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/';

export const getBacklog = (backlogId) => async (dispatch) => {
  try {
    const response = await axios.get(API_BASE_URL+`api/backlogs/${backlogId}`);
    dispatch({
      type: GET_BACKLOG,
      payload: response.data,
    });
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

export const createProjectTask = (projectTask, backlog_id, history) => async (
  dispatch
) => {
  try {
    await axios.post(API_BASE_URL+`api/backlogs/${backlog_id}`, projectTask);
    history.push(`/projectBoard/${backlog_id}`);
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

export const getProjectTask = (projectId, taskSequenceId, history) => async (
  dispatch
) => {
  try {
    const response = await axios.get(
      API_BASE_URL+`api/backlogs/${projectId}/${taskSequenceId}`
    );
    dispatch({
      type: GET_PROJECT_TASK,
      payload: response.data,
    });
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

export const updateProjectTask = (
  backlog_id,
  project_sequence_id,
  updatedTask,
  history
) => async (dispatch) => {
  try {
    await axios.patch(
      API_BASE_URL+`api/backlogs/${backlog_id}/${project_sequence_id}`,
      updatedTask
    );
    history.push(`/projectBoard/${backlog_id}`);
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

export const deleteProjectTask = (backlogId, projectSequenceId) => async (
  dispatch
) => {
  if (
    window.confirm(`Are you sure , you want to delete ${projectSequenceId}?? `)
  )
    await axios.delete(API_BASE_URL+`api/backlogs/${backlogId}/${projectSequenceId}`);
  dispatch({
    type: DELETE_PROJECT_TASK,
    payload: projectSequenceId,
  });
};
