-- Drop tables in reverse order of foreign key dependency
DROP TABLE IF EXISTS FavoriteList;
DROP TABLE IF EXISTS RecentList;
DROP TABLE IF EXISTS List;
DROP TABLE IF EXISTS WorkspaceMember;
DROP TABLE IF EXISTS Workspace;
DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS ListType;

-- Account table
CREATE TABLE Account
(
    Id              INT AUTO_INCREMENT PRIMARY KEY,
    Avatar          VARCHAR(255),
    FirstName       VARCHAR(255),
    LastName        VARCHAR(255),
    DateBirth       DATE,
    Email           VARCHAR(255) NOT NULL UNIQUE,
    Company         VARCHAR(255),
    AccountStatus   VARCHAR(50),
    AccountPassword VARCHAR(255),
    CreatedAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Workspace table
CREATE TABLE Workspace
(
    Id            INT AUTO_INCREMENT PRIMARY KEY,
    WorkspaceName VARCHAR(255),
    CreatedBy     INT                     NOT NULL,
    IsPersonal    BOOLEAN   DEFAULT FALSE NOT NULL,
    CreatedAt     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

-- List table
CREATE TABLE List
(
    Id          INT AUTO_INCREMENT PRIMARY KEY,
    ListName    VARCHAR(100) NOT NULL,
    Icon        VARCHAR(100),
    Color       VARCHAR(50),
    WorkspaceId INT          NOT NULL,
    CreatedBy   INT          NOT NULL,
    CreatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ListStatus  VARCHAR(50),
    FOREIGN KEY (WorkspaceId) REFERENCES Workspace (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

-- FavoriteList table
CREATE TABLE FavoriteList
(
    Id        INT AUTO_INCREMENT PRIMARY KEY,
    ListId    INT,
    AccountId INT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id)
);

-- WorkspaceMember table
CREATE TABLE WorkspaceMember
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    WorkspaceId  INT,
    AccountId    INT,
    JoinedAt     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    MemberStatus VARCHAR(50),
    UpdatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (WorkspaceId) REFERENCES Workspace (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id)
);

-- RecentList table
CREATE TABLE RecentList
(
    Id         INT AUTO_INCREMENT PRIMARY KEY,
    ListId     INT,
    AccountId  INT,
    AccessedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id)
);

CREATE TABLE ListType
(
    Id                  INT AUTO_INCREMENT PRIMARY KEY,
    Title               VARCHAR(255) NOT NULL, -- 'List', 'Form', 'Gallery', 'Calendar', 'Board'
    Icon                VARCHAR(255),
    ListTypeDescription VARCHAR(500),
    HeaderImage         VARCHAR(255)
);