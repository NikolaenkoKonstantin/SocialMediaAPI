package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionAcceptRequest;
import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionCancelRequest;
import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionRejectRequest;
import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionRequest;
import com.server.socialmediaapi.models.Friendship;
import com.server.socialmediaapi.models.FriendshipSuggestion;
import com.server.socialmediaapi.models.Subscription;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.repositories.FriendshipSuggestionRepository;
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
class FriendshipSuggestionServiceTest {
    @MockBean private FriendshipService friendshipService;
    @MockBean private FriendshipSuggestionRepository friendshipSuggestionRepo;
    @MockBean private SubscriptionService subscriptionService;
    @MockBean private UserRepository userRepo;

    @Autowired private FriendshipSuggestionService friendshipSuggestionService;

    private final User firstUser = new User(1, "name", "email", "password");
    private final User secondUser = new User(2, "name", "email", "password");

    @Test
    void suggestFriendshipSuggestion() {
        FriendshipSuggestion friendshipSuggestion = new FriendshipSuggestion(1, firstUser, secondUser);

        when(subscriptionService.subscribe(1, 2))
                .thenReturn(new Subscription(1, firstUser, secondUser));
        when(friendshipSuggestionRepo.save(any(FriendshipSuggestion.class)))
                .thenReturn(friendshipSuggestion);

        FriendshipSuggestion returnFriendshipSug = friendshipSuggestionService
                        .SubscribeAndSuggestFriendshipSuggestion(new FriendshipSuggestionRequest(1, 2));

        assertAll(
                () -> assertEquals(friendshipSuggestion.getId(), returnFriendshipSug.getId()),
                () -> assertEquals(friendshipSuggestion.getSender(), returnFriendshipSug.getSender()),
                () -> assertEquals(friendshipSuggestion.getConsumer(), returnFriendshipSug.getConsumer())
        );
    }

    @Test
    void rejectFriendshipSuggestion() {
        FriendshipSuggestionRejectRequest dto = new FriendshipSuggestionRejectRequest(1, 2);

        friendshipSuggestionService.rejectFriendshipSuggestion(dto);

        verify(friendshipSuggestionRepo).deleteBySenderAndConsumer(any(), any());
    }

    @Test
    void cancelFriendshipSuggestion() {
        FriendshipSuggestionCancelRequest dto = new FriendshipSuggestionCancelRequest(1, 2);

        when(subscriptionService.unsubscribe(1, 2)).thenReturn(true);
        friendshipSuggestionService.cancelFriendshipSuggestion(dto);

        verify(friendshipSuggestionRepo).deleteBySenderAndConsumer(any(), any());
    }

    @Test
    void acceptFriendshipSuggestion() {
        FriendshipSuggestionAcceptRequest dto = new FriendshipSuggestionAcceptRequest(1, 2);

        when(friendshipService.acceptFriendship(1, 2))
                .thenReturn(new Friendship(1, firstUser, secondUser));
        when(subscriptionService.subscribe(1, 2))
                .thenReturn(new Subscription(1, firstUser, secondUser));

        friendshipSuggestionService.acceptFriendshipSuggestion(dto);

        verify(friendshipSuggestionRepo).deleteBySenderAndConsumer(any(), any());
    }
}