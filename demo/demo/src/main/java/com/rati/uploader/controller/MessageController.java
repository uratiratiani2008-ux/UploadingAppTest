package com.rati.uploader.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rati.uploader.model.Message;
import com.rati.uploader.repository.messageRepository.MessageRepository;
import com.rati.uploader.repository.userRepository.UserRepositoryInterface;
import com.rati.uploader.request.UsernamePasswordBody;
import com.rati.uploader.request.UsernamePasswordMessageid;
import com.rati.uploader.request.UsernamePasswordMessageidBody;

@RestController
@RequestMapping("/messages")
@CrossOrigin
public class MessageController {

    private final MessageRepository messageRepository;
    private final UserRepositoryInterface userRepository;

    public MessageController(MessageRepository messageRepository, UserRepositoryInterface userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<Message> getMessageHandler(@RequestParam(required = false) Integer id, @RequestParam(required = false) String username) {
        if (id != null) {
            return List.of(messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Message not found with id: " + id)));
        }

        if (username != null) {
            List<Message> messages = messageRepository.findAllByUserId(userRepository.getIdFromUsername(username));
            return messages;
        }

        return messageRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addMessage(@RequestBody UsernamePasswordBody data) {
        if (userRepository.login(data.username(), data.password())){
            Message message = new Message(
                null,
                userRepository.getIdFromUsername(data.username()),
                data.body(),
                LocalDateTime.now(),
                null
            );
            messageRepository.save(message);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("")
    public void updateMessage(@RequestBody UsernamePasswordMessageidBody data) {
        if (userRepository.login(data.username(), data.password()) &&  messageRepository.existsByIdAndUserId(data.messageId(), userRepository.getIdFromUsername(data.username()))){
            Message existingMessage = messageRepository.findById(data.messageId()).orElseThrow(() -> new RuntimeException("Message not found with id: " + data.messageId()));
            Message message = new Message(
                data.messageId(),
                userRepository.getIdFromUsername(data.username()),
                data.body(),
                existingMessage.dateCreated(),
                LocalDateTime.now()
            );
            messageRepository.save(message);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    public void deleteMessage(@RequestBody UsernamePasswordMessageid data) {
        if (userRepository.login(data.username(), data.password()) && messageRepository.existsByIdAndUserId(data.messageId(), userRepository.getIdFromUsername(data.username()))){
            messageRepository.deleteById(data.messageId());
        }
    }

}