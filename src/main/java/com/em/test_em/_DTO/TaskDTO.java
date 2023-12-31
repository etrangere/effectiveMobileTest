package com.em.test_em._DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


public class TaskDTO {

    private long id;
    
    private String header;
    
    private String description;
    
    private String status;
    
    private String priority;
    
    private String author;
    
    //@JsonBackReference
    @JsonIgnore
    private List<UserDTO> users;
    
    @JsonManagedReference
    private List<CommentDTO> comments;
    
    public TaskDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TaskDTO(long id, String header, String description, String status, String priority, String author             ) {
        super();
        this.id = id;
        this.header = header;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.author = author;
        
    }
    
    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
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

    @Override
    public String toString() {
        return "TaskDTO [id=" + id + ", header=" + header + ", description=" + description + ", status=" + status
                + ", priority=" + priority + ", author=" + author + ", users=" + users + ", comments=" + comments + "]";
    }

   
    
}
