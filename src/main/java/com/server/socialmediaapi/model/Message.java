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
    private int id;

    @Column(name = "sender")
    private int sender;

    @Column(name = "consumer")
    private int consumer;

    @Column(name = "content")
    private String content;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;
}
