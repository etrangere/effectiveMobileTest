package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import com.em.test_em.beans.User;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
    
}