package hamon.first.budget_app.service;

import hamon.first.budget_app.models.User;
import hamon.first.budget_app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(int id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(RuntimeException::new);
    }

    public Optional<User> loadUserByUsername(String username){
        return userRepository.findByUsername(username);
    }


    @Transactional
    public void save(User user){
        enrichUser(user);
        userRepository.save(user);
    }

    private void enrichUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }


    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }


}