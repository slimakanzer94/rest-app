package com.slimakanzer.restapp.dto;

import java.util.ArrayList;
import java.util.List;

public class PersonDto {

    private Long id;
    private String name;
    private String lastName;
    private String birthDate;
    private List<PersonDto> children = new ArrayList<>();
    private PersonDto parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<PersonDto> getChildren() {
        return children;
    }

    public void setChildren(List<PersonDto> children) {
        this.children = children;
    }

    public PersonDto getParent() {
        return parent;
    }

    public void setParent(PersonDto parent) {
        this.parent = parent;
    }

}

