package com.em.test_em.services;

import com.em.test_em._DTO.CommentDTO;
import com.em.test_em._DTO.TaskDTO;
import com.em.test_em.beans.Comment;
import com.em.test_em.beans.Task;
import com.em.test_em.repositories.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/** Service implementation for managing comment-related operations. */
@Service
public class CommentServiceImpl implements CommentService {

  @Autowired private CommentRepository commentRepository;

  @Autowired private ModelMapper mapper;

  @Autowired private TaskService taskService;

  /**
   * Retrieves an optional comment by its unique identifier.
   *
   * @param id The unique identifier of the comment.
   * @return Optional containing CommentDTO with details of the specified comment if found.
   */
  @Override
  public Optional<CommentDTO> getCommentById(Long id) {
    Optional<Comment> comment = commentRepository.findById(id);
    return mapToEntity(comment);
  }

  /**
   * Retrieves a list of comments associated with a task.
   *
   * @param taskId The unique identifier of the task.
   * @return List of CommentDTO containing details of comments associated with the task.
   */
  @Override
  public List<CommentDTO> getCommentByTask(Long taskId) {
    List<Comment> comments = commentRepository.findByTaskId(taskId);
    return mapToDTOList(comments);
  }

  /**
   * Updates an existing comment associated with a task.
   *
   * @param task_id The unique identifier of the task.
   * @param comment_id The unique identifier of the comment to be updated.
   * @param updatedCommentDTO Details to update the comment.
   * @return CommentDTO containing details of the updated comment.
   * @throws ResponseStatusException with HTTP status 404 (Not Found) if the comment is not found.
   */
  @Override
  public CommentDTO updateCommentForTask(
      Long task_id, Long comment_id, CommentDTO updatedCommentDTO) {

    if (!commentRepository.existsByIdAndTaskId(comment_id, task_id)) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Unable to find the comment to update");
    }

    Comment existingComment =
        commentRepository
            .findById(comment_id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

    existingComment.setComment(updatedCommentDTO.getComment());

    Comment updatedComment = commentRepository.save(existingComment);

    return mapToDTO(updatedComment);
  }

  /**
   * Deletes a comment associated with a task.
   *
   * @param task_id The unique identifier of the task.
   * @param comment_id The unique identifier of the comment to be deleted.
   * @throws ResponseStatusException with HTTP status 404 (Not Found) if the comment is not found.
   */
  @Override
  public void deleteCommentForTask(Long task_id, Long comment_id) {
    // Check if the comment exists for the given task
    if (!commentRepository.existsByIdAndTaskId(comment_id, task_id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find comment to delete");
    }
    commentRepository.deleteById(comment_id);

    // delete successfully
    if (commentRepository.existsById(comment_id)) {
      throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error deleting comment");
    }
  }

  /**
   * Retrieves a comment by its unique identifier and associated task.
   *
   * @param task_id The unique identifier of the task.
   * @param comment_id The unique identifier of the comment.
   * @return CommentDTO containing details of the specified comment if found.
   * @throws EntityNotFoundException if the comment is not found for the specified task.
   */
  public CommentDTO getCommentByIdAndTaskId(Long task_id, Long comment_id) {
    TaskDTO commentContainTask = taskService.getTaskById(task_id);
    Optional<CommentDTO> commentOfTask = getCommentById(comment_id);

    if (commentContainTask != null
        && commentOfTask.isPresent()
        && commentContainTask.getComments().contains(commentOfTask.get())) {
      return commentOfTask.get(); // Extract the CommentDTO
    } else {
      throw new EntityNotFoundException("Comment not found for the specified task");
    }
  }

  /**
   * Creates a new comment associated with a task.
   *
   * @param task_id The unique identifier of the task.
   * @param commentDTO Details of the comment to be created.
   * @return CommentDTO containing details of the created comment.
   */
  @Override
  public CommentDTO createCommentForTask(Long task_id, CommentDTO commentDTO) {

    TaskDTO commentForThisTask = taskService.getTaskById(task_id);
    Comment comment = mapToEntity(commentDTO);
    comment.setTask(mapToEntity(commentForThisTask));
    commentForThisTask.getComments().add(mapToDTO(comment));

    return mapToDTO(commentRepository.save(comment));
  }

  /**
   * Retrieves a list of all comments associated with a task.
   *
   * @param taskId The unique identifier of the task.
   * @return List of CommentDTO containing details of all comments associated with the task.
   */
  @Override
  public List<CommentDTO> getAllCommentsForTask(Long taskId) {
    List<Comment> comments = commentRepository.findByTaskId(taskId);
    return comments.stream().map(comment -> mapToDTOWithTask(comment)).collect(Collectors.toList());
  }

  private CommentDTO mapToDTO(Comment comment) {
    return mapper.map(comment, CommentDTO.class);
  }

  private List<CommentDTO> mapToDTOList(List<Comment> comment) {
    return comment.stream().map(this::mapToDTO).collect(Collectors.toList());
  }

  private Comment mapToEntity(CommentDTO commentDTO) {
    return mapper.map(commentDTO, Comment.class);
  }

  private Task mapToEntity(TaskDTO taskDTO) {
    return mapper.map(taskDTO, Task.class);
  }

  private Optional<TaskDTO> mapToEntity(Task task) {
    if (task == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(mapper.map(task, TaskDTO.class));
  }

  private Optional<CommentDTO> mapToEntity(Optional<Comment> commentOptional) {
    return commentOptional.map(comment -> mapper.map(comment, CommentDTO.class));
  }

  private CommentDTO mapToDTOWithTask(Comment comment) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(comment.getId());
    commentDTO.setComment(comment.getComment());

    // Perform operations on Task if needed
    Task task = comment.getTask();

    // Assuming mapToEntity converts Task to TaskDTO
    Optional<TaskDTO> taskDTOOptional = mapToEntity(task);

    // Check if TaskDTO is present before setting it in CommentDTO
    if (taskDTOOptional.isPresent()) {
      // Set the TaskDTO in CommentDTO
      comment.setTask(mapToEntity(taskDTOOptional.get()));
    }

    return commentDTO;
  }
}
