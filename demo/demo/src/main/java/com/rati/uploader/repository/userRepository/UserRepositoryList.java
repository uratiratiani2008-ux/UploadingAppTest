package com.rati.uploader.repository.userRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rati.uploader.model.User;

import jakarta.annotation.PostConstruct;

@Repository
@Profile("DEV")
public class UserRepositoryList implements UserRepositoryInterface{
    private final List<User> userList = new ArrayList<>();

    public void add(String username, String password){
        userList.add(new User(username,password));
    }
    
    public boolean login(String username, String password) {
        Optional<User> user = userList.stream()
            .filter(u -> u.username().equals(username) && u.password().equals(password))
            .findFirst();
        return user.isPresent();
    }

    @PostConstruct
    private void init() {
        userList.add(new User("rati","ratibati"));   
        userList.add(new User("masho","mashomasho"));
        userList.add(new User("gio","giopio"));
    }

}
