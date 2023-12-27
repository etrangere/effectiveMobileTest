package com.em.test_em.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.em.test_em.beans.Task;
import com.em.test_em.beans.User;

public interface TaskRepository extends JpaRepository<Task,Long>{

    
    @Query("SELECT t FROM Task t JOIN t.user tu WHERE tu = :user AND tu.executor = true")
    List<Task> findByUserAndUserExecutorTrue(@Param("user") User user);

}
