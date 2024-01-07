package com.em.test_em.services;



import com.em.test_em._DTO.UserDTO;

/**
 * Service interface for handling operations related to users.
 */
public interface UserService {

    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user.
     * @return The UserDTO if found, otherwise null.
     */
    UserDTO getUserById(Long user_id);
    
    /**
     * Creates a new user.
     *
     * @param user The UserDTO containing the user details.
     * @return The created UserDTO.
     */
    UserDTO createUser(UserDTO user);

    /**
     * Updates an existing user.
     *
     * @param userDTO The updated UserDTO.
     * @param userId  The ID of the user to be updated.
     * @return The updated UserDTO.
     */
    UserDTO updateUser(UserDTO userDTO,Long user_id);
    
    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to be deleted.
     */
    void deleteUser(Long user_id);
    
}