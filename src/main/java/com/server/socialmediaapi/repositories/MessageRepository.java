package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Получение истории сообщений между двумя пользователями с пагинацией и граничным сообщением
     *
     * @param sender - id user
     * @param consumer - id user
     * @param id - id message
     * @param pageable - pagination
     * @return - page of messages
     */
    Page<Message> findAllBySenderAndConsumerAndIdAfterOrderById(int sender, int consumer, int id, Pageable pageable);


    /**
     * Получение истории сообщений между двумя пользователями с пагинацией без граничного сообщения
     *
     * @param sender - id user
     * @param consumer - id user
     * @param pageable - pagination
     * @return - page of messages
     */
    Page<Message> findAllBySenderAndConsumerOrderById(int sender, int consumer, Pageable pageable);
}
