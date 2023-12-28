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

import com.em.test_em._DTO.CommentsDTO;
import com.em.test_em._DTO.TaskDTO;
import com.em.test_em._DTO.UserDTO;
import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;
import com.em.test_em.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private CommentService commentsService;
    
    @Autowired
    private ModelMapper mapper;

    
    public List<TaskDTO> getAllTasksForTaskHolder(Long userTaskHolder_id) {
        UserDTO usertaskholderoptional = userService.getUserById(userTaskHolder_id);

        if (!(usertaskholderoptional==null)) {
            List<Task> tasks = taskRepository.findByUserAndUserExecutorFalse(mapToEntityUSER(usertaskholderoptional));
            return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
        } else {
            // Handle the case when the holder is not found
            return Collections.emptyList(); // or throw an exception, return null, etc.
        }
    }
    
    public List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id) {
        UserDTO usertaskexecutoroptional = userService.getUserById(userTaskExecutor_id);

        if (!(usertaskexecutoroptional==null)) {
            List<Task> tasks = taskRepository.findByUserAndUserExecutorTrue(mapToEntityUSER(usertaskexecutoroptional));
            return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
        } else {
            // Handle the case when the executor is not found
            return Collections.emptyList(); // or throw an exception, return null, etc.
        }
    }
    
    @Override
    
    public void deleteTaskForUser(Long userTaskHolder_id, Long task_id) {
        
        UserDTO usertasktodelete = userService.getUserById(userTaskHolder_id);
        List<TaskDTO> taskDTOlist = usertasktodelete.getTask();
        
        if (!taskRepository.existsById(task_id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task to delete");
        }
        if (!usertasktodelete.getTask().contains(task_id)) {
            
            // Perform the necessary operations
            usertasktodelete.getTask().remove(task_id);
         
        }else {
            // Handle the case where the user is not found
            
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public TaskDTO createTaskForUserTaskHolder(Long userTaskHolder_id, TaskDTO taskDTO) {
       
        UserDTO userOptional = userService.getUserById(userTaskHolder_id);
   
        if (!taskDTO.getUser().contains(taskDTO)) {
            Task task = new Task();
            User userTaskHolder = mapToEntityUSER(userOptional);

            // Perform the necessary operations
            userTaskHolder.setTask(task);
         // Save the task
            Task savedTask = taskRepository.save(task);

            // Convert the savedTask to TaskDTO and return
            return convertToTaskDTO(savedTask);
        }else {
            // Handle the case where the user is not found
            
        }
        
    }
    

    @Override
    public TaskDTO addExecutorToTask(Long taskId, Long userId) {
        Optional<TaskDTO> taskOptional = getTaskById(taskId);
        Optional<UserDTO> executorOptional = userService.getUserById(userId);

        // Check if both task and executor are present
        if (taskOptional.isPresent() && executorOptional.isPresent()) {
            TaskDTO taskDTO = taskOptional.get();
            UserDTO executorDTO = executorOptional.get();

            // Check if executor is not already in the task's executors list
            if (!taskDTO.getUser().contains(executorDTO)) {
                Task task = mapToEntity(taskDTO);
                User executor = mapToEntityUSER(executorDTO);

                // Perform the necessary operations
                task.getUser().add(executor);

                // Save and flush with a temporary variable
                Task savedTask = taskRepository.save(task);

                // Return the mapped DTO
                return mapToDTO(savedTask);
            }
        }

        // Task or executor not found or executor is already in the list
        return null; // You might want to handle this case appropriately
    }

    @Override
    public TaskDTO removeExecutorFromTask(Long taskId, Long userId) {
        Optional<TaskDTO> taskOptional = getTaskById(taskId);
        Optional<UserDTO> executorOptional = userService.getUserById(userId);

        // Check if both task and executor are present
        if (taskOptional.isPresent() && executorOptional.isPresent()) {
            TaskDTO taskDTO = taskOptional.get();
            UserDTO executorDTO = executorOptional.get();

            // Perform the necessary operations
            Task task = mapToEntity(taskDTO);
            User executor = mapToEntityUSER(executorDTO);
            task.getUser().remove(executor);

            // Save and flush with a temporary variable
            Task savedTask = taskRepository.save(task);

            // Return the mapped DTO
            return mapToDTO(savedTask);
        }

        // Task or executor not found
        return null; // You might want to handle this case appropriately
    }
    
    @Override
    public List<TaskDTO> getTasksWithCommentsByUser(Long userId) {
        List<Task> tasks = taskRepository.findAll(); // Assuming you have a method like this to get all tasks
        return tasks.stream()
                .map(task -> {
                    TaskDTO taskWithComments = mapToDTO(task); // This assumes you have a mapToDTO for Task entities
                    List<CommentsDTO> comments = commentsService.getCommentsByTask(task.getId());
                    taskWithComments.setComments(comments);
                    return taskWithComments;
                })
                .collect(Collectors.toList());
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
        return taskOptional.map(this::mapToDTO).orElse(null);
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
