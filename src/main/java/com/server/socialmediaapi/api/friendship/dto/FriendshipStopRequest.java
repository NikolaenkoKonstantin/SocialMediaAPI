package com.server.socialmediaapi.api.friendship.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipStopRequest {
    @Min(value = 1, message = "The senderStopFriendship field cannot be less than 1")
    private int senderStopFriendship;

    @Min(value = 1, message = "The recipientStopFriendship field cannot be less than 1")
    private int recipientStopFriendship;
}
