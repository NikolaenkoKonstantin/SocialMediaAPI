package com.server.socialmediaapi.api.friendshipSuggestion;

import com.server.socialmediaapi.api.friendshipSuggestion.dto.*;
import com.server.socialmediaapi.services.FriendshipSuggestionService;
import com.server.socialmediaapi.utils.HibernateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendshipSuggestion")
public class FriendshipSuggestionRestController {
    private final FriendshipSuggestionConverter converter;
    private final HibernateValidator hibernateValidator;
    private final FriendshipSuggestionService friendshipSuggestionService;


    @PutMapping("/accept")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void acceptFriendshipSuggestion(@RequestBody @Valid FriendshipSuggestionAcceptRequest dto,
                                                                 BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        friendshipSuggestionService.acceptFriendshipSuggestion(dto);
    }


    @PostMapping("/suggest")
    @ResponseStatus(HttpStatus.CREATED)
    public FriendshipSuggestionResponse suggestFriendship(@RequestBody @Valid FriendshipSuggestionRequest dto,
                                                        BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        return converter.convertToFriendshipSuggestionResponse(friendshipSuggestionService.suggestFriendshipSuggestion(dto));
    }


    @PutMapping("/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancelFriendshipSuggestion(@RequestBody @Valid FriendshipSuggestionCancelRequest dto,
                                                                 BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        friendshipSuggestionService.cancelFriendshipSuggestion(dto);
    }


    @PutMapping("/reject")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void rejectFriendship(@RequestBody @Valid FriendshipSuggestionRejectRequest dto,
                                                       BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        friendshipSuggestionService.rejectFriendshipSuggestion(dto);
    }
}
