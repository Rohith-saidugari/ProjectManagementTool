import React from "react";
import "./App.css";
import Dashboard from "./Components/Dashboard";
import Header from "./Components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route,Switch } from "react-router-dom";
import AddProject from "./Components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./Store";
import UpdateProject from "./Components/Project/UpdateProject";
import ProjectBoard from "./Components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./Components/ProjectBoard/ProjectTask/AddProjectTask";
import UpdateProjectTask from "./Components/ProjectBoard/ProjectTask/UpdateProjectTask";
import Landing from "./Components/Layout/Landing";
import Registration from "./Components/Authentication/Registration";
import Login from "./Components/Authentication/Login";
import jwt_decode from "jwt-decode";
import setJwtToken from "./SecurityUtils/SetJwtToken";
import { SET_CURRENT_USER } from "./Actions/types";
import { logout } from "./Actions/SecurityActions";
import SecureRoute from "./SecurityUtils/SecureRoute"
const jwtToken = localStorage.JwtToken;
if (jwtToken) {
  setJwtToken(jwtToken);
  const decoded = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded,
  });
  const currentTime = Date.now() / 1000;
  if (decoded.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}
//Provider is a component
function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          {
            //public routes
          }
          <Route exact path="/" component={Landing} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/signup" component={Registration} />
          {
            //private Routes
          }
          <Switch>
          <SecureRoute exact path="/dashboard" component={Dashboard} />
          <SecureRoute exact path="/addProject" component={AddProject} />
          <SecureRoute exact path="/updateProject/:id" component={UpdateProject} />
          <SecureRoute exact path="/projectBoard/:id" component={ProjectBoard} />
          <SecureRoute exact path="/addProjectTask/:id" component={AddProjectTask} />
          <SecureRoute
            exact
            path="/updateProjectTask/:backlog_Id/:project_task_id"
            component={UpdateProjectTask}
          />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
