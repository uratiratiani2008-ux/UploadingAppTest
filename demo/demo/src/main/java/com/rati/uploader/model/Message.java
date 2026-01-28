package com.rati.uploader.model;

import java.time.LocalDateTime;

public record Message (
    Integer id,
    String username,
    String body,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated
){
    public String getUsername() {
        return this.username;
    }
}
