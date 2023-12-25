package com.em.test_em.services;


import java.util.List;
import java.util.Optional;


import com.em.test_em.beans.Comments;

public interface CommentsService {

    List<Comments> getAllComments();
  
    Optional<Comments> getCommentsById(Long id);
    
    Comments createComment(Comments comments);

    Comments updateComment(Comments comments);

    void deleteComment(Long id);

}
