package com.medify.medifybackend.services;

import com.medify.medifybackend.models.User;
import com.medify.medifybackend.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User newUser) {
        newUser.setId(id);
        return entityManager.merge(newUser);

    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
