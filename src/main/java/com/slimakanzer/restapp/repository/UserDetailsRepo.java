package com.slimakanzer.restapp.repository;

import com.slimakanzer.restapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
