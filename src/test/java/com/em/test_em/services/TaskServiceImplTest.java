package com.em.test_em.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.em.test_em._DTO.TaskDTO;
import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;


@ExtendWith(MockitoExtension.class)
//to keep database fresh for each test 
@Transactional
public class TaskServiceImplTest {

    @Mock
    private TaskServiceImpl taskServiceImplMock;
    
    @Test
    public void testGetAllTasks() {
        // Create some sample tasks
        TaskDTO task1 = new TaskDTO();
        task1.setId(1L);
        task1.setHeader("Task 1");

        TaskDTO task2 = new TaskDTO();
        task2.setId(2L);
        task2.setHeader("Task 2");

        // Mock the behavior of the task service to return these tasks
        when(taskServiceImplMock.getAllTasks()).thenReturn(Arrays.asList(task1, task2));

        // Call the getAllTasks method
        List<TaskDTO> result = taskServiceImplMock.getAllTasks();

        // Verify that the result matches the expected tasks
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getHeader());
        assertEquals("Task 2", result.get(1).getHeader());
    }
    
    
    @Test
    public void testgetTaskById() {
        TaskDTO expectedTaskById = new TaskDTO();
        expectedTaskById.setId(1L);

        // Mock the repository method
        when(taskServiceImplMock.getTaskById(1L)).thenReturn(expectedTaskById);

        // Call the service method to get the task by ID
        Optional<TaskDTO> result = Optional.of(taskServiceImplMock.getTaskById(1L));

        // Now, you can perform assertions on the result if needed
        assertTrue(result.isPresent());
        assertEquals(expectedTaskById, result.get());
    }

    
    @Test
    public void testCreateTask() {
        TaskDTO createdtask = Mockito.mock(TaskDTO.class);

        // Mock the repository method
        Mockito.when(taskServiceImplMock.createTask(Mockito.any(TaskDTO.class))).thenReturn(createdtask);

        // Call the service method to create a user
        TaskDTO result = taskServiceImplMock.createTask(new TaskDTO());

       
        assertNotNull(result);
        assertEquals(createdtask, result);
    }
    
    
    @Test
    public void testCreateTaskForUserTaskHolder() {
        // Specify the userTaskHolder ID and the taskDTO
        Long userTaskHolderId = 1L;

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setHeader("New Task");
        taskDTO.setDescription("description");
        taskDTO.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setAuthor("author");

        // Mock the behavior of the task service
        when(taskServiceImplMock.createTaskForUserTaskHolder(userTaskHolderId, taskDTO)).thenReturn(taskDTO);

        // Call the createTaskForUserTaskHolder method
        TaskDTO result = taskServiceImplMock.createTaskForUserTaskHolder(userTaskHolderId, taskDTO);

        // Verify that the result matches the expected task
        assertEquals(1L, result.getId());
        assertEquals("New Task", result.getHeader());

        // Verify that the method was called with the specified parameters
        verify(taskServiceImplMock, times(1)).createTaskForUserTaskHolder(userTaskHolderId, taskDTO);
    }
    
    
    @Test
    public void testUpdateTask() {
        // Create a TaskDTO to represent the updated task data
        TaskDTO updatedTaskData = new TaskDTO();
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setHeader("New Task");
        taskDTO.setDescription("description");
        taskDTO.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setAuthor("author");

        
        
        // Mock the repository method
        Mockito.when(taskServiceImplMock.updateTask(Mockito.any(TaskDTO.class)))
               .thenReturn(updatedTaskData);

        // Call the service method to update a task
        TaskDTO result = taskServiceImplMock.updateTask(new TaskDTO());

        // Now, you can perform assertions on the result if needed
        assertNotNull(result);
        assertEquals(updatedTaskData, result);
    }
    
    
    /*


    TaskDTO updateTask(TaskDTO task);
    
    TaskDTO updateTaskForUser(Long userTaskHolder_id, Long task_id, TaskDTO updatedTaskDTO);

    List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id);

    void deleteTaskForUser(Long userTaskHolder_id, Long task_id);
    
    void removeExecutorFromTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);
    
    void addExecutorToTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);

    boolean isUserAssociatedWithTask(Long taskId, Long userId);
    
  */ 
    
    
    
    
    @Test
    public void testFindByStatusPaginated() {
        TaskDTO expectedTask = new TaskDTO();
        expectedTask.setId(1L);
        expectedTask.setStatus(TaskStatus.COMPLETED);

        // Create a Page with the expected content
        Page<TaskDTO> expectedPage = new PageImpl<>(Collections.singletonList(expectedTask));

        // Mock the repository method with pagination
        when(taskServiceImplMock.getTasksByStatus(1L, "COMPLETED", Pageable.unpaged()))
                .thenReturn(expectedPage);

        // Call the repository method
        Page<TaskDTO> tasks = taskServiceImplMock.getTasksByStatus(1L, "COMPLETED", Pageable.unpaged());

        // Assert the result
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }
    
    
    @Test
    public void testFindByPriorityPaginated() {
        TaskDTO expectedTask = new TaskDTO();
        expectedTask.setId(1L);
        expectedTask.setPriority(TaskPriority.MEDIUM);

        // Create a Page with the expected content
        Page<TaskDTO> expectedPage = new PageImpl<>(Collections.singletonList(expectedTask));

        // Mock the repository method with pagination
        when(taskServiceImplMock.getTasksByPriority(1L, "MEDIUM", Pageable.unpaged()))
                .thenReturn(expectedPage);

        // Call the repository method
        Page<TaskDTO> tasks = taskServiceImplMock.getTasksByPriority(1L, "MEDIUM", Pageable.unpaged());

        // Assert the result
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }
    
}

 