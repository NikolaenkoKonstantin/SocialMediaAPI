package com.server.socialmediaapi.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friendship")
public class Friendship {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Первый друг
     */
    @ManyToOne
    @JoinColumn(name = "first_friend", referencedColumnName = "id")
    private User firstFriend;

    /**
     * Второй друг
     */
    @ManyToOne
    @JoinColumn(name = "second_friend", referencedColumnName = "id")
    private User secondFriend;

    public Friendship(User firstFriend, User secondFriend) {
        this.firstFriend = firstFriend;
        this.secondFriend = secondFriend;
    }
}
