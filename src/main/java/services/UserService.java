package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import beans.User;
import repositories.UserRepository;

@Service
public class UserService {

    
    @Autowired
    private UserRepository userRepository;
    
    // get all users
    
    public List<User> getAllComments(){
        return userRepository.findAll();
    }  
    
    // get user by id
    
    public Optional<User> getCommentsById(Long id){
        return userRepository.findById((long) id);
    }  
    
    
    //create task
    public User create(User user) {
        return this.userRepository.save(user);
    }
    
    //update task
    public User update(User user) {
        if (!this.userRepository.existsById(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Impossible de trouver le resource à mettre à jour");
        }
        return this.userRepository.save(user);
    }
    
    //delete task
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