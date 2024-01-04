package com.em.test_em.repositories;

import com.em.test_em.beans.Task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
/*
    @Test
    public void testFindByStatus() {
        
        when(taskRepositoryMock.findByStatus("IN_PROGRESS")).thenReturn(Collections.singletonList(new Task()));

      
        List<Task> tasks = taskRepositoryMock.findByStatus("IN_PROGRESS");

        
        assertEquals(1, tasks.size());
    }

    
    @Test
    public void testFindByPriority() {
       
        when(taskRepositoryMock.findByPriority("MEDIUM")).thenReturn(Collections.singletonList(new Task()));

       
        List<Task> tasks = taskRepositoryMock.findByPriority("MEDIUM");

       
        assertEquals(1, tasks.size());
    }


    
    @Test
    public void testFindByUserAndUserExecutorFalse() {
       
        User user = new User();
        when(taskRepositoryMock.findByUserAndUserExecutorFalse(user)).thenReturn(Collections.singletonList(new Task()));

       
        List<Task> tasks = taskRepositoryMock.findByUserAndUserExecutorFalse(user);

       
        assertEquals(1, tasks.size());
    }

    
    
    @Test
    public void testFindByUsersIdAndUsersExecutorFalse() {
       
        when(taskRepositoryMock.findByUsersIdAndUsersExecutorFalse(1L, Pageable.unpaged()))
                .thenReturn(Page.empty());

        
        Page<Task> tasks = taskRepositoryMock.findByUsersIdAndUsersExecutorFalse(1L, Pageable.unpaged());

        
        assertEquals(0, tasks.getTotalElements());
    }
*/
    /*
     *     

       Optional<Task> findById(Long id);

    List<Task> findByPriority(String priority);

    Page<Task> findByStatus(String status, Pageable pageable);

    Page<Task> findByPriority(String priority, Pageable pageable);
    
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu = :user AND tu.executor = false")
    List<Task> findByUserAndUserExecutorFalse(@Param("user") User user);
    
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu.id = :user AND tu.executor = false")
    List<Task> findByUserAndUserExecutorFalse(@Param("user") Long userTaskHolder_id);

    
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu = :user AND tu.executor = true")
    List<Task> findByUserAndUserExecutorTrue(@Param("user") User user);
    
   
    //for function getAllTasksCommentsByExecutorId
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu.id = :userId AND tu.executor = true")
    List<Task> findByUserAndUserExecutorTrue(@Param("userId") Long userTaskExecutorId);
    //for task by status and priority methods in user controller
    Page<Task> findByUsersIdAndUsersExecutorFalse(Long userId, Pageable pageable);*/
}
