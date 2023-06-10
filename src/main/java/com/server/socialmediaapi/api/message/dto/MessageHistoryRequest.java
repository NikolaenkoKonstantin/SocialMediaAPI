package com.server.socialmediaapi.api.message.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageHistoryRequest {
    @Min(value = 0, message = "sender id cannot be less than 0")
    private int firstUser;

    @Min(value = 0, message = "consumer id cannot be less than 0")
    private int secondUser;
}
