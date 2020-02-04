package com.mckesson.controllers;

import com.mckesson.entities.Person;
import org.springframework.web.bind.annotation.*;

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
