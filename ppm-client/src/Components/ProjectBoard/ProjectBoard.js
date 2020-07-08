import React, { Component } from "react";
import { Link } from "react-router-dom";
import Backlog from "./Backlog";
import { getBacklog } from "../../Actions/BacklogActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";
class ProjectBoard extends Component {
  componentDidMount() {
    this.props.getBacklog(this.props.match.params.id);
  }
  render() {
    const { id } = this.props.match.params;

    return (
      <div className="container">
        <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
          <i className="fas fa-plus-circle"> Create Project Task</i>
        </Link>
        <br />
        <hr />
        <Backlog id={id} projectTasks={this.props.backlog.project_tasks} />
      </div>
    );
  }
}
ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired,
};
const mapStateToProps = (state) => {
  return {
    backlog: state.backlog,
  };
};
export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
