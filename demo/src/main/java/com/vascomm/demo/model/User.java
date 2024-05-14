package com.vascomm.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    String id;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column
    Boolean isActive;

    @Column
    String role;

}
