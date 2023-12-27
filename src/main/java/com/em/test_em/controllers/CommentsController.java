package com.em.test_em.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.em.test_em._DTO.CommentsDTO;
import com.em.test_em.services.CommentsService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/comment")
@Tag(name = "Comment", description = "Comment management APIs")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    // get all comments
    @GetMapping(value = "/getAllComments")
    public ResponseEntity<List<CommentsDTO>> findAll() {
        List<CommentsDTO> comments = commentsService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // get comment by id
    @GetMapping("/{id}")
    public ResponseEntity<CommentsDTO> findById(@PathVariable Long id) {
        Optional<CommentsDTO> comment = commentsService.getCommentsById(id);
        return comment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // create comment
    @PostMapping("")
    public ResponseEntity<CommentsDTO> create(@RequestBody CommentsDTO commentsDTO) {
        CommentsDTO createdComment = commentsService.createComment(commentsDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // update comment
    @PutMapping("/{id}")
    public ResponseEntity<CommentsDTO> update(@RequestBody CommentsDTO commentsDTO, @PathVariable("id") Long id) {
        if (!id.equals(commentsDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CommentsDTO updatedComment = commentsService.updateComment(commentsDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.ACCEPTED);
    }

    // delete comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentsService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
