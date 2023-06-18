package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionAcceptRequest;
import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionCancelRequest;
import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionRejectRequest;
import com.server.socialmediaapi.api.friendshipSuggestion.dto.FriendshipSuggestionRequest;
import com.server.socialmediaapi.models.FriendshipSuggestion;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.repositories.FriendshipSuggestionRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendshipSuggestionService {
    private final FriendshipService friendshipService;
    private final FriendshipSuggestionRepository friendshipSuggestionRepo;
    private final SubscriptionService subscriptionService;
    private final UserRepository userRepo;


    @Transactional
    public void cancelFriendshipSuggestion(FriendshipSuggestionCancelRequest dto){
        int friendRequester = dto.getFriendRequester();
        int friendReceiving = dto.getFriendReceiving();

        subscriptionService.unsubscribe(friendRequester, friendReceiving);

        deleteFriendshipSuggestion(friendReceiving, friendRequester);
    }


    @Transactional
    public void rejectFriendshipSuggestion(FriendshipSuggestionRejectRequest dto){
        deleteFriendshipSuggestion(dto.getFriendAccepting(), dto.getFriendRequester());
    }


    @Transactional
    public void acceptFriendshipSuggestion(FriendshipSuggestionAcceptRequest dto){
        int friendAccepting = dto.getFriendAccepting();
        int friendRequester = dto.getFriendRequester();

        subscriptionService.subscribe(friendAccepting, friendRequester);

        friendshipService.acceptFriendship(friendAccepting, friendRequester);

        deleteFriendshipSuggestion(friendAccepting, friendRequester);
    }


    private void deleteFriendshipSuggestion(int friendAccepting, int friendRequester) {
        User userAccepting = userRepo.getOrThrow(friendAccepting);
        User userRequester = userRepo.getOrThrow(friendRequester);

        friendshipSuggestionRepo.deleteBySenderAndConsumer(userRequester, userAccepting);
    }


    @Transactional
    public FriendshipSuggestion suggestFriendshipSuggestion(FriendshipSuggestionRequest dto){
        subscriptionService.subscribe(dto.getSender(), dto.getConsumer());

        return friendshipSuggestionRepo.save(createFriendshipSuggestion(dto));
    }


    private FriendshipSuggestion createFriendshipSuggestion(FriendshipSuggestionRequest dto) {
        User sender = userRepo.getOrThrow(dto.getSender());
        User consumer = userRepo.getOrThrow(dto.getConsumer());

        return new FriendshipSuggestion(sender, consumer);
    }
}
