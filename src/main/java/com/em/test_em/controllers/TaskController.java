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

import com.em.test_em.beans.Comments;
import com.em.test_em.beans.Task;
import com.em.test_em.services.CommentsService;
import com.em.test_em.services.TaskService;

@CrossOrigin()
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private CommentsService commentsService;
    
    @GetMapping(value = "/getAllTasks")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Task> findAll(){
        return this.taskService.getAllTask();
    }
    //get task with id
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Optional<Task> findById(@PathVariable Long id){
        return this.taskService.getTaskById(id);
    }
    
    //create task    
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        return this.taskService.create(task);
    }
    
 // add comment to task
    @PutMapping("/{taskId}/addComment")
    public ResponseEntity<Task> addCommentToTask(@PathVariable Long taskId, @RequestBody Comments comment) {
        try {
            // Fetch the task by ID
            Optional<Task> taskOptional = taskService.getTaskById(taskId);

            // Check if the task exists
            if (taskOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Get the task from Optional
            Task task = taskOptional.get();

            // Set the task for the comment
            comment.setTask(task);

            // Save the comment
            Comments savedComment = commentsService.create(comment);

            // Add the comment to the task's comments list
            task.getComments().add(savedComment);

            // Update the task
            taskService.update(task);

            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    //update task    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Task update(@RequestBody Task task, @PathVariable("id") Long id) {
        if (!id.equals(task.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Wrong session to update");
        }
        return this.taskService.update(task);
    }
    
    //delete task
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.taskService.delete(id);
    }
}
