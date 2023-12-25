package com.em.test_em.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.em.test_em._DTO.TaskDTO;
import com.em.test_em._DTO.UserDTO;
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
    public List<UserDTO> findAll() {
        return this.userService.getAllUsers();
    }

    //get user by id
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        Optional<UserDTO> user = this.userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //create user
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/tasksWithComments")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<TaskDTO>> getUserTasksWithComments(@PathVariable Long userId) {
        List<TaskDTO> tasksWithComments = taskService.getTasksWithCommentsByUser(userId);

        if (!tasksWithComments.isEmpty()) {
            return new ResponseEntity<>(tasksWithComments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    
    //update user
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) {
        if (!id.equals(userDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO updatedUser = this.userService.updateUser(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

    //delete user
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }
}
