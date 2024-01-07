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
        
     // creating object and setting attribute
        TaskDTO task1 = new TaskDTO();
        task1.setId(1L);
        task1.setHeader("Task 1");

        TaskDTO task2 = new TaskDTO();
        task2.setId(2L);
        task2.setHeader("Task 2");

     // repository method and objective of test
        when(taskServiceImplMock.getAllTasks()).thenReturn(Arrays.asList(task1, task2));

     // what to be checked or verified
        List<TaskDTO> result = taskServiceImplMock.getAllTasks();

     // assert to check
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getHeader());
        assertEquals("Task 2", result.get(1).getHeader());
    }
    
    
    @Test
    public void testgetTaskById() {
        
     // creating object and setting attribute
        TaskDTO expectedTaskById = new TaskDTO();
        expectedTaskById.setId(1L);

     // repository method and objective of test
        when(taskServiceImplMock.getTaskById(1L)).thenReturn(expectedTaskById);

     // what to be checked or verified
        Optional<TaskDTO> result = Optional.of(taskServiceImplMock.getTaskById(1L));

     // assert to check
        assertTrue(result.isPresent());
        assertEquals(expectedTaskById, result.get());
    }

    
    @Test
    public void testCreateTask() {
        
     // mock the TaskDTO 
        TaskDTO createdtask = Mockito.mock(TaskDTO.class);

     // repository method and objective of test
        Mockito.when(taskServiceImplMock.createTask(Mockito.any(TaskDTO.class))).thenReturn(createdtask);

     // what to be checked or verified
        TaskDTO result = taskServiceImplMock.createTask(new TaskDTO());

     // assert to check
        assertNotNull(result);
        assertEquals(createdtask, result);
    }
    
    
    @Test
    public void testCreateTaskForUserTaskHolder() {
        
     // creating object and setting attribute
        Long userTaskHolderId = 1L;

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setHeader("New Task");
        taskDTO.setDescription("description");
        taskDTO.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setAuthor("author");

     // repository method and objective of test
        when(taskServiceImplMock.createTaskForUserTaskHolder(userTaskHolderId, taskDTO)).thenReturn(taskDTO);

     // what to be checked or verified
        TaskDTO result = taskServiceImplMock.createTaskForUserTaskHolder(userTaskHolderId, taskDTO);

     // assert to check
        assertEquals(1L, result.getId());
        assertEquals("New Task", result.getHeader());

        // verify that the method was called with the specified parameters
        verify(taskServiceImplMock, times(1)).createTaskForUserTaskHolder(userTaskHolderId, taskDTO);
    }
    
    
    @Test
    public void testUpdateTask() {
        
     // creating object and setting attribute
        TaskDTO updatedTaskData = new TaskDTO();
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setHeader("New Task");
        taskDTO.setDescription("description");
        taskDTO.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setAuthor("author");

        
     // repository method and objective of test
        Mockito.when(taskServiceImplMock.updateTask(Mockito.any(TaskDTO.class)))
               .thenReturn(updatedTaskData);

     // what to be checked or verified
        TaskDTO result = taskServiceImplMock.updateTask(new TaskDTO());

     // assert to check
        assertNotNull(result);
        assertEquals(updatedTaskData, result);
    }
    
    @Test
    public void testUpdateTaskForUser(){
        
     // creating object and setting attribute
        Long userTaskHolderId = 1L;
        Long task_id = 1L;
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setHeader("New Task");
        taskDTO.setDescription("description");
        taskDTO.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO.setPriority(TaskPriority.HIGH);
        taskDTO.setAuthor("author");

     // repository method and objective of test
        Mockito.when(taskServiceImplMock.updateTaskForUser(userTaskHolderId, task_id, taskDTO))
               .thenReturn(taskDTO);
        
     // what to be checked or verified
        TaskDTO result = taskServiceImplMock.updateTaskForUser(userTaskHolderId, task_id, taskDTO);
     
     // assert to check
        assertEquals(taskDTO.getId(), result.getId());
        assertEquals(taskDTO.getHeader(), result.getHeader());
        assertEquals(taskDTO.getDescription(), result.getDescription());
        assertEquals(taskDTO.getStatus(), result.getStatus());
        assertEquals(taskDTO.getPriority(), result.getPriority());
        assertEquals(taskDTO.getAuthor(), result.getAuthor());
    }
    
    @Test
    public void testGetAllTasksForTaskExecutor() {
        
     // creating object and setting attribute
        Long userTaskExecutorId = 1L;
        List<TaskDTO> allTasksOfExecutor = new ArrayList<>();

        // populate the list with some TaskDTO objects
        TaskDTO taskDTO1 = new TaskDTO();
        taskDTO1.setId(1L);
        taskDTO1.setHeader("New Task1");
        taskDTO1.setDescription("description");
        taskDTO1.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO1.setPriority(TaskPriority.HIGH);
        taskDTO1.setAuthor("author");


        TaskDTO taskDTO2 = new TaskDTO();
        taskDTO2.setId(2L);
        taskDTO1.setHeader("New Task2");
        taskDTO1.setDescription("description");
        taskDTO1.setStatus(TaskStatus.IN_PROGRESS);
        taskDTO1.setPriority(TaskPriority.HIGH);
        taskDTO1.setAuthor("author");

        allTasksOfExecutor.add(taskDTO1);
        allTasksOfExecutor.add(taskDTO2);

     // repository method and objective of test
        Mockito.when(taskServiceImplMock.getAllTasksForTaskExecutor(userTaskExecutorId))
               .thenReturn(allTasksOfExecutor);
    
        // what to be checked or verified
        List<TaskDTO> result = taskServiceImplMock.getAllTasksForTaskExecutor(userTaskExecutorId);

        // first TaskDTO in the list
        TaskDTO expectedTaskDTO = allTasksOfExecutor.get(0);

     // assert to check
        assertEquals(expectedTaskDTO.getId(), result.get(0).getId());
        assertEquals(expectedTaskDTO.getHeader(), result.get(0).getHeader());
        assertEquals(expectedTaskDTO.getDescription(), result.get(0).getDescription());
        assertEquals(expectedTaskDTO.getStatus(), result.get(0).getStatus());
        assertEquals(expectedTaskDTO.getPriority(), result.get(0).getPriority());
        assertEquals(expectedTaskDTO.getAuthor(), result.get(0).getAuthor());
    }

    @Test
    public void testDeleteTaskForUser() {
        
     // setting attribute
        Long userTaskHolderId = 1L;
        Long taskId = 1L;

     // repository method and objective of test
        Mockito.doNothing().when(taskServiceImplMock).deleteTaskForUser(userTaskHolderId, taskId);

        // call the deleteTaskForUser method
        taskServiceImplMock.deleteTaskForUser(userTaskHolderId, taskId);

        // check method was called with the specified arguments
        Mockito.verify(taskServiceImplMock, Mockito.times(1)).deleteTaskForUser(userTaskHolderId, taskId);
    }

    @Test
    public void testRemoveExecutorFromTask() {
        
     // setting attribute
        Long userTaskHolderId = 1L;
        Long taskId = 1L;
        Long userExecutorId = 2L;

     // repository method and objective of test
        Mockito.doNothing().when(taskServiceImplMock).removeExecutorFromTask(userTaskHolderId,taskId,userExecutorId);

        //call the removeExecutorFromTask method
        taskServiceImplMock.removeExecutorFromTask(userTaskHolderId, taskId, userExecutorId);

        //verify that the repository removeExecutorFromTask called with the specified arguments
        Mockito.verify(taskServiceImplMock, Mockito.times(1))
                .removeExecutorFromTask(userTaskHolderId, taskId, userExecutorId);
    }

    
    @Test
    public void testAddExecutorToTask() {
        
     // setting attribute
        Long userTaskHolderId = 1L;
        Long taskId = 1L;
        Long userExecutorId = 2L;

     // repository method and objective of test
        Mockito.doNothing().when(taskServiceImplMock).addExecutorToTask(userTaskHolderId, taskId, userExecutorId);

     // what to be checked or verified
        taskServiceImplMock.addExecutorToTask(userTaskHolderId, taskId, userExecutorId);

        // verify addExecutorToTask method was called with the specified arguments
        Mockito.verify(taskServiceImplMock, Mockito.times(1))
                .addExecutorToTask(userTaskHolderId, taskId, userExecutorId);
    }

    
    @Test
    public void testForTrueIsUserAssociatedWithTask() {
     
     // creating object and setting attribute
        Task task = new Task();
        task.setId(1L);
        User user = new User();
        user.setId(1L);

        // associate the user with the task
        task.getUsers().add(user);

     // repository method and objective of test
        lenient().when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(task));

     // what to be checked or verified
        boolean resultTrue = taskServiceImplMock.isUserAssociatedWithTask(1L, 1L);
        
        // assert to check 
        assertFalse(resultTrue);
    }


    @Test
    public void testForFalseIsUserAssociatedWithTask() {
        
     // creating object and setting attribute
        Task task = new Task();
        task.setId(1L);
        User user = new User();
        user.setId(1L);

        // associate the user with the task
        task.getUsers().add(user);

     // repository method and objective of test
        lenient().when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(task));
        
        // what to be checked or verified
        boolean resultFalse = taskServiceImplMock.isUserAssociatedWithTask(1L, 2L);
        
        // assert to check
        assertFalse(resultFalse);
    }
    
    @Test
    public void testFindByStatusPaginated() {
        
     // creating object and setting attribute
        TaskDTO expectedTask = new TaskDTO();
        expectedTask.setId(1L);
        expectedTask.setStatus(TaskStatus.COMPLETED);

        // create a Page with the expected content
        Page<TaskDTO> expectedPage = new PageImpl<>(Collections.singletonList(expectedTask));

     // repository method and objective of test
        when(taskServiceImplMock.getTasksByStatus(1L, "COMPLETED", Pageable.unpaged()))
                .thenReturn(expectedPage);

     // what to be checked or verified
        Page<TaskDTO> tasks = taskServiceImplMock.getTasksByStatus(1L, "COMPLETED", Pageable.unpaged());

     // assert to check
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }
    
    
    @Test
    public void testFindByPriorityPaginated() {
        
     // creating object and setting attribute
        TaskDTO expectedTask = new TaskDTO();
        expectedTask.setId(1L);
        expectedTask.setPriority(TaskPriority.MEDIUM);

        // Create a Page with the expected content
        Page<TaskDTO> expectedPage = new PageImpl<>(Collections.singletonList(expectedTask));

     // repository method and objective of test
        when(taskServiceImplMock.getTasksByPriority(1L, "MEDIUM", Pageable.unpaged()))
                .thenReturn(expectedPage);

     // what to be checked or verified
        Page<TaskDTO> tasks = taskServiceImplMock.getTasksByPriority(1L, "MEDIUM", Pageable.unpaged());

     // assert to check
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }
    
}

 