package com.project.leeyejin.service;

import com.project.leeyejin.entity.TranslationHistory;
import com.project.leeyejin.repository.TranslationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TranslationHistoryService {
    @Autowired
    private TranslationHistoryRepository repository;

    public TranslationHistory saveTranslation(TranslationHistory history) {
        return repository.save(history);
    }

    public Page<TranslationHistory> getTranslations(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteTranslations(int id) {
        repository.deleteById((long) id);
    }
}
