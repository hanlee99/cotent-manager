# CMS Content Manager

간단한 CMS(Content Management System) REST API 구현 과제입니다.
 
---

## 프로젝트 실행 방법

### 요구 사항
- Java 25
- Spring Boot 4

### 실행

```bash
./gradlew bootRun
```

서버 실행 후 기본 포트: `http://localhost:8080`

H2 콘솔: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (없음)

---

## 구현 내용

### 인증 / 로그인
- **Spring Security 세션 기반 로그인** 방식 선택
- Form Login 방식으로 구현 (`/login` 엔드포인트 자동 생성)
- 비밀번호는 BCrypt로 암호화 저장
- 회원 Role: `ADMIN`, `USER`

### 콘텐츠 CRUD
- 콘텐츠 생성
- 콘텐츠 목록 조회 (페이징 처리, 기본 10개)
- 콘텐츠 상세 조회 (조회 시 view_count 자동 증가)
- 콘텐츠 수정
- 콘텐츠 삭제

### 접근 권한
- 콘텐츠 수정 / 삭제는 **작성자 본인** 또는 **ADMIN**만 가능
- 그 외 사용자가 시도할 경우 403 응답 반환

### DB 설계
- JPA Auditing 활용
    - `created_by`, `last_modified_by` 자동 기록 (로그인한 사용자명)
    - `created_date`, `last_modified_date` 자동 기록
- H2 인메모리 DB 사용

### 예외 처리
- `GlobalExceptionHandler`로 전역 예외 처리
    - 존재하지 않는 콘텐츠 조회 시 404
    - 권한 없는 수정/삭제 시도 시 403

---

## AI 도구 및 참고 자료

- **Claude (Anthropic)**: 코드 구조 설계 및 디버깅에 참고하였습니다.
 