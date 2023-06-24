package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.message.dto.MessageHistoryRequest;
import com.server.socialmediaapi.api.message.dto.MessageSendRequest;
import com.server.socialmediaapi.models.Message;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.repositories.MessageRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MessageServiceTest {
    @MockBean private MessageRepository messageRepository;
    @MockBean private UserRepository userRepository;

    @Autowired private MessageService messageService;

    private final User firstUser = new User(1, "name", "email", "password");
    private final User secondUser = new User(2, "name", "email", "password");


    @Test
    void sendMessage() {
        Message message = new Message(1, firstUser, secondUser, "msg", LocalDateTime.now());

        when(messageRepository.save(any(Message.class))).thenReturn(message);
        Message returnMessage = messageService.sendMessage(new MessageSendRequest(1, 2, "msg"));

        assertAll(
                () -> assertEquals(message.getId(), returnMessage.getId()),
                () -> assertEquals(message.getSender(), returnMessage.getSender()),
                () -> assertEquals(message.getConsumer(), returnMessage.getConsumer()),
                () -> assertEquals(message.getContent(), returnMessage.getContent()),
                () -> assertEquals(message.getDateOfCreation(), returnMessage.getDateOfCreation())
        );
    }

    @Test
    void getMessageHistory() {
        Page<Message> messages = new PageImpl<>(List.of(
                new Message(1, firstUser, secondUser, "Hello", null),
                new Message(2, secondUser, firstUser, "Hi", null)));

        when(messageRepository.search(any(), any(), any(Pageable.class))).thenReturn(messages);
        Page<Message> messagePage = messageService.getMessageHistory(new MessageHistoryRequest(1, 2), 0, 2);

        assertEquals(2, messagePage.getTotalElements());
    }
}