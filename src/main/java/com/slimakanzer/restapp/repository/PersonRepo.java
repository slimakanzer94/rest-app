package com.slimakanzer.restapp.repository;

import com.slimakanzer.restapp.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person, Long> {
    List<Person> findByName(String name);
}
