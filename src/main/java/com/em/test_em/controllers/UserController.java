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

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskService taskService;

    //get all users
    @GetMapping(value = "/getAll_users")
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserDTO> findAll() {
        return this.userService.getAllUsers();
    }

    //get user by id
    @GetMapping("/{user_id}/getById_user")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UserDTO> findById(@PathVariable Long user_id) {
        Optional<UserDTO> user = this.userService.getUserById(user_id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //create user
    @PostMapping("/create_user")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}/tasksWithComments")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<TaskDTO>> getUserTasksWithComments(@PathVariable Long user_id) {
        List<TaskDTO> tasksWithComments = taskService.getTasksWithCommentsByUser(user_id);

        if (!tasksWithComments.isEmpty()) {
            return new ResponseEntity<>(tasksWithComments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    
    //update user
    @PutMapping("/{user_id}/update_user")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable Long user_id) {
        if (!user_id.equals(userDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO updatedUser = this.userService.updateUser(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

    //delete user
    @DeleteMapping("/{user_id}/delete_user")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long user_id) {
        this.userService.deleteUser(user_id);
    }
}
