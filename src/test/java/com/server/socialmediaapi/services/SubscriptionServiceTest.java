package com.server.socialmediaapi.services;

import com.server.socialmediaapi.models.Subscription;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.repositories.SubscriptionRepository;
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
class SubscriptionServiceTest {
    @MockBean private SubscriptionRepository subscriptionRepository;
    @MockBean private UserRepository userRepository;

    @Autowired private SubscriptionService subscriptionService;

    @Test
    void subscribe() {
        User firstUser = new User(1, "name", "email", "password");
        User secondUser = new User(2, "name", "email", "password");
        Subscription subscription = new Subscription(1, firstUser, secondUser);

        when(subscriptionRepository.save(any())).thenReturn(subscription);
        Subscription returnSubscription = subscriptionService.subscribe(1, 2);

        assertAll(
                () -> assertEquals(subscription.getId(), returnSubscription.getId()),
                () -> assertEquals(subscription.getSubscriber(), returnSubscription.getSubscriber()),
                () -> assertEquals(subscription.getPublisher(), returnSubscription.getPublisher())
        );
    }

    @Test
    void unsubscribe() {
        when(subscriptionRepository.deleteBySubscriberAndPublisher(any(), any())).thenReturn(true);

        assertTrue(subscriptionService.unsubscribe(1, 2));
    }
}