package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionCancelRequest {
    @Min(value = 1, message = "The friendRequester field cannot be less than 1")
    private int friendRequester;

    @Min(value = 1, message = "The friendReceiving field cannot be less than 1")
    private int friendReceiving;
}
