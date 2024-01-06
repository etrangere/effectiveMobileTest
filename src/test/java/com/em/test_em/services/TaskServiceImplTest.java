package com.em.test_em.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;
import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;
import com.em.test_em.repositories.TaskRepository;


@ExtendWith(MockitoExtension.class)
//to keep database fresh for each test 
@Transactional
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepositoryMock;
    
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
    
    @Test
    public void testUpdateTaskForUser(){
     // Specify the userTaskHolder ID and the taskDTO
        Long userTaskHolderId = 1L;
        Long task_id = 1L;
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setHeader("New Task");
        taskDTO.setDescription("description");
        taskDTO.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setAuthor("author");

        // Mock the repository method
        Mockito.when(taskServiceImplMock.updateTaskForUser(userTaskHolderId, task_id, taskDTO))
               .thenReturn(taskDTO);
        
        // Call the service method to update a task
        TaskDTO result = taskServiceImplMock.updateTaskForUser(userTaskHolderId, task_id, taskDTO);
     
        //asserts
        assertEquals(taskDTO.getId(), result.getId());
        assertEquals(taskDTO.getHeader(), result.getHeader());
        assertEquals(taskDTO.getDescription(), result.getDescription());
        assertEquals(taskDTO.getStatus(), result.getStatus());
        assertEquals(taskDTO.getPriority(), result.getPriority());
        assertEquals(taskDTO.getAuthor(), result.getAuthor());
    }
    
    @Test
    public void testGetAllTasksForTaskExecutor() {
        // userTaskHolder ID
        Long userTaskExecutorId = 1L;
        List<TaskDTO> allTasksOfExecutor = new ArrayList<>();

        // Populate the list with some TaskDTO objects
        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setId(1L);
        taskDTO1.setHeader("New Task1");
        taskDTO1.setDescription("description");
        taskDTO1.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO1.setPriority(TaskPriority.HIGH);
        taskDTO1.setAuthor("author");

        // ... set other properties

        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(2L);
        taskDTO1.setHeader("New Task2");
        taskDTO1.setDescription("description");
        taskDTO1.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO1.setPriority(TaskPriority.HIGH);
        taskDTO1.setAuthor("author");

        allTasksOfExecutor.add(taskDTO1);
        allTasksOfExecutor.add(taskDTO2);

        // mock the repository method
        Mockito.when(taskServiceImplMock.getAllTasksForTaskExecutor(userTaskExecutorId))
               .thenReturn(allTasksOfExecutor);

        List<TaskDTO> result = taskServiceImplMock.getAllTasksForTaskExecutor(userTaskExecutorId);

        // first TaskDTO in the list
        TaskDTO expectedTaskDTO = allTasksOfExecutor.get(0);

       //asserts
        assertEquals(expectedTaskDTO.getId(), result.get(0).getId());
        assertEquals(expectedTaskDTO.getHeader(), result.get(0).getHeader());
        assertEquals(expectedTaskDTO.getDescription(), result.get(0).getDescription());
        assertEquals(expectedTaskDTO.getStatus(), result.get(0).getStatus());
        assertEquals(expectedTaskDTO.getPriority(), result.get(0).getPriority());
        assertEquals(expectedTaskDTO.getAuthor(), result.get(0).getAuthor());
    }

    @Test
    public void testDeleteTaskForUser() {
        // Specify the userTaskHolder ID and the task ID to be deleted
        Long userTaskHolderId = 1L;
        Long taskId = 1L;

        //  mock the repository method
        Mockito.doNothing().when(taskServiceImplMock).deleteTaskForUser(userTaskHolderId, taskId);

        // call the deleteTaskForUser method
        taskServiceImplMock.deleteTaskForUser(userTaskHolderId, taskId);

        // check method was called with the specified arguments
        Mockito.verify(taskServiceImplMock, Mockito.times(1)).deleteTaskForUser(userTaskHolderId, taskId);
    }

    @Test
    public void testRemoveExecutorFromTask() {
        //Specify the userTaskHolder ID, task ID, and userExecutor ID
        Long userTaskHolderId = 1L;
        Long taskId = 1L;
        Long userExecutorId = 2L;

        //Mock the repository
        Mockito.doNothing().when(taskServiceImplMock).removeExecutorFromTask(userTaskHolderId,taskId,userExecutorId);

        //call the removeExecutorFromTask method
        taskServiceImplMock.removeExecutorFromTask(userTaskHolderId, taskId, userExecutorId);

        //verify that the repository's removeExecutorFromTask called with the specified arguments
        Mockito.verify(taskServiceImplMock, Mockito.times(1))
                .removeExecutorFromTask(userTaskHolderId, taskId, userExecutorId);
    }

    
    @Test
    public void testAddExecutorToTask() {
        // Specify the userTaskHolder ID, task ID and userExecutor ID
        Long userTaskHolderId = 1L;
        Long taskId = 1L;
        Long userExecutorId = 2L;

        //Mock the repository
        Mockito.doNothing().when(taskServiceImplMock).addExecutorToTask(userTaskHolderId, taskId, userExecutorId);

        // call addExecutorToTask method
        taskServiceImplMock.addExecutorToTask(userTaskHolderId, taskId, userExecutorId);

        // verify addExecutorToTask method was called with the specified arguments
        Mockito.verify(taskServiceImplMock, Mockito.times(1))
                .addExecutorToTask(userTaskHolderId, taskId, userExecutorId);
    }

    
    @Test
    public void testForTrueIsUserAssociatedWithTask() {
     // Create a sample task and user
        Task task = new Task();
        task.setId(1L);
        User user = new User();
        user.setId(1L);

        // Associate the user with the task
        task.getUsers().add(user);

        // Mock the taskRepository to return the task when findById is called
        lenient().when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(task));

        // Check if a different user is not associated with the task
        boolean resultTrue = taskServiceImplMock.isUserAssociatedWithTask(1L, 1L);
        assertFalse(resultTrue);
    }


    @Test
    public void testForFalseIsUserAssociatedWithTask() {
        // Create a sample task and user
        Task task = new Task();
        task.setId(1L);
        User user = new User();
        user.setId(1L);

        // Associate the user with the task
        task.getUsers().add(user);

        // Mock the taskRepository to return the task when findById is called
        lenient().when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(task));

        // Check if a different user is not associated with the task
        boolean resultFalse = taskServiceImplMock.isUserAssociatedWithTask(1L, 2L);
        assertFalse(resultFalse);
    }
    
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

 