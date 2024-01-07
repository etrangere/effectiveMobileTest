package com.em.test_em.services;


import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.CommentDTO;

public interface CommentService {

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment.
     * @return An optional containing the CommentDTO if found, or empty otherwise.
     */
    Optional<CommentDTO> getCommentById(Long id);
    
    /**
     * Retrieves all comments for a specific task.
     *
     * @param taskId The ID of the task.
     * @return A list of CommentDTOs for the specified task.
     */
    List<CommentDTO> getCommentByTask(Long taskId);
    
    /**
     * Retrieves a specific comment for a task by comment ID and task ID.
     *
     * @param taskId    The ID of the task.
     * @param commentId The ID of the comment.
     * @return The CommentDTO if found, otherwise null.
     */
    CommentDTO getCommentByIdAndTaskId(Long task_id,Long comment_id);

    /**
     * Creates a new comment for a specific task.
     *
     * @param taskId      The ID of the task.
     * @param commentDTO  The CommentDTO containing the comment details.
     * @return The created CommentDTO.
     */
    CommentDTO createCommentForTask(Long task_id,CommentDTO commentDTO);
    
    /**
     * Retrieves all comments for a specific task.
     *
     * @param taskId The ID of the task.
     * @return A list of CommentDTOs for the specified task.
     */
    List<CommentDTO> getAllCommentsForTask(Long task_id);
    
    /**
     * Deletes a comment for a specific task.
     *
     * @param taskId    The ID of the task.
     * @param commentId The ID of the comment to be deleted.
     */
    void deleteCommentForTask(Long task_id,Long comment_id);
    
    /**
     * Updates a comment for a specific task.
     *
     * @param taskId        The ID of the task.
     * @param commentId     The ID of the comment to be updated.
     * @param updatedCommentDTO The updated CommentDTO.
     * @return The updated CommentDTO.
     */
    CommentDTO updateCommentForTask(Long task_id,Long comment_id,CommentDTO updatedCommentDTO);

   
}
