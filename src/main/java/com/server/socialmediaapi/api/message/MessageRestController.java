package com.server.socialmediaapi.api.message;

import com.server.socialmediaapi.api.message.dto.MessageConverter;
import com.server.socialmediaapi.api.message.dto.MessageHistoryRequestDTO;
import com.server.socialmediaapi.api.message.dto.MessageResponseDTO;
import com.server.socialmediaapi.api.message.dto.MessageSendRequestDTO;
import com.server.socialmediaapi.model.Message;
import com.server.socialmediaapi.services.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageRestController {
    private final MessageService messageService;
    private final MessageConverter converter;

    //С пагинацией
    @GetMapping
    public Page<MessageResponseDTO> getMessageHistory(@RequestBody @Valid MessageHistoryRequestDTO messageDTO,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size){
        Message message = converter.convertToMessage(messageDTO);
        return messageService.getMessageHistory(message, page, size)
                .map(converter::convertToMessageResponseDTO);
    }


    @PostMapping
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody @Valid MessageSendRequestDTO dto){
        Message message = messageService.sendMessage(converter.convertToMessage(dto));
        return ResponseEntity.ok(converter.convertToMessageResponseDTO(message));
    }
}
