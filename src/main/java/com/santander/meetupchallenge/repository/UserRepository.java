package com.santander.meetupchallenge.repository;

import com.santander.meetupchallenge.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByUsername(String username);
   Optional<User> findByUserId(Long userId);
}
