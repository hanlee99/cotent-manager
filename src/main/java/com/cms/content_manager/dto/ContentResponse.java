package com.cms.content_manager.dto;

import com.cms.content_manager.entity.Contents;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ContentResponse {
    private Long id;
    private String title;
    private String description;
    private Long viewCount;
    private String createdBy;
    private LocalDateTime createdDate;

    // Entity -> DTO 변환 메서드
    public static ContentResponse from(Contents content) {
        return ContentResponse.builder()
                .id(content.getId())
                .title(content.getTitle())
                .description(content.getDescription())
                .viewCount(content.getViewCount())
                .createdBy(content.getCreatedBy())
                .createdDate(content.getCreatedDate())
                .build();
    }


}
