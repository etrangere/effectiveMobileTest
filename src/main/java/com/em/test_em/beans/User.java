package com.em.test_em.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;

/**
 * Represents a User entity in the application.
 */
@Entity
@Table(name = "user_table")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "username",length=100)
    private String username;
    
    @Column(name = "password",length=100)
    private String password;
    
    @Column(name = "firstName",length=100)
    private String firstName;
    
    
    @Column(name = "lastName",length=100)
    private String lastName;
    
    @Column(name = "email",length=100)
    private String email;
    
    @Column(name = "executor")
    private boolean executor;
    
    @ManyToMany(mappedBy = "users",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();
    
    public User() {
        super();
    }

    /**
     * Constructs a User with the specified id, username, password, firstName, lastName, email, and executor status.
     *
     * @param id        The unique identifier for the user.
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @param email     The email address of the user.
     * @param executor  The executor status indicating if the user is an executor.
     */
    public User(long id, String username, String password, String firstName, String lastName, String email,
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
     * Checks if the user is an executor.
     *
     * @return True if the user is an executor, false otherwise.
     */
    public boolean isExecutor() {
        return executor;
    }

    /**
     * Sets the executor status indicating if the user is an executor.
     *
     * @param executor The executor status to set.
     */
    public void setExecutor(boolean executor) {
        this.executor = executor;
    }

    /**
     * Retrieves the list of tasks associated with the user.
     *
     * @return The list of tasks associated with the user.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks associated with the user.
     *
     * @param tasks The list of tasks to set.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Returns a string representation of the User.
     *
     * @return A string representation of the User.
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
                + ", lastName=" + lastName + ", email=" + email + ", executor=" + executor + ", tasks=" + tasks + "]";
    }

    

   

}
