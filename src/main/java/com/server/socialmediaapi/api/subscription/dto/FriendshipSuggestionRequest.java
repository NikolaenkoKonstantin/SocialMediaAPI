package com.server.socialmediaapi.api.subscription.dto;

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
