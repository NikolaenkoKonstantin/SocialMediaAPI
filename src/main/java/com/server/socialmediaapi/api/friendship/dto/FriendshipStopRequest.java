package com.server.socialmediaapi.api.friendship.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipStopRequest {
    private int senderStopFriendship;

    private int recipientStopFriendship;
}
