package com.santander.meetupchallenge.repository;

import com.santander.meetupchallenge.domain.Meetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeetupRepository extends JpaRepository<Meetup, Long> {
    Optional<Meetup> findById(Long id);
}
