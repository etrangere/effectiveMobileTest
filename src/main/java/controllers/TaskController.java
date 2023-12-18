package controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import beans.Task;
import services.TaskService;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
 
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/getTask", method = RequestMethod.GET, produces = "application/json")
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
    
    //update task    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Task update(@RequestBody Task task, @PathVariable("id") Long id) {
        if (!id.equals(task.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Mauvaise session à mettre à jour");
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
