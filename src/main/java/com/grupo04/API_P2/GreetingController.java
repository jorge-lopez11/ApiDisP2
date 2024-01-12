package com.grupo04.API_P2;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class GreetingController {


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/users")
    public ArrayList<User> users(@RequestParam() String name) {
        JSONReader reader = new JSONReader();
        ArrayList<User> userList = reader.readJsonFile("src/main/resources/users.json");
        return userList;
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<User> getByName(@PathVariable String name){
        DataHandling dataHandling = new DataHandling();
        User foundUser = dataHandling.getUserInfo(name);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PostMapping(path = "users", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User newUser) {
        DataHandling dataHandling = new DataHandling();
        dataHandling.addUser(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}

