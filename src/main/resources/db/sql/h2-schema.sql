CREATE TABLE IF NOT EXISTS site_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS contents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description CLOB,
    view_count BIGINT NOT NULL DEFAULT 0,
    created_date TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    last_modified_date TIMESTAMP,
    last_modified_by VARCHAR(50)
);