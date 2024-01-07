package com.em.test_em.controllers;

import com.em.test_em._DTO.CommentDTO;
import com.em.test_em.services.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Controller class for handling Comment-related APIs. */
@CrossOrigin()
@RestController
@RequestMapping("/api/v1/comment")
@Tag(name = "Comment", description = "Comment management APIs")
public class CommentController {

  @Autowired private CommentService commentService;

  /**
   * Retrieves a specific comment of a task by ID.
   *
   * @param taskId The ID of the task.
   * @param commentId The ID of the comment.
   * @return The CommentDTO if found, otherwise returns a NOT_FOUND response.
   */
  @GetMapping("/{task_id}/getById_comment/{comment_id}")
  public ResponseEntity<CommentDTO> getByIdCommentOfTask(
      @PathVariable long task_id, @PathVariable long comment_id) {

    CommentDTO commentDTO = commentService.getCommentByIdAndTaskId(task_id, comment_id);

    if (commentDTO != null) {
      return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * Creates a new comment for a task.
   *
   * @param task_id The ID of the task.
   * @param commentDTO The CommentDTO containing the comment details.
   * @return The created CommentDTO.
   */
  @PostMapping("/{task_id}/create_comment")
  public ResponseEntity<CommentDTO> createCommentForTask(
      @PathVariable long task_id, @RequestBody CommentDTO commentDTO) {

    CommentDTO createdComment = commentService.createCommentForTask(task_id, commentDTO);
    return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
  }

  /**
   * Retrieves all comments for a specific task.
   *
   * @param task_id The ID of the task.
   * @return A list of CommentDTOs for the specified task.
   */
  @GetMapping("/{task_id}/getAll_comments")
  public ResponseEntity<List<CommentDTO>> getAllCommentsForTask(@PathVariable long task_id) {

    List<CommentDTO> comments = commentService.getAllCommentsForTask(task_id);
    return new ResponseEntity<>(comments, HttpStatus.OK);
  }

  /**
   * Deletes a specific comment of a task.
   *
   * @param task_id The ID of the task.
   * @param comment_id The ID of the comment.
   * @return A NO_CONTENT response if the comment is successfully deleted.
   */
  @DeleteMapping("/{task_id}/delete_comment/{comment_id}")
  public ResponseEntity<Void> deleteCommentOfTask(
      @PathVariable long task_id, @PathVariable long comment_id) {

    commentService.deleteCommentForTask(task_id, comment_id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Updates a specific comment of a task.
   *
   * @param task_id The ID of the task.
   * @param comment_id The ID of the comment.
   * @param updatedCommentDTO The updated CommentDTO.
   * @return The updated CommentDTO.
   */
  @PutMapping("/{task_id}/update_comment/{comment_id}")
  public ResponseEntity<CommentDTO> updateCommentOfTask(
      @PathVariable long task_id,
      @PathVariable long comment_id,
      @RequestBody CommentDTO updatedCommentDTO) {

    CommentDTO updatedComment =
        commentService.updateCommentForTask(task_id, comment_id, updatedCommentDTO);
    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
  }
}
