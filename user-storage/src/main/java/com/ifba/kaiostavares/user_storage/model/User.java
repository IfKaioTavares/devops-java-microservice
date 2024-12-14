package com.ifba.kaiostavares.user_storage.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String username;
    @Column(length = 120, unique = true)
    private String email;
    @Column(name = "public_id")
    private UUID publicId;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.publicId = UUID.randomUUID();
    }
}
