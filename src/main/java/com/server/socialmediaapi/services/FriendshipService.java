package com.server.socialmediaapi.services;

import com.server.socialmediaapi.api.friendship.dto.FriendshipStopRequest;
import com.server.socialmediaapi.models.Friendship;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.repositories.FriendshipRepository;
import com.server.socialmediaapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final SubscriptionService subscriptionService;
    private final UserRepository userRepo;
    private final FriendshipRepository friendshipRepo;


    @Transactional
    public void stopFriendship(FriendshipStopRequest dto){
        int senderStopFriendship = dto.getSenderStopFriendship();
        int recipientStopFriendship = dto.getRecipientStopFriendship();

        subscriptionService.unsubscribe(senderStopFriendship, recipientStopFriendship);

        deleteFriendship(senderStopFriendship, recipientStopFriendship);
    }


    private void deleteFriendship(int senderStopFriendship, int recipientStopFriendship) {
        friendshipRepo.delete(
                userRepo.getOrThrow(senderStopFriendship),
                userRepo.getOrThrow(recipientStopFriendship)
        );
    }


    @Transactional
    public void acceptFriendship(int first, int second){
        friendshipRepo.save(createFriendship(first, second));
    }


    private Friendship createFriendship(int first, int second) {
        User firstUser = userRepo.getOrThrow(first);
        User secondUser = userRepo.getOrThrow(second);

        return new Friendship(firstUser, secondUser);
    }
}
