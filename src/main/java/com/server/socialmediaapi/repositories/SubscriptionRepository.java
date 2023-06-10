package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.model.Subscription;
import com.server.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    /**
     * Удаление подписки (отписаться)
     * @param subscriber - подписчик
     * @param publisher - публицист
     */
    void deleteBySubscriberAndPublisher(User subscriber, User publisher);
}
