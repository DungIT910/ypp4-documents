-- Drop tables in reverse dependency order
DROP TABLE IF EXISTS ShareLinkSettingValue;
DROP TABLE IF EXISTS ShareLinkUserAccess;
DROP TABLE IF EXISTS ShareLink;
DROP TABLE IF EXISTS Scope;
DROP TABLE IF EXISTS ListMemberPermission;
DROP TABLE IF EXISTS TrashItem;
DROP TABLE IF EXISTS ObjectType;
DROP TABLE IF EXISTS ListRowComment;
DROP TABLE IF EXISTS FileAttachment;
DROP TABLE IF EXISTS RecentList;
DROP TABLE IF EXISTS FavoriteList;
DROP TABLE IF EXISTS DynamicColumnSettingValue;
DROP TABLE IF EXISTS ListCellValue;
DROP TABLE IF EXISTS ListRow;
DROP TABLE IF EXISTS ColumnChoice;
DROP TABLE IF EXISTS ListViewSettingValue;
DROP TABLE IF EXISTS ListDynamicColumn;
DROP TABLE IF EXISTS SystemColumnSettingValue;
DROP TABLE IF EXISTS SystemColumn;
DROP TABLE IF EXISTS ListView;
DROP TABLE IF EXISTS TemplateSampleCell;
DROP TABLE IF EXISTS TemplateSampleRow;
DROP TABLE IF EXISTS TemplateViewSettingValue;
DROP TABLE IF EXISTS TemplateColumnSettingValue;
DROP TABLE IF EXISTS TemplateColumn;
DROP TABLE IF EXISTS TemplateView;
DROP TABLE IF EXISTS List;
DROP TABLE IF EXISTS ListTemplate;
DROP TABLE IF EXISTS DataTypeSettingKey;
DROP TABLE IF EXISTS ViewTypeSettingKey;
DROP TABLE IF EXISTS KeySetting;
DROP TABLE IF EXISTS ViewSettingKey;
DROP TABLE IF EXISTS Permission;
DROP TABLE IF EXISTS SystemDataType;
DROP TABLE IF EXISTS ViewType;
DROP TABLE IF EXISTS ListType;
DROP TABLE IF EXISTS WorkspaceMember;
DROP TABLE IF EXISTS TemplateProvider;
DROP TABLE IF EXISTS Workspace;
DROP TABLE IF EXISTS Account;

-- Fixed CREATE TABLE statements for H2
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
    CreatedAt       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

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

CREATE TABLE WorkspaceMember
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    WorkspaceId  INT,
    AccountId    INT,
    JoinedAt     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    MemberStatus VARCHAR(50),
    UpdatedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (WorkspaceId) REFERENCES Workspace (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id)
);

CREATE TABLE Permission
(
    Id                    INT AUTO_INCREMENT PRIMARY KEY,
    PermissionName        VARCHAR(100),
    PermissionCode        VARCHAR(50) NOT NULL,
    PermissionDescription VARCHAR(255),
    Icon                  VARCHAR(255)
);

CREATE TABLE ViewType
(
    Id                  INT AUTO_INCREMENT PRIMARY KEY,
    Title               VARCHAR(100) NOT NULL,
    HeaderImage         VARCHAR(255),
    Icon                VARCHAR(100),
    ViewTypeDescription VARCHAR(500)
);

CREATE TABLE ListType
(
    Id                  INT AUTO_INCREMENT PRIMARY KEY,
    Title               VARCHAR(255) NOT NULL,
    Icon                VARCHAR(255),
    ListTypeDescription VARCHAR(500),
    HeaderImage         VARCHAR(255)
);

CREATE TABLE SystemDataType
(
    Id                  INT AUTO_INCREMENT PRIMARY KEY,
    Icon                VARCHAR(100),
    DataTypeDescription VARCHAR(500),
    CoverImg            VARCHAR(255),
    DisplayName         VARCHAR(100) NOT NULL,
    DataTypeValue       VARCHAR(50)  NOT NULL
);

CREATE TABLE KeySetting
(
    Id                 INT AUTO_INCREMENT PRIMARY KEY,
    Icon               VARCHAR(100),
    KeyName            VARCHAR(100) NOT NULL,
    ValueType          VARCHAR(50)  NOT NULL,
    IsDefaultValue     BOOLEAN DEFAULT FALSE,
    ValueOfDefault     VARCHAR(255),
    IsShareLinkSetting BOOLEAN DEFAULT FALSE
);

