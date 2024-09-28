package com.project.leeyejin.repository;

import com.project.leeyejin.entity.TranslationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationHistoryRepository extends JpaRepository<TranslationHistory, Long> {
    void deleteAllById(int id);
}
