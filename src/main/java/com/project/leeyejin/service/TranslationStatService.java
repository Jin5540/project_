package com.project.leeyejin.service;

import com.project.leeyejin.exception.ResourceNotFoundException;
import com.project.leeyejin.entity.TranslationStat;
import com.project.leeyejin.repository.TranslationStatRepository;
import com.project.leeyejin.dto.UpdateStatsDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TranslationStatService {

    @Autowired
    private TranslationStatRepository repository;

    public TranslationStat createStat(TranslationStat stat) {
        return repository.save(stat);
    }

    public Page<TranslationStat> getFilteredStats(String ip, Integer minCharacterCount, Integer minWordCount, Integer minSentenceCount, Pageable pageable) {
        return repository.findByUserIpAndCharacterCountGreaterThanEqualAndWordCountGreaterThanEqualAndSentenceCountGreaterThanEqual(
                ip,
                minCharacterCount != null ? minCharacterCount : 0,
                minWordCount != null ? minWordCount : 0,
                minSentenceCount != null ? minSentenceCount : 0,
                pageable);
    }

    public Page<TranslationStat> getAllStats(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public TranslationStat updateStats(int id, UpdateStatsDTO updateStatsDTO) {
        TranslationStat stats = repository.findById((long) id)
                .orElseThrow(() -> new EntityNotFoundException("Stats not found"));

        // updateStatsDTO의 값을 설정합니다.
        if (updateStatsDTO.isOverwrite() || updateStatsDTO.getSentenceCount() != null) {
            stats.setSentenceCount(updateStatsDTO.getSentenceCount());
        }
        if (updateStatsDTO.isOverwrite() || updateStatsDTO.getWordCount() != null) {
            stats.setWordCount(updateStatsDTO.getWordCount());
        }
        if (updateStatsDTO.isOverwrite() || updateStatsDTO.getCharacterCount() != null) {
            stats.setCharacterCount(updateStatsDTO.getCharacterCount());
        }
        stats.setOverwrite(updateStatsDTO.isOverwrite());

        return repository.save(stats);
    }

    @Transactional
    public void deleteStats(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("잘못된 ID입니다. 유효한 ID를 입력하세요.");
        }
        try {
            repository.deleteById((long) id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("해당 ID에 대한 데이터가 존재하지 않습니다.");
        }
    }
}
