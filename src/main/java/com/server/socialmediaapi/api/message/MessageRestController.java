package com.server.socialmediaapi.api.message;

import com.server.socialmediaapi.api.message.dto.MessageConverter;
import com.server.socialmediaapi.api.message.dto.MessageHistoryRequest;
import com.server.socialmediaapi.api.message.dto.MessageResponse;
import com.server.socialmediaapi.api.message.dto.MessageSendRequest;
import com.server.socialmediaapi.models.Message;
import com.server.socialmediaapi.services.MessageService;
import com.server.socialmediaapi.utils.HibernateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageRestController {
    private final HibernateValidator hibernateValidator;
    private final MessageService messageService;
    private final MessageConverter converter;


    @GetMapping
    public Page<MessageResponse> getMessageHistory(@RequestBody @Valid MessageHistoryRequest messageDTO,
                                                   BindingResult bindingResult,
                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size){
        hibernateValidator.validate(bindingResult);

        return messageService.getMessageHistory(messageDTO, page, size)
                .map(converter::convertToMessageResponseDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse sendMessage(@RequestBody @Valid MessageSendRequest messageDTO,
                                                       BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        Message message = messageService.sendMessage(messageDTO);
        return converter.convertToMessageResponseDTO(message);
    }
}
