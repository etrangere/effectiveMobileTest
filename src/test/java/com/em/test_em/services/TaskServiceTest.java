package com.em.test_em.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
public class TaskServiceTest {

    @Mock
    private TaskServiceImpl taskServiceImplMock;
    
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
/*
 
    List<TaskDTO> getAllTasks();
    
    TaskDTO createTask(TaskDTO task);
    
    TaskDTO createTaskForUserTaskHolder(Long userTaskHolder_id, TaskDTO taskDTO);

    TaskDTO updateTask(TaskDTO task);
    
    TaskDTO updateTaskForUser(Long userTaskHolder_id, Long task_id, TaskDTO updatedTaskDTO);

    List<TaskDTO> getAllTasksForTaskExecutor(Long userTaskExecutor_id);

    void deleteTaskForUser(Long userTaskHolder_id, Long task_id);
    
    void removeExecutorFromTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);
    
    void addExecutorToTask(Long userTaskHolder_id,Long task_id,Long userExecutor_id);

    boolean isUserAssociatedWithTask(Long taskId, Long userId);
    
 * */
 