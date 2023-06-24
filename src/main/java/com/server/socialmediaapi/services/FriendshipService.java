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
        deleteFriendship(dto.getSenderStopFriendship(), dto.getRecipientStopFriendship());
    }


    private void deleteFriendship(int senderStopFriendship, int recipientStopFriendship) {
        unsubscribe(
                senderStopFriendship,
                recipientStopFriendship,
                friendshipRepo.delete(
                        userRepo.getOrThrow(senderStopFriendship),
                        userRepo.getOrThrow(recipientStopFriendship)
                )
        );
    }


    private void unsubscribe(int senderStopFriendship, int recipientStopFriendship, Boolean delete) {
        if(delete){
            subscriptionService.unsubscribe(senderStopFriendship, recipientStopFriendship);
        }
    }


    @Transactional
    public Friendship acceptFriendship(int first, int second){
        return friendshipRepo.save(createFriendship(first, second));
    }


    private Friendship createFriendship(int first, int second) {
        User firstUser = userRepo.getOrThrow(first);
        User secondUser = userRepo.getOrThrow(second);

        return new Friendship(firstUser, secondUser);
    }
}
