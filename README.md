📝 CMS 콘텐츠 관리 REST API 과제 제출
본 프로젝트는 (주)맑은기술 백엔드 서버 개발자(Java) 채용 전형의 코딩 과제로 제작되었습니다.
Java 25와 Spring Boot 4 환경에서 보안, 확장성, 데이터 무결성을 고려하여 설계된 콘텐츠 관리 시스템(CMS)입니다.

🛠 개발 환경 (Spec)
Language: Java 25
Framework: Spring Boot 4.0.4
Security: Spring Security (Form Login & Session 기반 인증)
Database: H2 Database (In-memory)
ORM: Spring Data JPA
Lombok: Boilerplate 코드 제거 및 생산성 향상

📂 프로젝트 구조 (Project Structure)
유지보수와 가독성을 위해 **계층형 아키텍처(Layered Architecture)**를 채택하였습니다.

com.cms.content_manager
├── config          # Security 권한 설정 및 JPA Auditing 설정
├── controller      # REST API 엔드포인트 및 요청/응답 제어
├── service         # 비즈니스 로직 및 권한 검증 (본인 확인/관리자 체크)
├── repository      # 데이터 접근 계층 (Spring Data JPA 활용)
├── entity          # 데이터 모델 정의 (Contents, SiteUser, UserRole)
├── dto             # Data Transfer Object (엔티티 노출 방지 및 API 규격 유지)
└── exception       # GlobalExceptionHandler를 통한 공통 예외 처리

✨ 주요 구현 내용 및 기술적 의사결정
1. 보안 및 접근 권한 (Spring Security)
    Role 기반 접근 제어: 사용자를 USER와 ADMIN으로 구분하여 관리합니다.
    동적 권한 검증: - 콘텐츠 수정/삭제 시 서비스 계층에서 현재 사용자 정보와 관리자 권한 여부를 체크합니다.
    "작성자 본인" 혹은 "ADMIN(관리자)" 권한을 가진 사용자만 작업이 가능하도록 설계하였습니다.

2. 데이터 자동화 (JPA Auditing)
    AuditorAware 구현: SecurityContextHolder를 활용해 로그인된 사용자의 ID를 자동으로 추적합니다.
    콘텐츠 생성 시 created_by, created_date가, 수정 시 last_modified_by, last_modified_date가 자동으로 기록됩니다.

3. API 안정성 및 유지보수
    DTO 적용: 내부 DB 구조인 Entity를 직접 반환하지 않고 ContentResponse DTO를 사용하여 필요한 정보만 선별적으로 노출하였습니다.
    페이징 처리: 요구사항에 맞춰 Pageable을 적용, 목록 조회 시 Page<ContentResponse>로 효율적으로 처리하도록 구현했습니다.
    예외 처리: GlobalExceptionHandler를 통해 권한 부족(403), 데이터 부재(404) 상황에 대해 일관된 JSON 응답 형식을 제공합니다.

🔑 테스트 계정 정보
ADMIN (관리자): admin / 1234
USER (일반 사용자): user1 / 1234

📖 REST API Documentation
1. 인증 (Auth)
    로그인 (POST /login): x-www-form-urlencoded (username, password)
    로그아웃 (POST /logout): 세션 만료 및 쿠키 제거
2. 콘텐츠 관리 (Contents)
    목록 조회 (GET /api/contents): 전체 / 페이징(?page=0&size=10) 적용
    상세 조회 (GET /api/contents/{id}): 전체 / 조회수(viewCount) 1 증가
    콘텐츠 추가 (POST /api/contents): USER/ADMIN / 사용자 정보 자동 기록
    콘텐츠 수정 (PUT /api/contents/{id}): 본인/ADMIN / 제목 및 내용 수정
    콘텐츠 삭제 (DELETE /api/contents/{id}): 본인/ADMIN / DB 물리적 삭제 수행 

🚀 실행 방법
프로젝트 루트 경로에서 ./gradlew bootRun 실행
서버 접속: http://localhost:8080
H2 Console 접속: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User Name: sa (Password 없음)

🤖 사용 도구 및 참고 자료
AI 도구: Gemini 3 Flash (Spring Security 권한 로직 설계 보완 및 JPA Auditing 설정 지원)

참고 자료: Spring Boot Reference Documentation, Java 25 Specification