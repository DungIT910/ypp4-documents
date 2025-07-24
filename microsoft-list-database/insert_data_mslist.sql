USE MsList;
GO

-- Insert into Permission (3 quyền: Admin, Contributor, Reader)
INSERT INTO Permission (Name, Code, Icon, CreateAt, UpdateAt)
VALUES 
    ('Admin', 'Admin', 'admin-icon.png', GETDATE(), GETDATE()),
    ('Contributor', 'Contributor', 'contributor-icon.png', GETDATE(), GETDATE()),
    ('Reader', 'Reader', 'reader-icon.png', GETDATE(), GETDATE());

-- Insert into Scope (3 loại: Public, Authorized, Specific)
INSERT INTO Scope (Code, Name, Description, Icon, CreateAt, UpdateAt)
VALUES 
    ('PUBLIC', 'Public', 'Accessible to everyone', 'public-icon.png', GETDATE(), GETDATE()),
    ('AUTHORIZED', 'Authorized', 'Accessible to users with existing permissions', 'authorized-icon.png', GETDATE(), GETDATE()),
    ('SPECIFIC', 'Specific', 'Accessible to specific users', 'specific-icon.png', GETDATE(), GETDATE());

-- Insert into ListType (5 loại: list, form, gallery, kanban, calendar)
INSERT INTO ListType (Title, Icon, Description, HeaderImage, CreateAt, UpdateAt)
VALUES 
    ('list', 'list-icon.png', 'Standard list view', 'list-header.jpg', GETDATE(), GETDATE()),
    ('form', 'form-icon.png', 'Form-based list', 'form-header.jpg', GETDATE(), GETDATE()),
    ('gallery', 'gallery-icon.png', 'Gallery view', 'gallery-header.jpg', GETDATE(), GETDATE()),
    ('kanban', 'kanban-icon.png', 'Kanban board view', 'kanban-header.jpg', GETDATE(), GETDATE()),
    ('calendar', 'calendar-icon.png', 'Calendar view', 'calendar-header.jpg', GETDATE(), GETDATE());

-- Insert into ViewType (4 loại: list, gallery, table, calendar)
INSERT INTO ViewType (Title, HeaderImage, Icon, Description, CreateAt, UpdateAt)
VALUES 
    ('list', 'list-header.jpg', 'list-icon.png', 'Standard list view', GETDATE(), GETDATE()),
    ('gallery', 'gallery-header.jpg', 'gallery-icon.png', 'Gallery view', GETDATE(), GETDATE()),
    ('table', 'table-header.jpg', 'table-icon.png', 'Table view', GETDATE(), GETDATE()),
    ('calendar', 'calendar-header.jpg', 'calendar-icon.png', 'Calendar view', GETDATE(), GETDATE());

-- Insert into SystemDataType (text, long text, choice, datetime, person, number, yesno, hyperlink, image, lookup, rate)
INSERT INTO SystemDataType (Icon, Description, CoverImg, DisplayName, DataTypeValue, CreateAt, UpdateAt)
VALUES 
    ('text-icon.png', 'Single line text', 'text-cover.jpg', 'Text', 'Text', GETDATE(), GETDATE()),
    ('longtext-icon.png', 'Multiple lines text', 'longtext-cover.jpg', 'Long Text', 'Text', GETDATE(), GETDATE()),
    ('choice-icon.png', 'Multiple choice', 'choice-cover.jpg', 'Choice', 'Text', GETDATE(), GETDATE()),
    ('datetime-icon.png', 'Date and time', 'datetime-cover.jpg', 'Datetime', 'Date', GETDATE(), GETDATE()),
    ('person-icon.png', 'Person or group', 'person-cover.jpg', 'Person', 'Text', GETDATE(), GETDATE()),
    ('number-icon.png', 'Numeric value', 'number-cover.jpg', 'Number', 'Number', GETDATE(), GETDATE()),
    ('yesno-icon.png', 'Yes/No value', 'yesno-cover.jpg', 'YesNo', 'Boolean', GETDATE(), GETDATE()),
    ('hyperlink-icon.png', 'URL or link', 'hyperlink-cover.jpg', 'Hyperlink', 'Text', GETDATE(), GETDATE()),
    ('image-icon.png', 'Image file', 'image-cover.jpg', 'Image', 'Text', GETDATE(), GETDATE()),
    ('lookup-icon.png', 'Lookup to another table', 'lookup-cover.jpg', 'Lookup', 'Text', GETDATE(), GETDATE()),
    ('rate-icon.png', 'Rating value', 'rate-cover.jpg', 'Rate', 'Number', GETDATE(), GETDATE());

