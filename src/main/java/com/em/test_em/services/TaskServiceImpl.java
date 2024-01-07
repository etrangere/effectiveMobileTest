package com.em.test_em.services;

import com.em.test_em._DTO.TaskDTO;
import com.em.test_em._DTO.UserDTO;
import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;
import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;
import com.em.test_em.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/** Service implementation for managing task-related operations. */
@Service
public class TaskServiceImpl implements TaskService {

  @Autowired private TaskRepository taskRepository;

  @Autowired private UserService userService;

  @Autowired private ModelMapper mapper;

  /**
   * Retrieves a list of all tasks.
   *
   * @return List of TaskDTO containing details of all tasks.
   */
  @Override
  public List<TaskDTO> getAllTasks() {
    List<Task> tasks = taskRepository.findAll();
    System.out.println(tasks.getClass().getName()); // Print class name of the list

    return mapToDTOList(tasks);
  }

  /**
   * Retrieves a task by its unique identifier.
   *
   * @param id The unique identifier of the task.
   * @return TaskDTO containing details of the specified task.
   * @throws EntityNotFoundException if the task with the specified ID is not found.
   */
  @Override
  public TaskDTO getTaskById(Long id) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    return taskOptional.map(this::mapToDTO).orElseThrow(EntityNotFoundException::new);
  }

  /**
   * Creates a new task with the provided details.
   *
   * @param taskDTO Details of the task to be created.
   * @return TaskDTO containing details of the created task.
   */
  @Override
  public TaskDTO createTask(TaskDTO taskDTO) {
    Task task = mapToEntity(taskDTO);
    return mapToDTO(taskRepository.save(task));
  }

  /**
   * Updates an existing task with new details.
   *
   * @param taskDTO Details to update the task.
   * @return TaskDTO containing details of the updated task.
   * @throws ResponseStatusException with HTTP status 404 (Not Found) if the task is not found.
   */
  @Override
  public TaskDTO updateTask(TaskDTO taskDTO) {
    if (!taskRepository.existsById(taskDTO.getId())) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find the task to update");
    }
    Task task = mapToEntity(taskDTO);
    return mapToDTO(taskRepository.save(task));
  }

  /**
   * Retrieves all tasks associated with a task executor.
   *
   * @param userTaskExecutor_id The unique identifier of the task executor.
   * @return List of TaskDTO containing details of tasks associated with the task executor.
   */
  public List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id) {
    UserDTO usertaskexecutoroptional = userService.getUserById(userTaskExecutor_id);

    if (!(usertaskexecutoroptional == null)) {
      List<Task> tasks =
          taskRepository.findByUserAndUserExecutorTrue(mapToEntityUSER(usertaskexecutoroptional));
      return tasks.stream().map(this::mapToDTO).collect(Collectors.toList());
    } else {
      return Collections.emptyList(); // or throw an exception, return null, etc.
    }
  }

  /**
   * Deletes a task associated with a user.
   *
   * @param userTaskHolder_id The unique identifier of the task holder.
   * @param task_id The unique identifier of the task to be deleted.
   */
  @Override
  public void deleteTaskForUser(Long userTaskHolder_id, Long task_id) {

    UserDTO usertasktodelete = userService.getUserById(userTaskHolder_id);

    Optional<TaskDTO> taskToDelete =
        usertasktodelete.getTasks().stream()
            .filter(task -> Long.valueOf(task.getId()).equals(task_id))
            .findFirst();

    if (taskToDelete.isPresent()) {
      usertasktodelete.getTasks().remove(taskToDelete.get());
      taskRepository.deleteById(task_id);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find task to delete");
    }
  }

  /**
   * Creates a new task associated with a user task holder.
   *
   * @param userTaskHolder_id The unique identifier of the user task holder.
   * @param taskDTO Details of the task to be created.
   * @return TaskDTO containing details of the created task.
   */
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

      List<TaskDTO> userTasks = userTaskHolder.getTasks();
      userTasks.add(convertToTaskDTO(task));

      // Associate the task with the userTaskHolder
      userTaskHolder.getTasks().add(mapToDTO(task));

      // Set the userTaskHolder for the task
      task.getUsers().add(mapToEntityUSER(userTaskHolder));

      Task savedTask = taskRepository.save(task);
      return convertToTaskDTO(savedTask);
    } else {
      // Handle the case where the user is not found
    }
    return taskDTO;
  }

  /**
   * Updates an existing task associated with a user task holder.
   *
   * @param userTaskHolder_id The unique identifier of the user task holder.
   * @param task_id The unique identifier of the task to be updated.
   * @param updatedTaskDTO Details to update the task.
   * @return TaskDTO containing details of the updated task.
   * @throws ResponseStatusException with HTTP status 404 (Not Found) if the task is not found.
   */
  @Override
  public TaskDTO updateTaskForUser(Long userTaskHolder_id, Long task_id, TaskDTO updatedTaskDTO) {
    UserDTO userForTaskToUpdate = userService.getUserById(userTaskHolder_id);

    // Check if the task with task_id exists in the user's tasks
    Optional<TaskDTO> taskToUpdateOptional =
        userForTaskToUpdate.getTasks().stream()
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

  /**
   * Removes an executor from a task.
   *
   * @param userTaskHolder_id The unique identifier of the user task holder.
   * @param task_id The unique identifier of the task.
   * @param userExecutor_id The unique identifier of the user executor to be removed.
   */
  @Override
  public void removeExecutorFromTask(Long userTaskHolder_id, Long task_id, Long userExecutor_id) {
    Optional<Task> taskToRemoveExecutor = taskRepository.findById(task_id);
    UserDTO taskUserExecutor = userService.getUserById(userExecutor_id);
    UserDTO taskUserHolder = userService.getUserById(userTaskHolder_id);

    // Check for both task, executor, and holder are present
    if (taskToRemoveExecutor.isPresent() && taskUserExecutor != null && taskUserHolder != null) {
      Task task = taskToRemoveExecutor.get();

      // Convert Set<UserDTO> to List<UserDTO>
      List<UserDTO> usersList = new ArrayList<>(mapToDTO(task).getUsers());

      // Convert Long to long for comparison
      long executorId = userExecutor_id;

      // Remove the user with the specified ID from the list
      usersList.removeIf(user -> user.getId() == executorId);

      // Convert List<UserDTO> back to Set<UserDTO>
      Set<UserDTO> updatedUsersSet = new HashSet<>(usersList);

      // Update the task with the new set of users
      TaskDTO taskDTO = mapToDTO(task);
      taskDTO.setUsers(new ArrayList<>(updatedUsersSet));

      // Save the updated task
      taskRepository.save(mapToEntity(taskDTO));
    } else {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Task, executor, or holder not found");
    }
  }

  /**
   * Adds an executor to a task.
   *
   * @param userTaskHolder_id The unique identifier of the user task holder.
   * @param task_id The unique identifier of the task.
   * @param userExecutor_id The unique identifier of the user executor to be added.
   */
  @Override
  public void addExecutorToTask(Long userTaskHolder_id, Long task_id, Long userExecutor_id) {
    Optional<Task> taskToAddExecutorOptional =
        taskRepository.findById(task_id); // check after not work with taskService
    UserDTO taskUserExecutor = userService.getUserById(userExecutor_id);
    UserDTO taskUserHolder = userService.getUserById(userTaskHolder_id);

    // Check if both task and executor are present
    if (taskToAddExecutorOptional.isPresent() && taskUserExecutor != null) {
      Task taskToAddExecutor = taskToAddExecutorOptional.get();

      if (taskUserHolder.getTasks() != null) {

        if (taskToAddExecutor.getUsers() != null) {
          taskToAddExecutor.getUsers().add(mapToEntityUSER(taskUserExecutor));
          taskRepository.save(taskToAddExecutor);
        } else {
          throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST, "Executor is already assigned to the task");
        }
      } else {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Task not found for the specified user task holder");
      }
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task or executor not found");
    }
  }

  /**
   * Checks if a user is associated with a task.
   *
   * @param taskId The unique identifier of the task.
   * @param userId The unique identifier of the user.
   * @return true if the user is associated with the task, false otherwise.
   */
  public boolean isUserAssociatedWithTask(Long taskId, Long userId) {
    Optional<Task> taskOptional = taskRepository.findById(taskId);

    if (taskOptional.isPresent()) {
      Task task = taskOptional.get();
      return task.getUsers().stream().anyMatch(user -> Objects.equals(user.getId(), userId));
    }
    // Task not found
    return false;
  }

  /**
   * Retrieves tasks based on status for a user task holder.
   *
   * @param userTaskHolder_id The unique identifier of the user task holder.
   * @param status The status of the tasks to retrieve.
   * @param pageable Pageable object for pagination.
   * @return Page containing TaskDTO with filtered tasks.
   */
  @Override
  public Page<TaskDTO> getTasksByStatus(Long userTaskHolder_id, String status, Pageable pageable) {
    Page<Task> tasksPage =
        taskRepository.findByUsersIdAndUsersExecutorFalse(userTaskHolder_id, pageable);

    // Convert the String status to TaskStatus enum
    TaskStatus taskStatus = TaskStatus.valueOf(status.toUpperCase());

    // Filter tasks by status
    List<TaskDTO> filteredTasks =
        mapToDTOList(tasksPage.getContent()).stream()
            .filter(task -> task.getStatus().equals(taskStatus))
            .collect(Collectors.toList());

    return new PageImpl<>(filteredTasks, pageable, tasksPage.getTotalElements());
  }

  /**
   * Retrieves tasks based on priority for a user task holder.
   *
   * @param userTaskHolder_id The unique identifier of the user task holder.
   * @param priority The priority of the tasks to retrieve.
   * @param pageable Pageable object for pagination.
   * @return Page containing TaskDTO with filtered tasks.
   */
  @Override
  public Page<TaskDTO> getTasksByPriority(
      Long userTaskHolder_id, String priority, Pageable pageable) {
    Page<Task> tasksPage =
        taskRepository.findByUsersIdAndUsersExecutorFalse(userTaskHolder_id, pageable);

    // Convert the String status to TaskPriority enum
    TaskPriority taskPriority = TaskPriority.valueOf(priority.toUpperCase());

    // Filter tasks by status
    List<TaskDTO> filteredTasks =
        mapToDTOList(tasksPage.getContent()).stream()
            .filter(task -> task.getPriority().equals(taskPriority))
            .collect(Collectors.toList());

    return new PageImpl<>(filteredTasks, pageable, tasksPage.getTotalElements());
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
