package com.cogent.twitter.backend.repository;

import com.cogent.twitter.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLoginId(String loginId);
    Optional<User> findByEmail(String email);
    Optional<User> findByLoginIdOrEmail(String username, String email);
    Boolean existsByLoginId(String LoginId);
    Boolean existsByEmail(String email);
}

