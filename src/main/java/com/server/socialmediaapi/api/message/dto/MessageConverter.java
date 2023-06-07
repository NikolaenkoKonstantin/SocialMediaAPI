package com.server.socialmediaapi.api.message.dto;

import com.server.socialmediaapi.model.Message;
import jakarta.mail.MessageAware;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter {
    ModelMapper modelMapper;


    public MessageResponseDTO convertToMessageResponseDTO(Message message){
        return modelMapper.map(message, MessageResponseDTO.class);
    }


    public Message convertToMessage(MessageHistoryRequestDTO dto){
        return modelMapper.map(dto, Message.class);
    }


    public Message convertToMessage(MessageSendRequestDTO dto){
        return modelMapper.map(dto, Message.class);
    }
}
