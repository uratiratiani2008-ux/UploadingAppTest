package com.rati.uploader.repository.messageRepository;

import java.util.List;
import java.util.Optional;


import com.rati.uploader.model.Message;

public interface RepositoryInterface {
    List<Message> getAllMessages();
    Optional<Message> getMessageById(Integer id);
    List<Message> getMessagesByUsername(String username);
    void add(Integer id, String username, String body);
    void delete(Integer id);
    void update(Integer id, String body);
    boolean isOwner(Integer id, String username);
}
