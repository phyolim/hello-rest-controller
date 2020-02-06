package com.galvanize.controllers;

import com.galvanize.entities.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloRestController {

    @GetMapping("/hello")
    public Person helloRegistration(
            @RequestParam String name,
            @RequestParam Date birthDate,
            @RequestParam String email) {
        return new Person(name, birthDate, email);
    }

    @PostMapping("/hello")
    public Person helloRegistration(@RequestBody Person person) {
        return person;
    }
}
