import React, { Component } from "react";
import { getProjectTask ,updateProjectTask } from "../../../Actions/BacklogActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import classnames from "classnames"
class UpdateProjectTask extends Component {
  constructor() {
    super();
    this.state = {
      id: "",
      projectSequence: "",
      acceptanceCriteria: "",
      status: "",
      priority: "",
      dueDate: "",
      projectIdentifier: "",
      summary: "",
      errors: {},
    };
    this.onChangeHandler = this.onChangeHandler.bind(this);
    this.onSubmitHandler = this.onSubmitHandler.bind(this);
  }

  onChangeHandler = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  onSubmitHandler = (event) =>{
      event.preventDefault();
      const {id,
      projectSequence,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      projectIdentifier,
      summary} = this.state
      this.props.updateProjectTask(projectIdentifier,projectSequence,{id,
        projectSequence,
        acceptanceCriteria,
        status,
        priority,
        dueDate,
        projectIdentifier,
        summary},this.props.history);
  }

  componentDidMount() {
    const { params } = this.props.match;
    this.props.getProjectTask(
      params.backlog_Id,
      params.project_task_id,
      this.props.history
    );
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors.projectNotFound || nextProps.errors.summary)
      this.setState({ errors: nextProps.errors });
    else this.setState({ ...nextProps.projectTask });
  }

  render() {
    const errorOrUpdate = () => {
      if (this.state.errors.projectNotFound)
        return (
          <div className="alert alert-danger text-center" role="alert">
            {this.state.errors.projectNotFound}
          </div>
        );
      else {
        return (
          <div>
            <h4 className="display-4 text-center">Update Project Task</h4>
            <p className="lead text-center">Project Name + Project Code</p>
            <form onSubmit = {this.onSubmitHandler}>
              <div className="form-group">
                <input
                  type="text"
                  className={classnames("form-control form-control-lg",{
                      "is-invalid":this.state.errors.summary
                  })}
                  name="summary"
                  placeholder="Project Task summary"
                  onChange={this.onChangeHandler}
                  value={this.state.summary}
                />
                {this.props.errors.summary && (
                    <div className="invalid-feedback">{this.state.errors.summary}</div>
                  )}
              </div>
              <div className="form-group">
                <textarea
                  className="form-control form-control-lg"
                  placeholder="Acceptance Criteria"
                  name="acceptanceCriteria"
                  onChange={this.onChangeHandler}
                  value={this.state.acceptanceCriteria}
                ></textarea>
              </div>
              <h6>Due Date</h6>
              <div className="form-group">
                <input
                  type="date"
                  className="form-control form-control-lg"
                  name="dueDate"
                  onChange={this.onChangeHandler}
                  value={this.state.dueDate}
                />
              </div>
              <div className="form-group">
                <select
                  className="form-control form-control-lg"
                  name="priority"
                  onChange={this.onChangeHandler}
                  value={this.state.priority}
                >
                  <option value={0}>Select Priority</option>
                  <option value={1}>High</option>
                  <option value={2}>Medium</option>
                  <option value={3}>Low</option>
                </select>
              </div>

              <div className="form-group">
                <select
                  className="form-control form-control-lg"
                  name="status"
                  onChange={this.onChangeHandler}
                  value={this.state.status}
                >
                  <option value="">Select Status</option>
                  <option value="TO_DO">TO DO</option>
                  <option value="IN_PROGRESS">IN PROGRESS</option>
                  <option value="DONE">DONE</option>
                </select>
              </div>

              <input type="submit" className="btn btn-primary btn-block mt-4" />
            </form>
          </div>
        );
      }
    };

    return (
      <div className="add-PBI">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <Link
                to={`/projectBoard/${this.props.match.params.backlog_Id}`}
                className="btn btn-light"
              >
                Back to Project Board
              </Link>
              <br />
              <br />
              {errorOrUpdate()}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateProjectTask.propTypes = {
  getProjectTask: PropTypes.func.isRequired,
  projectTask: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => {
  return {
    projectTask: state.backlog.project_task,
    errors: state.errors,
  };
};
export default connect(mapStateToProps, { getProjectTask, updateProjectTask })(UpdateProjectTask);
