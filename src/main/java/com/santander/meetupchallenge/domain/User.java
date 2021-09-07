package com.santander.meetupchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")

    private long userId;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Column(name = "role")
    private byte role;

    @OneToMany(mappedBy = "user")
    private List<UserMeetup> userMeetup;
}
