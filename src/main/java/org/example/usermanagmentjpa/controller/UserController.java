package org.example.usermanagmentjpa.controller;

import org.example.usermanagmentjpa.exception.UserNotFoundException;
import org.example.usermanagmentjpa.model.entity.UserEntity;
import org.example.usermanagmentjpa.service.UserService;
import org.example.usermanagmentjpa.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody UserEntity user) {
        UserEntity saveUser = userService.createUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user", saveUser);
        log.info("User saved: {}", saveUser);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserEntity>> getAllUsers() throws UserNotFoundException {
        List<UserEntity> users = userService.getAllUsers();
        log.info("All users found: {}", users);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        Optional<UserEntity> users = userService.getUserById(id);
        log.info("Users found: {}", users);
        return ResponseEntity.ok(users.get());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Map<String, Object>> deleteAllUsers() throws UserNotFoundException {
        Map<String, Object> response = new HashMap<>();
        response.put("users", userService.getAllUsers());
        log.info("User deleted succesfully: {}", userService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        Map<String, Object> response = new HashMap<>();
        response.put("user", userService.getUserById(id));
        log.info("All users deleted succesfully: {}", userService.getUserById(id));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/updatePhoto")
    public ResponseEntity<Map<String, Object>> updateUserPhoto(@RequestBody UserEntity user) throws UserNotFoundException {
        Map<String, Object> response = new HashMap<>();
        response.put("user", userService.getUserById(user.getId()));
        log.info("Users found: {}", userService.getUserById(user.getId()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> countUsers() throws UserNotFoundException {
        Map<String, Object> response = new HashMap<>();
        response.put("users", userService.getAllUsers());
        log.info("Users found: {}", userService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UserEntity user) throws UserNotFoundException {
        Map<String, Object> response = new HashMap<>();
        response.put("user", userService.getUserById(user.getId()));
        log.info("Users found: {}", userService.getUserById(user.getId()));
        return ResponseEntity.ok(response);
    }
}
