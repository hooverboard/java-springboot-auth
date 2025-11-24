package com.hever.site.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //ind by username
    Optional<User> findByUsername(String username);

    //find by email
    Optional<User> findByEmail(String email);

    //check if username exists
    boolean existsByUsername(String username);

    //check if email exists
    boolean existsByEmail(String email);
}
