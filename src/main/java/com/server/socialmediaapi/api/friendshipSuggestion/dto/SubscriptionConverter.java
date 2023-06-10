package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import com.server.socialmediaapi.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionConverter {
    public SubscriptionResponse convertToSubscriptionResponseDTO(Subscription subscription){
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getSubscriber().getId(),
                subscription.getPublisher().getId()
        );
    }
}
