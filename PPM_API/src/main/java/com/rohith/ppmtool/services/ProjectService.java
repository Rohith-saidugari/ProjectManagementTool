package com.rohith.ppmtool.services;

import com.rohith.ppmtool.domain.Project;
import com.rohith.ppmtool.exceptions.ProjectIdException;
import com.rohith.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch(Exception e){
            throw new ProjectIdException("Project id "+project.getProjectIdentifier().toUpperCase()+" already exists");
        }
    }
}
