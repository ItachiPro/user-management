package com.example.stanley.usermanagement.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor @NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Getter @Setter @Column(name = "username")
    private String username;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "pwd")
    private String password;
}
