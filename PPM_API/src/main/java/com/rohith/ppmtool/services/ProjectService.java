package com.rohith.ppmtool.services;

import com.rohith.ppmtool.exceptions.ProjectNotFoundException;
import com.rohith.ppmtool.model.Backlog;
import com.rohith.ppmtool.model.Project;
import com.rohith.ppmtool.exceptions.ProjectIdException;
import com.rohith.ppmtool.model.User;
import com.rohith.ppmtool.repositories.BacklogRepository;
import com.rohith.ppmtool.repositories.ProjectRepository;
import com.rohith.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project,String username){
        String projectIdentifier  = project.getProjectIdentifier().toUpperCase();
        try{
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
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

    public Project findProjectByIdentifier(String projectId,String username){
          Project project =   projectRepository.findByProjectIdentifier(projectId.toUpperCase());
          if(project == null)
              throw new ProjectIdException("Project "+projectId+" Does not exists");
          if(!project.getProjectLeader().equals(username))
              throw new ProjectNotFoundException("Project not found on your account");
          return project;
    }

    public Iterable<Project> findAllProjects(String username){
        return projectRepository.findAllByProjectLeader(username);
    }

    public void deleteProjectByIdentifier(String projectId, Principal principal){
        projectRepository.delete(findProjectByIdentifier(projectId,principal.getName()));
    }
}
