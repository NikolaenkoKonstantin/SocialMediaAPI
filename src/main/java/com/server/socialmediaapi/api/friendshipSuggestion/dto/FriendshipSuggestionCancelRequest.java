package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionCancelRequest {
    private int friendRequester;

    private int friendReceiving;
}
