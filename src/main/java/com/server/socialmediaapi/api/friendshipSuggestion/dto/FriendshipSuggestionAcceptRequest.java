package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionAcceptRequest {
    private int friendAccepting;

    private int friendRequester;
}
