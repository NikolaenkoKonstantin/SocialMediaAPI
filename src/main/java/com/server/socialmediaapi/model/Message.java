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
    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private User senderMessage;

    /**
     * Получатель сообщения
     */
    @ManyToOne
    @JoinColumn(name = "consumer", referencedColumnName = "id")
    private User consumerMessage;

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
}
