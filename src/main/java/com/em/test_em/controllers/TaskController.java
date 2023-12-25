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

import com.em.test_em.services.TaskService;

@CrossOrigin()
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    //read all executors of a task
    @GetMapping("/withTrueExecutors")
    public ResponseEntity<List<Task>> getTasksWithTrueExecutors() {
        List<Task> tasks = taskService.findTasksWithTrueExecutors();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    
    //add executor to a task
    @PostMapping("/{taskId}/addExecutor/{userId}")
    public ResponseEntity<Task> addExecutorToTask(@PathVariable Long taskId, @PathVariable Long userId) {
        Task updatedTask = taskService.addExecutorToTask(taskId, userId);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    //remove executor from a task
    @DeleteMapping("/{taskId}/removeExecutor/{userId}")
    public ResponseEntity<Task> removeExecutorFromTask(@PathVariable Long taskId, @PathVariable Long userId) {
        Task updatedTask = taskService.removeExecutorFromTask(taskId, userId);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
    
    //get all tasks
    @GetMapping(value = "/getAllTasks")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Task> findAll(){
        return this.taskService.getAllTasks();
    }
    
    //get task by id
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<Task> findById(@PathVariable Long id){
        return this.taskService.getTaskById(id);
    }
    
    //create task    
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return this.taskService.createTask(task);
    }
    
    //update task    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Task update(@RequestBody Task task, @PathVariable("id") Long id) {
        if (!id.equals(task.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Wrong task to update");
        }
        return this.taskService.updateTask(task);
    }
    
    //delete task
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.taskService.deleteTask(id);
    }
}
