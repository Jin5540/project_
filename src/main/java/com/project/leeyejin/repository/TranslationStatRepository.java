package com.project.leeyejin.repository;

import com.project.leeyejin.entity.TranslationStat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;

public interface TranslationStatRepository extends JpaRepository<TranslationStat, Long> {
    Page<TranslationStat> findByUserIpAndCharacterCountGreaterThanEqualAndWordCountGreaterThanEqualAndSentenceCountGreaterThanEqual(
            String userIp, Integer minCharacterCount, Integer minWordCount, Integer minSentenceCount, Pageable pageable);
}

