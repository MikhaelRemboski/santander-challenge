package com.santander.meetupchallenge.repository;

import com.santander.meetupchallenge.domain.UserMeetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserMeetupRepository extends JpaRepository<UserMeetup, Long> {
    @Transactional
    @Modifying
    @Query("update UserMeetup u set u.assistance = :assistance where u.meetupId = :meetupId and u.userId = :userId")
    void updateAssistance(@Param("userId") Long userId, @Param("meetupId") Long meetupId, @Param("assistance") Boolean assistance);


    @Query("select u from UserMeetup u where u.userId = :userId and u.meetupId = :meetupId")
    Optional<UserMeetup> findByUserAndMeetup(@Param("userId") Long userId, @Param("meetupId") Long meetupId);

    List<UserMeetup> findAllByMeetupId(Long meetupId);

}
