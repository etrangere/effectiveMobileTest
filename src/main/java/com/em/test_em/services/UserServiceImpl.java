package com.em.test_em.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


import com.em.test_em._DTO.UserDTO;

import com.em.test_em.beans.User;
import com.em.test_em.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UserServiceImpl implements UserService{
  
    @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    private ModelMapper mapper;
    
    //get all users
    @Override
    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        return mapToDTOList(users);
    }  

    //get user by id
    @Override
    public UserDTO getUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(this::mapToDTO).orElseThrow(EntityNotFoundException::new);
    }  
    
    //create user
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        return mapToDTO(userRepository.save(user));
    }
     
    //update user
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
    
    //delete user
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

    private List<UserDTO> mapToDTOList(List<User> user) {
        return user.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private User mapToEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }
    
}
