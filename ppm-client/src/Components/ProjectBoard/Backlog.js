import React, { Component } from "react";
import ProjectTask from "./ProjectTask/ProjectTask";

class Backlog extends Component {
  render() {
    const projectTasks = this.props.projectTasks.map((projectTask) => {
      return (
        <ProjectTask
          key={projectTask.projectSequence}
          projectTask={projectTask}
        />
      );
    });
    let to_do_Tasks = [];
    let inProgress_Tasks = [];
    let done_Tasks = [];
    for (let i = 0; i < projectTasks.length; i++) {
      if ((projectTasks[i].props.projectTask.status === "TO_DO"))
        to_do_Tasks.push(projectTasks[i]);
      if ((projectTasks[i].props.projectTask.status === "IN_PROGRESS"))
        inProgress_Tasks.push(projectTasks[i]);
      if ((projectTasks[i].props.projectTask.status === "DONE"))
        done_Tasks.push(projectTasks[i]);
    }
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-secondary text-white">
                <h3>TO DO</h3>
              </div>
            </div>
            {to_do_Tasks}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-primary text-white">
                <h3>In Progress</h3>
              </div>
            </div>
            {inProgress_Tasks}
          </div>
          <div className="col-md-4">
            <div className="card text-center mb-2">
              <div className="card-header bg-success text-white">
                <h3>Done</h3>
              </div>
            </div>
            {done_Tasks}
          </div>
        </div>
      </div>
    );
  }
}

export default Backlog;
