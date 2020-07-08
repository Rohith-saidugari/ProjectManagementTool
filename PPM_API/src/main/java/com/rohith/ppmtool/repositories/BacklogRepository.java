package com.rohith.ppmtool.repositories;

import com.rohith.ppmtool.model.Backlog;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<Backlog,Long> {
    Backlog findByProjectIdentifier(String projectIdentifier);
}
