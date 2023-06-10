package com.server.socialmediaapi.api.subscription.dto;

import com.server.socialmediaapi.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
