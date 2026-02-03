package com.rati.uploader.repository.userRepository;


public interface UserRepositoryInterface {
    void add(String username, String password);
    boolean login(String username, String password);
    Integer getIdFromUsername(String username);
}
