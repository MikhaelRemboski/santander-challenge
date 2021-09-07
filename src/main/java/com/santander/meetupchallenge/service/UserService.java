package com.santander.meetupchallenge.service;

import com.santander.meetupchallenge.domain.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
}
