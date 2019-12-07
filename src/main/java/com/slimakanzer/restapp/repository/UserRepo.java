package com.slimakanzer.restapp.repository;

import com.slimakanzer.restapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
