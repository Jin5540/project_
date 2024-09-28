package com.project.leeyejin.controller;

import com.project.leeyejin.entity.TranslationHistory;
import com.project.leeyejin.service.TranslationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;

import jakarta.validation.Valid; // 변경된 부분


@RestController
@RequestMapping("/history")
public class TranslationHistoryController {
    @Autowired
    private TranslationHistoryService service;

    @PostMapping
    public ResponseEntity<TranslationHistory> saveTranslation(@Valid @RequestBody TranslationHistory history) {
        TranslationHistory savedHistory = service.saveTranslation(history);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHistory);
    }

    // 번역 기록 조회
    @GetMapping
    public ResponseEntity<PagedModel<?>> getTranslations(
            @RequestParam(name = "page", required = false) Integer _page,
            @RequestParam(name = "size", required = false) Integer _size,
            PagedResourcesAssembler<TranslationHistory> assembler) {

        int page = (_page != null) ? _page : 0;
        int size = (_size != null) ? _size : 20;

        Page<TranslationHistory> translations = service.getTranslations(PageRequest.of(page, size));

        // Page<TranslationHistory>를 PagedModel로 변환하여 안정적인 JSON 구조 제공
        PagedModel<?> pagedModel = assembler.toModel(translations);

        return ResponseEntity.ok(pagedModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTranslations(@PathVariable(name = "id") int id) {
        service.deleteTranslations(id);
        return ResponseEntity.noContent().build();
    }
}
