package com.project.leeyejin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "translation_history")
public class TranslationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originText;
    private String sourceLanguage; // 번역된 문장
    private String targetLanguage; // 언어 정보
    private String translatedText; // 언어 정보
}
