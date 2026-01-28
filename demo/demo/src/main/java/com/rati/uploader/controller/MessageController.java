package com.rati.uploader.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rati.uploader.model.Message;
import com.rati.uploader.repository.messageRepository.RepositoryInterface;
import com.rati.uploader.repository.userRepository.UserRepositoryInterface;

@RestController
@RequestMapping("/content")
@CrossOrigin
public class MessageController {
    private final RepositoryInterface messageRepository;
    private final UserRepositoryInterface userRepository;

    public MessageController(RepositoryInterface messageRepository, UserRepositoryInterface userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<Message> getAllMessages() {
        return messageRepository.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Integer id) {
        return messageRepository.getMessageById(id).orElseThrow(() -> new RuntimeException("Message not found"));
    }

    @GetMapping("/username/{username}")
    public List<Message> getMessagesByUsername(@PathVariable String username) {
        return messageRepository.getMessagesByUsername(username);
    }

    @PostMapping("/add/{id}/{username}/{password}")
    public void addMessage(@PathVariable Integer id, @PathVariable String username,@PathVariable String password, @RequestBody String body) {
        if (userRepository.login(username, password)){
            messageRepository.add(id, username, body);
        }
    }

    @PutMapping("/update/{id}/{username}/{password}")
    public void updateMessage(@PathVariable Integer id, @PathVariable String username,@PathVariable String password,@RequestBody String body) {
        if (userRepository.login(username, password) &&  messageRepository.isOwner(id, username)){
            messageRepository.update(id, body);
        }
    }

    @DeleteMapping("/delete/{id}/{username}/{password}")
    public void deleteMessage(@PathVariable Integer id, @PathVariable String username, @PathVariable String password){
        if (userRepository.login(username, password) && messageRepository.isOwner(id, username)){
            messageRepository.delete(id);
        }
    }

}