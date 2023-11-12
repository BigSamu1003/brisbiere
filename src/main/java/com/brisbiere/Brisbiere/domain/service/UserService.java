package com.brisbiere.Brisbiere.domain.service;

import com.brisbiere.Brisbiere.domain.User;
import com.brisbiere.Brisbiere.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(int userId){
        return userRepository.getUser(userId);
    }

    public Optional<User> getUserByemail(String email){
        return userRepository.getUserByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public boolean delete(int userId){
        return getUser(userId).map(user -> {
            userRepository.delete(userId);
            return true;
        }).orElse(false);
    }

}
