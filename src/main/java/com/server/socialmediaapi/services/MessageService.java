package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.message.dto.MessageHistoryRequest;
import com.server.socialmediaapi.api.message.dto.MessageSendRequest;
import com.server.socialmediaapi.models.Message;
import com.server.socialmediaapi.models.User;
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
    public Message sendMessage(MessageSendRequest dto){
        return messageRepo.save(createMessage(dto));
    }

    private Message createMessage(MessageSendRequest dto){
        return new Message(
                userRepo.findById(dto.getSender()).get(),
                userRepo.findById(dto.getConsumer()).get(),
                dto.getContent(),
                LocalDateTime.now()
        );
    }


    public Page<Message> getMessageHistory(MessageHistoryRequest dto, int page, int size){
        User sender = userRepo.findById(dto.getFirstUser()).get();
        User consumer = userRepo.findById(dto.getSecondUser()).get();

        return messageRepo.search(sender, consumer, PageRequest.of(page, size));
    }

}
