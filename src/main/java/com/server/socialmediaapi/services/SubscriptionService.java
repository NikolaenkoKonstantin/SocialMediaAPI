package com.server.socialmediaapi.services;

import com.server.socialmediaapi.models.Subscription;
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
    public void subscribe(int subscriber, int publisher){
        subscriptionRepo.save(createSubscription(subscriber, publisher));
    }


    private Subscription createSubscription(int subscriber, int publisher) {
        return new Subscription(
                userRepo.findById(subscriber).get(),
                userRepo.findById(publisher).get()
        );
    }


    @Transactional
    public void unsubscribe(int subscriber, int publisher){
        subscriptionRepo.deleteBySubscriberAndPublisher(
                userRepo.findById(subscriber).get(),
                userRepo.findById(publisher).get()
        );
    }
}
