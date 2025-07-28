USE MsList;
GO

-- Insert into Account (1000 unique accounts)
INSERT INTO Account (FirstName, LastName, Email, AccountPassword, Avatar, Company, AccountStatus, CreateAt, UpdateAt)
SELECT TOP 1000
    'FirstName' + CAST(n AS NVARCHAR),
    'LastName' + CAST(n AS NVARCHAR),
    'user' + CAST(n AS NVARCHAR) + '@example.com',
    'password' + CAST(n AS NVARCHAR),
    'https://avatar.example.com/avatar' + CAST(n % 100 AS NVARCHAR) + '.png',
    'Company' + CAST(n % 10 AS NVARCHAR),
    CASE WHEN n % 3 = 0 THEN 'Active' ELSE 'Inactive' END,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into Workspace
INSERT INTO Workspace (WorkspaceName, CreateAt, UpdateAt)
SELECT TOP 1000
    'Workspace' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TemplateProvider
INSERT INTO TemplateProvider (ProviderName, CreateAt, UpdateAt)
SELECT TOP 1000
    'Provider' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListTemplate
INSERT INTO ListTemplate (Title, HeaderImage, Description, Icon, Color, Summary, Feature, ProviderId, CreateAt, UpdateAt)
SELECT TOP 1000
    'Template' + CAST(n AS NVARCHAR),
    'https://image.example.com/img' + CAST(n % 100 AS NVARCHAR) + '.png',
    'Description for template ' + CAST(n AS NVARCHAR),
    'icon' + CAST(n % 5 AS NVARCHAR),
    CASE WHEN n % 3 = 0 THEN '#FF0000' ELSE '#00FF00' END,
    'Summary for template ' + CAST(n AS NVARCHAR),
    'Feature set ' + CAST(n AS NVARCHAR),
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ViewType
INSERT INTO ViewType (Title, HeaderImage, Icon, Description, CreateAt, UpdateAt)
SELECT TOP 1000
    CASE WHEN n % 3 = 0 THEN 'Calendar'
         WHEN n % 3 = 1 THEN 'Grid'
         ELSE 'Board' END + CAST(n AS NVARCHAR),
    'https://image.example.com/view' + CAST(n % 100 AS NVARCHAR) + '.png',
    'view-icon' + CAST(n % 5 AS NVARCHAR),
    'Description for view type ' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ViewSetting
INSERT INTO ViewSetting (SettingKey, ValueType, CreateAt, UpdateAt)
SELECT TOP 1000
    CASE WHEN n % 3 = 0 THEN 'ShowEvents'
         WHEN n % 3 = 1 THEN 'ColumnWidth'
         ELSE 'GroupBy' END + CAST(n AS NVARCHAR),
    CASE WHEN n % 2 = 0 THEN 'Boolean' ELSE 'String' END,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into SystemDataType
INSERT INTO SystemDataType (Icon, Description, CoverImg, DisplayName, DataTypeValue, CreateAt, UpdateAt)
SELECT TOP 1000
    'icon' + CAST(n % 5 AS NVARCHAR),
    'Data type description ' + CAST(n AS NVARCHAR),
    'https://cover.example.com/cover' + CAST(n % 100 AS NVARCHAR) + '.png',
    'DataType' + CAST(n AS NVARCHAR),
    CASE WHEN n % 3 = 0 THEN 'Text'
         WHEN n % 3 = 1 THEN 'Number'
         ELSE 'Date' END,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into KeySetting
INSERT INTO KeySetting (Icon, KeyName, ValueType, DefaultValue, IsShareLinkSetting, CreateAt, UpdateAt)
SELECT TOP 1000
    'key-icon' + CAST(n % 5 AS NVARCHAR),
    'Key' + CAST(n AS NVARCHAR),
    CASE WHEN n % 2 = 0 THEN 'Boolean' ELSE 'String' END,
    'Default' + CAST(n AS NVARCHAR),
    CASE WHEN n % 4 = 0 THEN 1 ELSE 0 END,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into Permission
