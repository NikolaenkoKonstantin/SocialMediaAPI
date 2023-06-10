package com.server.socialmediaapi.api.friendshipSuggestion;

import com.server.socialmediaapi.api.friendshipSuggestion.dto.*;
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
@RequestMapping("/friendshipSuggestion")
public class FriendshipSuggestionRestController {
    private final FriendshipSuggestionService friendshipSuggestionService;


    @PutMapping("/accept")
    public ResponseEntity<HttpStatus> acceptFriendshipSuggestion(
            @RequestBody @Valid FriendshipSuggestionAcceptRequest dto){
        friendshipSuggestionService.acceptFriendshipSuggestion(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PostMapping("/suggest")
    public ResponseEntity<HttpStatus> suggestFriendship(@RequestBody @Valid FriendshipSuggestionRequest dto){
        friendshipSuggestionService.suggestFriendshipSuggestion(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/cancel")
    public ResponseEntity<HttpStatus> cancelFriendshipSuggestion(
            @RequestBody @Valid FriendshipSuggestionCancelRequest dto){
        friendshipSuggestionService.cancelFriendshipSuggestion(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/reject")
    public ResponseEntity<HttpStatus> rejectFriendship(@RequestBody @Valid FriendshipSuggestionRejectRequest dto){
        friendshipSuggestionService.rejectFriendshipSuggestion(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
