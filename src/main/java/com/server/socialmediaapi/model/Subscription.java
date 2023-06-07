package com.server.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Подписчик
     */
    @Column(name = "subscriber")
    private int subscriber;

    /**
     * Издатель
     */
    @Column(name = "publisher")
    private int publisher;
}
