package com.server.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friendship_suggestion")
public class FriendshipSuggestion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Отправитель заявки в друзья
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private User sender;

    /**
     * Получатель заявки в друзья
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "consumer", referencedColumnName = "id")
    private User consumer;


    public FriendshipSuggestion(User sender, User consumer) {
        this.sender = sender;
        this.consumer = consumer;
    }
}
