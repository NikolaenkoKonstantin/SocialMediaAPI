package com.server.socialmediaapi.api.message;

import com.server.socialmediaapi.api.message.dto.MessageConverter;
import com.server.socialmediaapi.api.message.dto.MessageHistoryRequest;
import com.server.socialmediaapi.api.message.dto.MessageResponse;
import com.server.socialmediaapi.api.message.dto.MessageSendRequest;
import com.server.socialmediaapi.model.Message;
import com.server.socialmediaapi.services.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageRestController {
    private final MessageService messageService;
    private final MessageConverter converter;

    //добавить проверку валидации, исключения с ошибками
    @GetMapping
    public Page<MessageResponse> getMessageHistory(@RequestBody @Valid MessageHistoryRequest messageDTO,
                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                   BindingResult bindingResult){
        return messageService.getMessageHistory(messageDTO, page, size)
                .map(converter::convertToMessageResponseDTO);
    }


    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody @Valid MessageSendRequest messageDTO,
                                                       BindingResult bindingResult){
        Message message = messageService.sendMessage(messageDTO);
        return ResponseEntity.ok(converter.convertToMessageResponseDTO(message));
    }
}
