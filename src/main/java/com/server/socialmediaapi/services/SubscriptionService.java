package com.server.socialmediaapi.services;

import com.server.socialmediaapi.models.Subscription;
import com.server.socialmediaapi.repositories.SubscriptionRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepo;
    private final UserRepository userRepo;


    @Transactional
    public Subscription subscribe(int subscriber, int publisher){
        return subscriptionRepo.save(createSubscription(subscriber, publisher));
    }


    private Subscription createSubscription(int subscriber, int publisher) {
        return new Subscription(
                userRepo.getOrThrow(subscriber),
                userRepo.getOrThrow(publisher)
        );
    }


    @Transactional
    public boolean unsubscribe(int subscriber, int publisher){
        return subscriptionRepo.deleteBySubscriberAndPublisher(
                userRepo.getOrThrow(subscriber),
                userRepo.getOrThrow(publisher)
        );
    }
}
