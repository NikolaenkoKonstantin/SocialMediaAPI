package com.server.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Отправитель сообщения
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private User sender;

    /**
     * Получатель сообщения
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "consumer", referencedColumnName = "id")
    private User consumer;

    /**
     * Содержание сообщения
     */
    @Column(name = "content")
    private String content;

    /**
     * Дата и время отправки сообщения
     */
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;


    public Message(User sender, User consumer, String content, LocalDateTime dateOfCreation) {
        this.sender = sender;
        this.consumer = consumer;
        this.content = content;
        this.dateOfCreation = dateOfCreation;
    }
}
