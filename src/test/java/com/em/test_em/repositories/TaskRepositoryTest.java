package com.em.test_em.repositories;

import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;
import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskRepositoryTest {

 

    @Mock
    private TaskRepository taskRepositoryMock;

    @Test
    public void testFindById() {
        
        // mock the repository behavior
        Task expectedTask = new Task();
        expectedTask.setId(1L);  // Set relevant attributes for comparison

        when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedTask));

        // repository method
        Optional<Task> task = taskRepositoryMock.findById(1L);

        // assert to compare the result
        assertTrue(task.isPresent());
        assertEquals(expectedTask.getId(), task.get().getId());
        
    }

    @Test
    public void testFindByStatus() {
        Task expectedTask = new Task();
        expectedTask.setStatus(TaskStatus.IN_PROGRESS);  // Assuming TaskStatus is the enum type

        // repository method
        when(taskRepositoryMock.findByStatus("IN_PROGRESS"))
                .thenReturn(Collections.singletonList(expectedTask));

        // call the repository method
        List<Task> tasks = taskRepositoryMock.findByStatus("IN_PROGRESS");

        // assert the result
        assertEquals(1, tasks.size());
        assertEquals(expectedTask, tasks.get(0));
    }

    @Test
    public void testFindByStatusPaginated() {
        Task expectedTask = new Task();
        expectedTask.setStatus(TaskStatus.IN_PROGRESS);  // Assuming TaskStatus is the enum type

        // repository method
        when(taskRepositoryMock.findByStatus("IN_PROGRESS", Pageable.unpaged()))
                .thenReturn(new PageImpl<>(Collections.singletonList(expectedTask)));

        // call the repository method
        Page<Task> tasks = taskRepositoryMock.findByStatus("IN_PROGRESS", Pageable.unpaged());

        // assert the result
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }

  
    @Test
    public void testFindByPriority() {
        Task expectedTask = new Task();
        expectedTask.setPriority(TaskPriority.MEDIUM);  // Assuming TaskStatus is the enum type

        // repository method
        when(taskRepositoryMock.findByPriority("MEDIUM"))
                .thenReturn(Collections.singletonList(expectedTask));

        // call the repository method
        List<Task> tasks = taskRepositoryMock.findByPriority("MEDIUM");

        // assert the result
        assertEquals(1, tasks.size());
        assertEquals(expectedTask, tasks.get(0));
    }

    @Test
    public void testFindByPriorityPaginated() {
        Task expectedTask = new Task();
        expectedTask.setPriority(TaskPriority.MEDIUM);  // Assuming TaskStatus is the enum type

        // repository method
        when(taskRepositoryMock.findByPriority("MEDIUM", Pageable.unpaged()))
                .thenReturn(new PageImpl<>(Collections.singletonList(expectedTask)));

        // call the repository method
        Page<Task> tasks = taskRepositoryMock.findByPriority("MEDIUM", Pageable.unpaged());

        // assert the result
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }

    
    @Test
    public void testFindByUserAndUserExecutorFalse() {
       
        User user = new User();
     // repository method
        when(taskRepositoryMock.findByUserAndUserExecutorFalse(user)).thenReturn(Collections.singletonList(new Task()));

     // call the repository method
        List<Task> tasks = taskRepositoryMock.findByUserAndUserExecutorFalse(user);

     // assert the result
        assertEquals(1, tasks.size());
    }

      
    
    @Test
    public void testFindByUsersIdAndUsersExecutorFalse() {
       
     // repository method
        when(taskRepositoryMock.findByUsersIdAndUsersExecutorFalse(1L, Pageable.unpaged()))
                .thenReturn(Page.empty());

     // call the repository method
        Page<Task> tasks = taskRepositoryMock.findByUsersIdAndUsersExecutorFalse(1L, Pageable.unpaged());

     // assert the result
        assertEquals(0, tasks.getTotalElements());
    }

    
    @Test
    public void testfindByUserAndUserExecutorTrue() {
       
        User user = new User();
     // repository method
        when(taskRepositoryMock.findByUserAndUserExecutorTrue(user)).thenReturn(Collections.singletonList(new Task()));

     // call the repository method
        List<Task> tasks = taskRepositoryMock.findByUserAndUserExecutorTrue(user);

     // assert the result
        assertEquals(1, tasks.size());
    }
    
    @Test
    public void testfindByUserAndUserExecutorTrueWithUserId() {
        //repository method
        when(taskRepositoryMock.findByUserAndUserExecutorTrue(1L))
                .thenReturn(Collections.singletonList(new Task())); 

        // call the repository method
        List<Task> tasks = taskRepositoryMock.findByUserAndUserExecutorTrue(1L);

        // assert the result
        assertEquals(1, tasks.size());
    }
}
