-- Xóa dữ liệu cũ để tránh xung đột
DELETE
FROM ShareLinkUserAccess;
DELETE
FROM ShareLink;
DELETE
FROM Scope;
DELETE
FROM ListMemberPermission;
DELETE
FROM FileAttachment;
DELETE
FROM ListRowComment;
DELETE
FROM RecentList;
DELETE
FROM FavoriteList;
DELETE
FROM ListCellValue;
DELETE
FROM ListRow;
DELETE
FROM ColumnChoice;
DELETE
FROM ListView;
DELETE
FROM ListDynamicColumn;
DELETE
FROM SystemColumn;
DELETE
FROM List;
DELETE
FROM WorkspaceMember;
DELETE
FROM Workspace;
DELETE
FROM Account;
DELETE
FROM Permission;
DELETE
FROM ViewType;
DELETE
FROM SystemDataType;
DELETE
FROM KeySetting;
DELETE
FROM ViewSettingKey;
DELETE
FROM ViewTypeSettingKey;
DELETE
FROM DataTypeSettingKey;

-- Insert vào Account với đầy đủ các trường theo schema
INSERT INTO Account (Id, FirstName, LastName, Email, AccountPassword, Avatar, Company, AccountStatus, DateBirth,
                     CreatedAt, UpdatedAt)
