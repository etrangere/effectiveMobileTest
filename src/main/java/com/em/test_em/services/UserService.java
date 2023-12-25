package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import com.em.test_em._DTO.UserDTO;

public interface UserService {

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    void deleteUser(Long id);
    
}