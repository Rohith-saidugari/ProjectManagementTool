package com.rohith.ppmtool.services;

import com.rohith.ppmtool.model.Backlog;
import com.rohith.ppmtool.model.ProjectTask;
import com.rohith.ppmtool.exceptions.ProjectNotFoundException;
import com.rohith.ppmtool.repositories.BacklogRepository;
import com.rohith.ppmtool.repositories.ProjectRepository;
import com.rohith.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;


    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask,String username){

            Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier,username).getBacklog();
                    //backlogRepository.findByProjectIdentifier(projectIdentifier);
            projectTask.setBacklog(backlog);
            Integer backlogSequence = backlog.getPTSequence();
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);
            projectTask.setProjectSequence(projectIdentifier + "_" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);
            // 1 Highest Priority 3 Lowest Priority
            if (projectTask.getPriority() == null || projectTask.getPriority() == 0)
                projectTask.setPriority(3);
            if (projectTask.getStatus() == null || projectTask.getStatus().equals(""))
                //TODO enums for tasks
                projectTask.setStatus("TO_DO");
            return projectTaskRepository.save(projectTask);





    }

     public Iterable<ProjectTask> findBacklogById(String backlog_id,String username) {
        projectService.findProjectByIdentifier(backlog_id,username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findTaskBySequence(String backlog_id,String sequence, String username){
        //Backlog Does not exists
        projectService.findProjectByIdentifier(backlog_id,username);
        //Project Task does not exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if(projectTask == null)
            throw new ProjectNotFoundException("Project Task "+sequence+" does not exists");
        //Project task does not belong to backlog
        if(!projectTask.getProjectIdentifier().equals(backlog_id))
            throw new ProjectNotFoundException("Project Task "+sequence+" does not exist in project "+backlog_id);
        return projectTask;
    }

    public ProjectTask updateProjectTaskBySequence (ProjectTask updatedTask, String backlog_id,String sequence_id,String username){
        ProjectTask projectTask = findTaskBySequence(backlog_id, sequence_id,username);
        /*if(!projectTask.getProjectSequence().equals(updatedTask.getProjectSequence()))
            throw new ProjectSequenceUpdateException();*/
        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }

    public void deleteProjectTaskBySequence(String backlog_id,String sequence_id,String username){
        ProjectTask projectTask = findTaskBySequence(backlog_id, sequence_id,username);
        projectTaskRepository.delete(projectTask);
    }
}
