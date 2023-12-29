package com.em.test_em.services;


import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.CommentDTO;

public interface CommentService {

  
    Optional<CommentDTO> getCommentById(Long id);

    List<CommentDTO> getCommentByTask(Long taskId);
    
    CommentDTO getCommentByIdAndTaskId(Long comment_id, Long task_id);

    CommentDTO createCommentForTask(Long task_id,CommentDTO commentDTO);
    
    List<CommentDTO> getAllCommentsForTask(Long task_id);
    
    void deleteCommentForTask(Long task_id,Long comment_id);
    
    CommentDTO updateCommentForTask(Long task_id,Long comment_id,CommentDTO updatedCommentDTO);

   
}
