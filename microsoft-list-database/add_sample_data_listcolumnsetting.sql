USE [MsList]
GO

-- Khai báo và sử dụng biến @CurrentDateTime trong cùng một batch
DECLARE @CurrentDateTime DATETIME = '2025-07-23 15:20:00';

-- 1. Permission
INSERT INTO [dbo].[Permission] ([PermissionName], [PermissionCode])
SELECT 
    PermissionName,
    PermissionCode
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        PermissionName = CASE RowNum 
            WHEN 1 THEN 'Admin'
            WHEN 2 THEN 'Contributor'
            WHEN 3 THEN 'Reader'
            WHEN 4 THEN 'Guest'
            WHEN 5 THEN 'Editor'
        END,
        PermissionCode = CASE RowNum 
            WHEN 1 THEN 'admin'
            WHEN 2 THEN 'contributor'
            WHEN 3 THEN 'reader'
            WHEN 4 THEN 'guest'
            WHEN 5 THEN 'editor'
        END
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 2. DataType
INSERT INTO [dbo].[DataType] ([DisplayName], [TypeCode])
SELECT 
    DisplayName,
    TypeCode
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        DisplayName = CASE RowNum 
            WHEN 1 THEN 'Text'
            WHEN 2 THEN 'Number'
            WHEN 3 THEN 'Date'
            WHEN 4 THEN 'Choice'
            WHEN 5 THEN 'YesNo'
        END,
        TypeCode = CASE RowNum 
            WHEN 1 THEN 'text'
            WHEN 2 THEN 'number'
            WHEN 3 THEN 'date'
            WHEN 4 THEN 'choice'
            WHEN 5 THEN 'yesno'
        END
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 3. KeySetting
INSERT INTO [dbo].[KeySetting] ([KeyName], [ValueType])
SELECT 
    KeyName,
    ValueType
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        KeyName = CASE RowNum 
            WHEN 1 THEN 'MaxLength'
            WHEN 2 THEN 'Format'
            WHEN 3 THEN 'Required'
            WHEN 4 THEN 'DefaultValue'
            WHEN 5 THEN 'Color'
        END,
        ValueType = CASE RowNum 
            WHEN 1 THEN 'int'
            WHEN 2 THEN 'string'
            WHEN 3 THEN 'boolean'
            WHEN 4 THEN 'string'
            WHEN 5 THEN 'string'
        END
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 4. ViewType
INSERT INTO [dbo].[ViewType] ([ViewName], [DisplayName])
SELECT 
    ViewName,
    DisplayName
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        ViewName = 'ViewType' + CAST(RowNum AS NVARCHAR(10)),
        DisplayName = 'Display ' + CAST(RowNum AS NVARCHAR(10))
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 5. Account
INSERT INTO [dbo].[Account] ([FullName], [LastName], [DateBirth], [Email], [AccountPassword])
SELECT 
    FullName,
    LastName,
    DateBirth,
    Email,
    AccountPassword
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        FullName = 'User ' + CAST(RowNum AS NVARCHAR(10)) + ' Nguyen',
        LastName = 'Nguyen',
        DateBirth = DATEADD(day, -RAND() * 10000, '2025-01-01'),
        Email = 'user' + CAST(RowNum AS NVARCHAR(10)) + '@example.com',
        AccountPassword = 'pass' + CAST(RowNum AS NVARCHAR(10))
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 6. Workspace
INSERT INTO [dbo].[Workspace] ([WorkspaceName])
SELECT 
    WorkspaceName
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        WorkspaceName = 'Workspace ' + CAST(RowNum AS NVARCHAR(10))
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 7. WorkspaceMember
INSERT INTO [dbo].[WorkspaceMember] ([WorkspaceId], [AccountId], [CreateAt], [UpdateAt])
SELECT 
    WorkspaceId,
    AccountId,
    @CurrentDateTime,
    @CurrentDateTime
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        (RowNum % 5) + 1 AS WorkspaceId,
        (RowNum % 5) + 1 AS AccountId
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 8. List
INSERT INTO [dbo].[List] ([ListName], [CreatedBy], [CreatedAt], [ListTemplateId], [IsTemplate])
SELECT 
    ListName,
    CreatedBy,
    @CurrentDateTime,
    NULL,
    IsTemplate
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        ListName = CASE RowNum 
            WHEN 1 THEN 'List 1'
            WHEN 2 THEN 'Template 1'
            WHEN 3 THEN 'List 2'
            WHEN 4 THEN 'Trash'
            WHEN 5 THEN 'Template 2'
        END,
        CreatedBy = (RowNum % 5) + 1,
        IsTemplate = CASE WHEN RowNum IN (2, 5) THEN 1 ELSE 0 END
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 9. DynamicColumn
INSERT INTO [dbo].[DynamicColumn] ([ListId], [ColumnName], [ColDescription], [DisplayOrder], [DataTypeId], [IsVisible], [IsRequired])
SELECT 
    ListId,
    ColumnName,
    ColDescription,
    DisplayOrder,
    DataTypeId,
    IsVisible,
    IsRequired
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        ListId = CASE WHEN (RowNum % 5) IN (2, 5) THEN (RowNum % 5) + 2 ELSE (RowNum % 5) + 1 END,
        ColumnName = 'Column ' + CAST(RowNum AS NVARCHAR(10)),
        ColDescription = 'Description for Column ' + CAST(RowNum AS NVARCHAR(10)),
        DisplayOrder = RowNum,
        DataTypeId = (RowNum % 5) + 1,
        IsVisible = 1,
        IsRequired = CASE WHEN RowNum % 2 = 0 THEN 1 ELSE 0 END
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5 AND Source.ListId IN (1, 2, 3, 5); -- Loại bỏ Trash (ID 4)

