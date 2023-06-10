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
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "subscriber", referencedColumnName = "id")
    private User subscriber;

    /**
     * Издатель
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "publisher", referencedColumnName = "id")
    private User publisher;


    public Subscription(User subscriber, User publisher) {
        this.subscriber = subscriber;
        this.publisher = publisher;
    }
}
