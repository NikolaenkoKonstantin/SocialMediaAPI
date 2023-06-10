package com.server.socialmediaapi.api.message.dto;

import com.server.socialmediaapi.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConverter {
    public MessageResponse convertToMessageResponseDTO(Message message){
        return new MessageResponse(
                message.getId(),
                message.getSender().getId(),
                message.getConsumer().getId(),
                message.getContent(),
                message.getDateOfCreation()
        );
    }
}
