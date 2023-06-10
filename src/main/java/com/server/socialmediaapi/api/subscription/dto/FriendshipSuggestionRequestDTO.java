package com.server.socialmediaapi.api.subscription.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionRequestDTO {
    private int sender;

    private int consumer;
}
