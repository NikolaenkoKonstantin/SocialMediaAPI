package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.friendship.dto.FriendshipStopRequest;
import com.server.socialmediaapi.models.Friendship;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.repositories.FriendshipRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FriendshipServiceTest {
    @MockBean private FriendshipRepository friendshipRepository;
    @MockBean private UserRepository userRepository;
    @MockBean private SubscriptionService subscriptionService;

    @Autowired private FriendshipService friendshipService;

    @Test
    void acceptFriendship() {
        User firstUser = new User(1, "name", "email", "password");
        User secondUser = new User(2, "name", "email", "password");
        Friendship friendship = new Friendship(1, firstUser, secondUser);

        when(friendshipRepository.save(any(Friendship.class))).thenReturn(friendship);
        Friendship returnFriendship = friendshipService.acceptFriendship(1, 2);

        assertAll(
                () -> assertEquals(friendship.getId(), returnFriendship.getId()),
                () -> assertEquals(friendship.getFirstFriend(), returnFriendship.getFirstFriend()),
                () -> assertEquals(friendship.getSecondFriend(), returnFriendship.getSecondFriend())
        );
    }

    @Test
    void stopFriendship() {
        when(friendshipRepository.delete(any(), any())).thenReturn(true);
        when(subscriptionService.unsubscribe(1, 2)).thenReturn(true);

        friendshipService.stopFriendship(new FriendshipStopRequest(1, 2));

        verify(subscriptionService).unsubscribe(1, 2);
    }
}