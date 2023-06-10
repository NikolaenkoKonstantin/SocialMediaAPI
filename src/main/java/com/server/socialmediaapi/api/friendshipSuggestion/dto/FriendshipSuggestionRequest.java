package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionRequest {
    private int sender;

    private int consumer;
}
