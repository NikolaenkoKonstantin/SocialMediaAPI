package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.model.Message;
import com.server.socialmediaapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    /**
     * Поиск истории сообщение между двумя пользователями
     * @param firstUser - первый пользователь диалога
     * @param secondUser - второй пользователь диалога
     * @param pageable - пагинация
     * @return страница с историей сообщений
     */
    @Query("select m from Message m " +
            "where (m.sender = :firstUser and m.consumer = :secondUser) " +
            "or (m.sender = :secondUser and m.consumer = :firstUser) " +
            "order by m.dateOfCreation")
    Page<Message> search(@Param("firstUser") User firstUser,
                         @Param("secondUser") User secondUser,
                         Pageable pageable);
}
