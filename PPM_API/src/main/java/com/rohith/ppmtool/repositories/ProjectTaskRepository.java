package com.rohith.ppmtool.repositories;

import com.rohith.ppmtool.model.ProjectTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
    ProjectTask findByProjectSequence(String projectSequence);
}
