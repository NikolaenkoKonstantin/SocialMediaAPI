package com.server.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Владелец поста
     */
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;

    /**
     * Заголовок поста
     */
    @Column(name = "title")
    private String title;

    /**
     * Содержание поста
     */
    @Column(name = "content")
    private String content;

    /**
     * Дата создания поста
     */
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    /**
     * Данные о изображении
     * пока что временная заглушка
     */
    @Column(name = "image")
    private int image; //будет какая то информация по изображению
}
