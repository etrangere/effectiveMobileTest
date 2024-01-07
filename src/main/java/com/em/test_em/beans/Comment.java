package com.em.test_em.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a comment entity associated with a task.
 */
@Entity
@Table(name = "comment_table")
public class Comment implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "comments",columnDefinition = "LONGTEXT",nullable = true)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Task task;
    
    public Comment() {
        super();
    }

    /**
     * Constructs a Comment with the specified id and comment.
     *
     * @param id      The unique identifier for the comment.
     * @param comment The text content of the comment.
     */
    public Comment(long id, String comment) {
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
     * Retrieves the text content of the comment.
     *
     * @return The comment text.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the text content of the comment.
     *
     * @param comment The comment text to set.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Retrieves the task associated with the comment.
     *
     * @return The task associated with the comment.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Sets the task associated with the comment.
     *
     * @param task The task to set.
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Returns a string representation of the Comment.
     *
     * @return A string representation of the Comment.
     */
    @Override
    public String toString() {
        return "Comment [id=" + id + ", comment=" + comment + ", task=" + task + "]";
    }


    
    
}
