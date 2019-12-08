package com.slimakanzer.restapp.controller;

import com.slimakanzer.restapp.dto.PersonDto;
import com.slimakanzer.restapp.entities.Person;
import com.slimakanzer.restapp.repository.PersonRepo;
import com.slimakanzer.restapp.service.PersonDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonDtoService personDtoService;

    @GetMapping
    public List<PersonDto> getPerson() {
       return personDtoService.getPerson();
    }

    @GetMapping("{id}")
    public PersonDto getPerson(@PathVariable("id") Long id) {
        return personDtoService.getPerson(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto addPerson(@RequestBody PersonDto personDto) throws ParseException {
        return personDtoService.savePerson(personDto);
    }

    @PutMapping("{id}")
    public PersonDto person(@RequestBody PersonDto personDto) throws ParseException {
        return personDtoService.savePerson(personDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable("id") Long id) {
        personDtoService.deletePerson(id);
    }
}

