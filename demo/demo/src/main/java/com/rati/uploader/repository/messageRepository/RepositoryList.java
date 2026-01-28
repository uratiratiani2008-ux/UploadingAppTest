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

    public List<Message> getAllMessages() {
        return messageList;
    }
    
    public Optional<Message> getMessageById(Integer id) {
        return messageList.stream()
                .filter(message -> message.id().equals(id))
                .findFirst();
    }

    public List<Message> getMessagesByUsername(String username) {
        return messageList.stream()
                .filter(message -> message.username().equals(username))
                .toList();
    }

    public void add(Integer id, String username, String body) {
        Message newMessage = new Message(id, username, body, java.time.LocalDateTime.now(), null);
        messageList.add(newMessage);
    }
    
    public void delete(Integer id) {
        messageList.removeIf(message -> (message.id().equals(id)));
    }

    public void update(Integer id, String body) {
        Optional<Message> messageToUpdate = getMessageById(id);
        messageToUpdate.ifPresent(message -> {
            messageList.remove(message);
            Message updatedMessage = new Message(
                message.id(),
                message.username(),
                body,
                message.dateCreated(),
                java.time.LocalDateTime.now()
            );
            messageList.add(updatedMessage);
        });
    }

    public boolean isOwner(Integer id, String username) {
        Optional<Message> message = getMessageById(id);
        if (message.isEmpty()) {
            return false;
        }
        return message.get().getUsername().equals(username);
    }

    @PostConstruct
    private void init() {
        messageList.add(new Message(1,"rati","description of ratis first blog",LocalDateTime.now(),null));   
        messageList.add(new Message(2,"rati","description of ratis second blog",LocalDateTime.now(),null));
        messageList.add(new Message(3,"masho","description of mashos first blog",LocalDateTime.now(),null));
    }

}
