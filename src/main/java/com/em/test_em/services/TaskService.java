package com.em.test_em.services;

import java.util.List;


import com.em.test_em._DTO.TaskDTO;




public interface TaskService {

  
    List<TaskDTO> getAllTasks();
    
    TaskDTO getTaskById(Long task_id);
  
    TaskDTO createTask(TaskDTO task);
    
    TaskDTO createTaskForUserTaskHolder(Long userTaskHolder_id, TaskDTO taskDTO);

    TaskDTO updateTask(TaskDTO task);
    
    TaskDTO updateTaskForUser(Long userTaskHolder_id, Long task_id, TaskDTO updatedTaskDTO);
    
  
    
    List<TaskDTO> getAllTasksForTaskHolder(Long userTaskHolder_id);

    List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id);

    void deleteTaskForUser(Long userTaskHolder_id, Long task_id);
    
    void removeExecutorFromTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);
    
    void addExecutorToTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);

    
    
    
}