import { GET_ERRORS } from "../Actions/types";

const initialState = {};
//Payload contains corresponding data
export default function (state = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      return action.payload;
    default:
      return state;
  }
}
