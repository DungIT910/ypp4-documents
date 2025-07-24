use master
IF DB_ID('MsList') IS NULL
BEGIN
    CREATE DATABASE MsList;
END
GO

USE MsList;
GO

-- Drop tables in reverse dependency order
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ShareLink') DROP TABLE ShareLink;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListMemberPermission') DROP TABLE ListMemberPermission;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Activity') DROP TABLE Activity;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Trash') DROP TABLE Trash;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ChangeLog') DROP TABLE ChangeLog;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Comment') DROP TABLE Comment;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'FileAttachment') DROP TABLE FileAttachment;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'FavList') DROP TABLE FavList;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Notifications') DROP TABLE Notifications;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListViewSetting') DROP TABLE ListViewSetting;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ViewTypeSettingKey') DROP TABLE ViewTypeSettingKey;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListColumnSettingValue') DROP TABLE ColumnSettingValue;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListColValue') DROP TABLE ListColValue;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListRow') DROP TABLE ListRow;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListColumnChoice') DROP TABLE ListColumnChoice;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListColumn') DROP TABLE DynamicColumn;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ListView') DROP TABLE ListView;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'List') DROP TABLE List;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Template') DROP TABLE Template;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Permission') DROP TABLE Permission;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'DataTypeSettingKey') DROP TABLE DataTypeSettingKey;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'KeySetting') DROP TABLE KeySetting;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'DataType') DROP TABLE DataType;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'ViewType') DROP TABLE ViewType;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'WorkspaceMember') DROP TABLE WorkspaceMember;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Workspace') DROP TABLE Workspace;
IF EXISTS (SELECT 1 FROM sys.tables WHERE name = 'Account') DROP TABLE Account;
GO

-- Create tables in dependency order
CREATE TABLE Account (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    FullName NVARCHAR(255),
    LastName NVARCHAR(255),
    DateBirth DATE,
    Email NVARCHAR(255),
    AccountPassword NVARCHAR(255)
);

CREATE TABLE Workspace (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    WorkspaceName NVARCHAR(255)
);

