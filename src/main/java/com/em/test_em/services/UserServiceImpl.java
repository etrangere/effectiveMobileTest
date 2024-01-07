package com.em.test_em.services;

import com.em.test_em._DTO.UserDTO;
import com.em.test_em.beans.User;
import com.em.test_em.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

/** Service implementation for managing user-related operations. */
@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private ModelMapper mapper;

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param id The unique identifier of the user.
   * @return UserDTO containing user details.
   * @throws EntityNotFoundException if the user with the specified ID is not found.
   */
  @Override
  public UserDTO getUserById(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    return userOptional.map(this::mapToDTO).orElseThrow(EntityNotFoundException::new);
  }

  /**
   * Creates a new user with the provided details.
   *
   * @param userDTO Details of the user to be created.
   * @return UserDTO containing details of the created user.
   */
  @Override
  public UserDTO createUser(UserDTO userDTO) {
    User user = mapToEntity(userDTO);
    return mapToDTO(userRepository.save(user));
  }

  /**
   * Updates an existing user with new details.
   *
   * @param userDTO Details to update the user.
   * @param user_id The unique identifier of the user to be updated.
   * @return UserDTO containing details of the updated user.
   * @throws ResponseStatusException with HTTP status 404 (Not Found) if the user is not found.
   */
  @Override
  public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable Long user_id) {
    Optional<User> userOptional = userRepository.findById(user_id);

    if (userOptional.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find the user to update");
    }

    User existingUser = userOptional.get();
    existingUser.setUsername(userDTO.getUsername());
    existingUser.setPassword(userDTO.getPassword());
    existingUser.setFirstName(userDTO.getFirstName());
    existingUser.setLastName(userDTO.getLastName());
    existingUser.setEmail(userDTO.getEmail());
    existingUser.setExecutor(userDTO.isExecutor());

    // Save the updated user
    User updatedUser = userRepository.save(existingUser);

    return mapToDTO(updatedUser);
  }

  /**
   * Deletes a user by their unique identifier.
   *
   * @param user_id The unique identifier of the user to be deleted.
   * @throws ResponseStatusException with HTTP status 404 (Not Found) if the user is not found.
   * @throws ResponseStatusException with HTTP status 417 (Expectation Failed) if there is an error
   *     deleting the user.
   */
  @Override
  public void deleteUser(Long user_id) {
    if (!userRepository.existsById(user_id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user to delete");
    }
    userRepository.deleteById(user_id);
    if (userRepository.existsById(user_id)) {
      throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error deleting user");
    }
  }

  private UserDTO mapToDTO(User user) {
    return mapper.map(user, UserDTO.class);
  }

  private User mapToEntity(UserDTO userDTO) {
    return mapper.map(userDTO, User.class);
  }
}
