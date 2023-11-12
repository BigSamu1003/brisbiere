package com.brisbiere.Brisbiere.domain.repository;

import com.brisbiere.Brisbiere.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUser(int userId);
    Optional<User> getUserByEmail(String email);
    void save(User user);
    void delete(int userId);
    Optional<User> hasAccount(String email);
}
