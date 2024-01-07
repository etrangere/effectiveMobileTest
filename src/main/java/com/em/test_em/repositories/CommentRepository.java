package com.em.test_em.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.em.test_em.beans.Comment;

/**
 * JPA repository for handling Comment entities.
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{
   
    /**
     * Retrieves a list of comments associated with a specific task.
     *
     * @param taskId The ID of the task.
     * @return A list of comments associated with the specified task.
     */
    List<Comment> findByTaskId(Long taskId);
   
    /**
     * Checks if a comment with a given ID exists for a specific task.
     *
     * @param commentId The ID of the comment.
     * @param taskId    The ID of the task.
     * @return True if a comment with the given ID exists for the specified task; otherwise, false.
     */
    boolean existsByIdAndTaskId(Long commentId, Long taskId);
  
}
