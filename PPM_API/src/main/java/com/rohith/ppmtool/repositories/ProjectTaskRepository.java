package com.rohith.ppmtool.repositories;

import com.rohith.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
}
