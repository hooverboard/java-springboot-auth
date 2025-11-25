package com.hever.site.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //find user by email
    Optional<UserEntity> findByEmail(String email);

    //check if email already exists
    boolean existsByEmail(String email);
}
