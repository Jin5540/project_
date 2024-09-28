package com.project.leeyejin.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "translation_stats")
@Data // Lombok을 사용하여 getter, setter, toString 등 자동 생성
public class TranslationStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int sentenceCount; // 문장 수
    private int wordCount;     // 단어 수
    private int characterCount; // 글자 수
    private String userIp;
    private LocalDateTime createdAt;
    private boolean isOverwrite;
    // 사용자 IP
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 엔티티가 처음 저장될 때 현재 시간을 설정
        this.isOverwrite = true; // 엔티티가 처음 저장될 때 현재 시간을 설정
    }
}