package com.em.test_em.services;


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
    private CommentsService commentsService;
    
    @Autowired
    private ModelMapper mapper;

    public List<TaskDTO> findTasksWithTrueExecutors(UserDTO executor) {
        User user = mapToEntityUSER(executor);
        List<Task> tasks = taskRepository.findByTask_user_executorsInAndTask_user_executorsExecutorTrue(user);
        return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
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
            if (!taskDTO.getTask_user_executors().contains(executorDTO)) {
                Task task = mapToEntity(taskDTO);
                User executor = mapToEntityUSER(executorDTO);

                // Perform the necessary operations
                task.getTask_user_executors().add(executor);

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
            task.getTask_user_executors().remove(executor);

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
    public Optional<TaskDTO> getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(this::mapToDTO);
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

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task to delete");
        }
        taskRepository.deleteById(id);
        if (taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error deleting task");
        }
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
