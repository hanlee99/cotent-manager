package com.malgn.controller;

import com.malgn.dto.ContentCreateRequest;
import com.malgn.dto.ContentResponse;
import com.malgn.entity.Contents;
import com.malgn.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contents")
@RequiredArgsConstructor
public class ContentsController {
    private final ContentsService contentsService;

    // 1. 글 쓰기 (생성)
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ContentCreateRequest request) {
        Long id = contentsService.create(request);
        return ResponseEntity.ok(id);
    }

    // 2. 전체 목록 조회
    @GetMapping
    public ResponseEntity<Page<ContentResponse>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<ContentResponse> responses = contentsService.findAll(pageable)
                .map(ContentResponse::from);
        return ResponseEntity.ok(responses);
    }

    // 3. 상세보기 (단건 조회)
    @GetMapping("/{id}")
    public ResponseEntity<Contents> findById(@PathVariable Long id) {
        Contents content = contentsService.findById(id);
        return ResponseEntity.ok(content);
    }

    // 4. 글 수정 (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody ContentCreateRequest request) {
        return ResponseEntity.ok(contentsService.update(id, request));
    }

    // 5. 글 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contentsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
