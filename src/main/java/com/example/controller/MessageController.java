package com.example.controller;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Message;
import com.example.pojo.User;
import com.example.repository.MessageRepository;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/messages")
    public Message sendMessage(@RequestBody Message message) {
        // Save the message
        Message savedMessage = messageRepository.save(message);

        // Trigger notification logic here (e.g., send a notification to the receiver)

        return savedMessage;
    }

    @GetMapping("/messages/{receiverId}")
    public String getNotification(@PathVariable Long receiverId) {
        // Fetch the receiver's name
        User receiver = userRepository.findById(receiverId).orElse(null);
        if (receiver == null) {
            return "User not found";
        }

        // Return the notification message
        return "You have a message from user " + receiver.getId();
    }
}