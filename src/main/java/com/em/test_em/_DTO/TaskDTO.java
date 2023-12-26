package com.em.test_em._DTO;

import java.util.List;

public class TaskDTO {

    private long id;
    
    private String header;
    
    private String description;
    
    private String status;
    
    private String priority;
    
    private String author;
    
    private List<UserDTO> task_user_executors;
    
    private List<CommentsDTO> comments;
    
    public TaskDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TaskDTO(long id, String header, String description, String status, String priority, String author,
             List<UserDTO> task_user_executors) {
        super();
        this.id = id;
        this.header = header;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        this.task_user_executors = task_user_executors;
    }



    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
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

    public List<UserDTO> getTask_user_executors() {
        return task_user_executors;
    }

    public void setTask_user_executors(List<UserDTO> task_user_executors) {
        this.task_user_executors = task_user_executors;
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
    
}