VALUES (1, 'John', 'Doe', 'user100@example.com', 'pass100', 'https://avatar.example.com/user100.png', 'TechCorp',
        'ACTIVE', '1990-01-01', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'Jane', 'Smith', 'user200@example.com', 'pass200', 'https://avatar.example.com/user200.png', 'InnovateInc',
        'ACTIVE', '1995-02-02', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 'Bob', 'Johnson', 'user300@example.com', 'pass300', 'https://avatar.example.com/user300.png', 'StartupXYZ',
        'ACTIVE', '1992-03-03', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 'Alice', 'Brown', 'user400@example.com', 'pass400', 'https://avatar.example.com/user400.png', 'BigCompany',
        'ACTIVE', '1988-04-04', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào Workspace
INSERT INTO Workspace (Id, WorkspaceName, CreatedAt, UpdatedAt, CreatedBy, IsPersonal)
VALUES (1, 'Workspace A', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, FALSE),
       (2, 'Workspace B', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, FALSE),
       (3, 'Workspace C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, TRUE),
       (4, 'Workspace D', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, TRUE);

-- Insert vào WorkspaceMember
INSERT INTO WorkspaceMember (Id, WorkspaceId, AccountId, JoinedAt, MemberStatus, UpdatedAt)
VALUES (1, 1, 1, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP),
       (2, 2, 2, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP),
       (3, 1, 2, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP),
       (4, 3, 3, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP),
       (5, 4, 4, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP),
       (6, 1, 4, CURRENT_TIMESTAMP, 'ACTIVE', CURRENT_TIMESTAMP);

-- Insert vào Permission
INSERT INTO Permission (Id, PermissionName, PermissionCode, PermissionDescription, Icon)
VALUES (1, 'Administrator', 'Admin', 'Full access to manage and edit all resources', 'admin-icon.png'),
       (2, 'Contributor', 'Contributor', 'Can edit and contribute to resources', 'contributor-icon.png'),
       (3, 'Reader', 'Reader', 'Read-only access to view resources', 'reader-icon.png');

-- Insert vào ViewType
INSERT INTO ViewType (Id, Title, HeaderImage, Icon, ViewTypeDescription)
VALUES (1, 'List', 'https://example.com/images/list.png', 'list-icon.png', 'Displays data in a tabular list format'),
       (2, 'Gallery', 'https://example.com/images/gallery.png', 'gallery-icon.png', 'Shows data as a visual gallery'),
       (3, 'Calendar', 'https://example.com/images/calendar.png', 'calendar-icon.png',
        'Organizes data in a calendar view'),
       (4, 'Board', 'https://example.com/images/board.png', 'board-icon.png', 'Presents data in a kanban board style');

-- Insert vào SystemDataType
INSERT INTO SystemDataType (Id, DisplayName, DataTypeValue, Icon, DataTypeDescription, CoverImg)
VALUES (1, 'Single line of text', 'Text', 'text-icon.png', 'A single line of text for short entries',
        'https://example.com/images/text-cover.png'),
       (2, 'Number', 'Number', 'number-icon.png', 'Numeric values for calculations or counts',
        'https://example.com/images/number-cover.png'),
       (3, 'Date and time', 'DateTime', 'datetime-icon.png', 'Date and time values',
        'https://example.com/images/datetime-cover.png'),
       (4, 'Choice', 'Choice', 'choice-icon.png', 'Predefined options for selection',
        'https://example.com/images/choice-cover.png'),
       (5, 'Yes/No', 'Boolean', 'yesno-icon.png', 'Boolean values for yes or no choices',
        'https://example.com/images/yesno-cover.png'),
       (6, 'Person or Group', 'Person', 'person-icon.png', 'Select a person or group',
        'https://example.com/images/person-cover.png'),
       (7, 'Hyperlink', 'Text', 'hyperlink-icon.png', 'URL or web address',
        'https://example.com/images/hyperlink-cover.png'),
       (8, 'Picture', 'Text', 'picture-icon.png', 'Image or picture URL',
        'https://example.com/images/picture-cover.png');

-- Insert vào KeySetting
INSERT INTO KeySetting (Id, KeyName, ValueType, IsShareLinkSetting, IsDefaultValue, ValueOfDefault)
VALUES (1, 'Max length', 'number', FALSE, TRUE, '255'),
       (2, 'Is required', 'boolean', FALSE, TRUE, 'FALSE'),
       (3, 'Choice setting', 'choice', FALSE, FALSE, NULL),
       (4, 'Allow multiple choice', 'boolean', FALSE, TRUE, 'FALSE'),
       (5, 'Include time', 'boolean', FALSE, TRUE, 'FALSE'),
       (6, 'Default value', 'text', FALSE, FALSE, NULL);

-- Insert vào ViewSettingKey
INSERT INTO ViewSettingKey (Id, SettingKey, ValueType)
VALUES (1, 'Set this as public view', 'BOOLEAN'),
       (2, 'Start date on calendar', 'COLUMN'),
       (3, 'End date on calendar', 'COLUMN'),
       (4, 'Sort table by', 'COLUMN');

-- Insert vào ViewTypeSettingKey
INSERT INTO ViewTypeSettingKey (ViewTypeId, ViewSettingKeyId)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 1),
       (2, 4);

-- Insert vào DataTypeSettingKey
INSERT INTO DataTypeSettingKey (SystemDataTypeId, KeySettingId)
VALUES (1, 1),
       (1, 2),
       (1, 6),
       (4, 3),
       (4, 4),
       (3, 5),
       (2, 2),
       (5, 2);

INSERT INTO ListType (Id, Title, Icon, ListTypeDescription, HeaderImage)
VALUES (1, 'List', 'list-icon.png', 'A tabular list for structured data display',
        'https://example.com/images/list-header.png'),
       (2, 'Form', 'form-icon.png', 'A form-based interface for data entry',
        'https://example.com/images/form-header.png'),
       (3, 'Gallery', 'gallery-icon.png', 'A visual gallery for image or card-based data',
        'https://example.com/images/gallery-header.png'),
       (4, 'Calendar', 'calendar-icon.png', 'A calendar view for date-based data',
        'https://example.com/images/calendar-header.png'),
       (5, 'Board', 'board-icon.png', 'A kanban board for task and workflow management',
        'https://example.com/images/board-header.png');

INSERT INTO TemplateProvider (Id, ProviderName)
VALUES (1, 'Microsoft'),
       (2, 'Google');

INSERT INTO ListTemplate (Id, Title, HeaderImage, TemplateDescription, Icon, Color, Sumary, Feature, ProviderId)
VALUES (1, 'Task Management', 'https://example.com/img/task.png', 'Manage tasks easily', 'task-icon.png', 'blue',
        'Quick summary for tasks', 'Drag-and-drop, Due dates', 1),
       (2, 'Project Planner', 'https://example.com/img/planner.png', 'Plan your projects', 'planner-icon.png', 'green',
        'Plan overview', 'Timelines, Dependencies', 1),
       (3, 'Event Scheduler', 'https://example.com/img/event.png', 'Schedule events efficiently', 'calendar-icon.png',
        'red', 'Event summary', 'Reminders, Recurrence', 2),
       (4, 'Content Calendar', 'https://example.com/img/content.png', 'Manage content publishing', 'content-icon.png',
        'purple', 'Content overview', 'Tags, Publish dates', 2);

INSERT INTO TemplateColumn (SystemDataTypeId, ListTemplateId, ColumnName, ColumnDescription, DisplayOrder, IsVisible)
VALUES
    (1, 1, 'Task Name', 'Name of the task', 1, TRUE),
    (3, 1, 'Due Date', 'Due date for the task', 2, TRUE),
    (4, 1, 'Status', 'Status of the task', 3, TRUE);

INSERT INTO TemplateColumnSettingValue (TemplateColumnId, DataTypeSettingKeyId, KeyValue)
VALUES
    -- Cột 1: Task Name (SystemDataTypeId = 1)
    (1, 1, '100'), -- Max length
    (1, 2, 'TRUE'), -- Is required
    (1, 6, NULL), -- Default value
    -- Cột 2: Due Date (SystemDataTypeId = 3)
    (2, 5, 'TRUE'), -- Include time
    (2, 2, 'TRUE'), -- Is required
    -- Cột 3: Status (SystemDataTypeId = 4)
    (3, 3, 'To Do,In Progress,Done'), -- Choice setting
    (3, 4, 'FALSE'), -- Allow multiple choice
    (3, 2, 'TRUE'); -- Is required

INSERT INTO TemplateSampleRow (ListTemplateId, DisplayOrder)
VALUES
    (1, 1),
    (1, 2),
    (1, 3);

INSERT INTO TemplateSampleCell (TemplateColumnId, TemplateSampleRowId, CellValue)
VALUES
    -- Dòng 1
    (1, 1, 'Write report'), -- Task Name
    (2, 1, '2025-08-20 17:00:00'), -- Due Date
    (3, 1, 'To Do'), -- Status
    -- Dòng 2
    (1, 2, 'Review code'), -- Task Name
    (2, 2, '2025-08-21 14:00:00'), -- Due Date
    (3, 2, 'In Progress'), -- Status
    -- Dòng 3
    (1, 3, 'Deploy application'), -- Task Name
    (2, 3, '2025-08-22 09:00:00'), -- Due Date
    (3, 3, 'Done'); -- Status

-- Insert vào List
INSERT INTO List (Id, ListName, Icon, Color, WorkspaceId, CreatedBy, CreatedAt, UpdatedAt, ListStatus)
VALUES (1, 'Project Tasks', 'task-icon.png', 'blue', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE'),
       (2, 'Marketing Campaigns', 'campaign-icon.png', 'green', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE'),
       (3, 'Asset Inventory', 'asset-icon.png', 'orange', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ACTIVE');

-- Insert vào SystemColumn
INSERT INTO SystemColumn (Id, SystemDataTypeId, ColumnName, DisplayOrder, CreatedBy, CreatedAt, CanRename)
VALUES (1, 1, 'Title', 1, NULL, CURRENT_TIMESTAMP, TRUE),
       (2, 2, 'ID', 2, NULL, CURRENT_TIMESTAMP, FALSE),
       (3, 3, 'CreatedDate', 3, NULL, CURRENT_TIMESTAMP, FALSE),
       (4, 3, 'ModifiedDate', 4, NULL, CURRENT_TIMESTAMP, FALSE),
       (5, 6, 'CreatedBy', 5, NULL, CURRENT_TIMESTAMP, FALSE),
       (6, 5, 'IsRecord', 6, NULL, CURRENT_TIMESTAMP, FALSE);

-- Insert vào ListView
INSERT INTO ListView (Id, ListId, CreatedBy, ViewTypeId, ViewName, DisplayOrder, CreatedAt, UpdatedAt)
VALUES (1, 1, 1, 1, 'Task List View', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 1, 1, 4, 'Task Board View', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 2, 2, 1, 'Campaign List View', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 3, 1, 1, 'Asset List View', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào ListDynamicColumn
INSERT INTO ListDynamicColumn (Id, ListId, SystemDataTypeId, SystemColumnId, ColumnName, ColumnDescription,
                               DisplayOrder, IsSystemColumn, IsVisible, CreatedBy, CreatedAt, UpdatedAt)
VALUES
    -- List 1: Project Tasks
    (1, 1, 1, 1, 'Title', 'System column: Title', 1, TRUE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 1, 2, 2, 'ID', 'System column: ID', 6, TRUE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 1, 3, 3, 'CreatedDate', 'System column: CreatedDate', 7, TRUE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 1, 6, 5, 'CreatedBy', 'System column: CreatedBy', 8, TRUE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 1, 4, NULL, 'Status', 'Task status', 2, FALSE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 1, 6, NULL, 'AssignedTo', 'Task assignee', 3, FALSE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 1, 3, NULL, 'DueDate', 'Task due date', 4, FALSE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- List 2: Marketing Campaigns
    (8, 2, 1, 1, 'Title', 'System column: Title', 1, TRUE, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 2, 2, 2, 'ID', 'System column: ID', 5, TRUE, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 2, 3, NULL, 'StartDate', 'Campaign start date', 2, FALSE, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (11, 2, 3, NULL, 'EndDate', 'Campaign end date', 3, FALSE, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (12, 2, 4, NULL, 'Status', 'Campaign status', 4, FALSE, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- List 3: Asset Inventory
    (13, 3, 1, 1, 'Title', 'System column: Title', 1, TRUE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (14, 3, 2, 2, 'ID', 'System column: ID', 5, TRUE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (15, 3, 1, NULL, 'AssetCode', 'Asset identification code', 2, FALSE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (16, 3, 4, NULL, 'Category', 'Asset category', 3, FALSE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (17, 3, 4, NULL, 'Status', 'Asset status', 4, FALSE, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào ColumnChoice
INSERT INTO ColumnChoice (ColumnId, DisplayName, DisplayColor, DisplayOrder, Context, CreatedAt, UpdatedAt)
VALUES
    -- Status choices for Task list (column 5)
    (5, 'To Do', 'blue', 1, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'In Progress', 'orange', 2, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Done', 'green', 3, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Status choices for Campaign list (column 12)
    (12, 'Planning', 'blue', 1, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (12, 'ACTIVE', 'green', 2, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (12, 'Completed', 'gray', 3, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Category choices for Asset list (column 16)
    (16, 'Hardware', 'blue', 1, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (16, 'Software', 'green', 2, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (16, 'Equipment', 'orange', 3, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- Status choices for Asset list (column 17)
    (17, 'Available', 'green', 1, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (17, 'In Use', 'blue', 2, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (17, 'Maintenance', 'orange', 3, 'LIST', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào ListRow
INSERT INTO ListRow (Id, ListId, DisplayOrder, CreatedBy, ListRowStatus, CreatedAt, UpdatedAt)
VALUES
    -- List 1: Project Tasks (3 rows)
    (1, 1, 1, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 1, 2, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 1, 3, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- List 2: Marketing Campaigns (2 rows)
    (4, 2, 1, 2, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 2, 2, 2, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- List 3: Asset Inventory (2 rows)
    (6, 3, 1, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 3, 2, 1, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào ListCellValue
INSERT INTO ListCellValue (ListRowId, ListColumnId, CellValue, CreatedBy, CreatedAt, UpdatedAt)
VALUES
    -- List 1 Row 1: Project Tasks
    (1, 1, 'Setup Database Schema', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 2, '1001', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 5, '1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Status: To Do
    (1, 6, '2', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- AssignedTo: Jane
    (1, 7, '2025-08-20 10:00:00', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- List 1 Row 2: Project Tasks
    (2, 1, 'Create API Endpoints', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, '1002', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 5, '2', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Status: In Progress
    (2, 6, '1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- AssignedTo: John
    (2, 7, '2025-08-25 15:00:00', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    -- List 2 Row 1: Marketing Campaigns
    (4, 8, 'Summer Sale Campaign', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 9, '2001', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 10, '2025-07-01 09:00:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 11, '2025-08-31 18:00:00', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 12, '2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Status: ACTIVE
    -- List 3 Row 1: Asset Inventory
    (6, 13, 'MacBook Pro 16inch', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 14, '3001', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 15, 'MBP-2023-001', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 16, '1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Category: Hardware
    (6, 17, '2', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- Status: In Use

-- Insert vào FavoriteList
INSERT INTO FavoriteList (ListId, AccountId, CreatedAt, UpdatedAt)
VALUES (1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào RecentList
INSERT INTO RecentList (ListId, AccountId, AccessedAt)
VALUES (1, 3, CURRENT_TIMESTAMP),
       (2, 3, CURRENT_TIMESTAMP),
       (3, 1, CURRENT_TIMESTAMP);

-- Insert vào ListRowComment
INSERT INTO ListRowComment (ListRowId, Content, CreatedBy, CreatedAt, UpdatedAt)
VALUES (1, 'Database schema looks good', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 'Campaign on track', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (6, 'MacBook assigned', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào FileAttachment
INSERT INTO FileAttachment (ListRowId, FileAttachmentName, FileUrl, CreatedAt, UpdatedAt)
VALUES (1, 'database_schema.sql', 'https://example.com/files/database_schema.sql', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP),
       (4, 'campaign_plan.pdf', 'https://example.com/files/campaign_plan.pdf', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (6, 'macbook_receipt.pdf', 'https://example.com/files/macbook_receipt.pdf', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP);

-- Insert vào ObjectType
INSERT INTO ObjectType (Id, Code, DisplayName, Icon)
VALUES (1, 'LIST', 'List', 'list-icon.png'),
       (2, 'LISTROW', 'List Row', 'row-icon.png'),
       (3, 'FILE', 'File', 'file-icon.png');

-- Insert vào Scope
INSERT INTO Scope (Id, Code, DisplayName, ScopeDescription, Icon)
VALUES (1, 'PUBLIC', 'Public', 'Accessible to everyone', 'public-icon.png'),
       (2, 'AUTHORIZED', 'Authorized', 'Accessible to authorized users', 'auth-icon.png'),
       (3, 'SPECIFIC', 'Specific', 'Accessible to specific users', 'specific-icon.png');

-- Insert vào ShareLink
INSERT INTO ShareLink (ListId, TargetUrl, ScopeId, PermissionId, LinkStatus, CreatedBy, CreatedAt, UpdatedAt)
VALUES (1, '/share/list/1/public', 1, 2, 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, '/share/list/2/authorized', 2, 3, 'ACTIVE', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, '/share/list/3/specific', 3, 2, 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào ShareLinkUserAccess
INSERT INTO ShareLinkUserAccess (ShareLinkId, AccountId, Email, CreatedAt, UpdatedAt)
VALUES (1, 2, 'user200@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 3, 'user300@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 4, 'user400@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert vào ListMemberPermission
INSERT INTO ListMemberPermission (ListId, AccountId, HighestPermissionId, GrantedBy, Note, CreatedAt, UpdatedAt)
VALUES (1, 2, 2, 1, 'Contributor access', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 3, 3, 2, 'Read access', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 4, 2, 1, 'Contributor for inventory', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);