CREATE TABLE Account
(
    Id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    Avatar          VARCHAR(255),
    FirstName       VARCHAR(255),
    LastName        VARCHAR(255),
    DateBirth       DATE,
    Email           VARCHAR(255) NOT NULL UNIQUE,
    Company         VARCHAR(255),
    AccountStatus   VARCHAR(255),
    AccountPassword VARCHAR(255),
    CreatedAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);