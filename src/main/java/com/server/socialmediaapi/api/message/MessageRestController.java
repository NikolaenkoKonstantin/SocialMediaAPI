package com.server.socialmediaapi.api.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageRestController {

    //С пагинацией
    @GetMapping
    public ResponseEntity<HttpStatus> getMessageHistory(){
        return null;
    }


    @PostMapping
    public ResponseEntity<HttpStatus> sendMessage(){
        return null;
    }
}