INSERT INTO Permission (Name, Code, Icon, CreateAt, UpdateAt)
SELECT TOP 1000
    'Permission' + CAST(n AS NVARCHAR),
    'PERM' + CAST(n AS NVARCHAR),
    'perm-icon' + CAST(n % 5 AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into Scope
INSERT INTO Scope (Code, Name, Description, Icon, CreateAt, UpdateAt)
SELECT TOP 1000
    'SCOPE' + CAST(n AS NVARCHAR),
    'Scope' + CAST(n AS NVARCHAR),
    'Scope description ' + CAST(n AS NVARCHAR),
    'scope-icon' + CAST(n % 5 AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListType
INSERT INTO ListType (Title, Icon, Description, HeaderImage, CreateAt, UpdateAt)
SELECT TOP 1000
    'ListType' + CAST(n AS NVARCHAR),
    'list-icon' + CAST(n % 5 AS NVARCHAR),
    'List type description ' + CAST(n AS NVARCHAR),
    'https://header.example.com/header' + CAST(n % 100 AS NVARCHAR) + '.png',
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ObjectType
INSERT INTO ObjectType (Name, Code, Icon)
SELECT TOP 1000
    'ObjectType' + CAST(n AS NVARCHAR),
    'OBJTYPE' + CAST(n AS NVARCHAR),
    'obj-icon' + CAST(n % 5 AS NVARCHAR)
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into WorkspaceMember
INSERT INTO WorkspaceMember (AccountId, WorkspaceId, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TemplateView
INSERT INTO TemplateView (ListTemplateId, ViewName, ViewTypeId, DisplayOrder, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    'View' + CAST(n AS NVARCHAR),
    (n - 1) % 1000 + 1,
    n % 10,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TemplateColumn
INSERT INTO TemplateColumn (ListTemplateId, ColumnName, ColumnDescription, DisplayOrder, IsVisible, SystemDataTypeId, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    'Column' + CAST(n AS NVARCHAR),
    'Description for column ' + CAST(n AS NVARCHAR),
    n % 10,
    CASE WHEN n % 2 = 0 THEN 1 ELSE 0 END,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TemplateSampleRow
INSERT INTO TemplateSampleRow (ListTemplateId, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TemplateSampleCell
INSERT INTO TemplateSampleCell (TemplateColumnId, TemplateSampleRowId, CellValue, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'Value' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ViewTypeSetting
INSERT INTO ViewTypeSetting (ViewTypeId, ViewSettingId, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TemplateViewSetting
INSERT INTO TemplateViewSetting (TemplateViewId, ViewTypeSettingId, GroupByColumnId, RawValue, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'RawValue' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into DataTypeSettingKey
INSERT INTO DataTypeSettingKey (DataTypeId, KeySettingId, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TemplateColumnSettingValue
WITH NumberedTemplateColumns AS (
    SELECT Id AS TemplateColumnId, ROW_NUMBER() OVER (ORDER BY Id) AS tc_rn
    FROM TemplateColumn
),
NumberedDataTypeSettingKeys AS (
    SELECT Id AS DataTypeSettingKeyId, ROW_NUMBER() OVER (ORDER BY Id) AS dtsk_rn
    FROM DataTypeSettingKey
)
INSERT INTO TemplateColumnSettingValue (TemplateColumnId, DataTypeSettingKeyId, KeyValue, CreateAt, UpdateAt)
SELECT TOP 1000
    tc.TemplateColumnId,
    dtsk.DataTypeSettingKeyId,
    'KeyValue' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
LEFT JOIN NumberedTemplateColumns tc ON tc.tc_rn = (n - 1) % (SELECT COUNT(*) FROM TemplateColumn) + 1
LEFT JOIN NumberedDataTypeSettingKeys dtsk ON dtsk.dtsk_rn = (n - 1) % (SELECT COUNT(*) FROM DataTypeSettingKey) + 1
WHERE n BETWEEN 1 AND 1000
  AND tc.TemplateColumnId IS NOT NULL
  AND dtsk.DataTypeSettingKeyId IS NOT NULL;

-- Insert into List
INSERT INTO List (ListName, Icon, Color, WorkspaceId, ListTypeId, TemplateId, CreateBy, ListStatus, CreateAt, UpdateAt)
SELECT TOP 1000
    'List' + CAST(n AS NVARCHAR),
    'list-icon' + CAST(n % 5 AS NVARCHAR),
    CASE WHEN n % 3 = 0 THEN '#FF0000' ELSE '#00FF00' END,
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    CASE WHEN n % 2 = 0 THEN 'Active' ELSE 'Draft' END,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListView
INSERT INTO ListView (ListId, ViewName, ViewTypeId, DisplayOrder, CreatedBy, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    'View' + CAST(n AS NVARCHAR),
    (n - 1) % 1000 + 1,
    n % 10,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListDynamicColumn
INSERT INTO ListDynamicColumn (ListId, SystemDataTypeId, ColumnName, Description, DisplayOrder, IsSystemColumn, IsVisible, CreatedBy, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'Column' + CAST(n AS NVARCHAR),
    'Column description ' + CAST(n AS NVARCHAR),
    n % 10,
    CASE WHEN n % 2 = 0 THEN 1 ELSE 0 END,
    CASE WHEN n % 2 = 0 THEN 1 ELSE 0 END,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListRow
INSERT INTO ListRow (ListId, DisplayOrder, Status, CreatedBy, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    n % 10,
    CASE WHEN n % 2 = 0 THEN 'Active' ELSE 'Pending' END,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListCellValue
INSERT INTO ListCellValue (ListRowId, ListColumnId, Value, CreatedBy, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'Value' + CAST(n AS NVARCHAR),
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListRowComment
INSERT INTO ListRowComment (Content, ListRowId, CreatedBy, CreateAt, UpdateAt)
SELECT TOP 1000
    'Comment ' + CAST(n AS NVARCHAR),
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListColumnSettingObject (Updated with new structure)
-- Insert for TEMPLATE context (500 records)
INSERT INTO ListColumnSettingObject (ColumnId, DisplayName, DisplayColor, DisplayOrder, Context, CreateAt, UpdateAt)
SELECT TOP 500
    (n - 1) % 1000 + 1, -- ColumnId references TemplateColumn.Id for TEMPLATE context
    'TemplateSetting' + CAST(n AS NVARCHAR),
    CASE WHEN n % 3 = 0 THEN '#FF0000' ELSE '#00FF00' END,
    n % 10,
    'TEMPLATE',
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 500;

-- Insert for LIST context (500 records)
INSERT INTO ListColumnSettingObject (ColumnId, DisplayName, DisplayColor, DisplayOrder, Context, CreateAt, UpdateAt)
SELECT TOP 500
    (n - 1) % 1000 + 1, -- ColumnId references ListDynamicColumn.Id for LIST context
    'ListSetting' + CAST(n AS NVARCHAR),
    CASE WHEN n % 3 = 0 THEN '#0000FF' ELSE '#FF00FF' END,
    n % 10,
    'LIST',
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 501 AND 1000;

-- Insert into DynamicColumnSettingValue
INSERT INTO DynamicColumnSettingValue (ColumnId, DataTypeSettingKeyId, KeyValue, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'KeyValue' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListViewSetting
WITH NumberedRows AS (
    SELECT 
        lv.Id AS ListViewId,
        vts.Id AS ViewTypeSettingId,
        (n - 1) % 1000 + 1 AS GroupByColumnId,
        'RawValue' + CAST(n AS NVARCHAR) AS RawValue,
        DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27') AS CreateAt,
        DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27') AS UpdateAt,
        ROW_NUMBER() OVER (ORDER BY lv.Id, vts.Id) AS rn
    FROM ListView lv
    CROSS JOIN ViewTypeSetting vts
    CROSS JOIN (SELECT ROW_NUMBER() OVER (ORDER BY object_id) AS n
                FROM sys.objects) AS numbers
    WHERE vts.ViewTypeId = lv.ViewTypeId
)
INSERT INTO ListViewSetting (ListViewId, ViewTypeSettingId, GroupByColumnId, RawValue, CreateAt, UpdateAt)
SELECT TOP 1000 ListViewId, ViewTypeSettingId, GroupByColumnId, RawValue, CreateAt, UpdateAt
FROM NumberedRows
WHERE rn BETWEEN 1 AND 1000;

-- Insert into FavoriteList
INSERT INTO FavoriteList (AccountId, ListId, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ShareLink
INSERT INTO ShareLink (ListId, URL, ScopeId, ExpirationDate, IsLoginRequired, Password, PermissionId, Note, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    'https://share.example.com/link' + CAST(n % 100 AS NVARCHAR),
    (n - 1) % 1000 + 1,
    DATEADD(DAY, n % 365, '2025-07-27'),
    CASE WHEN n % 2 = 0 THEN 1 ELSE 0 END,
    'pass' + CAST(n AS NVARCHAR),
    (n - 1) % 1000 + 1,
    'Note for share link ' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ShareLinkSettingValue
INSERT INTO ShareLinkSettingValue (ShareLinkId, KeySettingId, KeyValue, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'KeyValue' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ShareLinkUserAccess
INSERT INTO ShareLinkUserAccess (ShareLinkId, AccountId, Email, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'share' + CAST(n AS NVARCHAR) + '@example.com',
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into ListMemberPermission
INSERT INTO ListMemberPermission (ListId, AccountId, Email, HighestPermissionCode, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'member' + CAST(n AS NVARCHAR) + '@example.com',
    'PERM' + CAST(n AS NVARCHAR),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into FileAttachment
INSERT INTO FileAttachment (ListItemId, FileName, FileUrl, Size, Status, CreateAt, UpdateAt)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    'File' + CAST(n AS NVARCHAR) + '.pdf',
    'https://file.example.com/file' + CAST(n % 100 AS NVARCHAR) + '.pdf',
    n % 1000000,
    CASE WHEN n % 2 = 0 THEN 'Uploaded' ELSE 'Pending' END,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27')
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;

-- Insert into TrashItem
INSERT INTO TrashItem (ObjectId, ObjectTypeId, ObjectName, Path, ViewType, ObjectStatus, CreatedBy, DeletedAt, DeleteBy)
SELECT TOP 1000
    (n - 1) % 1000 + 1,
    (n - 1) % 1000 + 1,
    'Object' + CAST(n AS NVARCHAR),
    '/path/to/obj' + CAST(n % 100 AS NVARCHAR),
    CASE WHEN n % 3 = 0 THEN 'Calendar' ELSE 'Grid' END,
    'Deleted',
    (n - 1) % 1000 + 1,
    DATEADD(DAY, -ABS(CHECKSUM(NEWID()) % 912), '2025-07-27'),
    (n - 1) % 1000 + 1
FROM (SELECT ROW_NUMBER() OVER (ORDER BY a.object_id) AS n
      FROM sys.objects a CROSS JOIN sys.objects b) AS numbers
WHERE n BETWEEN 1 AND 1000;
GO