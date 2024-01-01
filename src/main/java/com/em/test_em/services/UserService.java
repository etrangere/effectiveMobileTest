package com.em.test_em.services;



import com.em.test_em._DTO.UserDTO;


public interface UserService {

   // List<UserDTO> getAllUsers();

    UserDTO getUserById(Long user_id);
    
    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO userDTO,Long user_id);
    
    void deleteUser(Long user_id);
    
}