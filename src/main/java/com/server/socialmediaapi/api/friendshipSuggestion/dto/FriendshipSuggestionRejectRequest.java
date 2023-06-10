package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionRejectRequest {
    @Min(value = 1, message = "The friendAccepting field cannot be less than 1")
    private int friendAccepting;

    @Min(value = 1, message = "The friendRequester field cannot be less than 1")
    private int friendRequester;
}
