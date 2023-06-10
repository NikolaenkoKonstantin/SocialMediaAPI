package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.subscription.dto.FriendshipSuggestionRequest;
import com.server.socialmediaapi.model.FriendshipSuggestion;
import com.server.socialmediaapi.model.User;
import com.server.socialmediaapi.repositories.FriendshipSuggestionRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FriendshipSuggestionService {
    private final FriendshipSuggestionRepository friendshipSuggestionRepo;
    private final SubscriptionService subscriptionService;
    private final UserRepository userRepo;

    @Transactional
    public void suggestFriendship(FriendshipSuggestionRequest dto){
        subscriptionService.subscribe(dto);

        friendshipSuggestionRepo.save(createFriendshipSuggestion(dto));
    }

    private FriendshipSuggestion createFriendshipSuggestion(FriendshipSuggestionRequest dto) {
        User sender = userRepo.findById(dto.getSender()).get();
        User consumer = userRepo.findById(dto.getConsumer()).get();

        return new FriendshipSuggestion(sender, consumer);
    }
}
