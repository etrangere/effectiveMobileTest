package com.em.test_em.controllers;

import java.util.List;


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
    
   // @Autowired
   // private CommentService commentService;

    
    @GetMapping("/getAll_users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("/{user_id}/getById_user")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long user_id) {
        UserDTO user = userService.getUserById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/create_user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {     
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
     
    @DeleteMapping("/{user_id}/delete_user")
    public ResponseEntity<Void> deleteUser(@PathVariable long user_id) {      
        userService.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  
    
    @PutMapping("/{user_id}/update_user")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") Long user_id) {
        UserDTO updatedUser = this.userService.updateUser(userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
  
    @PostMapping("/{userTaskHolder_id}/create_task")
    public ResponseEntity<TaskDTO> createTask(@PathVariable long userTaskHolder_id, @RequestBody TaskDTO taskDTO) { 
        TaskDTO createdTask = null;  // Declare the variable outside the if-else block

        UserDTO tempUser = userService.getUserById(userTaskHolder_id);
        
        // Check if the user is a userExecutor or a userTaskHolder
        if (tempUser != null) {
            if (!(tempUser.isExecutor())) {
                createdTask = taskService.createTaskForUserTaskHolder(userTaskHolder_id, taskDTO);
            } else {
                System.out.print("The user is task executor");  
            }
        }
        
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
    
    @GetMapping("/{userTaskHolder_id}/getAll_tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasksForUser(@PathVariable long userTaskHolder_id) {
        List<TaskDTO> tasks = taskService.getAllTasksForTaskHolder(userTaskHolder_id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{userTaskHolder_id}/delete_task/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long userTaskHolder_id, @PathVariable long task_id) {
        taskService.deleteTaskForUser(userTaskHolder_id, task_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userTaskHolder_id}/update_task/{task_id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable long userTaskHolder_id,
            @PathVariable long task_id,
            @RequestBody TaskDTO updatedTaskDTO) {
       
        TaskDTO updatedTask = taskService.updateTaskForUser(userTaskHolder_id, task_id, updatedTaskDTO);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
/*
    @GetMapping("/{userTaskHolder_id}/getAll_tasks_comments")
    public ResponseEntity<List<CommentDTO>> getAllTasksCommentsForUser(@PathVariable Long userTaskHolder_id) {
      

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{userTaskHolder_id}/{task_id}/getAll_tasks_comments")
    public ResponseEntity<List<CommentDTO>> getAllTaskCommentsForUser(
            @PathVariable Long userTaskHolder_id, @PathVariable Long task_id) {
       

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
*/ 
    @PostMapping("/{userTaskHolder_id}/{task_id}/addExecutor/{userExecutor_id}")
    public ResponseEntity<String> addExecutorToTask(
            @PathVariable long userTaskHolder_id,
            @PathVariable long task_id,
            @PathVariable long userExecutor_id) {
        taskService.addExecutorToTask(userTaskHolder_id, task_id, userExecutor_id);
        return new ResponseEntity<>("Executor added successfully", HttpStatus.OK);
    }
    
    @PostMapping("/{userTaskHolder_id}/{task_id}/removeExecutor/{userExecutor_id}")
    public ResponseEntity<String> removeExecutorFromTask(
            @PathVariable long userTaskHolder_id,
            @PathVariable long task_id,
            @PathVariable long userExecutor_id) {
        taskService.removeExecutorFromTask(userTaskHolder_id, task_id, userExecutor_id);
        return new ResponseEntity<>("Executor removed successfully", HttpStatus.OK);
    }
   
    @GetMapping("/{userExecutor_id}/getAll_tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasksForExecutor(@PathVariable Long userExecutor_id) {
            List<TaskDTO> tasks = taskService.getAllTasksForTaskExecutor(userExecutor_id);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @PostMapping("/update_task_status/{task_id}/{status_code}")
    public ResponseEntity<String> updateTaskStatus(
            @PathVariable Long userExecutor_id, @PathVariable Long task_id, String status_code) {
     
        TaskDTO taskForStatusUpdate = taskService.getTaskById(task_id);
      
        taskForStatusUpdate.setStatus(status_code);
        taskService.updateTask(taskForStatusUpdate);
        
        return new ResponseEntity<>("Task status updated successfully", HttpStatus.OK);
    }
    
    
}
