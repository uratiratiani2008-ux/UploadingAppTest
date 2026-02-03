package com.rati.uploader;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rati.uploader.model.Message;
import com.rati.uploader.repository.messageRepository.MessageRepository;

@SpringBootApplication
public class UploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploaderApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MessageRepository messageRepository) {
		return args -> {
			Message message = new Message(
					1,
					1,
					"Hello, this is a sample message from rati!",
					LocalDateTime.now(),
					null);
					
			messageRepository.save(message);
		};
	}
}