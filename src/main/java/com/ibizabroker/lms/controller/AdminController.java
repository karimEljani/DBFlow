package com.ibizabroker.lms.controller;

import com.ibizabroker.lms.entity.Users;
import com.ibizabroker.lms.exceptions.NotFoundException;
import com.ibizabroker.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/users")
    public ResponseEntity<Users> addUserByAdmin(@RequestBody Users user) {
        try {
            Users newUser = userService.addUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        try {
            Users user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users userDetails) {
        try {
            Users updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<Void> deleteMultiUsers(@RequestBody List<Integer> userIds) {
        try {
            userService.deleteMultipleUsers(userIds);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
