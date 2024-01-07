package com.em.test_em.services;

import com.em.test_em._DTO.TaskDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Service interface for handling operations related to tasks. */
public interface TaskService {

  /**
   * Retrieves all tasks.
   *
   * @return A list of TaskDTOs representing all tasks.
   */
  List<TaskDTO> getAllTasks();

  /**
   * Retrieves a task by its ID.
   *
   * @param taskId The ID of the task.
   * @return The TaskDTO if found, otherwise null.
   */
  TaskDTO getTaskById(Long task_id);

  /**
   * Creates a new task.
   *
   * @param task The TaskDTO containing the task details.
   * @return The created TaskDTO.
   */
  TaskDTO createTask(TaskDTO task);

  /**
   * Creates a new task for a specific user (task holder).
   *
   * @param userTaskHolderId The ID of the user (task holder).
   * @param taskDTO The TaskDTO containing the task details.
   * @return The created TaskDTO.
   */
  TaskDTO createTaskForUserTaskHolder(Long userTaskHolder_id, TaskDTO taskDTO);

  /**
   * Updates an existing task.
   *
   * @param task The updated TaskDTO.
   * @return The updated TaskDTO.
   */
  TaskDTO updateTask(TaskDTO task);

  /**
   * Updates an existing task for a specific user.
   *
   * @param userTaskHolderId The ID of the user (task holder).
   * @param taskId The ID of the task to be updated.
   * @param updatedTaskDTO The updated TaskDTO.
   * @return The updated TaskDTO.
   */
  TaskDTO updateTaskForUser(Long userTaskHolder_id, Long task_id, TaskDTO updatedTaskDTO);

  /**
   * Retrieves all tasks associated with a specific task executor (user).
   *
   * @param userTaskExecutorId The ID of the user (task executor).
   * @return A list of TaskDTOs associated with the specified task executor.
   */
  List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id);

  /**
   * Deletes a task associated with a specific user (task holder).
   *
   * @param userTaskHolderId The ID of the user (task holder).
   * @param taskId The ID of the task to be deleted.
   */
  void deleteTaskForUser(Long userTaskHolder_id, Long task_id);

  /**
   * Removes a task executor from a specific task.
   *
   * @param userTaskHolderId The ID of the user (task holder).
   * @param taskId The ID of the task.
   * @param userExecutorId The ID of the user (task executor) to be removed.
   */
  void removeExecutorFromTask(Long userTaskHolder_id, Long task_id, Long userExecutor_id);

  /**
   * Adds a task executor to a specific task.
   *
   * @param userTaskHolderId The ID of the user (task holder).
   * @param taskId The ID of the task.
   * @param userExecutorId The ID of the user (task executor) to be added.
   */
  void addExecutorToTask(Long userTaskHolder_id, Long task_id, Long userExecutor_id);

  /**
   * Checks if a user is associated with a specific task.
   *
   * @param taskId The ID of the task.
   * @param userId The ID of the user.
   * @return True if the user is associated with the task, false otherwise.
   */
  boolean isUserAssociatedWithTask(Long taskId, Long userId);

  /**
   * Retrieves tasks by status for a specific user (task holder) with pagination.
   *
   * @param userTaskHolderId The ID of the user (task holder).
   * @param status The status of the tasks.
   * @param pageable The Pageable object for pagination.
   * @return A page of TaskDTOs based on the specified status and user ID.
   */
  Page<TaskDTO> getTasksByStatus(Long userTaskHolder_id, String status, Pageable pageable);

  /**
   * Retrieves tasks by priority for a specific user (task holder) with pagination.
   *
   * @param userTaskHolderId The ID of the user (task holder).
   * @param priority The priority of the tasks.
   * @param pageable The Pageable object for pagination.
   * @return A page of TaskDTOs based on the specified priority and user ID.
   */
  Page<TaskDTO> getTasksByPriority(Long userTaskHolder_id, String priority, Pageable pageable);
}
