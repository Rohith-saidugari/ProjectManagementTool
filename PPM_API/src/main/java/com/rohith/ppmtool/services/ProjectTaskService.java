package com.rohith.ppmtool.services;

import com.rohith.ppmtool.domain.Backlog;
import com.rohith.ppmtool.domain.Project;
import com.rohith.ppmtool.domain.ProjectTask;
import com.rohith.ppmtool.exceptions.ProjectIdException;
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

    public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask){
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
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
        catch(Exception e)
        {
            throw new ProjectNotFoundException("The Project backlog "+ projectIdentifier+ " you are trying to access Does not exists");
        }

    }

     public Iterable<ProjectTask> findBacklogById(String backlog_id) {
        if(projectRepository.findByProjectIdentifier(backlog_id) == null)
            throw new ProjectNotFoundException("The Project backlog "+ backlog_id+ " you are trying to access Does not exists");
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
    }

    public ProjectTask findTaskBySequence(String sequence){
        //Edge Case : Invalid Sequence
        //Edge Case : Invalid Backlog
        return projectTaskRepository.findByProjectSequence(sequence);
    }
}
