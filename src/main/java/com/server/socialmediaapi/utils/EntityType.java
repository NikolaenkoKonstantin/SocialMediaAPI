package com.server.socialmediaapi.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EntityType {
    User("User"),
    Post("Post");

    private final String name;
}
