package com.server.socialmediaapi.services;

import com.server.socialmediaapi.model.Message;
import com.server.socialmediaapi.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepo;

    @Transactional
    public Message sendMessage(Message message){
        setMessageDateTime(message);
        return messageRepo.save(message);
    }

    private void setMessageDateTime(Message message){
        message.setDateOfCreation(LocalDateTime.now());
    }


    public Page<Message> getMessageHistory(Message message, int page, int size){
        if(message.getId() != null) {
            return getMessageHistoryWithBorderMessage(message, page, size);
        } else {
            return getMessageHistoryOfLimitlessMessage(message, page, size);
        }
    }


    private Page<Message> getMessageHistoryWithBorderMessage(Message message, int page, int size){
        return messageRepo.findAllBySenderAndConsumerAndIdAfterOrderById(
                message.getSender(),
                message.getConsumer(),
                message.getId(),
                PageRequest.of(page, size));
    }


    private Page<Message> getMessageHistoryOfLimitlessMessage(Message message, int page, int size){
        return messageRepo.findAllBySenderAndConsumerOrderById(
                message.getSender(),
                message.getConsumer(),
                PageRequest.of(page, size));
    }

}
