package com.server.socialmediaapi.api.friendshipSuggestion.dto;

import com.server.socialmediaapi.models.FriendshipSuggestion;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendshipSuggestionConverter {
    private final ModelMapper modelMapper;

    public FriendshipSuggestionResponse convertToFriendshipSuggestionResponse(FriendshipSuggestion friendshipSuggestion){
        return modelMapper.map(friendshipSuggestion, FriendshipSuggestionResponse.class);
    }
}
