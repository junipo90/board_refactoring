package com.example.board_refactoring.board.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "t_jpa_file")
@Data
public class BoardFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idx;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String storedFilePath;

    @Column(nullable = false)
    private long fileSize;

    @Column(nullable = false)
    private String creatorId;

    @Column(nullable = false)
    private LocalDateTime createdDatetime = LocalDateTime.now();

    private String updaterId;

    private LocalDateTime updatedDatetime;
}