CREATE TABLE WorkspaceMember (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    WorkspaceId INT FOREIGN KEY REFERENCES Workspace(Id),
    AccountId INT FOREIGN KEY REFERENCES Account(Id),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

-- có 4 loại list, kanban, gallery và calendar
CREATE TABLE ViewType (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ViewName NVARCHAR(255) NOT NULL UNIQUE,
	DisplayName NVARCHAR(100)
);

-- các loại data cho cột (loại data mà ItemValue được phép trả về)
--> cần xác định key setting cho từng loại
CREATE TABLE DataType (
    Id INT IDENTITY(1,1) PRIMARY KEY,
	DisplayName NVARCHAR(100), 
    TypeCode NVARCHAR(100) -- text, number, date, yesno, choice
);

-- 3 quyền truy cập 
CREATE TABLE Permission (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    PermissionName NVARCHAR(100),
    PermissionCode NVARCHAR(50)
);

-- key setting cho column 
CREATE TABLE KeySetting (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    KeyName NVARCHAR(100), -- e.x: giá trị lớn nhất
    ValueType NVARCHAR(100) -- number, text, boolean,...
);

CREATE TABLE Template (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    TemplateName NVARCHAR(255) NOT NULL,
    TemplateDescription NVARCHAR(255),
    TemplateImage NVARCHAR(255),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
)

CREATE TABLE List (
    Id INT PRIMARY KEY IDENTITY,
    ListName NVARCHAR(255),
    CreatedBy NVARCHAR(100),
    CreatedAt DATETIME,
    ListTemplateId INT NOT NULL FOREIGN KEY REFERENCES Template(Id),         -- Tham chiếu đến List mẫu
    IsTemplate BIT NOT NULL DEFAULT 0  -- Đánh dấu đây có phải là template không
)

--- 1 list có thể tạo nhiều view
--- cần trigger auto tạo 1 view tên "Tất cả khoản mục"
CREATE TABLE ListView (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListId INT FOREIGN KEY REFERENCES List(Id),
    ViewName NVARCHAR(255),
    ViewTypeId INT FOREIGN KEY REFERENCES ViewType(Id),
    CreatedBy INT FOREIGN KEY REFERENCES Account(Id),
    CreatedAt DATETIME
);

CREATE TABLE DynamicColumn (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListId INT FOREIGN KEY REFERENCES List(Id),
    ColumnName NVARCHAR(255),
    ColDescription NVARCHAR(MAX),
    DisplayOrder INT NOT NULL ,
    DataTypeId INT FOREIGN KEY REFERENCES DataType(Id),
    IsVisible BIT,
    IsRequired BIT
);

-- lưu các giá trị cho cột có dạng là choice
CREATE TABLE ListColumnChoice (
    Id INT PRIMARY KEY IDENTITY,
    ColumnId INT FOREIGN KEY REFERENCES DynamicColumn(Id),
    DisplayName NVARCHAR(255),    -- Tên hiển thị 
    Color NVARCHAR(20) NOT NULL CONSTRAINT DF_ListColumnChoice_Color DEFAULT '#28A745', -- Màu mặc định nếu không chọn
    DisplayOrder INT NOT NULL DEFAULT 0          -- Thứ tự hiển thị trong dropdown
)

CREATE TABLE ViewTypeSettingKey (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ViewTypeId INT FOREIGN KEY REFERENCES ViewType(Id),
    KeySettingId INT FOREIGN KEY REFERENCES KeySetting(Id)
)

CREATE TABLE ListViewSetting (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListViewId INT FOREIGN KEY REFERENCES ListView(Id),
    ViewTypeSettingKeyId INT FOREIGN KEY REFERENCES ViewTypeSettingKey(Id),
    GroupByColumnId INT FOREIGN KEY REFERENCES DynamicColumn(Id),
    RawValue NVARCHAR(255)
);

CREATE TABLE ListRow (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListId INT FOREIGN KEY REFERENCES List(Id),
    ModifiedAt DATETIME,
    CreatedBy INT FOREIGN KEY REFERENCES Account(Id),
    DisplayOrder INT NOT NULL ,
	CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

-- lưu giá trị của row tại 1 col
CREATE TABLE ListColValue (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListRowId INT FOREIGN KEY REFERENCES ListRow(Id),
    ColumnId INT FOREIGN KEY REFERENCES DynamicColumn(Id),
    RowValue NVARCHAR(MAX),
	-- Có thể bỏ - dùng chung ItemValue
	IsMultiChoice BIT, 
	RowValueYesNo BIT
);

-- need check
--CREATE TABLE KeySettingEnumOption (
--    Id INT IDENTITY(1,1) PRIMARY KEY,
--    KeySettingId INT NOT NULL FOREIGN KEY REFERENCES KeySetting(Id),
--    OptionValue VARCHAR(255) NOT NULL,
--    OptionLabel VARCHAR(255) NOT NULL
--);

-- xác định loại col nào thì có setting gì
CREATE TABLE DataTypeSettingKey (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    DataTypeId INT FOREIGN KEY REFERENCES DataType(Id),
    KeySettingId INT FOREIGN KEY REFERENCES KeySetting(Id)
);

-- giá trị lưu cho setting của col 
CREATE TABLE ColumnSettingValue (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ColumnId INT FOREIGN KEY REFERENCES DynamicColumn(Id),
    KeySettingId INT FOREIGN KEY REFERENCES KeySetting(Id),
    KeyValue NVARCHAR(255),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE Notifications (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    AccountId INT FOREIGN KEY REFERENCES Account(Id),
    Title NVARCHAR(255),
    CreatedBy INT FOREIGN KEY REFERENCES Account(Id),
    IsRead BIT,
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE FavList (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListId INT FOREIGN KEY REFERENCES List(Id),
    FavListOfUser INT FOREIGN KEY REFERENCES Account(Id),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE FileAttachment (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListRowId INT FOREIGN KEY REFERENCES ListRow(Id),
    FileAttachmentName NVARCHAR(255),
    FileUrl NVARCHAR(500),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE Comment (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListItemId INT FOREIGN KEY REFERENCES ListRow(Id),
    Content NVARCHAR(MAX),
    CreatedBy INT FOREIGN KEY REFERENCES Account(Id),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE ChangeLog (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListRowId INT FOREIGN KEY REFERENCES ListRow(Id),
    EditedBy INT FOREIGN KEY REFERENCES Account(Id),
    ChangedField NVARCHAR(255),
    OldValue NVARCHAR(MAX),
    NewValue NVARCHAR(MAX),
	CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE Trash (
    Id INT IDENTITY(1,1) PRIMARY KEY,
	EntityType NVARCHAR(50), -- 'List', 'ListItem', 'FileAttachment'
    EntityId INT, -- ID of the deleted entity
    UserDeleteId INT FOREIGN KEY REFERENCES Account(Id),
	DeletedAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE Activity (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListId INT FOREIGN KEY REFERENCES List(Id),
    ListRowId INT FOREIGN KEY REFERENCES ListRow(Id),
    ListCommentId INT FOREIGN KEY REFERENCES Comment(Id),
    ActionType NVARCHAR(100),
    Note NVARCHAR(MAX),
    CreatedBy INT FOREIGN KEY REFERENCES Account(Id),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE ListMemberPermission (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListId INT FOREIGN KEY REFERENCES List(Id),
    AccountId INT FOREIGN KEY REFERENCES Account(Id),
    HighestPermissionCode NVARCHAR(50),
    HighestPermissionId INT FOREIGN KEY REFERENCES Permission(Id),
    GrantedByAccountId INT FOREIGN KEY REFERENCES Account(Id),
    Note NVARCHAR(MAX),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE ShareLink (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ListId INT FOREIGN KEY REFERENCES List(Id),
    TargetUrl NVARCHAR(500),
    IsPublic BIT,
    PermissionId INT FOREIGN KEY REFERENCES Permission(Id),
    ExpirationDate DATETIME,
    IsLoginRequired BIT,
    LinkPassword NVARCHAR(255),
    CreatedBy INT FOREIGN KEY REFERENCES Account(Id),
    CreateAt DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateAt DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE TABLE ShareLinkUserAccess (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    ShareLinkId INT NOT NULL FOREIGN KEY REFERENCES ShareLink(Id),
    Email NVARCHAR(255) NOT NULL,
    AccountId INT NULL FOREIGN KEY REFERENCES Account(Id)
);
GO