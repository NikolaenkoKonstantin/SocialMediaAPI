package com.server.socialmediaapi.api.friendship;

import com.server.socialmediaapi.api.friendship.dto.FriendshipStopRequest;
import com.server.socialmediaapi.services.FriendshipService;
import com.server.socialmediaapi.utils.HibernateValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendship")
public class FriendshipRestController {
    private final HibernateValidator hibernateValidator;
    private final FriendshipService friendshipService;


    @PutMapping("/stop")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void stopFriendship(@RequestBody @Valid FriendshipStopRequest dto,
                                                     BindingResult bindingResult){
        hibernateValidator.validate(bindingResult);

        friendshipService.stopFriendship(dto);
    }
}
