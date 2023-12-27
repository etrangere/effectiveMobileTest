package com.em.test_em.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.em.test_em._DTO.TaskDTO;
import com.em.test_em.services.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin()
@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task", description = "Task management APIs")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // read all executors of a task
    @GetMapping("/executors/{user_executorId}")
    public ResponseEntity<List<TaskDTO>> getTasksWithTrueExecutors(@PathVariable Long user_executorId) {
        List<TaskDTO> tasks = taskService.findTasksWithTrueExecutors(user_executorId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // add executor to a task
    @PostMapping("/{userId}/addExecutor/{taskId}")
    public ResponseEntity<TaskDTO> addExecutorToTask(@PathVariable Long taskId, @PathVariable Long userId) {
        TaskDTO updatedTask = taskService.addExecutorToTask(taskId, userId);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    // remove executor from a task
    @DeleteMapping("/{userId}/removeExecutor/{taskId}")
    public ResponseEntity<TaskDTO> removeExecutorFromTask(@PathVariable Long taskId, @PathVariable Long userId) {
        TaskDTO updatedTask = taskService.removeExecutorFromTask(taskId, userId);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    // get all tasks
    @GetMapping(value = "/getAllTasks")
    @ResponseStatus(code = HttpStatus.OK)
    public List<TaskDTO> findAll() {
        return this.taskService.getAllTasks();
    }

    // get task by id
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
        Optional<TaskDTO> task = this.taskService.getTaskById(id);
        return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // create task
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = this.taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // update task
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO, @PathVariable("id") Long id) {
        if (!id.equals(taskDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TaskDTO updatedTask = this.taskService.updateTask(taskDTO);
        return new ResponseEntity<>(updatedTask, HttpStatus.ACCEPTED);
    }

    // delete task
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.taskService.deleteTask(id);
    }
}
