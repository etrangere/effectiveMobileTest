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

    public Task(long id, String header, String description, TaskStatus status, TaskPriority priority, String author) {
        super();
        this.id = id;
        this.header = header;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        
    }

    
   
    

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", header=" + header + ", description=" + description + ", status=" + status
                + ", priority=" + priority + ", author=" + author + ", users=" + users
                + ", comments=" + comments + "]";
    }


   
       
}
