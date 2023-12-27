package com.em.test_em.services;

import java.util.List;



import com.em.test_em._DTO.UserDTO;


public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long user_id);
    
    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user);

    void deleteUser(Long user_id);
    
}