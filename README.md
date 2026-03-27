# Simple CMS API Project

본 프로젝트는 간단한 콘텐츠 관리(CMS) 기능을 제공하는 REST API 서버입니다.

## 🛠 Tech Stack
- **Language:** Java 25
- **Framework:** Spring Boot 4.x
- **Security:** Spring Security (Form Login / JWT 중 선택한 것 기재)
- **Database:** H2 Database (In-memory)
- **ORM:** Spring Data JPA

## 📂 Project Structure
프로젝트의 유지보수와 가독성을 위해 **계층형 아키텍처(Layered Architecture)**를 채택하였습니다.

Plaintext
com.cms.content
├── config          # Security, JPA Auditing 등 전역 설정
├── controller      # REST API 엔드포인트 (ContentsController)
├── service         # 비즈니스 로직 및 권한 검증
├── repository      # JPA 데이터 접근 계층
├── entity          # 데이터 모델 (Contents, BaseEntity)
├── dto             # 데이터 전송 객체 (Java 25 Record 사용)
└── exception       # 공통 예외 처리 (GlobalExceptionHandler)

## 🔑 Login & Auth
- **로그인 방식:** (예: Spring Security 기반의 Session 로그인을 사용합니다.)
- **사용자 계정:**
    - ADMIN: admin / 1234
    - USER: user1 / 1234

## 🚀 실행 방법
1. `./gradlew bootRun`
2. H2 Console 접속: `http://localhost:8080/h2-console`