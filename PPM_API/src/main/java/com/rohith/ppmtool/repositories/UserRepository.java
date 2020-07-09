package com.rohith.ppmtool.repositories;

import com.rohith.ppmtool.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
