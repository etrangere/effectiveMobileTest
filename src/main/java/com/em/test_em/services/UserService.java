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
public class UserService {

    
    @Autowired
    private UserRepository userRepository;
    
    // get all users
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }  
    
    // get user by id
    
    public Optional<User> getUserById(Long id){
        return userRepository.findById((long) id);
    }  
    
    
    //create user
    public User create(User user) {
        return this.userRepository.save(user);
    }
    
    //update user
    public User update(User user) {
        if (!this.userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Impossible de trouver le resource à mettre à jour");
        }
        return this.userRepository.save(user);
    }
    
    //delete user
    public void delete(Long id) {
        if (!this.userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Impossible de trouver le project à supprimer");
        }
        this.userRepository.deleteById(id);
        if (this.userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "Erreur lors de la suppression de project");
        }
    }
}