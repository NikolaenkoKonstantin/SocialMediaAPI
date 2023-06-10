package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.model.Friendship;
import com.server.socialmediaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    /**
     * Прекращение дружбы
     * @param firstUser - первый друг
     * @param secondUser - второй друг
     */
    @Modifying
    @Query("delete from Friendship f " +
            "where (f.firstFriend = :firstUser and f.secondFriend = :secondUser) " +
            "or (f.firstFriend = :secondUser and f.secondFriend = :firstUser)")
    void delete(@Param("firstUser") User firstUser, @Param("secondUser") User secondUser);
}