CREATE TABLE ViewSettingKey
(
    Id         INT AUTO_INCREMENT PRIMARY KEY,
    SettingKey VARCHAR(100) NOT NULL,
    ValueType  VARCHAR(50)  NOT NULL
);

CREATE TABLE ViewTypeSettingKey
(
    Id               INT AUTO_INCREMENT PRIMARY KEY,
    ViewTypeId       INT,
    ViewSettingKeyId INT,
    FOREIGN KEY (ViewTypeId) REFERENCES ViewType (Id),
    FOREIGN KEY (ViewSettingKeyId) REFERENCES ViewSettingKey (Id)
);

CREATE TABLE DataTypeSettingKey
(
    Id               INT AUTO_INCREMENT PRIMARY KEY,
    SystemDataTypeId INT,
    KeySettingId     INT,
    FOREIGN KEY (SystemDataTypeId) REFERENCES SystemDataType (Id),
    FOREIGN KEY (KeySettingId) REFERENCES KeySetting (Id)
);

CREATE TABLE TemplateProvider
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    ProviderName VARCHAR(255)
);

CREATE TABLE ListTemplate
(
    Id                  INT AUTO_INCREMENT PRIMARY KEY,
    Title               VARCHAR(255),
    HeaderImage         VARCHAR(500),
    TemplateDescription VARCHAR(500),
    Icon                VARCHAR(100),
    Color               VARCHAR(50),
    Sumary              VARCHAR(500),
    Feature             VARCHAR(500),
    ProviderId          INT NOT NULL,
    FOREIGN KEY (ProviderId) REFERENCES TemplateProvider (Id)
);

CREATE TABLE TemplateView
(
    Id             INT AUTO_INCREMENT PRIMARY KEY,
    ListTemplateId INT NOT NULL,
    ViewTypeId     INT NOT NULL,
    ViewName       VARCHAR(255),
    DisplayOrder   INT,
    FOREIGN KEY (ListTemplateId) REFERENCES ListTemplate (Id)
);

CREATE TABLE TemplateColumn
(
    Id                INT AUTO_INCREMENT PRIMARY KEY,
    SystemDataTypeId  INT NOT NULL,
    ListTemplateId    INT NOT NULL,
    ColumnName        VARCHAR(255),
    ColumnDescription VARCHAR(500),
    DisplayOrder      INT,
    IsVisible         BOOLEAN,
    FOREIGN KEY (SystemDataTypeId) REFERENCES SystemDataType (Id),
    FOREIGN KEY (ListTemplateId) REFERENCES ListTemplate (Id)
);

CREATE TABLE TemplateColumnSettingValue
(
    Id                   INT AUTO_INCREMENT PRIMARY KEY,
    TemplateColumnId     INT NOT NULL,
    DataTypeSettingKeyId INT NOT NULL,
    KeyValue             VARCHAR(255),
    FOREIGN KEY (TemplateColumnId) REFERENCES TemplateColumn (Id),
    FOREIGN KEY (DataTypeSettingKeyId) REFERENCES DataTypeSettingKey (Id)
);

CREATE TABLE TemplateViewSettingValue
(
    Id                INT AUTO_INCREMENT PRIMARY KEY,
    TemplateViewId    INT NOT NULL,
    ViewTypeSettingId INT NOT NULL,
    GroupByColumnId   INT,
    RawValue          VARCHAR(500),
    FOREIGN KEY (TemplateViewId) REFERENCES TemplateView (Id),
    FOREIGN KEY (ViewTypeSettingId) REFERENCES ViewTypeSettingKey (Id),
    FOREIGN KEY (GroupByColumnId) REFERENCES TemplateColumn (Id)
);

CREATE TABLE TemplateSampleRow
(
    Id             INT AUTO_INCREMENT PRIMARY KEY,
    ListTemplateId INT NOT NULL,
    DisplayOrder   INT,
    FOREIGN KEY (ListTemplateId) REFERENCES ListTemplate (Id)
);

CREATE TABLE TemplateSampleCell
(
    Id                  INT AUTO_INCREMENT PRIMARY KEY,
    TemplateColumnId    INT NOT NULL,
    TemplateSampleRowId INT NOT NULL,
    CellValue           VARCHAR(500),
    FOREIGN KEY (TemplateColumnId) REFERENCES TemplateColumn (Id),
    FOREIGN KEY (TemplateSampleRowId) REFERENCES TemplateSampleRow (Id)
);

