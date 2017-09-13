package br.gbraghim.eventmanager.api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.gbraghim.eventmanager")
@EntityScan("br.com.gbraghim.eventmanager.model")
public class EventApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }
}