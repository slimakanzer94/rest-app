package com.slimakanzer.restapp.service;

import com.slimakanzer.restapp.dto.PersonDto;
import com.slimakanzer.restapp.entities.Person;
import com.slimakanzer.restapp.repository.PersonRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDtoService {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private ModelMapper mapper;

    public PersonDto savePerson(PersonDto personDto) throws ParseException {
        Person person = convertPersonDtoToPerson(personDto);
        if (personDto.getId() != null) {
            Person personFromDb = personRepo.findById(personDto.getId()).get();
            extractPerson(person, personFromDb);
            personRepo.save(personFromDb);
            return convertPersonToPersonDto(personFromDb);
        } else {
            return convertPersonToPersonDto(personRepo.save(person));
        }
    }

    private void extractPerson(Person person, Person personFromDb) {
        personFromDb.setbDate(person.getbDate());
        personFromDb.setName(person.getName());
        personFromDb.setLastName(person.getLastName());
    }

    public PersonDto getPerson(Long id) {
        return convertPersonToPersonDto(personRepo.findById(id).get());
    }

    public List<PersonDto> getPerson() {
        List<Person> personList = (List<Person>) personRepo.findAll();
        return personList.stream()
                .map(this::convertPersonToPersonDto)
                .collect(Collectors.toList());
    }

    public void deletePerson(Long id) {
        personRepo.deleteById(id);
    }

    private Person convertPersonDtoToPerson(PersonDto personDto) throws ParseException {
        Person person = mapper.map(personDto, Person.class);
        if (!personDto.getBirthDate().isEmpty()) {
            person.setbDate(convertToDate(personDto.getBirthDate()));
        }
        return person;

    }

    private PersonDto convertPersonToPersonDto(Person person) {
        PersonDto personDto = mapper.map(person, PersonDto.class);
        if (person.getbDate() != null) {
            personDto.setBirthDate(convertToString(person.getbDate()));
        }
        return personDto;
    }

    private Date convertToDate(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }

    private String convertToString(Date date) {
        return DATE_FORMAT.format(date);
    }

}