CREATE TABLE List
(
    Id          INT AUTO_INCREMENT PRIMARY KEY,
    ListName    VARCHAR(100) NOT NULL,
    Icon        VARCHAR(100),
    Color       VARCHAR(50),
    WorkspaceId INT          NOT NULL,
    CreatedBy   INT          NOT NULL,
    CreatedAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ListStatus  VARCHAR(50),
    FOREIGN KEY (WorkspaceId) REFERENCES Workspace (Id)
);

CREATE TABLE SystemColumn
(
    Id               INT AUTO_INCREMENT PRIMARY KEY,
    SystemDataTypeId INT          NOT NULL,
    ColumnName       VARCHAR(100) NOT NULL,
    DisplayOrder     INT,
    CreatedBy        INT,
    CreatedAt        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CanRename        BOOLEAN               DEFAULT FALSE,
    FOREIGN KEY (SystemDataTypeId) REFERENCES SystemDataType (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

CREATE TABLE SystemColumnSettingValue
(
    Id                   INT AUTO_INCREMENT PRIMARY KEY,
    SystemColumnId       INT NOT NULL,
    DataTypeSettingKeyId INT NOT NULL,
    KeyValue             VARCHAR(255),
    FOREIGN KEY (SystemColumnId) REFERENCES SystemColumn (Id),
    FOREIGN KEY (DataTypeSettingKeyId) REFERENCES DataTypeSettingKey (Id)
);

CREATE TABLE ListView
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    ListId       INT       NOT NULL,
    CreatedBy    INT       NOT NULL,
    ViewTypeId   INT       NOT NULL,
    ViewName     VARCHAR(255),
    DisplayOrder INT       NOT NULL DEFAULT 0,
    CreatedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id),
    FOREIGN KEY (ViewTypeId) REFERENCES ViewType (Id)
);

CREATE TABLE ListDynamicColumn
(
    Id                INT AUTO_INCREMENT PRIMARY KEY,
    ListId            INT          NOT NULL,
    SystemDataTypeId  INT          NOT NULL,
    SystemColumnId    INT,
    ColumnName        VARCHAR(100) NOT NULL,
    ColumnDescription VARCHAR(255),
    DisplayOrder      INT          NOT NULL DEFAULT 0,
    IsSystemColumn    BOOLEAN      NOT NULL DEFAULT FALSE,
    IsVisible         BOOLEAN      NOT NULL DEFAULT TRUE,
    CreatedBy         INT          NOT NULL,
    CreatedAt         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (SystemDataTypeId) REFERENCES SystemDataType (Id),
    FOREIGN KEY (SystemColumnId) REFERENCES SystemColumn (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

CREATE TABLE ColumnChoice
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    ColumnId     INT,
    DisplayName  VARCHAR(255),
    DisplayColor VARCHAR(20),
    DisplayOrder INT         NOT NULL DEFAULT 0,
    Context      VARCHAR(50) NOT NULL,
    CreatedAt    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ListViewSettingValue
(
    Id                   INT AUTO_INCREMENT PRIMARY KEY,
    ListViewId           INT,
    ViewTypeSettingKeyId INT,
    GroupByColumnId      INT,
    RawValue             VARCHAR(255),
    CreatedAt            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListViewId) REFERENCES ListView (Id),
    FOREIGN KEY (ViewTypeSettingKeyId) REFERENCES ViewTypeSettingKey (Id),
    FOREIGN KEY (GroupByColumnId) REFERENCES ListDynamicColumn (Id)
);

CREATE TABLE ListRow
(
    Id            INT AUTO_INCREMENT PRIMARY KEY,
    ListId        INT       NOT NULL,
    DisplayOrder  INT       NOT NULL DEFAULT 0,
    CreatedBy     INT       NOT NULL,
    ListRowStatus VARCHAR(50),
    CreatedAt     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

CREATE TABLE ListCellValue
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    ListRowId    INT       NOT NULL,
    ListColumnId INT       NOT NULL,
    CellValue    VARCHAR(500),
    CreatedBy    INT       NOT NULL,
    CreatedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListRowId) REFERENCES ListRow (Id),
    FOREIGN KEY (ListColumnId) REFERENCES ListDynamicColumn (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

CREATE TABLE DynamicColumnSettingValue
(
    Id                   INT AUTO_INCREMENT PRIMARY KEY,
    DynamicColumnId      INT,
    DataTypeSettingKeyId INT,
    KeyValue             VARCHAR(255),
    CreatedAt            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (DynamicColumnId) REFERENCES ListDynamicColumn (Id),
    FOREIGN KEY (DataTypeSettingKeyId) REFERENCES DataTypeSettingKey (Id)
);

CREATE TABLE FavoriteList
(
    Id        INT AUTO_INCREMENT PRIMARY KEY,
    ListId    INT,
    AccountId INT,
    CreatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id)
);

CREATE TABLE RecentList
(
    Id         INT AUTO_INCREMENT PRIMARY KEY,
    ListId     INT,
    AccountId  INT,
    AccessedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id)
);

