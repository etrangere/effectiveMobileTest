package com.em.test_em.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * Represents a Task entity in the application.
 */
@Entity
@Table(name = "task_table")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "header",columnDefinition = "TEXT",nullable = true)
    private String header;
    
    @Column(name = "description",columnDefinition = "LONGTEXT",nullable = true)
    private String description;
    
    @Column(name = "status",length=15,nullable = true)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    @Column(name = "priority",length=15,nullable = true)
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    
    @Column(name = "author",length=100,nullable = true)
    private String author;
    
    @ManyToMany
    @JsonBackReference
    private List<User> users = new ArrayList<>();


    @OneToMany(mappedBy="task", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List <Comment> comments = new ArrayList<>();
    
    public Task() {
        super();
    }

    /**
     * Constructs a Task with the specified id, header, description, status, priority, and author.
     *
     * @param id          The unique identifier for the task.
     * @param header      The header or title of the task.
     * @param description The detailed description of the task.
     * @param status      The status of the task (e.g., OPEN, IN_PROGRESS, DONE).
     * @param priority    The priority level of the task (e.g., HIGH, MEDIUM, LOW).
     * @param author      The author or creator of the task.
     */
    public Task(long id, String header, String description, TaskStatus status, TaskPriority priority, String author) {
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
     * Retrieves the priority level of the task.
     *
     * @return The priority of the task.
     */
    public TaskPriority getPriority() {
        return priority;
    }

    /**
     * Sets the priority level of the task.
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
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users associated with the task.
     *
     * @param users The list of users to set.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Retrieves the list of comments associated with the task.
     *
     * @return The list of comments associated with the task.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets the list of comments associated with the task.
     *
     * @param comments The list of comments to set.
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return A string representation of the Task.
     */
    @Override
    public String toString() {
        return "Task [id=" + id + ", header=" + header + ", description=" + description + ", status=" + status
                + ", priority=" + priority + ", author=" + author + ", users=" + users
                + ", comments=" + comments + "]";
    }


   
       
}
