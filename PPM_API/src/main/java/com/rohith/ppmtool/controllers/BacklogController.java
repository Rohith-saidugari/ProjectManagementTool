package com.rohith.ppmtool.controllers;

import com.rohith.ppmtool.model.ProjectTask;
import com.rohith.ppmtool.services.ProjectTaskService;
import com.rohith.ppmtool.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/backlogs")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask ,
                                                     BindingResult result,@PathVariable String backlog_id){
       if(result.hasErrors())
           return validationErrorService.mapErrors(result);
       ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id,projectTask);
       return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping ("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
        return  projectTaskService.findBacklogById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{sequence_id}")
    public ResponseEntity<?> getProjectTaskBySequence(@PathVariable String backlog_id,@PathVariable String sequence_id){
        return new ResponseEntity<>(projectTaskService.findTaskBySequence(backlog_id,sequence_id),HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{sequence_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask updatedProjectTask,BindingResult result,
                                               @PathVariable String backlog_id,@PathVariable String sequence_id ){
        if(result.hasErrors())
            return validationErrorService.mapErrors(result);
        ProjectTask updatedTask = projectTaskService.updateProjectTaskBySequence(updatedProjectTask,backlog_id,sequence_id);
        return new ResponseEntity<>(updatedTask,HttpStatus.OK);
    }

    @DeleteMapping ("/{backlog_id}/{sequence_id}")
    public ResponseEntity<?> updateProjectTask(@PathVariable String backlog_id,@PathVariable String sequence_id ){
        projectTaskService.deleteProjectTaskBySequence(backlog_id,sequence_id);
        return new ResponseEntity<>("Project Task "+sequence_id+ " has been deleted successfully",HttpStatus.OK);
    }
}
