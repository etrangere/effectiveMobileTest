package com.em.test_em.services;


import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.CommentDTO;

public interface CommentService {

    List<CommentDTO> getAllComments();
  
    Optional<CommentDTO> getCommentById(Long id);
    
    CommentDTO updateComment(CommentDTO comments);

    List<CommentDTO> getCommentByTask(Long taskId);
    
    void deleteComment(Long id);
    
    CommentDTO getCommentByIdAndTaskId(Long comment_id, Long task_id);

    CommentDTO createCommentForTask(Long task_id,CommentDTO commentDTO);
}
