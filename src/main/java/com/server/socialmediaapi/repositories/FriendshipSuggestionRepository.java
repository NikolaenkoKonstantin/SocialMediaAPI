package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.models.FriendshipSuggestion;
import com.server.socialmediaapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipSuggestionRepository extends JpaRepository<FriendshipSuggestion, Integer> {
    /**
     * Удаление заявки в друзья
     * @param sender - отправивший заявку в друзья
     * @param consumer - принимающий заявку в друзья
     */
    void deleteBySenderAndConsumer(User sender, User consumer);
}
