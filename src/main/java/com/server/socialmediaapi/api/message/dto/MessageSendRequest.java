package com.server.socialmediaapi.api.message.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageSendRequest {
    @Min(value = 1, message = "sender id cannot be less than 0")
    private int sender;

    @Min(value = 1, message = "consumer id cannot be less than 0")
    private int consumer;

    @Size(min = 1, max = 500, message = "The message field cannot have more than 500 characters")
    private String content;
}
