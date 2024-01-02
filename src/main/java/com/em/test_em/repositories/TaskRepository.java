package com.em.test_em.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;


public interface TaskRepository extends JpaRepository<Task,Long>{

    Optional<Task> findById(Long id);

    List<Task> findByStatus(String status);

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

}
