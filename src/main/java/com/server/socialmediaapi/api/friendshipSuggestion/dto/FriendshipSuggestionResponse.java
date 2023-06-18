package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionResponse {
    private int id;

    private int sender;

    private int consumer;
}
