package com.em.test_em.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.em.test_em._DTO.TaskDTO;
import com.em.test_em._DTO.UserDTO;
import com.em.test_em.services.CommentService;
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
    
    @Autowired
    private CommentService commentService;

    
    @GetMapping("/getAll_users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // Implement logic to get all users
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @GetMapping("/{user_id}/getById_user")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long user_id) {
        // Implement logic to get the specified user by ID
        UserDTO user = userService.getUserById(user_id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/create_user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        // Implement logic to create a new user
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
     
    @DeleteMapping("/user/{user_id}/delete_user")
    public ResponseEntity<Void> deleteUser(@PathVariable long user_id) {
        // Implement logic to delete the specified user
        userService.deleteUser(user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  
    
    @PutMapping("/user/{user_id}/update_user")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable long user_id,
            @RequestBody UserDTO updatedUserDTO) {
        // Implement logic to update the specified user
        UserDTO updatedUser = userService.updateUser(user_id, updatedUserDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
  
    @PostMapping("/user/{userTaskHolder_id}/create_task")
    public ResponseEntity<TaskDTO> createTask(@PathVariable long userTaskHolder_id, @RequestBody TaskDTO taskDTO) {
        // Implement logic to create a task for the specified userTaskHolder
        // You may want to validate userTaskHolder_id and perform other necessary operations
        TaskDTO createdTask = taskService.createTaskForUser(userTaskHolder_id, taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
    
    @GetMapping("/user/{userTaskHolder_id}/getAll_tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasksForUser(@PathVariable long userTaskHolder_id) {
        // Implement logic to get all tasks for the specified userTaskHolder
        List<TaskDTO> tasks = taskService.getAllTasksForUser(userTaskHolder_id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userTaskHolder_id}/delete_task/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long userTaskHolder_id, @PathVariable long task_id) {
        // Implement logic to delete the specified task for the specified userTaskHolder
        taskService.deleteTaskForUser(userTaskHolder_id, task_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/{userTaskHolder_id}/update_task/{task_id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable long userTaskHolder_id,
            @PathVariable long task_id,
            @RequestBody TaskDTO updatedTaskDTO) {
        // Implement logic to update the specified task for the specified userTaskHolder
        TaskDTO updatedTask = taskService.updateTaskForUser(userTaskHolder_id, task_id, updatedTaskDTO);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @GetMapping("/{userTaskHolder_id}/getAll_tasks_comments")
    public ResponseEntity<List<CommentDTO>> getAllTasksCommentsForUser(@PathVariable Long userTaskHolder_id) {
        // Implement logic to get all comments for tasks of the specified userTaskHolder
        // ...

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{userTaskHolder_id}/{task_id}/getAll_tasks_comments")
    public ResponseEntity<List<CommentDTO>> getAllTaskCommentsForUser(
            @PathVariable Long userTaskHolder_id, @PathVariable Long task_id) {
        // Implement logic to get all comments for the specified task of the userTaskHolder
        // ...

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/{userTaskHolder_id}/{task_id}/addExecutor/{userExecutor_id}")
    public ResponseEntity<String> addExecutorToTask(
            @PathVariable long userTaskHolder_id,
            @PathVariable long task_id,
            @PathVariable long userExecutor_id) {
        // Implement logic to add an executor to the specified task for the specified userTaskHolder
        taskService.addExecutorToTask(userTaskHolder_id, task_id, userExecutor_id);
        return new ResponseEntity<>("Executor added successfully", HttpStatus.OK);
    }
     
    @PostMapping("/{userTaskHolder_id}/{task_id}/removeExecutor/{userExecutor_id}")
    public ResponseEntity<String> removeExecutorFromTask(
            @PathVariable long userTaskHolder_id,
            @PathVariable long task_id,
            @PathVariable long userExecutor_id) {
        // Implement logic to remove an executor from the specified task for the specified userTaskHolder
        taskService.removeExecutorFromTask(userTaskHolder_id, task_id, userExecutor_id);
        return new ResponseEntity<>("Executor removed successfully", HttpStatus.OK);
    }
   
    @GetMapping("/{userExecutor_id}/getAll_tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasksForExecutor(@PathVariable Long userExecutor_id) {
        // Implement logic to get all tasks assigned to the specified userExecutor
        // ...

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    @PostMapping("/{userExecutor_id}/update_task_status/{task_id}")
    public ResponseEntity<String> updateTaskStatus(
            @PathVariable Long userExecutor_id, @PathVariable Long task_id, @RequestBody TaskStatusDTO taskStatusDTO) {
        // Implement logic to update the status of the specified task assigned to the userExecutor
        // ...

        return new ResponseEntity<>("Task status updated successfully", HttpStatus.OK);
    }
    
    
}
