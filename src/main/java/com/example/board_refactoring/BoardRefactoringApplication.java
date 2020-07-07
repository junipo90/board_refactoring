package com.example.board_refactoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(exclude={MultipartAutoConfiguration.class})
public class BoardRefactoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardRefactoringApplication.class, args);
    }

}
