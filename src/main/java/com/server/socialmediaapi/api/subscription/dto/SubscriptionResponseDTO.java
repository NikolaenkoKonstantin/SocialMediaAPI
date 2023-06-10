package com.server.socialmediaapi.api.subscription.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionResponseDTO {
    private int id;

    private int subscriber;

    private int publisher;
}
