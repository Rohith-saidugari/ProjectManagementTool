import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { getBacklog } from "../../Actions/BacklogActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";
class ProjectBoard extends Component {
  constructor() {
    super();
    this.state = {
      errors: {},
    };
  }
  componentDidMount() {
    this.props.getBacklog(this.props.match.params.id);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({
        errors: nextProps.errors,
      });
    }
  }

  render() {
    const { id } = this.props.match.params;
    const {errors} = this.state; 
     const boardAlgorithm = (errors, project_tasks) => {
      if (project_tasks.length < 1) {
        if (errors.projectNotFound) {
          return (
            <div className="alert alert-danger text-center" role="alert">
              {errors.projectNotFound}
            </div>
          );
        }else if (errors.projectIdentifier) {
          return (
            <div className="alert alert-danger text-center" role="alert">
              {errors.projectIdentifier}
            </div>
          );
        }
         else {
          return (
            <div className="alert alert-info text-center" role="alert">
              No Project Tasks on this board
            </div>
          );
        }
      } else {
        return <Backlog id={id} projectTasks={project_tasks} />;
      }
    };
    
     return (
      <div className="container">
       <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        {boardAlgorithm(errors, this.props.backlog.project_tasks)}
      </div>
    );
  }
}
ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
};
const mapStateToProps = (state) => {
  return {
    backlog: state.backlog,
    errors: state.errors,
  };
};
export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
