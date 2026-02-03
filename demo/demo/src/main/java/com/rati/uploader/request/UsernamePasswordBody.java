package com.rati.uploader.request;

public record UsernamePasswordBody (
    String username,
    String password,
    String body
){
    
}
