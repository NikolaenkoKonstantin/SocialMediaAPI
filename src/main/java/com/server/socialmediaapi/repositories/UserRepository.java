package com.server.socialmediaapi.repositories;

import com.server.socialmediaapi.exceptions.EntityNotFoundException;
import com.server.socialmediaapi.models.User;
import com.server.socialmediaapi.utils.EntityType;
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
        return findById(id).orElseThrow(() -> new EntityNotFoundException(EntityType.User, id));
    }
}
