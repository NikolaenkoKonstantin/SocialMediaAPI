package com.server.socialmediaapi.api.message.dto;

import com.server.socialmediaapi.model.Message;
import jakarta.mail.MessageAware;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConverter {
    private final ModelMapper modelMapper;


    public MessageResponseDTO convertToMessageResponseDTO(Message message){
        return new MessageResponseDTO(
                message.getId(),
                message.getSender().getId(),
                message.getConsumer().getId(),
                message.getContent(),
                message.getDateOfCreation()
        );
    }
}
