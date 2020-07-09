package com.rohith.ppmtool.controllers;


import com.rohith.ppmtool.model.Project;
import com.rohith.ppmtool.services.ProjectService;
import com.rohith.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal){
        if(result.hasErrors())
            return validationErrorService.mapErrors(result);
        return new ResponseEntity<>(projectService.saveOrUpdateProject(project,principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId,Principal principal){
        Project project = projectService.findProjectByIdentifier(projectId,principal.getName());
        return new ResponseEntity<>(project,HttpStatus.OK);
    }

    @GetMapping
    public Iterable<Project> getAllProjects(Principal principal){
        return projectService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId,Principal principal){
        projectService.deleteProjectByIdentifier(projectId,principal);
        return new ResponseEntity<>("Project with Id :"+projectId+" was deleted successfully",HttpStatus.OK);
    }


}
