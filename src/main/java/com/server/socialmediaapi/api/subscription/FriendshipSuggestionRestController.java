package com.server.socialmediaapi.api.subscription;

import com.server.socialmediaapi.api.subscription.dto.SubscriptionConverter;
import com.server.socialmediaapi.api.subscription.dto.FriendshipSuggestionRequestDTO;
import com.server.socialmediaapi.api.subscription.dto.SubscriptionResponseDTO;
import com.server.socialmediaapi.model.Subscription;
import com.server.socialmediaapi.services.FriendshipSuggestionService;
import com.server.socialmediaapi.services.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendship")
public class FriendshipSuggestionRestController {
    private final SubscriptionConverter converter;
    private final SubscriptionService subscriptionService;
    private final FriendshipSuggestionService friendshipSuggestionService;

    @PostMapping("/accept")
    public void acceptFriendship(@RequestBody FriendshipSuggestionRequestDTO requestDTO){

    }


    @PostMapping("/suggest")
    public ResponseEntity<HttpStatus> suggestFriendship(@RequestBody @Valid FriendshipSuggestionRequestDTO requestDTO,
                                                             BindingResult bindingResult){
        friendshipSuggestionService.suggestFriendship(requestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
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
