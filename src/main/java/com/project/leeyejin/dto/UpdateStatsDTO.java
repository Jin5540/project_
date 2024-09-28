package com.project.leeyejin.dto;

import lombok.Data;

@Data
public class UpdateStatsDTO {
    private Integer sentenceCount; // 수정할 문장 수
    private Integer wordCount;     // 수정할 단어 수
    private Integer characterCount; // 수정할 글자 수
    private boolean isOverwrite;   // overwrite 여부를 결정하는 플래그
}

