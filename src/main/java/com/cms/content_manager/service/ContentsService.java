package com.cms.content_manager.service;

import com.cms.content_manager.dto.ContentCreateRequest;
import com.cms.content_manager.entity.Contents;
import com.cms.content_manager.repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ContentsService {
    private final ContentsRepository contentsRepository; // 레포지토리는 필수입니다!

    // 1. 글 쓰기 (생성)
    @Transactional
    public Long create(ContentCreateRequest request) {
        // 엔티티의 필드명인 'description'에 맞춰서 빌더 호출
        Contents content = Contents.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        // @CreatedBy 설정 덕분에 save만 하면 작성자 이름이 자동으로 박힙니다.
        return contentsRepository.save(content).getId();
    }

    // 2. 글 목록 보기 (전체 조회)
    public Page<Contents> findAll(Pageable pageable) {
        return contentsRepository.findAll(pageable);
    }

    // 3. 글 상세보기 (단건 조회 + 조회수 증가)
    @Transactional
    public Contents findById(Long id) {
        Contents content = contentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 엔티티에 직접 만드신 메서드를 호출해서 조회수를 올립니다.
        content.incrementViewCount();

        return content;
    }
    // 4. 글 수정 (본인 확인 OR 관리자 권한 확인)
    @Transactional
    public Long update(Long id, ContentCreateRequest request) {
        Contents content = contentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 1. 현재 사용자 정보와 권한(Role) 가져오기
        org.springframework.security.core.Authentication auth =
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

        String currentUsername = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // 2. [핵심 로직] 본인이 아니면서 + 관리자도 아니라면 거부!
        if (!content.getCreatedBy().equals(currentUsername) && !isAdmin) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        content.update(request.getTitle(), request.getDescription());
        return id;
    }

    // 5. 글 삭제 (본인 확인 OR 관리자 권한 확인)
    @Transactional
    public void delete(Long id) {
        Contents content = contentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 1. 현재 사용자 정보와 권한 가져오기
        org.springframework.security.core.Authentication auth =
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

        String currentUsername = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        // 2. [핵심 로직] 본인이 아니면서 + 관리자도 아니라면 거부!
        if (!content.getCreatedBy().equals(currentUsername) && !isAdmin) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        contentsRepository.delete(content);
    }
}
