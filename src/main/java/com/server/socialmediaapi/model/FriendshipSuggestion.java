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
    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private User senderFriendship;

    /**
     * Получатель заявки в друзья
     */
    @ManyToOne
    @JoinColumn(name = "consumer", referencedColumnName = "id")
    private User consumerFriendship;
}
