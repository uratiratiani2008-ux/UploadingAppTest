package com.rati.uploader.repository.messageRepository;

import java.util.List;
import java.util.Optional;


import com.rati.uploader.model.Message;

public interface RepositoryInterface {
    List<Message> findAll();
    Optional<Message> findById(Integer id);
    List<Message> findAllByUserId(Integer userId);
    void save(Message message);
    void deleteById(Integer id);
    boolean existsByIdAndUserId(Integer id, Integer userId);
}
