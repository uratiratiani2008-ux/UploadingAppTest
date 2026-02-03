package com.rati.uploader.request;

public record UsernamePasswordMessageidBody (
    String username,
    String password,
    Integer messageId,
    String body
){
    
}
