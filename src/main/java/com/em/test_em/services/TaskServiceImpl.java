package com.em.test_em.services;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.em.test_em._DTO.TaskDTO;
import com.em.test_em._DTO.UserDTO;
import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;
import com.em.test_em.repositories.TaskRepository;


import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;
    
   // @Autowired
  //  private CommentService commentsService;
    
    @Autowired
    private ModelMapper mapper;

    
    public List<TaskDTO> getAllTasksForTaskHolder(Long userTaskHolder_id) {
        UserDTO usertaskholderoptional = userService.getUserById(userTaskHolder_id);

        if (!(usertaskholderoptional==null)) {
            List<Task> tasks = taskRepository.findByUserAndUserExecutorFalse(mapToEntityUSER(usertaskholderoptional));
            return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // or throw an exception, return null, etc.
        }
    }
    
    public List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id) {
        UserDTO usertaskexecutoroptional = userService.getUserById(userTaskExecutor_id);

        if (!(usertaskexecutoroptional==null)) {
            List<Task> tasks = taskRepository.findByUserAndUserExecutorTrue(mapToEntityUSER(usertaskexecutoroptional));
            return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // or throw an exception, return null, etc.
        }
    }
    
    @Override 
    public void deleteTaskForUser(Long userTaskHolder_id, Long task_id) {
        
        UserDTO usertasktodelete = userService.getUserById(userTaskHolder_id);
      
        Optional<TaskDTO> taskToDelete = usertasktodelete.getTask().stream()
                .filter(task -> Long.valueOf(task.getId()).equals(task_id))
                .findFirst();
       
        if (taskToDelete.isPresent()) {
            usertasktodelete.getTask().remove(taskToDelete.get());
            taskRepository.deleteById(task_id);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task to delete");
        } 
       
    }

    @Override
    public TaskDTO createTaskForUserTaskHolder(Long userTaskHolder_id, TaskDTO taskDTO) {
       
        UserDTO userTaskHolder = userService.getUserById(userTaskHolder_id);
   
        if (userTaskHolder != null) {
            Task task = new Task();
            task.setHeader(taskDTO.getHeader());
            task.setDescription(taskDTO.getDescription());
            task.setStatus(taskDTO.getStatus());
            task.setPriority(taskDTO.getPriority());
            task.setAuthor(taskDTO.getAuthor());
         
            List<TaskDTO> userTasks = userTaskHolder.getTask();
            userTasks.add(convertToTaskDTO(task));
         
            // Associate the task with the userTaskHolder
            userTaskHolder.getTask().add(mapToDTO(task));

            // Set the userTaskHolder for the task
            task.getUser().add(mapToEntityUSER(userTaskHolder));
            
            
            Task savedTask = taskRepository.save(task);
            return convertToTaskDTO(savedTask);
        }else {
            // Handle the case where the user is not found 
        }
        return taskDTO;  
    }
      
    @Override
    public TaskDTO updateTaskForUser(Long userTaskHolder_id, Long task_id, TaskDTO updatedTaskDTO) {
        UserDTO userForTaskToUpdate = userService.getUserById(userTaskHolder_id);

        // Check if the task with task_id exists in the user's tasks
        Optional<TaskDTO> taskToUpdateOptional = userForTaskToUpdate.getTask()
                .stream()
                .filter(task -> Long.valueOf(task.getId()).equals(task_id))
                .findFirst();

        if (taskToUpdateOptional.isPresent()) {
            // Update the properties of the found task
            TaskDTO taskToUpdate = taskToUpdateOptional.get();
            taskToUpdate.setHeader(updatedTaskDTO.getHeader());
            taskToUpdate.setDescription(updatedTaskDTO.getDescription());
            taskToUpdate.setStatus(updatedTaskDTO.getStatus());
            taskToUpdate.setPriority(updatedTaskDTO.getPriority());
            taskToUpdate.setAuthor(updatedTaskDTO.getAuthor());
         
            Task updatedTask = taskRepository.save(mapToEntity(taskToUpdate));

          
            return mapToDTO(updatedTask);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find the task to update");
        }
    }

    @Override
    public void removeExecutorFromTask(Long userTaskHolder_id, Long task_id, Long userExecutor_id) {
        Optional<Task> taskToRemoveExecutor = taskRepository.findById(task_id);//check after not work with taskService
        UserDTO taskUserExecutor = userService.getUserById(userExecutor_id);
        UserDTO taskUserHolder = userService.getUserById(userTaskHolder_id);

        System.out.println(taskUserExecutor.toString());
        
        //Check for both task and executor are present
        if (!(taskToRemoveExecutor == null)  && !(taskUserExecutor == null)) {
            Task task = taskToRemoveExecutor.get();
            
            if (taskUserHolder.getTask()!= null) {
               
                if (task.getUser() != null) {
                  
                    mapToDTO(task).getUser().remove(taskUserExecutor);
                  taskRepository.save(task);
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Executor not found in the task");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for the specified user task holder");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task or executor not found");
        }
    }

  
    
  
    @Override
    public void addExecutorToTask(Long userTaskHolder_id, Long task_id, Long userExecutor_id) {
        Optional<Task> taskToAddExecutorOptional = taskRepository.findById(task_id);
        UserDTO taskUserExecutor = userService.getUserById(userExecutor_id);
        UserDTO taskUserHolder = userService.getUserById(userTaskHolder_id);
       
        // Check if both task and executor are present
        if (taskToAddExecutorOptional.isPresent() && taskUserExecutor != null) {
            Task taskToAddExecutor = taskToAddExecutorOptional.get();

            if (taskUserHolder.getTask()!= null) {

                if (taskToAddExecutor.getUser() != null) {
                    taskToAddExecutor.getUser().add(mapToEntityUSER(taskUserExecutor));
                    taskRepository.save(taskToAddExecutor);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Executor is already assigned to the task");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for the specified user task holder");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task or executor not found");
        }
    }

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        System.out.println(tasks.getClass().getName()); // Print class name of the list

        // Assuming you have a mapToDTOList method to convert List<Task> to List<TaskDTO>
        return mapToDTOList(tasks);
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(this::mapToDTO).orElseThrow(EntityNotFoundException::new);
    }
    
   
    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = mapToEntity(taskDTO);
        return mapToDTO(taskRepository.save(task));
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) {
        if (!taskRepository.existsById(taskDTO.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find the task to update");
        }
        Task task = mapToEntity(taskDTO);
        return mapToDTO(taskRepository.save(task));
    }

    

    private TaskDTO convertToTaskDTO(Task task) {
        return mapper.map(task, TaskDTO.class);
    }
    public TaskDTO mapToDTO(Task task) {
        return mapper.map(task, TaskDTO.class);
    }

    private List<TaskDTO> mapToDTOList(List<Task> tasks) {
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private Task mapToEntity(TaskDTO taskDTO) {
        return mapper.map(taskDTO, Task.class);
    }
    
    private User mapToEntityUSER(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }

   

}
