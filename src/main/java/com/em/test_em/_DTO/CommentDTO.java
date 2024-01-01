package com.em.test_em._DTO;

import com.em.test_em.beans.Task;

public class CommentDTO {
    
    private long id;
    
    private String comment;
    
    private Task task;
    
    public CommentDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentDTO(long id, String comment) {
        super();
        this.id = id;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentDTO [id=" + id + ", comment=" + comment + "]";
    }
}
