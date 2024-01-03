package com.em.test_em._DTO;

import java.util.List;
import com.em.test_em.enums.TaskPriority;
import com.em.test_em.enums.TaskStatus;


public class TaskDTO {

    private long id;
    
    private String header;
    
    private String description;
    
    private TaskStatus status;
    
    private TaskPriority priority;
    
    private String author;
    
    private List<UserDTO> users;
    
    private List<CommentDTO> comments;
    
    public TaskDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TaskDTO(long id, String header, String description, TaskStatus status, TaskPriority priority, String author             ) {
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
        return "TaskDTO [id=" + id + ", header=" + header + ", description=" + description + ", status=" + status
                + ", priority=" + priority + ", author=" + author + "]";
    }
}
