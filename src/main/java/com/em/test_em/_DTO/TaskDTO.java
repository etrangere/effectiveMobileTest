package com.em.test_em._DTO;

import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;
import java.util.List;

/** Data Transfer Object (DTO) for representing tasks. */
public class TaskDTO {

  private long id;

  private String header;

  private String description;

  private TaskStatus status;

  private TaskPriority priority;

  private String author;

  private List<UserDTO> users;

  private List<CommentDTO> comments;

  public TaskDTO() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructs a TaskDTO with the specified id, header, description, status, priority, and author.
   *
   * @param id The unique identifier for the task.
   * @param header The header or title of the task.
   * @param description The detailed description of the task.
   * @param status The status of the task (e.g., OPEN, IN_PROGRESS, COMPLETED).
   * @param priority The priority of the task (e.g., LOW, MEDIUM, HIGH).
   * @param author The author or creator of the task.
   */
  public TaskDTO(
      long id,
      String header,
      String description,
      TaskStatus status,
      TaskPriority priority,
      String author) {
    super();
    this.id = id;
    this.header = header;
    this.description = description;
    this.status = status;
    this.priority = priority;
    this.author = author;
  }

  /**
   * Retrieves the unique identifier for the task.
   *
   * @return The id of the task.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the task.
   *
   * @param id The id to set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Retrieves the header or title of the task.
   *
   * @return The header of the task.
   */
  public String getHeader() {
    return header;
  }

  /**
   * Sets the header or title of the task.
   *
   * @param header The header to set.
   */
  public void setHeader(String header) {
    this.header = header;
  }

  /**
   * Retrieves the detailed description of the task.
   *
   * @return The description of the task.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the detailed description of the task.
   *
   * @param description The description to set.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Retrieves the status of the task.
   *
   * @return The status of the task.
   */
  public TaskStatus getStatus() {
    return status;
  }

  /**
   * Sets the status of the task.
   *
   * @param status The status to set.
   */
  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  /**
   * Retrieves the priority of the task.
   *
   * @return The priority of the task.
   */
  public TaskPriority getPriority() {
    return priority;
  }

  /**
   * Sets the priority of the task.
   *
   * @param priority The priority to set.
   */
  public void setPriority(TaskPriority priority) {
    this.priority = priority;
  }

  /**
   * Retrieves the author or creator of the task.
   *
   * @return The author of the task.
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Sets the author or creator of the task.
   *
   * @param author The author to set.
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Retrieves the list of users associated with the task.
   *
   * @return The list of users associated with the task.
   */
  public List<UserDTO> getUsers() {
    return users;
  }

  /**
   * Sets the list of users associated with the task.
   *
   * @param users The list of users to set.
   */
  public void setUsers(List<UserDTO> users) {
    this.users = users;
  }

  /**
   * Retrieves the list of comments associated with the task.
   *
   * @return The list of comments associated with the task.
   */
  public List<CommentDTO> getComments() {
    return comments;
  }

  /**
   * Sets the list of comments associated with the task.
   *
   * @param comments The list of comments to set.
   */
  public void setComments(List<CommentDTO> comments) {
    this.comments = comments;
  }

  /**
   * Returns a string representation of the TaskDTO.
   *
   * @return A string representation of the TaskDTO.
   */
  @Override
  public String toString() {
    return "TaskDTO [id="
        + id
        + ", header="
        + header
        + ", description="
        + description
        + ", status="
        + status
        + ", priority="
        + priority
        + ", author="
        + author
        + "]";
  }
}
