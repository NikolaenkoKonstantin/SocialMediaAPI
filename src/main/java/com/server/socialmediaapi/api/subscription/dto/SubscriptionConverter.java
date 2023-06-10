package com.server.socialmediaapi.api.subscription.dto;

import com.server.socialmediaapi.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionConverter {
    public SubscriptionResponseDTO convertToSubscriptionResponseDTO(Subscription subscription){
        return new SubscriptionResponseDTO(
                subscription.getId(),
                subscription.getSubscriber().getId(),
                subscription.getPublisher().getId()
        );
    }
}
