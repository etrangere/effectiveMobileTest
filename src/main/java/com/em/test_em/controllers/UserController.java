package com.em.test_em.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;
import com.em.test_em.services.TaskService;
import com.em.test_em.services.UserService;

@CrossOrigin()
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskService taskService;
    
    //get all users
    @GetMapping(value = "/getAllUsers")
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> findAll(){
        return this.userService.getAllUsers();
    }
    //get user by id
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<User> findById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }
    
    //user task    
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return this.userService.create(user);
    }
    
 // Add task to user
    @PutMapping("/{userId}/addTask")
    public ResponseEntity<User> addTaskToUser(@PathVariable Long userId, @RequestBody Task task) {
        try {
            // Fetch the user by ID
            Optional<User> userOptional = userService.getUserById(userId);

            // Check if the user exists
            if (userOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Get the user from Optional
            User user = userOptional.get();

            // Set the user for the task
            task.setUser(user);

            // Save the task
            Task savedTask = taskService.create(task);

            // Add the task to the user's task list
            user.getTask().add(savedTask);

            // Update the user
            userService.update(user);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update user    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public User update(@RequestBody User user, @PathVariable("id") Long id) {
        if (!id.equals(user.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Wrong session to update");
        }
        return this.userService.update(user);
    }
    
    //delete user
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}