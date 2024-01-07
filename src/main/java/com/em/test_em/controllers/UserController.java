package com.em.test_em.controllers;

import com.em.test_em._DTO.CommentDTO;
import com.em.test_em._DTO.TaskDTO;
import com.em.test_em._DTO.UserDTO;
import com.em.test_em.beans.Task;
import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;
import com.em.test_em.repositories.TaskRepository;
import com.em.test_em.services.CommentService;
import com.em.test_em.services.TaskService;
import com.em.test_em.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing users, tasks, and comments through RESTful APIs.
 *
 * @author Gourgen KHACHATRIAN
 * @version 1.0
 */
@CrossOrigin()
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User management APIs")
public class UserController {

  @Autowired private UserService userService;

  @Autowired private TaskService taskService;

  @Autowired private CommentService commentService;

  @Autowired private TaskRepository taskRepository;

  /**
   * Creates a new user.
   *
   * @param userDTO The data of the user to be created.
   * @return ResponseEntity<UserDTO> The created user and HTTP status.
   */
  @PostMapping("/create_user")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
    UserDTO createdUser = userService.createUser(userDTO);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  /**
   * Deletes a user by ID.
   *
   * @param user_id The ID of the user to be deleted.
   * @return ResponseEntity<Void> HTTP status indicating success or failure.
   */
  @DeleteMapping("/{user_id}/delete_user")
  public ResponseEntity<Void> deleteUser(@PathVariable long user_id) {
    userService.deleteUser(user_id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Updates a user by ID.
   *
   * @param userDTO The updated user data.
   * @param user_id The ID of the user to be updated.
   * @return ResponseEntity<UserDTO> The updated user and HTTP status.
   */
  @PutMapping("/{user_id}/update_user")
  public ResponseEntity<UserDTO> updateUser(
      @RequestBody UserDTO userDTO, @PathVariable Long user_id) {
    UserDTO updatedUser = this.userService.updateUser(userDTO, user_id);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  /**
   * Creates a new task for a user (either a task holder or an executor).
   *
   * @param userTaskHolder_id The ID of the user for whom the task is created.
   * @param taskDTO The data of the task to be created.
   * @return ResponseEntity<TaskDTO> The created task and HTTP status.
   */
  @PostMapping("/{userTaskHolder_id}/create_task")
  public ResponseEntity<TaskDTO> createTask(
      @PathVariable long userTaskHolder_id, @RequestBody TaskDTO taskDTO) {
    TaskDTO createdTask = null; // Declare the variable outside the if-else block

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

  /**
   * Deletes a task for a user (task holder) by ID.
   *
   * @param userTaskHolder_id The ID of the task holder user.
   * @param task_id The ID of the task to be deleted.
   * @return ResponseEntity<Void> HTTP status indicating success or failure.
   */
  @DeleteMapping("/{userTaskHolder_id}/delete_task/{task_id}")
  public ResponseEntity<Void> deleteTask(
      @PathVariable long userTaskHolder_id, @PathVariable long task_id) {
    taskService.deleteTaskForUser(userTaskHolder_id, task_id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Updates a task for a user (either a task holder or an executor) by ID.
   *
   * @param userTaskHolder_id The ID of the user for whom the task is updated.
   * @param task_id The ID of the task to be updated.
   * @param updatedTaskDTO The updated data for the task.
   * @return ResponseEntity<TaskDTO> The updated task and HTTP status.
   */
  @PutMapping("/{userTaskHolder_id}/update_task/{task_id}")
  public ResponseEntity<TaskDTO> updateTask(
      @PathVariable long userTaskHolder_id,
      @PathVariable long task_id,
      @RequestBody TaskDTO updatedTaskDTO) {

    TaskDTO updatedTask = taskService.updateTaskForUser(userTaskHolder_id, task_id, updatedTaskDTO);
    return new ResponseEntity<>(updatedTask, HttpStatus.OK);
  }

  /**
   * Retrieves all tasks with comments for a user (executor) by ID.
   *
   * @param userTaskExecutor_id The ID of the user (executor).
   * @return ResponseEntity<List<Map<String, Object>>> List of tasks with associated comments and
   *     HTTP status.
   */
  @GetMapping("/{userTaskExecutor_id}/getAll_tasks_comments_executor")
  public ResponseEntity<List<Map<String, Object>>> getAllTasksCommentsByExecutorId(
      @PathVariable Long userTaskExecutor_id) {
    List<Task> tasks = taskRepository.findByUserAndUserExecutorTrue(userTaskExecutor_id);

    if (!tasks.isEmpty()) {
      // Assuming you have a service method to retrieve all comments for tasks
      List<Map<String, Object>> tasksWithComments = new ArrayList<>();

      for (Task task : tasks) {
        List<CommentDTO> comments = commentService.getAllCommentsForTask(task.getId());

        Map<String, Object> taskWithComments = new HashMap<>();
        taskWithComments.put("task", task);
        taskWithComments.put("comments", comments);

        tasksWithComments.add(taskWithComments);
      }

      return new ResponseEntity<>(tasksWithComments, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or another appropriate status
    }
  }

  /**
   * Retrieves all tasks with comments for a user (task holder) by ID.
   *
   * @param userTaskHolder_id The ID of the user (task holder).
   * @return ResponseEntity<List<Map<String, Object>>> List of tasks with associated comments and
   *     HTTP status.
   */
  @GetMapping("/{userTaskHolder_id}/getAll_tasks_comments_holder")
  public ResponseEntity<List<Map<String, Object>>> getAllTasksCommentsByTaskHolderId(
      @PathVariable Long userTaskHolder_id) {
    List<Task> tasks = taskRepository.findByUserAndUserExecutorFalse(userTaskHolder_id);

    if (!tasks.isEmpty()) {
      // Assuming you have a service method to retrieve all comments for tasks
      List<Map<String, Object>> tasksWithComments = new ArrayList<>();

      for (Task task : tasks) {
        List<CommentDTO> comments = commentService.getAllCommentsForTask(task.getId());

        Map<String, Object> taskWithComments = new HashMap<>();
        taskWithComments.put("task", task);
        taskWithComments.put("comments", comments);

        tasksWithComments.add(taskWithComments);
      }

      return new ResponseEntity<>(tasksWithComments, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or another appropriate status
    }
  }

  /**
   * Retrieves a task and its comments for a user (task holder) by task and user IDs.
   *
   * @param userTaskHolder_id The ID of the user (task holder).
   * @param task_id The ID of the task.
   * @return ResponseEntity<Map<String, Object>> Task and associated comments with HTTP status.
   */
  @GetMapping("/{userTaskHolder_id}/{task_id}/getAll_task_comment_holder_id_task_id")
  public ResponseEntity<Map<String, Object>> getTaskAndCommentsOfTaskHolderByIds(
      @PathVariable Long userTaskHolder_id, @PathVariable Long task_id) {

    // Check if the user is associated with the task
    if (taskService.isUserAssociatedWithTask(task_id, userTaskHolder_id)) {
      // If associated, retrieve comments for the task
      List<CommentDTO> comments = commentService.getAllCommentsForTask(task_id);

      // Construct the response map
      Map<String, Object> taskWithComments = new HashMap<>();
      taskWithComments.put("task_id", task_id);
      taskWithComments.put("comments", comments);

      return new ResponseEntity<>(taskWithComments, HttpStatus.OK);
    } else {
      // If not associated, return a not found response or another appropriate status
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Adds an executor to a task for a user (task holder).
   *
   * @param userTaskHolder_id The ID of the user (task holder).
   * @param task_id The ID of the task.
   * @param userExecutor_id The ID of the user (executor) to be added.
   * @return ResponseEntity<String> Message indicating success and HTTP status.
   */
  @PostMapping("/{userTaskHolder_id}/{task_id}/addExecutor/{userExecutor_id}")
  public ResponseEntity<String> addExecutorToTask(
      @PathVariable long userTaskHolder_id,
      @PathVariable long task_id,
      @PathVariable long userExecutor_id) {
    taskService.addExecutorToTask(userTaskHolder_id, task_id, userExecutor_id);
    return new ResponseEntity<>("Executor added successfully", HttpStatus.OK);
  }

  /**
   * Removes an executor from a task for a user (task holder).
   *
   * @param userTaskHolder_id The ID of the user (task holder).
   * @param task_id The ID of the task.
   * @param userExecutor_id The ID of the user (executor) to be removed.
   * @return ResponseEntity<String> Message indicating success and HTTP status.
   */
  @PostMapping("/{userTaskHolder_id}/{task_id}/removeExecutor/{userExecutor_id}")
  public ResponseEntity<String> removeExecutorFromTask(
      @PathVariable long userTaskHolder_id,
      @PathVariable long task_id,
      @PathVariable long userExecutor_id) {
    taskService.removeExecutorFromTask(userTaskHolder_id, task_id, userExecutor_id);
    return new ResponseEntity<>("Executor removed successfully", HttpStatus.OK);
  }

  /**
   * Updates the status of a task.
   *
   * @param task_id The ID of the task to update.
   * @param status_code The new status code for the task.
   * @return ResponseEntity<String> Message indicating success and HTTP status.
   */
  @PostMapping("/update_task_status/{task_id}/{status_code}")
  public ResponseEntity<String> updateTaskStatus(
      @PathVariable Long task_id, TaskStatus status_code) {

    TaskDTO taskForStatusUpdate = taskService.getTaskById(task_id);

    taskForStatusUpdate.setStatus(status_code);
    taskService.updateTask(taskForStatusUpdate);

    return new ResponseEntity<>("Task status updated successfully", HttpStatus.OK);
  }

  /**
   * Updates the priority of a task.
   *
   * @param task_id The ID of the task to update.
   * @param priority_code The new priority code for the task.
   * @return ResponseEntity<String> Message indicating success and HTTP status.
   */
  @PostMapping("/update_task_priority/{task_id}/{priority_code}")
  public ResponseEntity<String> updateTaskPriority(
      @PathVariable Long task_id, TaskPriority priority_code) {

    TaskDTO taskForPriorityUpdate = taskService.getTaskById(task_id);

    taskForPriorityUpdate.setPriority(priority_code);
    taskService.updateTask(taskForPriorityUpdate);

    return new ResponseEntity<>("Task status updated successfully", HttpStatus.OK);
  }

  /**
   * Retrieves tasks for a user (task holder) based on the specified status.
   *
   * @param userTaskHolder_id The ID of the user (task holder).
   * @param status The status of the tasks to retrieve.
   * @param page The page number for paginated results.
   * @param size The number of tasks per page.
   * @param sort The sorting criteria for the results.
   * @return ResponseEntity<Page<TaskDTO>> Paginated list of tasks and HTTP status.
   */
  @GetMapping("/{userTaskHolder_id}/getAll_user_tasks_by_status/{status}")
  public ResponseEntity<Page<TaskDTO>> getTasksByStatus(
      @PathVariable Long userTaskHolder_id,
      @PathVariable String status,
      @RequestParam(required = false, defaultValue = "1") Integer page,
      @RequestParam(required = false, defaultValue = "10") Integer size,
      @RequestParam(required = false, defaultValue = "id") String sortBy,
      @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

    try {
      Page<TaskDTO> tasks;

      // Check if page, size, and sort are provided
      if (page != null && size != null && sortOrder != null) {
        tasks =
            taskService.getTasksByStatus(
                userTaskHolder_id,
                status,
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy)));
      } else {
        // If not provided, use default values
        tasks =
            taskService.getTasksByStatus(
                userTaskHolder_id,
                status,
                PageRequest.of(0, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy)));
      }

      return ResponseEntity.ok(tasks);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new PageImpl<>(Collections.emptyList()));
    }
  }

  /**
   * Retrieves tasks for a user (task holder) based on the specified priority.
   *
   * @param userTaskHolder_id The ID of the user (task holder).
   * @param priority The priority of the tasks to retrieve.
   * @param page The page number for paginated results.
   * @param size The number of tasks per page.
   * @param sort The sorting criteria for the results.
   * @return ResponseEntity<Page<TaskDTO>> Paginated list of tasks and HTTP status.
   */
  @GetMapping("/{userTaskHolder_id}/getAll_user_tasks_by_priority/{priority}")
  public ResponseEntity<Page<TaskDTO>> getTasksByPriority(
      @PathVariable Long userTaskHolder_id,
      @PathVariable String priority,
      @RequestParam(required = false, defaultValue = "1") Integer page,
      @RequestParam(required = false, defaultValue = "10") Integer size,
      @RequestParam(required = false, defaultValue = "id") String sortBy,
      @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

    try {
      Page<TaskDTO> tasks;

      // Check if page, size, and sort are provided
      if (page != null && size != null && sortOrder != null) {
        tasks =
            taskService.getTasksByPriority(
                userTaskHolder_id,
                priority,
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy)));
      } else {
        // If not provided, use default values
        tasks =
            taskService.getTasksByPriority(
                userTaskHolder_id,
                priority,
                PageRequest.of(0, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy)));
      }

      return ResponseEntity.ok(tasks);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new PageImpl<>(Collections.emptyList()));
    }
  }
}
