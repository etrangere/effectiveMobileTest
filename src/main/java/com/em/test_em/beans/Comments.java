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

@Entity
@Table(name = "comments_table")
public class Comments implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "comments",columnDefinition = "LONGTEXT",nullable = true)
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Task task;
    
    public Comments() {
        super();
    }


    public Comments(long id, String comments) {
        super();
        this.id = id;
        this.comments = comments;
    }


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comments [id=" + id + ", comments=" + comments + "]";
    }
    
}
