package com.em.test_em.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.em.test_em.beans.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{

    List<Task> findByExecutorsExecutorTrue();
}
