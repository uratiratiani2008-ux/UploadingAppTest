package com.rati.uploader.request;

public record UsernamePasswordMessageid (
    String username,
    String password,
    Integer messageId
){
    
}
