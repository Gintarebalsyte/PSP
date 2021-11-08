package com.example.third_task.controller;

import com.example.third_task.model.User;
import com.example.third_task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService service;

    //http://localhost:8080/register
    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User newUser){
        User user = service.add(newUser);

        return new ResponseEntity<> (user, HttpStatus.CREATED);
    }

    //http://localhost:8080/users/{userId}
    @GetMapping("users/{userId}")
    public User getUserById(@PathVariable Long userId){
        return service.findById(userId);
    }

    //http://localhost:8080/users
    @GetMapping("users")
    public List<User> getAllUsers(){
        return service.findAll();
    }

    //http://localhost:8080/update
    @PostMapping("update")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser){
        service.update(updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //http://localhost:8080/update/{userId}
    @PostMapping("update/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser, @PathVariable Long userId){
        service.updateById(userId, updatedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    http://localhost:8080/delete
    @PostMapping("delete")
    public ResponseEntity<User> deleteUser(@RequestBody User user){
        service.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    http://localhost:8080/delete/{userId}
    @PostMapping("delete/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId){
        service.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
