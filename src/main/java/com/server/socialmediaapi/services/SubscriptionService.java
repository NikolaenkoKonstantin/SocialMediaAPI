package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.subscription.dto.FriendshipSuggestionRequest;
import com.server.socialmediaapi.model.Subscription;
import com.server.socialmediaapi.model.User;
import com.server.socialmediaapi.repositories.SubscriptionRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepo;
    private final UserRepository userRepo;

    @Transactional
    public void subscribe(FriendshipSuggestionRequest subscriptionDTO){
        subscriptionRepo.save(createSubscription(subscriptionDTO));
    }


    private Subscription createSubscription(FriendshipSuggestionRequest subscriptionDTO) {
        User subscriber = userRepo.findById(subscriptionDTO.getSender()).get();
        User publisher = userRepo.findById(subscriptionDTO.getConsumer()).get();

        return new Subscription(subscriber, publisher, "subscription");
    }


    //Ещё не настроен до конца
    @Transactional
    public void unsubscribe(Subscription subscription){
        subscriptionRepo.delete(subscription);
    }


    //Ещё не настроен до конца
    @Transactional
    public Subscription rejectFriendship(Subscription subscription){
        return subscriptionRepo.save(subscription);
    }

}
