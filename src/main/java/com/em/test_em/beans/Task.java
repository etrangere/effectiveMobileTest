package com.em.test_em.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_table")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "header_column",columnDefinition = "TEXT")
    private String header;
    
    @Column(name = "description_column",columnDefinition = "LONGTEXT")
    private String description;
    
    @Column(name = "status_column",columnDefinition = "LONGTEXT")
    private String status;
    
    
    @Column(name = "priority_column",length=100)
    private String priority;
    
    
    @Column(name = "author_column",length=100)
    private String author;
    
    
    @Column(name = "executor_column",length=100)
    private String executor;

    @OneToMany(mappedBy="task", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List <Comments> comments = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private User user;
    
    public Task() {
        super();
    }


    public Task(long id, String header, String description, String status, String priority, String author,
            String executor) {
        super();
        this.id = id;
        this.header = header;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.executor = executor;
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


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getExecutor() {
        return executor;
    }


    public void setExecutor(String executor) {
        this.executor = executor;
    }


    @Override
    public String toString() {
        return "Task [id=" + id + ", header=" + header + ", description=" + description + ", status=" + status
                + ", priority=" + priority + ", author=" + author + ", executor=" + executor + "]";
    }
       

}
