package com.em.test_em.services;


import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.CommentsDTO;

public interface CommentsService {

    List<CommentsDTO> getAllComments();
  
    Optional<CommentsDTO> getCommentsById(Long id);
    
    CommentsDTO createComment(CommentsDTO comments);

    CommentsDTO updateComment(CommentsDTO comments);

    void deleteComment(Long id);

}
