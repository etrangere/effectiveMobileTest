package com.em.test_em.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.em.test_em.beans.User;
import com.em.test_em.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService{
  
    @Autowired
    private UserRepository userRepository;
    
    
    //get all users
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }  
    
    //get user by id
    @Override
    public Optional<User> getUserById(Long id){
        return userRepository.findById((long) id);
    }  
    
    //create user
    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }
    
    //update user
    @Override
    public User updateUser(User user) {
        if (!this.userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find the user to update");
        }
        return this.userRepository.save(user);
    }
    
    //delete user
    @Override
    public void deleteUser(Long id) {
        if (!this.userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Unable to find user to delete");
        }
        this.userRepository.deleteById(id);
        if (this.userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Error deleting user");
        }
    }
}
