package com.em.test_em.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.em.test_em._DTO.TaskDTO;




public interface TaskService {

  
    List<TaskDTO> getAllTasks();
    
    TaskDTO getTaskById(Long task_id);
  
    TaskDTO createTask(TaskDTO task);
    
    TaskDTO createTaskForUserTaskHolder(Long userTaskHolder_id, TaskDTO taskDTO);

    TaskDTO updateTask(TaskDTO task);
    
    TaskDTO updateTaskForUser(Long userTaskHolder_id, Long task_id, TaskDTO updatedTaskDTO);

    List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id);

    void deleteTaskForUser(Long userTaskHolder_id, Long task_id);
    
    void removeExecutorFromTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);
    
    void addExecutorToTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);

    boolean isUserAssociatedWithTask(Long taskId, Long userId);
    
    Page<TaskDTO> getTasksByStatus(Long userTaskHolder_id, String status, Pageable pageable);

    Page<TaskDTO> getTasksByPriority(Long userTaskHolder_id, String priority, Pageable pageable);
}