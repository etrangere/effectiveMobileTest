package com.em.test_em.services;


import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.CommentsDTO;

public interface CommentService {

    List<CommentsDTO> getAllComments();
  
    Optional<CommentsDTO> getCommentsById(Long id);
    
    CommentsDTO createComment(CommentsDTO comments);

    CommentsDTO updateComment(CommentsDTO comments);

    List<CommentsDTO> getCommentsByTask(Long taskId);
    
    void deleteComment(Long id);

}
