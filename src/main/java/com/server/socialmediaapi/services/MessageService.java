package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.message.dto.MessageHistoryRequestDTO;
import com.server.socialmediaapi.api.message.dto.MessageSendRequestDTO;
import com.server.socialmediaapi.model.Message;
import com.server.socialmediaapi.model.User;
import com.server.socialmediaapi.repositories.MessageRepository;
import com.server.socialmediaapi.repositories.UserRepository;
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
    private final UserRepository userRepo;

    @Transactional
    public Message sendMessage(MessageSendRequestDTO messageDTO){
        return messageRepo.save(createMessage(messageDTO));
    }

    private Message createMessage(MessageSendRequestDTO messageDTO){
        User sender = userRepo.findById(messageDTO.getSender()).get();
        User consumer = userRepo.findById(messageDTO.getConsumer()).get();

        return new Message(
                sender,
                consumer,
                messageDTO.getContent(),
                LocalDateTime.now()
        );
    }


    public Page<Message> getMessageHistory(MessageHistoryRequestDTO messageDTO, int page, int size){
        User sender = userRepo.findById(messageDTO.getFirstUser()).get();
        User consumer = userRepo.findById(messageDTO.getSecondUser()).get();

        return messageRepo.search(sender, consumer, PageRequest.of(page, size));
    }

}
