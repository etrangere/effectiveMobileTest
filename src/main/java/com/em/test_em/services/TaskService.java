package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.em.test_em.beans.Task;
import com.em.test_em.repositories.TaskRepository;

@Service
public class TaskService {

    
    @Autowired
    private TaskRepository taskRepository;
    
    // get all tasks
    
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }  
    
    // get task by id
    
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById((long) id);
    }  
    
    
    //create task
    public Task create(Task task) {
        return this.taskRepository.save(task);
    }
    
    //update task
    public Task update(Task task) {
        if (!this.taskRepository.existsById(task.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Impossible de trouver le resource à mettre à jour");
        }
        return this.taskRepository.save(task);
    }
    
    //delete task
    public void delete(Long id) {
        if (!this.taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Impossible de trouver le project à supprimer");
        }
        this.taskRepository.deleteById(id);
        if (this.taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Erreur lors de la suppression de project");
        }
    }
}