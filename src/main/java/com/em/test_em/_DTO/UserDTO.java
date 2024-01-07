package com.em.test_em._DTO;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing users.
 */
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
    
    /**
     * Constructs a UserDTO with the specified id, username, password, firstName, lastName, email, and executor status.
     *
     * @param id        The unique identifier for the user.
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param executor  The executor status indicating whether the user is an executor.
     */
    public UserDTO(long id, String username, String password, String firstName, String lastName, String email,
            boolean executor) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.executor = executor;
        
    }

    /**
     * Retrieves the unique identifier for the user.
     *
     * @return The id of the user.
     */
    public long getId() {
        return id;
    }
    
    /**
     * Sets the unique identifier for the user.
     *
     * @param id The id to set.
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Sets the username of the user.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
   
    /**
     * Retrieves the list of tasks associated with the user.
     *
     * @return The list of tasks associated with the user.
     */
    public List<TaskDTO> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks associated with the user.
     *
     * @param tasks The list of tasks to set.
     */
    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the executor status indicating whether the user is an executor.
     *
     * @return The executor status of the user.
     */
    public boolean isExecutor() {
        return executor;
    }

    /**
     * Sets the executor status indicating whether the user is an executor.
     *
     * @param executor The executor status to set.
     */
    public void setExecutor(boolean executor) {
        this.executor = executor;
    }

    /**
     * Returns a string representation of the UserDTO.
     *
     * @return A string representation of the UserDTO.
     */
    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + ", executor=" + executor + "]";
    }

    
    
}
