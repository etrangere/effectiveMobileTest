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


    public Comment(long id, String comment) {
        super();
        this.id = id;
        this.comment = comment;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }


    public Task getTask() {
        return task;
    }


    public void setTask(Task task) {
        this.task = task;
    }


    @Override
    public String toString() {
        return "Comment [id=" + id + ", comment=" + comment + ", task=" + task + "]";
    }


    
    
}
