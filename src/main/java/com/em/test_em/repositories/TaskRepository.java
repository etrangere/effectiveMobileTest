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

/**
 * JPA repository for handling Task entities.
 */
public interface TaskRepository extends JpaRepository<Task,Long>{

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task.
     * @return An optional containing the task with the specified ID, or empty if not found.
     */
    Optional<Task> findById(Long id);

    /**
     * Retrieves a list of tasks by their status.
     *
     * @param status The status of the tasks.
     * @return A list of tasks with the specified status.
     */
    List<Task> findByStatus(String status);

    /**
     * Retrieves a list of tasks by their priority.
     *
     * @param priority The priority of the tasks.
     * @return A list of tasks with the specified priority.
     */
    List<Task> findByPriority(String priority);

    /**
     * Retrieves a page of tasks by their status.
     *
     * @param status   The status of the tasks.
     * @param pageable Pageable object for pagination.
     * @return A page of tasks with the specified status.
     */
    Page<Task> findByStatus(String status, Pageable pageable);

    /**
     * Retrieves a page of tasks by their priority.
     *
     * @param priority The priority of the tasks.
     * @param pageable Pageable object for pagination.
     * @return A page of tasks with the specified priority.
     */
    Page<Task> findByPriority(String priority, Pageable pageable);
    
    /**
     * Retrieves a list of tasks for a specific user where the user is not an executor.
     *
     * @param user The user for whom tasks are retrieved.
     * @return A list of tasks associated with the specified user where the user is not an executor.
     */    
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu = :user AND tu.executor = false")
    List<Task> findByUserAndUserExecutorFalse(@Param("user") User user);
    
    /**
     * Retrieves a list of tasks for a specific user ID where the user is not an executor.
     *
     * @param userTaskHolderId The ID of the user for whom tasks are retrieved.
     * @return A list of tasks associated with the specified user where the user is not an executor.
     */
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu.id = :user AND tu.executor = false")
    List<Task> findByUserAndUserExecutorFalse(@Param("user") Long userTaskHolder_id);

    /**
     * Retrieves a list of tasks for a specific user where the user is an executor.
     *
     * @param user The user for whom tasks are retrieved.
     * @return A list of tasks associated with the specified user where the user is an executor.
     */
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu = :user AND tu.executor = true")
    List<Task> findByUserAndUserExecutorTrue(@Param("user") User user);
    
   
    /**
     * Retrieves a list of tasks for a specific user ID where the user is an executor.
     *
     * @param userTaskExecutorId The ID of the user for whom tasks are retrieved.
     * @return A list of tasks associated with the specified user where the user is an executor.
     */
    @Query("SELECT t FROM Task t JOIN t.users tu WHERE tu.id = :userId AND tu.executor = true")
    List<Task> findByUserAndUserExecutorTrue(@Param("userId") Long userTaskExecutorId);
   
    /**
     * Retrieves a page of tasks for a specific user ID where the user is not an executor.
     *
     * @param userId   The ID of the user for whom tasks are retrieved.
     * @param pageable Pageable object for pagination.
     * @return A page of tasks associated with the specified user where the user is not an executor.
     */
    Page<Task> findByUsersIdAndUsersExecutorFalse(Long userId, Pageable pageable);

}
