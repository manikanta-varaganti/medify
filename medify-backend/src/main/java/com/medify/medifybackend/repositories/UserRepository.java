package com.medify.medifybackend.repositories;

import com.medify.medifybackend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // Find user by username
    Optional<User> findByUsername(String username);

    // Find users by role
    List<User> findByRole(String role);

}
