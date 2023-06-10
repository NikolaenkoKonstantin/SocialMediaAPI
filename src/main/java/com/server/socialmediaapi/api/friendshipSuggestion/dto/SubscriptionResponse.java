package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponse {
    private int id;

    private int subscriber;

    private int publisher;
}
