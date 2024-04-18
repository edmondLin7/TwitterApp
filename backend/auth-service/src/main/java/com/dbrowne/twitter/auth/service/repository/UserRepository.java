package com.dbrowne.twitter.auth.service.repository;


import com.dbrowne.twitter.auth.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByusername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByusernameOrEmail(String username, String email);
    Boolean existsByusername(String username);
    Boolean existsByEmail(String email);
}