-- Insert into ViewSetting (for calendar: Titles, startDate, endDate, extra title, IsPublic; for kanban: sort kanban by)
INSERT INTO ViewSetting (SettingKey, ValueType, CreateAt, UpdateAt)
VALUES 
    ('Titles of calendar items', 'Text', GETDATE(), GETDATE()),
    ('startDate', 'Date', GETDATE(), GETDATE()),
    ('endDate', 'Date', GETDATE(), GETDATE()),
    ('extra title', 'Text', GETDATE(), GETDATE()),
    ('IsPublic', 'Boolean', GETDATE(), GETDATE()),
    ('sort kanban by', 'Text', GETDATE(), GETDATE());

-- Insert into KeySetting (settings phù hợp với SystemDataType)
INSERT INTO KeySetting (Icon, KeyName, ValueType, DefaultValue, IsShareLinkSetting, CreateAt, UpdateAt)
VALUES 
    ('default-icon.png', 'defaultValue', 'Text', 'Sample Text', 0, GETDATE(), GETDATE()), -- for Text, Long Text
    ('maxlength-icon.png', 'maxLength', 'Number', '255', 0, GETDATE(), GETDATE()), -- for Text, Long Text
    ('decimalplaces-icon.png', 'decimalPlaces', 'Number', '2', 0, GETDATE(), GETDATE()), -- for Number
    ('isrequired-icon.png', 'isRequired', 'Boolean', '0', 0, GETDATE(), GETDATE()), -- for all types
    ('min-icon.png', 'min', 'Number', '0', 0, GETDATE(), GETDATE()), -- for Number, Rate
    ('max-icon.png', 'max', 'Number', '100', 0, GETDATE(), GETDATE()), -- for Number, Rate
    ('defaultdate-icon.png', 'defaultDate', 'Date', '2025-01-01', 0, GETDATE(), GETDATE()), -- for Datetime
    ('choices-icon.png', 'choices', 'Text', 'Option1,Option2', 0, GETDATE(), GETDATE()); -- for Choice

