package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.exceptions.NotFoundException;
import com.server.socialmediaapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     *
     * @param id - id
     * @return user
     */
    default User getOrThrow(int id){
        return findById(id).orElseThrow(() -> new NotFoundException("User is not found"));
    }
}
