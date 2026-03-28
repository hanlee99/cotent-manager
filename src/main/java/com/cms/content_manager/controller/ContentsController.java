package com.cms.content_manager.controller;

import com.cms.content_manager.dto.ContentCreateRequest;
import com.cms.content_manager.entity.Contents;
import com.cms.content_manager.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Page<Contents>> findAll(
            @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {

        // 서비스에서 findAll(pageable)을 호출하도록 변경!
        Page<Contents> list = contentsService.findAll(pageable);
        return ResponseEntity.ok(list);
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