-- Insert into Account (1000 accounts)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO Account (FirstName, LastName, Email, AccountPassword, Avatar, Company, AccountStatus, CreateAt, UpdateAt)
SELECT 
    'User' + CAST(RowNum AS NVARCHAR(10)),
    'Last' + CAST(RowNum AS NVARCHAR(10)),
    'user' + CAST(RowNum AS NVARCHAR(10)) + '@example.com',
    'password' + CAST(RowNum AS NVARCHAR(10)),
    'avatar' + CAST(RowNum AS NVARCHAR(10)) + '.png',
    'Company' + CAST(RowNum / 10 AS NVARCHAR(10)),
    CASE WHEN RowNum % 3 = 0 THEN 'Active' ELSE 'Pending' END,
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into Workspace (1000 workspaces)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO Workspace (WorkspaceName, CreateAt, UpdateAt)
SELECT 
    'Workspace' + CAST(RowNum AS NVARCHAR(10)),
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into TemplateProvider (100 providers)
;WITH Numbers AS (
    SELECT TOP 100 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO TemplateProvider (ProviderName, CreateAt, UpdateAt)
SELECT 
    'Provider' + CAST(RowNum AS NVARCHAR(10)),
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ListTemplate (1000 templates)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListTemplate (Title, HeaderImage, Description, Icon, Color, Summary, Feature, ProviderId, CreateAt, UpdateAt)
SELECT 
    'Template' + CAST(RowNum AS NVARCHAR(10)),
    'header' + CAST(RowNum AS NVARCHAR(10)) + '.jpg',
    'Description for template ' + CAST(RowNum AS NVARCHAR(10)),
    'icon' + CAST(RowNum AS NVARCHAR(10)) + '.png',
    CASE WHEN RowNum % 3 = 0 THEN '#FF0000' ELSE '#00FF00' END,
    'Summary for template ' + CAST(RowNum AS NVARCHAR(10)),
    'Feature for template ' + CAST(RowNum AS NVARCHAR(10)),
    (RowNum % 100) + 1, -- Link to TemplateProvider
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into TemplateView (1000 views, linked to ListTemplate and ViewType)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO TemplateView (ListTemplateId, ViewName, ViewTypeId, DisplayOrder, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to ListTemplate
    'View' + CAST(RowNum AS NVARCHAR(10)),
    (RowNum % 4) + 1, -- Link to ViewType (1-4: list, gallery, table, calendar)
    RowNum,
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into TemplateColumn (1000 columns, linked to ListTemplate and SystemDataType)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO TemplateColumn (ListTemplateId, ColumnName, ColumnDescription, DisplayOrder, IsVisible, SystemDataTypeId, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to ListTemplate
    'Column' + CAST(RowNum AS NVARCHAR(10)),
    'Description for column ' + CAST(RowNum AS NVARCHAR(10)),
    RowNum,
    CASE WHEN RowNum % 2 = 0 THEN 1 ELSE 0 END,
    (RowNum % 11) + 1, -- Link to SystemDataType (1-11: text, long text, choice, datetime, person, number, yesno, hyperlink, image, lookup, rate)
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into TemplateSampleRow (1000 rows, linked to ListTemplate)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO TemplateSampleRow (ListTemplateId, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to ListTemplate
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into TemplateSampleCell (1000 cells, linked to TemplateColumn and TemplateSampleRow)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO TemplateSampleCell (TemplateColumnId, TemplateSampleRowId, CellValue, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to TemplateColumn
    (RowNum % 1000) + 1, -- Link to TemplateSampleRow
    'Value' + CAST(RowNum AS NVARCHAR(10)),
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ViewTypeSetting (link ViewType and ViewSetting)
INSERT INTO ViewTypeSetting (ViewTypeId, ViewSettingId, CreateAt, UpdateAt)
SELECT 
    vt.Id,
    vs.Id,
    GETDATE(),
    GETDATE()
FROM ViewType vt
CROSS JOIN ViewSetting vs
WHERE vt.Title IN ('calendar', 'kanban'); -- Only for calendar and kanban settings

-- Insert into TemplateViewSetting (1000 settings, linked to TemplateView, ViewTypeSetting, and TemplateColumn)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO TemplateViewSetting (TemplateViewId, ViewTypeSettingId, GroupByColumnId, RawValue, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to TemplateView
    vts.Id,
    CASE 
        WHEN vts.ViewSettingId IN (SELECT Id FROM ViewSetting WHERE SettingKey IN ('startDate', 'endDate')) 
        THEN (SELECT TOP 1 Id FROM TemplateColumn WHERE SystemDataTypeId = (SELECT Id FROM SystemDataType WHERE DataTypeValue = 'Date') AND ListTemplateId = tv.ListTemplateId)
        WHEN vts.ViewSettingId = (SELECT Id FROM ViewSetting WHERE SettingKey = 'sort kanban by')
        THEN (SELECT TOP 1 Id FROM TemplateColumn WHERE SystemDataTypeId = (SELECT Id FROM SystemDataType WHERE DisplayName = 'Choice') AND ListTemplateId = tv.ListTemplateId)
        ELSE NULL
    END,
    CASE 
        WHEN vts.ViewSettingId = (SELECT Id FROM ViewSetting WHERE SettingKey = 'IsPublic') THEN CAST((RowNum % 2) AS NVARCHAR(10))
        ELSE 'SampleValue' + CAST(RowNum AS NVARCHAR(10))
    END,
    GETDATE(),
    GETDATE()
FROM Numbers
CROSS JOIN ViewTypeSetting vts
JOIN TemplateView tv ON tv.Id = (RowNum % 1000) + 1
WHERE vts.ViewTypeId IN (SELECT Id FROM ViewType WHERE Title IN ('calendar', 'kanban'));

-- Insert into WorkspaceMember (1000 members, linked to Account and Workspace)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO WorkspaceMember (AccountId, WorkspaceId, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to Account
    (RowNum % 1000) + 1, -- Link to Workspace
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into List (1000 lists, linked to Workspace, ListType, ListTemplate, Account)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO List (ListName, Icon, Color, WorkspaceId, ListTypeId, TemplateId, CreateBy, ListStatus, CreateAt, UpdateAt)
SELECT 
    'List' + CAST(RowNum AS NVARCHAR(10)),
    'list-icon' + CAST(RowNum AS NVARCHAR(10)) + '.png',
    CASE WHEN RowNum % 3 = 0 THEN '#FF0000' ELSE '#00FF00' END,
    (RowNum % 1000) + 1, -- Link to Workspace
    (RowNum % 5) + 1, -- Link to ListType (1-5: list, form, gallery, kanban, calendar)
    (RowNum % 1000) + 1, -- Link to ListTemplate
    (RowNum % 1000) + 1, -- Link to Account
    CASE WHEN RowNum % 2 = 0 THEN 'Active' ELSE 'Draft' END,
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ListView (1000 views for non-calendar, 2000 for calendar lists)
;WITH Numbers AS (
    SELECT TOP 2000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListView (ListId, ViewName, ViewTypeId, DisplayOrder, CreatedBy, CreateAt, UpdateAt)
SELECT 
    l.Id,
    CASE 
        WHEN l.ListTypeId = (SELECT Id FROM ListType WHERE Title = 'calendar') AND RowNum % 2 = 0 THEN 'Calendar View' + CAST(RowNum AS NVARCHAR(10))
        ELSE 'List View' + CAST(RowNum AS NVARCHAR(10))
    END,
    CASE 
        WHEN l.ListTypeId = (SELECT Id FROM ListType WHERE Title = 'calendar') AND RowNum % 2 = 0 THEN (SELECT Id FROM ViewType WHERE Title = 'calendar')
        ELSE (SELECT Id FROM ViewType WHERE Title = 'list')
    END,
    RowNum,
    l.CreateBy,
    GETDATE(),
    GETDATE()
FROM List l
CROSS JOIN (SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum FROM sys.objects a CROSS JOIN sys.objects b) n
WHERE (l.ListTypeId IN (SELECT Id FROM ListType WHERE Title IN ('list', 'form', 'gallery', 'kanban')) AND n.RowNum = 1)
   OR (l.ListTypeId = (SELECT Id FROM ListType WHERE Title = 'calendar') AND n.RowNum <= 2);

-- Insert into ListDynamicColumn (1000 columns, linked to List and SystemDataType)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListDynamicColumn (ListId, SystemDataTypeId, ColumnName, Description, DisplayOrder, IsSystemColumn, IsVisible, CreatedBy, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to List
    (RowNum % 11) + 1, -- Link to SystemDataType
    'Column' + CAST(RowNum AS NVARCHAR(10)),
    'Description for column ' + CAST(RowNum AS NVARCHAR(10)),
    RowNum,
    CASE WHEN RowNum % 5 = 0 THEN 1 ELSE 0 END,
    CASE WHEN RowNum % 2 = 0 THEN 1 ELSE 0 END,
    (RowNum % 1000) + 1, -- Link to Account
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ListRow (1000 rows, linked to List)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListRow (ListId, DisplayOrder, Status, CreatedBy, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to List
    RowNum,
    CASE WHEN RowNum % 2 = 0 THEN 'Active' ELSE 'Pending' END,
    (RowNum % 1000) + 1, -- Link to Account
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ListCellValue (1000 values, linked to ListRow and ListDynamicColumn)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListCellValue (ListRowId, ListColumnId, Value, CreatedBy, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to ListRow
    (RowNum % 1000) + 1, -- Link to ListDynamicColumn
    'Value' + CAST(RowNum AS NVARCHAR(10)),
    (RowNum % 1000) + 1, -- Link to Account
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ListColumnSettingObject (1000 objects for choice/person columns)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListColumnSettingObject (ListDynamicColumnId, DisplayName, DisplayColor, DisplayOrder, CreateAt, UpdateAt)
SELECT 
    ldc.Id,
    'Choice' + CAST(RowNum AS NVARCHAR(10)),
    CASE WHEN RowNum % 3 = 0 THEN '#FF0000' ELSE '#00FF00' END,
    RowNum,
    GETDATE(),
    GETDATE()
FROM Numbers
CROSS JOIN ListDynamicColumn ldc
WHERE ldc.SystemDataTypeId IN (SELECT Id FROM SystemDataType WHERE DisplayName IN ('Choice', 'Person'));

-- Insert into DataTypeSettingKey (link SystemDataType and KeySetting)
INSERT INTO DataTypeSettingKey (DataTypeId, KeySettingId, CreateAt, UpdateAt)
SELECT 
    sdt.Id,
    ks.Id,
    GETDATE(),
    GETDATE()
FROM SystemDataType sdt
CROSS JOIN KeySetting ks
WHERE (sdt.DisplayName IN ('Text', 'Long Text') AND ks.KeyName IN ('defaultValue', 'maxLength', 'isRequired'))
   OR (sdt.DisplayName = 'Number' AND ks.KeyName IN ('defaultValue', 'decimalPlaces', 'isRequired', 'min', 'max'))
   OR (sdt.DisplayName = 'Datetime' AND ks.KeyName IN ('defaultDate', 'isRequired'))
   OR (sdt.DisplayName = 'Choice' AND ks.KeyName IN ('choices', 'isRequired'))
   OR (sdt.DisplayName IN ('Person', 'YesNo', 'Hyperlink', 'Image', 'Lookup', 'Rate') AND ks.KeyName = 'isRequired');

-- Insert into ListColumnSettingValue (1000 values, linked to ListDynamicColumn and DataTypeSettingKey)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListColumnSettingValue (ColumnId, DataTypeSettingKeyId, KeyValue, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to ListDynamicColumn
    dtsk.Id,
    'SettingValue' + CAST(RowNum AS NVARCHAR(10)),
    GETDATE(),
    GETDATE()
FROM Numbers
CROSS JOIN DataTypeSettingKey dtsk;

-- Insert into ListViewSetting (1000 settings, linked to ListView, ViewTypeSetting, and ListDynamicColumn)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListViewSetting (ListViewId, ViewTypeSettingId, GroupByColumnId, RawValue, CreateAt, UpdateAt)
SELECT 
    lv.Id,
    vts.Id,
    CASE 
        WHEN vts.ViewSettingId IN (SELECT Id FROM ViewSetting WHERE SettingKey IN ('startDate', 'endDate')) 
        THEN (SELECT TOP 1 Id FROM ListDynamicColumn WHERE SystemDataTypeId = (SELECT Id FROM SystemDataType WHERE DataTypeValue = 'Date') AND ListId = lv.ListId)
        WHEN vts.ViewSettingId = (SELECT Id FROM ViewSetting WHERE SettingKey = 'sort kanban by')
        THEN (SELECT TOP 1 Id FROM ListDynamicColumn WHERE SystemDataTypeId = (SELECT Id FROM SystemDataType WHERE DisplayName = 'Choice') AND ListId = lv.ListId)
        ELSE NULL
    END,
    CASE 
        WHEN vts.ViewSettingId = (SELECT Id FROM ViewSetting WHERE SettingKey = 'IsPublic') THEN CAST((RowNum % 2) AS NVARCHAR(10))
        ELSE 'SampleValue' + CAST(RowNum AS NVARCHAR(10))
    END,
    GETDATE(),
    GETDATE()
FROM Numbers
CROSS JOIN ListView lv
CROSS JOIN ViewTypeSetting vts
WHERE vts.ViewTypeId IN (SELECT Id FROM ViewType WHERE Title IN ('calendar', 'kanban'));

-- Insert into ListRowComment (1000 comments, linked to ListRow)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListRowComment (Content, ListRowId, CreatedBy, CreateAt, UpdateAt)
SELECT 
    'Comment' + CAST(RowNum AS NVARCHAR(10)),
    (RowNum % 1000) + 1, -- Link to ListRow
    (RowNum % 1000) + 1, -- Link to Account
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into FavoriteList (1000 favorites, linked to Account and List)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO FavoriteList (AccountId, ListId, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to Account
    (RowNum % 1000) + 1, -- Link to List
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ShareLink (1000 links, linked to List, Scope, Permission)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ShareLink (URL, ScopeId, ExpirationDate, IsLoginRequired, Password, PermissionId, Note, CreateAt, UpdateAt)
SELECT 
    'https://example.com/share/' + CAST(RowNum AS NVARCHAR(10)),
    (RowNum % 3) + 1, -- Link to Scope (Public, Authorized, Specific)
    DATEADD(DAY, 30, GETDATE()),
    CASE WHEN RowNum % 2 = 0 THEN 1 ELSE 0 END,
    'password' + CAST(RowNum AS NVARCHAR(10)),
    (RowNum % 3) + 1, -- Link to Permission (Admin, Contributor, Reader)
    'Note for share link ' + CAST(RowNum AS NVARCHAR(10)),
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into ListMemberPermission (1000 permissions for Public ShareLinks)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ListMemberPermission (ListId, AccountId, Email, HighestPermissionCode, CreateAt, UpdateAt)
SELECT 
    sl.Id, -- Link to List (via ShareLink)
    (RowNum % 1000) + 1, -- Link to Account
    'user' + CAST(RowNum AS NVARCHAR(10)) + '@example.com',
    p.Code,
    GETDATE(),
    GETDATE()
FROM ShareLink sl
JOIN Permission p ON sl.PermissionId = p.Id
CROSS JOIN Numbers
WHERE sl.ScopeId = (SELECT Id FROM Scope WHERE Code = 'PUBLIC');

-- Insert into ShareLinkUserAccess (500 records for Specific ShareLinks)
;WITH Numbers AS (
    SELECT TOP 500 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO ShareLinkUserAccess (ShareLinkId, AccountId, Email, CreateAt, UpdateAt)
SELECT 
    sl.Id,
    (RowNum % 1000) + 1, -- Link to Account
    'user' + CAST(RowNum AS NVARCHAR(10)) + '@example.com',
    GETDATE(),
    GETDATE()
FROM ShareLink sl
CROSS JOIN Numbers
WHERE sl.ScopeId = (SELECT Id FROM Scope WHERE Code = 'SPECIFIC');

-- Insert into FileAttachment (1000 attachments, linked to ListRow)
;WITH Numbers AS (
    SELECT TOP 1000 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO FileAttachment (ListItemId, FileName, FileUrl, Size, Status, CreateAt, UpdateAt)
SELECT 
    (RowNum % 1000) + 1, -- Link to ListRow
    'File' + CAST(RowNum AS NVARCHAR(10)) + '.pdf',
    'https://example.com/files/' + CAST(RowNum AS NVARCHAR(10)) + '.pdf',
    1024 * (RowNum % 10 + 1),
    CASE WHEN RowNum % 2 = 0 THEN 'Uploaded' ELSE 'Pending' END,
    GETDATE(),
    GETDATE()
FROM Numbers;

-- Insert into TrashItem (100 items, linked to List, ListRow, FileAttachment)
;WITH Numbers AS (
    SELECT TOP 100 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
    FROM sys.objects a CROSS JOIN sys.objects b
)
INSERT INTO TrashItem (ObjectId, ObjectTypeId, ObjectName, Path, ViewType, ObjectStatus, CreatedBy, DeletedAt, DeleteBy)
SELECT 
    (RowNum % 1000) + 1,
    CASE 
        WHEN RowNum % 3 = 0 THEN 'List'
        WHEN RowNum % 3 = 1 THEN 'ListRow'
        ELSE 'FileAttachment'
    END,
    'DeletedItem' + CAST(RowNum AS NVARCHAR(10)),
    '/trash/' + CAST(RowNum AS NVARCHAR(10)),
    CASE WHEN RowNum % 4 = 0 THEN 'list' ELSE 'calendar' END,
    'Deleted',
    (RowNum % 1000) + 1, -- Link to Account
    GETDATE(),
    (RowNum % 1000) + 1 -- Link to Account
FROM Numbers;

GO