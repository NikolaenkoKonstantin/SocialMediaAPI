package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.models.Post;
import com.server.socialmediaapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    /**
     * Получение постов от подписок на других пользователей
     * @param subscriber - подписчик
     * @param pageable - пагинация
     * @return страницу с постами
     */
    @Query("select p from Post as p " +
            "join Subscription as s " +
            "on p.owner = s.publisher " +
            "where s.subscriber = :subscriber" )
    Page<Post> search(@Param("subscriber") User subscriber, Pageable pageable);
}
