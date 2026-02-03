package com.rati.uploader.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public record Message (
    @Id
    Integer id,
    Integer userId,
    String body,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated
){
    public Integer getUserId() {
        return this.userId;
    }
}
