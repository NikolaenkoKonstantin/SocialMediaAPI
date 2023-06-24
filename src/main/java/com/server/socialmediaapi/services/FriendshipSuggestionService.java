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

        if(subscriptionService.unsubscribe(friendRequester, friendReceiving)) {
            deleteFriendshipSuggestion(friendReceiving, friendRequester);
        }
    }


    @Transactional
    public void rejectFriendshipSuggestion(FriendshipSuggestionRejectRequest dto){
        deleteFriendshipSuggestion(dto.getFriendAccepting(), dto.getFriendRequester());
    }


    @Transactional
    public void acceptFriendshipSuggestion(FriendshipSuggestionAcceptRequest dto){
        acceptFriendship(dto.getFriendAccepting(), dto.getFriendRequester());
    }


    private void acceptFriendship(int friendAccepting, int friendRequester){
        subscribe(
                friendAccepting,
                friendRequester,
                friendshipService.acceptFriendship(friendAccepting, friendRequester)
        );
    }


    private void subscribe(int friendAccepting, int friendRequester, Friendship friendship){
        if(friendship != null) {
            deleteFriendshipSuggestion(
                    friendAccepting,
                    friendRequester,
                    subscriptionService.subscribe(friendAccepting, friendRequester)
            );
        }
    }


    private void deleteFriendshipSuggestion(int friendAccepting, int friendRequester, Subscription subscription) {
        if(subscription != null) {
            deleteFriendshipSuggestion(friendAccepting, friendRequester);
        }
    }


    private void deleteFriendshipSuggestion(int friendAccepting, int friendRequester) {
            friendshipSuggestionRepo.deleteBySenderAndConsumer(
                    userRepo.getOrThrow(friendAccepting),
                    userRepo.getOrThrow(friendRequester));
    }


    @Transactional
    public FriendshipSuggestion SubscribeAndSuggestFriendshipSuggestion(FriendshipSuggestionRequest dto){
        subscriptionService.subscribe(dto.getSender(), dto.getConsumer());

        return friendshipSuggestionRepo.save(createFriendshipSuggestion(dto));
    }


    private FriendshipSuggestion createFriendshipSuggestion(FriendshipSuggestionRequest dto) {
        User sender = userRepo.getOrThrow(dto.getSender());
        User consumer = userRepo.getOrThrow(dto.getConsumer());

        return new FriendshipSuggestion(sender, consumer);
    }
}
