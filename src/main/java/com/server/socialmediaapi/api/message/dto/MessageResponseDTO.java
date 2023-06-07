package com.server.socialmediaapi.api.message.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDTO {
    private Integer id;

    private int sender;

    private int consumer;

    private String content;

    private LocalDateTime dateOfCreation;
}
