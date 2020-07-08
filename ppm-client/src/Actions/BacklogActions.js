import {
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
  GET_ERRORS,
} from "./types";
import axios from "axios";

export const getBacklog = (backlogId) => async (dispatch) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/backlogs/${backlogId}`
    );
    dispatch({
      type: GET_BACKLOG,
      payload: response.data,
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
    await axios.post(
      `http://localhost:8080/api/backlogs/${backlog_id}`,
      projectTask
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
