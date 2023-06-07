package com.server.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя
     */
    @Column(name = "name")
    private String name;

    /**
     * Почта (логин)
     */
    @Column(name = "email")
    private String email;

    /**
     * Пароль
     */
    @Column(name = "password")
    private String password;
}