-- 10. DataTypeSettingKey
INSERT INTO [dbo].[DataTypeSettingKey] ([DataTypeId], [KeySettingId])
SELECT 
    DataTypeId,
    KeySettingId
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        (RowNum % 5) + 1 AS DataTypeId,
        (RowNum % 5) + 1 AS KeySettingId
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 11. ViewTypeSettingKey
INSERT INTO [dbo].[ViewTypeSettingKey] ([ViewTypeId], [KeySettingId])
SELECT 
    ViewTypeId,
    KeySettingId
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        (RowNum % 5) + 1 AS ViewTypeId,
        (RowNum % 5) + 1 AS KeySettingId
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 12. ListColumnChoice
INSERT INTO [dbo].[ListColumnChoice] ([ColumnId], [DisplayName], [Color], [DisplayOrder])
SELECT 
    ColumnId,
    DisplayName,
    Color,
    DisplayOrder
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        4 AS ColumnId, -- Giả định ColumnId 4 là Choice
        DisplayName = 'Choice ' + CAST(RowNum AS NVARCHAR(10)),
        Color = CASE RowNum 
            WHEN 1 THEN '#28A745'
            WHEN 2 THEN '#DC3545'
            WHEN 3 THEN '#007BFF'
            WHEN 4 THEN '#FFC107'
            WHEN 5 THEN '#17A2B8'
        END,
        DisplayOrder = RowNum
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 13. ColumnSettingValue
INSERT INTO [dbo].[ColumnSettingValue] ([ColumnId], [KeySettingId], [KeyValue], [CreateAt], [UpdateAt])
SELECT 
    ColumnId,
    KeySettingId,
    KeyValue,
    @CurrentDateTime,
    @CurrentDateTime
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        (RowNum % 5) + 1 AS ColumnId,
        (RowNum % 5) + 1 AS KeySettingId,
        KeyValue = 'Value' + CAST(RowNum AS NVARCHAR(10))
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 14. ListView
INSERT INTO [dbo].[ListView] ([ListId], [ViewName], [ViewTypeId], [CreatedBy], [CreatedAt])
SELECT 
    ListId,
    ViewName,
    ViewTypeId,
    CreatedBy,
    @CurrentDateTime
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        (RowNum % 5) + 1 AS ListId,
        ViewName = 'View ' + CAST(RowNum AS NVARCHAR(10)),
        (RowNum % 5) + 1 AS ViewTypeId,
        (RowNum % 5) + 1 AS CreatedBy
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;

-- 15. ListViewSetting
INSERT INTO [dbo].[ListViewSetting] ([ListViewId], [ViewTypeSettingKeyId], [GroupByColumnId], [RawValue])
SELECT 
    ListViewId,
    ViewTypeSettingKeyId,
    GroupByColumnId,
    RawValue
FROM (
    SELECT 
        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum,
        (RowNum % 5) + 1 AS ListViewId,
        (RowNum % 5) + 1 AS ViewTypeSettingKeyId,
        (RowNum % 5) + 1 AS GroupByColumnId,
        RawValue = 'Setting' + CAST(RowNum AS NVARCHAR(10))
    FROM (
        SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS RowNum
        FROM sys.objects a CROSS JOIN sys.objects b
    ) AS Numbered
) AS Source
WHERE RowNum <= 5;
GO