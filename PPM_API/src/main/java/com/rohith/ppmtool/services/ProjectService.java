package com.rohith.ppmtool.services;

import com.rohith.ppmtool.model.Backlog;
import com.rohith.ppmtool.model.Project;
import com.rohith.ppmtool.exceptions.ProjectIdException;
import com.rohith.ppmtool.repositories.BacklogRepository;
import com.rohith.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){
        String projectIdentifier  = project.getProjectIdentifier().toUpperCase();
        try{
            project.setProjectIdentifier(projectIdentifier);
            //Create only for new projects
            if(project.getId() == null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectIdentifier);
            }
            //On Update Set Project backlog with existing backlog
            if(project.getId()!=null){
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
            }
            return projectRepository.save(project);
        }
        catch(Exception e){
            throw new ProjectIdException("Project id "+projectIdentifier+" already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){
          Project project =   projectRepository.findByProjectIdentifier(projectId.toUpperCase());
          if(project == null)
              throw new ProjectIdException("Project "+projectId+" Does not exists");
          return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project =   projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null)
            throw new ProjectIdException("Cannot delete project Id "+projectId+" . The project does not exists");
        projectRepository.delete(project);
    }
}
