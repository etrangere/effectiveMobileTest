package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.TaskDTO;
import com.em.test_em._DTO.UserDTO;




public interface TaskService {

    List<TaskDTO> findTasksWithTrueExecutors(Long user_executorId);

    TaskDTO addExecutorToTask(Long taskId, Long userId);

    TaskDTO removeExecutorFromTask(Long taskId, Long userId);

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO createTask(TaskDTO task);
    
    TaskDTO createTaskForUserTaskHolder(Long userTaskHolder_id, TaskDTO taskDTO);

    TaskDTO updateTask(TaskDTO task);

    void deleteTask(Long id);
    
    List<TaskDTO> getAllTasksForTaskHolder(Long userTaskHolder_id);

    List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id);

    TaskDTO deleteTaskForUser(Long userTaskHolder_id, Long task_id);
}