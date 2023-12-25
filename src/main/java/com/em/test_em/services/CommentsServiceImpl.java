package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.em.test_em.beans.Comments;
import com.em.test_em.repositories.CommentsRepository;

@Service
public class CommentsServiceImpl implements CommentsService{
    
    @Autowired
    private CommentsRepository commentsRepository;
    
    //get all comments
    public List<Comments> getAllComments(){
        return commentsRepository.findAll();
    }  
    
    //get comment by id
    public Optional<Comments> getCommentsById(Long id){
        return commentsRepository.findById((long) id);
    }  
        
    //create comment
    public Comments createComment(Comments comments) {
        return this.commentsRepository.save(comments);
    }
    
    //update comment
    public Comments updateComment(Comments comments) {
        if (!this.commentsRepository.existsById(comments.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find the comment to update");
        }
        return this.commentsRepository.save(comments);
    }
    
    //delete comment
    public void deleteComment(Long id) {
        if (!this.commentsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find comment to delete");
        }
        this.commentsRepository.deleteById(id);
        if (this.commentsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Error deleting comment");
        }
    }
}
