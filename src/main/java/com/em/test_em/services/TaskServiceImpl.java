package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;
import com.em.test_em.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserService userService;
    
    @Override
    public List<Task> findTasksWithTrueExecutors() {
        return taskRepository.findByExecutorsExecutorTrue();
    }
    
    @Override
    public Task addExecutorToTask(Long taskId, Long userId) {
        Optional<Task> taskOptional = getTaskById(taskId);
        Optional<User> executorOptional = userService.getUserById(userId);

        // Check if both task and executor are present
        if (taskOptional.isPresent() && executorOptional.isPresent()) {
            Task task = taskOptional.get();
            User executor = executorOptional.get();

            // Check if executor is not already in the task's executors list
            if (!task.getExecutors().contains(executor)) {
                task.getExecutors().add(executor);
                return taskRepository.save(task);
            }
        }

        // Task or executor not found or executor is already in the list
        return null; // You might want to handle this case appropriately
    }

    @Override
    public Task removeExecutorFromTask(Long taskId, Long userId) {
        Optional<Task> taskOptional = getTaskById(taskId);
        Optional<User> executorOptional = userService.getUserById(userId);

        // Check if both task and executor are present
        if (taskOptional.isPresent() && executorOptional.isPresent()) {
            Task task = taskOptional.get();
            User executor = executorOptional.get();

            // Remove the executor from the task's executors list
            task.getExecutors().remove(executor);
            return taskRepository.save(task);
        }

        // Task or executor not found
        return null; // You might want to handle this case appropriately
    }
    
    
    //get all tasks
    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }  
    
    //get task by id
    @Override
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById((long) id);
    }  
    
    //create task
    @Override
    public Task createTask(Task task) {
        return this.taskRepository.save(task);
    }
    
    //update task
    @Override
    public Task updateTask(Task task) {
        if (!this.taskRepository.existsById(task.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find the task to update");
        }
        return this.taskRepository.save(task);
    }
    
    //delete task
    @Override
    public void deleteTask(Long id) {
        if (!this.taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find task to delete");
        }
        this.taskRepository.deleteById(id);
        if (this.taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Error deleting task");
        }
    }

}
