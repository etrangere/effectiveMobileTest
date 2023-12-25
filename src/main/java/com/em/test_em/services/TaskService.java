package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.TaskDTO;


public interface TaskService {

    List<TaskDTO> findTasksWithTrueExecutors();

    TaskDTO addExecutorToTask(Long taskId, Long userId);

    TaskDTO removeExecutorFromTask(Long taskId, Long userId);

    List<TaskDTO> getAllTasks();

    Optional<TaskDTO> getTaskById(Long id);

    TaskDTO createTask(TaskDTO task);

    TaskDTO updateTask(TaskDTO task);

    void deleteTask(Long id);
}