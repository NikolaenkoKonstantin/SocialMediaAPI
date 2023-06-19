package com.server.socialmediaapi.exceptions;

import com.server.socialmediaapi.utils.EntityType;


public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(EntityType entityType, int id) {
        super(entityType.getName() + " not found by ID: " + id);
    }
}
