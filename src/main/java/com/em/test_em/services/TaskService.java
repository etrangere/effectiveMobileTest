package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import com.em.test_em.beans.Task;


public interface TaskService {

    List<Task> findTasksWithTrueExecutors();

    Task addExecutorToTask(Long taskId, Long userId);

    Task removeExecutorFromTask(Long taskId, Long userId);

    List<Task> getAllTasks();

    Optional<Task> getTaskById(Long id);

    Task createTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Long id);
}