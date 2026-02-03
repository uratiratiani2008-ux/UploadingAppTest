package com.rati.uploader.repository.messageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rati.uploader.model.Message;

import jakarta.annotation.PostConstruct;

@Repository
@Profile("DEV")
public class RepositoryList implements RepositoryInterface {
    
    private final List<Message> messageList = new ArrayList<>();

    @Override
    public List<Message> findAll() {
        return messageList;
    }
    
    @Override
    public Optional<Message> findById(Integer id) {
        return messageList.stream()
                .filter(message -> message.id().equals(id))
                .findFirst();
    }

    @Override
    public List<Message> findAllByUserId(Integer userId) {
        return messageList.stream()
                .filter(message -> message.userId().equals(userId))
                .toList();
    }

    @Override
    public void save(Message message) {
        messageList.removeIf(m -> m.id().equals(message.id()));
        messageList.add(message);
    }
    
    @Override
    public void deleteById(Integer id) {
        messageList.removeIf(message -> (message.id().equals(id)));
    }

    @Override
    public boolean existsByIdAndUserId(Integer id, Integer userId) {
        Optional<Message> message = findById(id);
        if (message.isEmpty()) {
            return false;
        }
        return message.get().userId().equals(userId);
    }

    
    @PostConstruct
    private void init() {
        messageList.add(new Message(1,1,"description of ratis first blog",LocalDateTime.now(),null));   
        messageList.add(new Message(2,1,"description of ratis second blog",LocalDateTime.now(),null));
        messageList.add(new Message(3,2,"description of mashos first blog",LocalDateTime.now(),null));
    }

}
