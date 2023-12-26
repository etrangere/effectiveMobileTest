package com.em.test_em._DTO;

import java.util.List;

public class UserDTO {
    
    private long id;
    
    private String username;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private boolean executor;
    
    private List<TaskDTO> tasks;
        
    public UserDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public UserDTO(long id, String username, String password, String firstName, String lastName, String email,
            boolean executor, List<TaskDTO> tasks) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.executor = executor;
        this.tasks = tasks;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<TaskDTO> getTasks() {
        return tasks;
    }
    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExecutor() {
        return executor;
    }

    public void setExecutor(boolean executor) {
        this.executor = executor;
    }

}
