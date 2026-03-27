1. 🚀 과제 성공 커밋 로드맵 (Step-by-Step)

단계	커밋 타입 & 메시지	주요 작업 내용
1단계	chore: Initialize project package structure	7개 패키지 구조 생성 및 .gitkeep 추가
2단계	feat: Add BaseEntity and JPA Auditing	created_date, created_by 자동화 설정
3단계	feat: Create Contents entity and Repository	Contents 엔티티 및 JpaRepository 구현
4단계	feat: Implement Content CRUD with Pagination	콘텐츠 등록, 수정, 삭제 및 페이징 목록 조회
5단계	feat: Implement View Count increment logic	상세 조회 시 조회수 증가 비즈니스 로직
6단계	feat: Configure Spring Security and User roles	ADMIN, USER 권한 설정 및 로그인 뼈대
7단계	feat: Implement Ownership & Admin Authorization	본인 또는 관리자만 수정/삭제 가능 로직
8단계	feat: Add Global Exception Handling	@RestControllerAdvice로 깔끔한 에러 응답
9단계	docs: Finalize README.md and API Specs	실행 방법, 로그인 방식, AI 활용 내역 명시
