package com.server.socialmediaapi.api.friendship;

import com.server.socialmediaapi.api.friendship.dto.FriendshipStopRequest;
import com.server.socialmediaapi.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friendship")
public class FriendshipRestController {
    private final FriendshipService friendshipService;


    @PutMapping("/stop")
    public ResponseEntity<HttpStatus> stopFriendship(@RequestBody FriendshipStopRequest dto){
        friendshipService.stopFriendship(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
