package com.bluethink.dev.service;

import com.bluethink.dev.enitity.User;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    void update(Long id, User user);

    User save(User user);

    void deleteById(Long id);
}
