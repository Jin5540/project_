package com.project.leeyejin.controller;

import com.project.leeyejin.entity.TranslationStat;
import com.project.leeyejin.service.TranslationStatService;
import com.project.leeyejin.dto.UpdateStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // 변경된 부분


@RestController
@RequestMapping("/stats") // API의 기본 경로 설정
public class TranslationStatController {
    @Autowired
    private TranslationStatService service;

    @PostMapping
    public ResponseEntity<TranslationStat> createStat(@Valid @RequestBody TranslationStat stat) {
        TranslationStat createdStat = service.createStat(stat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStat); // 201 Created 응답
    }

    @GetMapping
    public Page<TranslationStat> getFilteredStats(
            @RequestParam(name = "ip", required = false) String ip,
            @RequestParam(name = "minCharacterCount", required = false) Integer minCharacterCount,
            @RequestParam(name = "minWordCount", required = false) Integer minWordCount,
            @RequestParam(name = "minSentenceCount", required = false) Integer minSentenceCount,
            @RequestParam(name= "page",defaultValue = "0") Integer page, // 기본값 0
            @RequestParam(name= "size",defaultValue = "20") Integer size) { // 기본값 20

        Pageable pageable = PageRequest.of(page, size); // Pageable 객체 생성
        return service.getFilteredStats(ip, minCharacterCount, minWordCount, minSentenceCount, pageable);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TranslationStat> updateStat(@PathVariable(name = "id") int id,
                                                      @RequestBody UpdateStatsDTO updateStatsDTO) {
        TranslationStat updatedStat = service.updateStats(id, updateStatsDTO);
        System.out.println(id+":::"+updateStatsDTO);
        if (updatedStat != null) {
            return ResponseEntity.ok(updatedStat); // 수정된 통계 반환
        } else {
            return ResponseEntity.notFound().build(); // 수정할 통계가 없을 경우 404 Not Found 반환
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStats(@PathVariable(name = "id") int id) {
        service.deleteStats(id);
        return ResponseEntity.noContent().build();
    }
}
