package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipSuggestionRejectRequest {
    private int friendAccepting;

    private int friendRequester;
}
