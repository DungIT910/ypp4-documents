```sql
USE MsList

-- Dashboard Screen

    -- Get information of a user
    DECLARE @AccountId INT;
    SET @AccountId = 1;
    SELECT acc.Id, acc.Avatar, acc.Email, acc.FirstName, acc.LastName, acc.Company, acc.AccountStatus
    FROM 
	    Account AS acc
    WHERE acc.Id = @AccountId

    -- Get lists of a user
    DECLARE @AccountId INT;
    SET @AccountId = 1;
    SELECT l.Id, l.Color, l.Icon, l.ListName, l.ListStatus, l.CreatedBy, l.UpdatedAt
    FROM 
	    List AS l
    INNER JOIN 
	    Account AS acc ON l.CreatedBy = acc.Id
    WHERE 
        acc.Id = @AccountId
    ORDER BY 
	    l.UpdatedAt ASC

    -- Get favorite lists of a user
    DECLARE @AccountId INT;
    SET @AccountId = 3;
    SELECT 
        l.Id, l.Color, l.Icon, l.ListName, l.ListStatus, l.CreatedBy, l.CreatedAt
    FROM 
        List AS l
    INNER JOIN 
        FavoriteList AS fl ON l.Id = fl.ListId
    WHERE 
        fl.AccountId = @AccountId
    ORDER BY 
        l.UpdatedAt ASC;

-- Create List

    -- Get all list types 
    SELECT lt.Id, lt.Icon, lt.Title, lt.ListTypeDescription FROM ListType lt

    -- Get all providers
    SELECT tp.Id, tp.ProviderName FROM TemplateProvider tp

    -- Get all templates of a provider
    DECLARE @ProviderId INT;
    SET @ProviderId = 1;
    SELECT 
        tp.Id, tp.ProviderName, lt.Id,lt.Title, lt.TemplateDescription, lt.HeaderImage
    FROM 
       TemplateProvider tp
    INNER JOIN 
        ListTemplate lt ON tp.Id = lt.ProviderId
    WHERE 
        tp.Id = @ProviderId; 

-- Create List From List Type

    -- Get HeaderImage of a list type
    DECLARE @ListTypeId INT;
    SET @ListTypeId = 1;
    SELECT 
        lt.Id, lt.HeaderImage
    FROM 
        ListType lt 
    WHERE
        lt.Id = @ListTypeId

    -- Get all workspaces of a user
    DECLARE @AccountId INT;
    SET @AccountId = 3;
    SELECT 
        w.Id AS WorkspaceId, w.WorkspaceName, wm.AccountId AS UserId
    FROM 
        WorkspaceMember wm
    INNER JOIN 
        Workspace w ON wm.WorkspaceId = w.Id
    WHERE 
        wm.AccountId = @AccountId
    ORDER BY 
        wm.Id DESC;

-- Create List From Template
    
    -- Get basic information of a template
    DECLARE @TemplateId INT;
    SET @TemplateId = 1;
    SELECT
        lt.Id, lt.Title, lt.Icon, lt.Sumary, lt.Feature
    FROM 
        ListTemplate lt
    WHERE 
        lt.Id = @TemplateId

    -- Get sample data of a template
    DECLARE @TemplateId INT;
    SET @TemplateId = 1;
    SELECT
        tcol.Id AS colId,
        tcol.ColumnName,
        sdt.Icon,
        trow.Id rowid,
        tcell.CellValue,  
        tcol.ListTemplateId
    FROM 
        TemplateColumn tcol
    INNER JOIN
        SystemDataType sdt ON tcol.SystemDataTypeId = sdt.Id
    INNER JOIN
        TemplateSampleRow trow ON tcol.ListTemplateId = trow.ListTemplateId
    LEFT  JOIN 
        TemplateSampleCell tcell 
            ON tcol.Id = tcell.TemplateColumnId 
            AND trow.Id = tcell.TemplateSampleRowId
    WHERE 
        tcol.ListTemplateId = @TemplateId

    -- Get all views of a template
    DECLARE @TemplateId INT;
    SET @TemplateId = 1;
    SELECT
        tv.Id, 
        tv.ViewName, 
        tv.ViewTypeId,
        vt.Icon, 
        vs.SettingKey,
        tvs.GroupByColumnId, 
        tvs.RawValue
    FROM 
        TemplateView tv
    INNER JOIN
        ViewType vt ON tv.ViewTypeId = vt.Id
    LEFT JOIN 
        TemplateViewSettingValue tvs ON tv.Id = tvs.TemplateViewId
    LEFT JOIN 
        ViewTypeSettingKey vts ON tvs.ViewTypeSettingId = vts.Id
    LEFT JOIN
        ViewSettingKey vs ON vts.ViewSettingKeyId = vs.Id
    WHERE
        tv.ListTemplateId = @TemplateId
    ORDER BY
        tv.DisplayOrder

    -- Get all columns of a template and their setting value
    DECLARE @TemplateId INT;
    SET @TemplateId = 1;
    SELECT
        tc.Id, tc.ColumnName, sdt.Icon, ks.KeyName, tcsv.KeyValue
    FROM 
        TemplateColumn tc
    INNER JOIN
        SystemDataType sdt ON tc.SystemDataTypeId = sdt.Id
    LEFT JOIN
        TemplateColumnSettingValue tcsv ON tc.Id = tcsv.TemplateColumnId
    LEFT JOIN 
        DataTypeSettingKey dtsk ON tcsv.DataTypeSettingKeyId = dtsk.Id
    LEFT JOIN 
        KeySetting ks ON dtsk.KeySettingId =  ks.Id
    WHERE 
        tc.ListTemplateId = @TemplateId
    ORDER BY
        tc.DisplayOrder
    
    -- Get all column setting object of needed columns (choice)
    DECLARE @TemplateId INT;
    SET @TemplateId = 1;
    SELECT 
        lcso.Id,
        lcso.DisplayName,
        lcso.DisplayColor,
        lcso.DisplayOrder
    FROM 
        ListColumnSettingObject lcso
    INNER JOIN 
        TemplateColumn tc ON lcso.TemplateColumnId = tc.Id 
    WHERE 
        lcso.Context = 'TEMPLATE'
        AND tc.ListTemplateId = @TemplateId;

-- List Management
    
    -- Get all data of a list
    DECLARE @ListId INT;
    SET @ListId = 1;
    SELECT 
        lc.Id AS ColumnId,
        sdt.Icon AS ColumnIcon,
        lc.ColumnName AS ColumnName,
        lr.Id AS RowId,
        lcvl.CellValue AS CellValue
    FROM 
        ListDynamicColumn AS lc
    INNER JOIN 
        ListRow AS lr ON lc.ListId = lr.ListId
    INNER JOIN 
        ListCellValue AS lcvl ON lcvl.ListColumnId = lc.Id AND lcvl.ListRowId = lr.Id
    INNER JOIN 
        SystemDataType AS sdt ON lc.SystemDataTypeId = sdt.Id
    WHERE
        lc.ListId = @ListId
    ORDER BY lc.DisplayOrder ASC, lr.DisplayOrder ASC

    -- Get all column setting object of needed columns (choice)
    DECLARE @ListId INT;
    SET @ListId = 1;
    SELECT 
        lcso.Id,
        lcso.ListDynamicColumnId,
        lcso.DisplayName,
        lcso.DisplayColor,
        lcso.DisplayOrder,
        lcso.Context
    FROM 
        ListColumnSettingObject lcso
    INNER JOIN 
        ListDynamicColumn ldc ON lcso.ListDynamicColumnId = ldc.Id 
    WHERE 
        lcso.Context = 'LIST'
        AND LDC.ListId = @ListId;

    -- Get all views of a list
    DECLARE @ListId INT;
    SET @ListId = 1;
    SELECT 
        lv.Id, lv.ViewName, vt.Icon
    FROM 
        ListView lv
    INNER JOIN
        ViewType vt ON lv.ViewTypeId = vt.Id
    WHERE 
        lv.ListId = @ListId
    ORDER BY
        lv.DisplayOrder ASC

    -- Get all view settings of a list view
    DECLARE @ListViewId INT;
    SET @ListViewId = 1;
    DECLARE @ViewTypeId INT;
    SET @ViewTypeId = 4;
    SELECT 
        vs.Id, 
        vs.SettingKey,
        vs.ValueType,
        lvs.ListViewId,
        lvs.GroupByColumnId,
        lvs.RawValue
    FROM 
        ViewSettingKey vs
    INNER JOIN 
        ViewTypeSettingKey vts ON vs.Id = vts.ViewSettingKeyId
    LEFT JOIN 
        ListViewSettingValue lvs 
            ON vts.Id = lvs.ViewTypeSettingKeyId
            AND lvs.ListViewId = @ListViewId
    WHERE
        vts.ViewTypeId = @ViewTypeId

    -- Get all comment of a row
    DECLARE @RowId INT;
    SET @RowId = 1;
    SELECT 
        lrc.Id AS RowId, a.Avatar, a.FirstName, a.LastName, lrc.Content, lrc.UpdatedAt
    FROM 
        Account a
    INNER JOIN 
        ListRowComment lrc ON a.Id = lrc.CreatedBy
    WHERE 
        lrc.ListRowId = @RowId
    ORDER BY
        lrc.UpdatedAt DESC

    -- Get all attachment files of a row
    DECLARE @RowId INT;
    SET @RowId = 1;
    SELECT 
        fa.Id, fa.FileAttachmentName, fa.FileUrl
    FROM 
        FileAttachment fa
    WHERE 
        fa.ListRowId = @RowId
    ORDER BY
        fa.CreatedAt DESC

    -- Get all system data types
    SELECT
        sdt.Id, sdt.Icon, sdt.CoverImg, sdt.DataTypeDescription, sdt.DisplayName
    FROM 
        SystemDataType sdt

    -- Get all key setting of a system data type
    DECLARE @DataTypeId INT;
    SET @DataTypeId = 1;
    SELECT
        ks.Id, ks.KeyName, ks.ValueOfDefault, ks.ValueType
    FROM 
       KeySetting ks 
    INNER JOIN 
        DataTypeSettingKey dtsk ON ks.Id = dtsk.KeySettingId
    WHERE 
        dtsk.SystemDataTypeId = @DataTypeId

    -- Get all datatype settings of a column
    DECLARE @DataTypeId INT;
    SET @DataTypeId = 1;
    DECLARE @ColumnId INT;
    SET @ColumnId = 1;
    SELECT 
        ks.Id, 
        ks.KeyName, 
        ks.ValueOfDefault,
        ks.ValueType,
        lcsv.KeyValue
    FROM 
        KeySetting ks
    INNER JOIN 
        DataTypeSettingKey dtsk ON ks.Id = dtsk.KeySettingId
    LEFT JOIN 
        DynamicColumnSettingValue lcsv 
            ON dtsk.Id = lcsv.DataTypeSettingKeyId 
            AND lcsv.DynamicColumnId = @ColumnId
    WHERE 
        dtsk.SystemDataTypeId = @DataTypeId;

    -- Get all view types
    SELECT
        vt.Id, vt.Title, vt.ViewTypeDescription, vt.Icon
    FROM 
        ViewType vt

    -- Get all settings of a view type
    DECLARE @ViewTypeId INT;
    SET @ViewTypeId = 4;
    SELECT 
        vs.Id, 
        vs.SettingKey,
        vs.ValueType
    FROM 
        ViewSettingKey vs
    INNER JOIN 
        ViewTypeSettingKey vts ON vs.Id = vts.ViewSettingKeyId
    WHERE 
        vts.ViewTypeId = @ViewTypeId;

    -- Get all permisions
    SELECT 
        p.Id, p.PermissionCode, p.PermissionName, p.Icon
    FROM 
        Permission p

    -- Get all scopes
    SELECT 
        s.Id, s.Code, s.DisplayName, s.Icon, s.ScopeDescription
    FROM 
        Scope s

    -- Get all sharelink settings
    SELECT 
        ks.Id, ks.Icon, ks.KeyName, ks.ValueType, ks.ValueOfDefault
    FROM 
        KeySetting ks
    WHERE
        ks.IsShareLinkSetting = 1

    -- Get all current list members
    DECLARE @ListId INT;
    SET @ListId = 1;
    SELECT 
        a.Avatar,
        a.FirstName,
        a.LastName
    FROM 
        ListMemberPermission lmp
    LEFT JOIN 
        Account a ON a.Id = lmp.AccountId
    WHERE 
        lmp.ListId = @ListId;  
    
    -- Get all sharelinks of a list
    DECLARE @ListId INT;
    SET @ListId = 1;
    SELECT 
        sl.Id, sl.TargetUrl, p.PermissionName, s.Icon, a.Avatar, slua.AccountId, slua.Email
    FROM 
        ShareLink sl
    INNER JOIN 
        Scope s ON sl.ScopeId = s.Id
    INNER JOIN 
        Permission p ON sl.PermissionId = p.Id
    LEFT JOIN 
        ShareLinkUserAccess slua ON sl.Id = slua.ShareLinkId AND s.Code = 'SPECIFIC'
    LEFT JOIN 
        Account a ON slua.AccountId = a.Id
    WHERE 
        sl.ListId = @ListId 

   -- Get all settings of a sharelink
    DECLARE @ShareLinkId INT;
    SET @ShareLinkId = 1;
    SELECT 
        ks.Id, ks.KeyName, ks.Icon, ks.ValueType, ks.ValueOfDefault, slsv.KeyValue
    FROM 
        KeySetting ks
    LEFT JOIN 
        ShareLinkSettingValue slsv 
            ON ks.Id = slsv.KeySettingId 
            AND slsv.ShareLinkId = @ShareLinkId
    WHERE 
        ks.IsShareLinkSetting = 1

-- SCREEN: TRASH 
    -- Get all trash items of a user
    DECLARE @AccountId INT;
    SET @AccountId = 1;
    SELECT 
        ti.Id, ti.ObjectId, ti.ObjectTypeId, ot.Icon, ti.UserDeleteId, ti.DeletedAt
    FROM 
        TrashItem ti
    INNER JOIN
        ObjectType ot ON ti.ObjectTypeId = ot.Id
    WHERE
        ti.UserDeleteId = @AccountId
