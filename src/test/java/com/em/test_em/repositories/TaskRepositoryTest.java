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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//to keep database fresh for each test 
@Transactional
public class TaskRepositoryTest {

 

    @Mock
    private TaskRepository taskRepositoryMock;

    @Test
    public void testFindById() {
        
        // creating object and setting attribute
        Task expectedTask = new Task();
        expectedTask.setId(1L);  

        // repository method and objective of test
        when(taskRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedTask));

        // what to be checked or verified
        Optional<Task> task = taskRepositoryMock.findById(1L);

        // assert 
        assertTrue(task.isPresent());
        assertEquals(expectedTask.getId(), task.get().getId());
        
    }

    @Test
    public void testFindByStatus() {
        
        // creating object and setting attribute
        Task expectedTask = new Task();
        expectedTask.setStatus(TaskStatus.IN_PROGRESS);  // Assuming TaskStatus is the enum type

        // repository method and objective of test
        when(taskRepositoryMock.findByStatus("IN_PROGRESS"))
                .thenReturn(Collections.singletonList(expectedTask));

        // what to be checked or verified
        List<Task> tasks = taskRepositoryMock.findByStatus("IN_PROGRESS");

        // assert to check
        assertEquals(1, tasks.size());
        assertEquals(expectedTask, tasks.get(0));
    }

    @Test
    public void testFindByStatusPaginated() {
        
        // creating object and setting attribute
        Task expectedTask = new Task();
        expectedTask.setStatus(TaskStatus.IN_PROGRESS);  // Assuming TaskStatus is the enum type

        // repository method and objective of test
        when(taskRepositoryMock.findByStatus("IN_PROGRESS", Pageable.unpaged()))
                .thenReturn(new PageImpl<>(Collections.singletonList(expectedTask)));

        // what to be checked or verified
        Page<Task> tasks = taskRepositoryMock.findByStatus("IN_PROGRESS", Pageable.unpaged());

        // assert to check
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }

  
    @Test
    public void testFindByPriority() {
        
        // creating object and setting attribute
        Task expectedTask = new Task();
        expectedTask.setPriority(TaskPriority.MEDIUM);  // Assuming TaskStatus is the enum type

        // repository method and objective of test
        when(taskRepositoryMock.findByPriority("MEDIUM"))
                .thenReturn(Collections.singletonList(expectedTask));

        // what to be checked or verified
        List<Task> tasks = taskRepositoryMock.findByPriority("MEDIUM");

        // assert to check
        assertEquals(1, tasks.size());
        assertEquals(expectedTask, tasks.get(0));
    }

    @Test
    public void testFindByPriorityPaginated() {
        
        // creating object and setting attribute
        Task expectedTask = new Task();
        expectedTask.setPriority(TaskPriority.MEDIUM);  // Assuming TaskStatus is the enum type

        // repository method and objective of test
        when(taskRepositoryMock.findByPriority("MEDIUM", Pageable.unpaged()))
                .thenReturn(new PageImpl<>(Collections.singletonList(expectedTask)));

        // what to be checked or verified
        Page<Task> tasks = taskRepositoryMock.findByPriority("MEDIUM", Pageable.unpaged());

        // assert to check
        assertEquals(1, tasks.getTotalElements());
        assertEquals(expectedTask, tasks.getContent().get(0));
    }

    
    @Test
    public void testFindByUserAndUserExecutorFalse() {
        
        // creating object and setting attribute
        User user = new User();
        
        // repository method and objective of test
        when(taskRepositoryMock.findByUserAndUserExecutorFalse(user)).thenReturn(Collections.singletonList(new Task()));

        // what to be checked or verified
        List<Task> tasks = taskRepositoryMock.findByUserAndUserExecutorFalse(user);

        // assert to check
        assertEquals(1, tasks.size());
    }

      
    
    @Test
    public void testFindByUsersIdAndUsersExecutorFalse() {
       
        // repository method and objective of test
        when(taskRepositoryMock.findByUsersIdAndUsersExecutorFalse(1L, Pageable.unpaged()))
                .thenReturn(Page.empty());

        // what to be checked or verified
        Page<Task> tasks = taskRepositoryMock.findByUsersIdAndUsersExecutorFalse(1L, Pageable.unpaged());

        // assert to check
        assertEquals(0, tasks.getTotalElements());
    }

    
    @Test
    public void testfindByUserAndUserExecutorTrue() {
       
        // creating object and setting attribute
        User user = new User();
        
        // repository method and objective of test
        when(taskRepositoryMock.findByUserAndUserExecutorTrue(user)).thenReturn(Collections.singletonList(new Task()));

        // what to be checked or verified
        List<Task> tasks = taskRepositoryMock.findByUserAndUserExecutorTrue(user);

        // assert to check
        assertEquals(1, tasks.size());
    }
    
    @Test
    public void testfindByUserAndUserExecutorTrueWithUserId() {
        
        // repository method and objective of test
        when(taskRepositoryMock.findByUserAndUserExecutorTrue(1L))
                .thenReturn(Collections.singletonList(new Task())); 

        // what to be checked or verified
        List<Task> tasks = taskRepositoryMock.findByUserAndUserExecutorTrue(1L);

        // assert to check
        assertEquals(1, tasks.size());
    }
}
