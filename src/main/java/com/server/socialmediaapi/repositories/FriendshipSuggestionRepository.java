package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.model.FriendshipSuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipSuggestionRepository extends JpaRepository<FriendshipSuggestion, Integer> {
}
