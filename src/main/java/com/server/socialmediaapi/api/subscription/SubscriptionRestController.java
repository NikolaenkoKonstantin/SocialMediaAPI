package com.server.socialmediaapi.api.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionRestController {

    @PostMapping("/subscribe")
    public ResponseEntity<HttpStatus> subscribe(){
       return null;
    }


    @PostMapping("/unsubscribe")
    public ResponseEntity<HttpStatus> unsubscribe(){
        return null;
    }


    @PostMapping("/reject")
    public ResponseEntity<HttpStatus> rejectFriendship(){
        return null;
    }
}
