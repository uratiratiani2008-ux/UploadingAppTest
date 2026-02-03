package com.rati.uploader.model;

import org.springframework.data.annotation.Id;

public record User(
    @Id
    Integer id,
    String username,
    String password
) {
    
}
