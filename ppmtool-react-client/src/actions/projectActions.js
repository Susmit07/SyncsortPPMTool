import axios from "axios";
import { GET_ERRORS } from "./types";

// history is passed to redirect the project to dashboard screen, once project
// succesfully creatd.
export const createProject = (project, history) => async dispatch => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/project/addProject",
      project
    );
    history.push("/dashboard");
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
