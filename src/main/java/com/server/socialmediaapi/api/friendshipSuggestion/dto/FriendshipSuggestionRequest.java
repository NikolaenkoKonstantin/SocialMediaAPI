package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionRequest {
    @Min(value = 1, message = "The sender field cannot be less than 1")
    private int sender;

    @Min(value = 1, message = "The consumer field cannot be less than 1")
    private int consumer;
}