CREATE TABLE FileAttachment
(
    Id                 INT AUTO_INCREMENT PRIMARY KEY,
    ListRowId          INT,
    FileAttachmentName VARCHAR(255),
    FileUrl            VARCHAR(500),
    CreatedAt          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListRowId) REFERENCES ListRow (Id)
);

CREATE TABLE ListRowComment
(
    Id        INT AUTO_INCREMENT PRIMARY KEY,
    ListRowId INT,
    Content   VARCHAR(500) NOT NULL,
    CreatedBy INT,
    CreatedAt TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListRowId) REFERENCES ListRow (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

CREATE TABLE ObjectType
(
    Id          INT AUTO_INCREMENT PRIMARY KEY,
    Code        VARCHAR(50),
    DisplayName VARCHAR(255),
    Icon        VARCHAR(100)
);

CREATE TABLE TrashItem
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    ObjectTypeId INT,
    ObjectId     INT,
    UserDeleteId INT,
    DeletedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    OriginalPath VARCHAR(255),
    FOREIGN KEY (ObjectTypeId) REFERENCES ObjectType (Id),
    FOREIGN KEY (UserDeleteId) REFERENCES Account (Id)
);

CREATE TABLE ListMemberPermission
(
    Id                  INT AUTO_INCREMENT PRIMARY KEY,
    ListId              INT,
    AccountId           INT,
    HighestPermissionId INT,
    GrantedBy           INT,
    Note                VARCHAR(500),
    CreatedAt           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id),
    FOREIGN KEY (HighestPermissionId) REFERENCES Permission (Id),
    FOREIGN KEY (GrantedBy) REFERENCES Account (Id)
);

CREATE TABLE Scope
(
    Id               INT AUTO_INCREMENT PRIMARY KEY,
    Code             VARCHAR(50)  NOT NULL UNIQUE,
    DisplayName      VARCHAR(100) NOT NULL,
    ScopeDescription VARCHAR(255),
    Icon             VARCHAR(100)
);

CREATE TABLE ShareLink
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    ListId       INT,
    TargetUrl    VARCHAR(500),
    ScopeId      INT,
    PermissionId INT,
    LinkStatus   VARCHAR(50),
    CreatedBy    INT,
    CreatedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ListId) REFERENCES List (Id),
    FOREIGN KEY (ScopeId) REFERENCES Scope (Id),
    FOREIGN KEY (PermissionId) REFERENCES Permission (Id),
    FOREIGN KEY (CreatedBy) REFERENCES Account (Id)
);

CREATE TABLE ShareLinkUserAccess
(
    Id          INT AUTO_INCREMENT PRIMARY KEY,
    ShareLinkId INT          NOT NULL,
    AccountId   INT          NULL,
    Email       VARCHAR(255) NOT NULL,
    CreatedAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ShareLinkId) REFERENCES ShareLink (Id),
    FOREIGN KEY (AccountId) REFERENCES Account (Id)
);

CREATE TABLE ShareLinkSettingValue
(
    Id           INT AUTO_INCREMENT PRIMARY KEY,
    ShareLinkId  INT          NOT NULL,
    KeySettingId INT          NOT NULL,
    KeyValue     VARCHAR(255) NOT NULL,
    FOREIGN KEY (ShareLinkId) REFERENCES ShareLink (Id),
    FOREIGN KEY (KeySettingId) REFERENCES KeySetting (Id)
);