package com.microservices.userservice.controllers;

import com.microservices.userservice.entities.User;
import com.microservices.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    //single user get
    @GetMapping("/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }


    //all users get
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
