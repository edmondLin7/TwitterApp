package com.dbrowne.twitter.auth.service.repository;


import com.dbrowne.twitter.auth.service.entity.User;
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

