package com.example.lab4_20203248.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int iduser;
    @Column(nullable = false, length = 45)
    private String username;
    @Column(nullable = false, length = 45)
    private String password;
}
