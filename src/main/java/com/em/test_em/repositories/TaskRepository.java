package com.em.test_em.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.em.test_em._DTO.UserDTO;
import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;

public interface TaskRepository extends JpaRepository<Task,Long>{

    Optional<Task> findById(Long id);
    
    @Query("SELECT t FROM Task t JOIN t.user tu WHERE tu = :user AND tu.executor = false")
    List<Task> findByUserAndUserExecutorFalse(@Param("user") User user);
    
    @Query("SELECT t FROM Task t JOIN t.user tu WHERE tu = :user AND tu.executor = false")
    UserDTO findByUserAndUserExecutorFalse(@Param("user") Long userTaskHolder_id);
    
    @Query("SELECT t FROM Task t JOIN t.user tu WHERE tu = :user AND tu.executor = true")
    List<Task> findByUserAndUserExecutorTrue(@Param("user") User user);
    
    @Query("SELECT t FROM Task t JOIN t.user tu WHERE tu = :user AND tu.executor = true")
    UserDTO findByUserAndUserExecutorTrue(@Param("user") Long userExecutor_id);
    
}
