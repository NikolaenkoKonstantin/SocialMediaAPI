package com.server.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    /**
     * Список постов пользователя
     */
    @OneToMany(mappedBy = "owner")
    private List<Post> posts;

    /**
     * Список подписок пользователя
     */
    @OneToMany(mappedBy = "publisher")
    private List<Subscription> subscriptions;

    /**
     * Список подписчиков пользователя
     */
    @OneToMany(mappedBy = "subscriber")
    private List<Subscription> subscribers;

    /**
     * Список отправленных заявок в друзья
     */
    @OneToMany(mappedBy = "senderFriendship")
    private List<FriendshipSuggestion> listOfFriendRequestsSent;

    /**
     * Список полученных заявок в друзья
     */
    @OneToMany(mappedBy = "consumerFriendship")
    private List<FriendshipSuggestion> listOfReceivedFriendRequests;

    /**
     * Список отправленных сообщений
     */
    @OneToMany(mappedBy = "senderMessage")
    private List<FriendshipSuggestion> listOfSentMessages;

    /**
     * Список полученных сообщений
     */
    @OneToMany(mappedBy = "consumerMessage")
    private List<FriendshipSuggestion> listOfReceivedMessages;
}
