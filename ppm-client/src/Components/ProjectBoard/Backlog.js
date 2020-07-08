import React, { Component } from "react";
import ProjectTask from "./ProjectTask/ProjectTask";

class Backlog extends Component {
  render() {
    const todoTasks = this.props.projectTasks.filter(
      (projectTask) => projectTask.status === "TO_DO"
    );
    const ProjectTasks = this.props.projectTasks.map((projectTask) => {
      return (
        <ProjectTask
          key={projectTask.projectSequence}
          projectTask={projectTask}
        />
      );
    });
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>

            {/*<!-- SAMPLE PROJECT TASK STARTS HERE -->*/}

            {console.log(todoTasks)}
            {/*<!-- SAMPLE PROJECT TASK ENDS HERE -->*/}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {/*<!-- SAMPLE PROJECT TASK STARTS HERE -->
                    <!-- SAMPLE PROJECT TASK ENDS HERE -->*/}
            {ProjectTasks}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {/* <!-- SAMPLE PROJECT TASK STARTS HERE -->

                   <!-- SAMPLE PROJECT TASK ENDS HERE -->*/}
          </div>
        </div>
      </div>
    );
  }
}

export default Backlog;
