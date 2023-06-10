package com.server.socialmediaapi.api.subscription;

import com.server.socialmediaapi.api.subscription.dto.SubscriptionConverter;
import com.server.socialmediaapi.api.subscription.dto.FriendshipSuggestionRequest;
import com.server.socialmediaapi.services.FriendshipSuggestionService;
import com.server.socialmediaapi.services.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendship")
public class FriendshipSuggestionRestController {
    private final SubscriptionConverter converter;
    private final SubscriptionService subscriptionService;
    private final FriendshipSuggestionService friendshipSuggestionService;

    @PutMapping("/accept")
    public void acceptFriendship(@RequestBody FriendshipSuggestionRequest requestDTO){

    }


    @PostMapping("/suggest")
    public ResponseEntity<HttpStatus> suggestFriendship(@RequestBody @Valid FriendshipSuggestionRequest requestDTO,
                                                             BindingResult bindingResult){
        friendshipSuggestionService.suggestFriendship(requestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/unsubscribe")
    public ResponseEntity<HttpStatus> unsubscribe(){
        return null;
    }


    @PutMapping("/reject")
    public ResponseEntity<HttpStatus> rejectFriendship(){
        return null;
    }
}
