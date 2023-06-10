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
    @ToString.Exclude
    @OneToMany(mappedBy = "owner")
    private List<Post> posts;

    /**
     * Список подписок пользователя
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "publisher")
    private List<Subscription> subscriptions;

    /**
     * Список подписчиков пользователя
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "subscriber")
    private List<Subscription> subscribers;

    /**
     * Список отправленных заявок в друзья
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "sender")
    private List<FriendshipSuggestion> listOfFriendRequestsSent;

    /**
     * Список полученных заявок в друзья
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "consumer")
    private List<FriendshipSuggestion> listOfReceivedFriendRequests;

    /**
     * Список отправленных сообщений
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "sender")
    private List<Message> listOfSentMessages;

    /**
     * Список полученных сообщений
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "consumer")
    private List<Message> listOfReceivedMessages;
}
