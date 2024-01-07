package com.em.test_em._DTO;

import java.util.Objects;

/** Data Transfer Object (DTO) for representing comments. */
public class CommentDTO {

  private long id;

  private String comment;

  public CommentDTO() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Constructs a CommentDTO with the specified id and comment.
   *
   * @param id The unique identifier for the comment.
   * @param comment The content of the comment.
   */
  public CommentDTO(long id, String comment) {
    super();
    this.id = id;
    this.comment = comment;
  }

  /**
   * Retrieves the unique identifier for the comment.
   *
   * @return The id of the comment.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the comment.
   *
   * @param id The id to set.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Retrieves the content of the comment.
   *
   * @return The comment content.
   */
  public String getComment() {
    return comment;
  }

  /**
   * Sets the content of the comment.
   *
   * @param comment The comment content to set.
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Returns a string representation of the CommentDTO.
   *
   * @return A string representation of the CommentDTO.
   */
  @Override
  public String toString() {
    return "CommentDTO [id=" + id + ", comment=" + comment + "]";
  }

  /**
   * Computes a hash code for the CommentDTO based on its id and comment.
   *
   * @return The computed hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(comment, id);
  }

  /**
   * Determines if the CommentDTO is equal to another object based on its id and comment.
   *
   * @param obj The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    CommentDTO other = (CommentDTO) obj;
    return Objects.equals(comment, other.comment) && id == other.id;
  }
}
