package com.rati.uploader.repository.messageRepository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.rati.uploader.model.Message;

public interface MessageRepository extends ListCrudRepository<Message, Integer> {
    List<Message> findAllByUserId(Integer userId);
    boolean existsByIdAndUserId(Integer messageId, Integer userId);
}
