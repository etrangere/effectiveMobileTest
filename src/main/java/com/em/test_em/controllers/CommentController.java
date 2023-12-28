package com.em.test_em.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.em.test_em._DTO.CommentDTO;

import com.em.test_em.services.CommentService;


import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/comment")
@Tag(name = "Comment", description = "Comment management APIs")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    
    @GetMapping("/comment/{task_id}/getById_comment/{comment_id}")
    public ResponseEntity<CommentDTO> getByIdCommentOfTask(@PathVariable long task_id, @PathVariable long comment_id) {
       
        CommentDTO commentDTO = commentService.getCommentByIdAndTaskId(task_id, comment_id);
        
        if (commentDTO != null) {
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping("/comment/{task_id}/create_comment")
    public ResponseEntity<CommentDTO> createCommentForTask(@PathVariable long task_id, @RequestBody CommentDTO commentDTO) {
        
        CommentDTO createdComment = commentService.createCommentForTask(task_id, commentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    /*
    @GetMapping("/comment/{task_id}/getAll_comments")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForTask(@PathVariable long task_id) {
      
        List<CommentDTO> comments = commentService.getAllCommentsForTask(task_id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{task_id}/delete_comment/{comment_id}")
    public ResponseEntity<Void> deleteCommentOfTask(@PathVariable long task_id, @PathVariable long comment_id) {
        
        commentService.deleteCommentForTask(task_id, comment_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/comment/{task_id}/update_comment/{comment_id}")
    public ResponseEntity<CommentDTO> updateCommentOfTask(
            @PathVariable long task_id,
            @PathVariable long comment_id,
            @RequestBody CommentDTO updatedCommentDTO) {
        
        CommentDTO updatedComment = commentService.updateCommentForTask(task_id, comment_id, updatedCommentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }
    */
}
