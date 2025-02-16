package com.mike.curso.springboot.jpa.springboot_jpa.dto;

public class PersonDto {

    private final String name;
    private final String lastName;

    public PersonDto(String lastName, String name) {
        this.lastName = lastName;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
        return "PersonDto [name=" + name + ", lastName=" + lastName + "]";
    }



}
