package com.kubapiecuch.springbootwithdatabase.controller;

import com.kubapiecuch.springbootwithdatabase.model.User;
import com.kubapiecuch.springbootwithdatabase.service.UserService;
import com.kubapiecuch.springbootwithdatabase.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/username/{id}")
    public ResponseEntity<User> updateUserName(@PathVariable("id") Long id, @RequestBody User user) {
        User userToUpdate = userService.updateUserName(id, user);
        return new ResponseEntity<>(userToUpdate, HttpStatus.OK);

    }

    @PutMapping("/email/{id}")
    public ResponseEntity<User> updateUserEmail(@PathVariable("id") Long id, @RequestBody User user) {
        User userToUpdate = userService.updateEmail(id, user);
        return new ResponseEntity<>(userToUpdate, HttpStatus.OK);

    }
}
