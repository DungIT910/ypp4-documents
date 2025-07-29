USE MsList

-- Dashboard Screen

    -- Get information of user 1
    SELECT acc.id, acc.Avatar, acc.FirstName, acc.LastName, acc.Company, acc.AccountStatus
    FROM 
	    Account AS acc
    WHERE acc.Id = 1

    -- Get personal lists of user 3 
    SELECT l.Id, l.Color, l.Icon, l.ListName, l.ListStatus, l.CreateBy, l.CreateAt
    FROM 
	    List AS l
    INNER JOIN 
	    Account AS acc ON l.CreateBy = acc.Id
    WHERE 
        acc.Id = 3
    ORDER BY 
	    l.CreateAt ASC

    -- Get favorite lists of user 3
    SELECT 
        l.Id, l.Color, l.Icon, l.ListName, l.ListStatus, l.CreateBy, l.CreateAt
    FROM 
        List AS l
    INNER JOIN 
        Account AS acc ON l.CreateBy = acc.Id
    WHERE 
        acc.Id = 3
    ORDER BY 
        l.CreateAt ASC;

--  Create List

    -- Get all list types 
    SELECT lt.Id, lt.Icon, lt.Title, lt.[Description] FROM ListType lt

    -- Get all providers
    SELECT tp.Id, tp.ProviderName FROM TemplateProvider tp

    -- Get all templates of a specific provider
    SELECT 
        tp.Id, tp.ProviderName, lt.Id,lt.Title, lt.[Description], lt.HeaderImage
    FROM 
       TemplateProvider tp
    INNER JOIN 
        ListTemplate lt ON tp.Id = lt.ProviderId
    WHERE 
        tp.Id = 1; 

-- Create List From List Type

    -- Get HeaderImage of a specific list type
    SELECT 
        lt.Id, lt.HeaderImage
    FROM 
        ListType lt 
    WHERE
        lt.Id = 1

    -- Get all workspaces of a specific user
    SELECT 
        w.Id AS WorkspaceId, w.WorkspaceName, wm.AccountId AS UserId
    FROM 
        WorkspaceMember wm
    INNER JOIN 
        Workspace w ON wm.WorkspaceId = w.Id
    WHERE 
        wm.AccountId = 3
    ORDER BY 
        wm.Id DESC;

-- Create List From Template
    
    -- Get basic information of a specific template
    SELECT
        lt.Id, lt.Title, lt.Icon, lt.Summary, lt.Feature
    FROM 
        ListTemplate lt
    WHERE 
        lt.Id = 1

    -- Get sample data of a specific template
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
    INNER JOIN 
        TemplateSampleCell tcell 
            ON tcol.Id = tcell.TemplateColumnId 
            AND trow.Id = tcell.TemplateSampleRowId
    WHERE 
        tcol.ListTemplateId = 1

    -- Get all views of a template
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
        TemplateViewSetting tvs ON tv.Id = tvs.TemplateViewId
    LEFT JOIN 
        ViewTypeSetting vts ON tvs.ViewTypeSettingId = vts.Id
    LEFT JOIN
        ViewSetting vs ON vts.ViewSettingId = vs.Id
    WHERE
        tv.ListTemplateId = 1
    ORDER BY
        tv.DisplayOrder

    -- Get all columns of a template and their setting value
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
        tc.ListTemplateId = 1
    ORDER BY
        tc.DisplayOrder
    
    -- Get all column setting object of needed columns (choice)
    SELECT 
        lcso.Id,
        lcso.DisplayName,
        lcso.DisplayColor,
        lcso.DisplayOrder
    FROM 
        ListColumnSettingObject lcso
    INNER JOIN 
        TemplateColumn tc ON lcso.ColumnId = tc.Id 
    WHERE 
        lcso.Context = 'TEMPLATE'
        AND tc.ListTemplateId = 1;

-- List Management
    
    -- Get all data of a specific list
    SELECT 
        lc.Id AS ColumnId,
        sdt.Icon AS ColumnIcon,
        lc.ColumnName AS ColumnName,
        lr.Id AS RowId,
        lcvl.[Value] AS CellValue
    FROM 
        ListDynamicColumn AS lc
    INNER JOIN 
        ListRow AS lr ON lc.ListId = lr.ListId
    INNER JOIN 
        ListCellValue AS lcvl ON lcvl.ListColumnId = lc.Id AND lcvl.ListRowId = lr.Id
    INNER JOIN 
        SystemDataType AS sdt ON lc.SystemDataTypeId = sdt.Id
    WHERE
        lc.ListId = 1
    ORDER BY lc.DisplayOrder ASC, lr.DisplayOrder ASC

    -- Get all column setting object of needed columns (choice)
    SELECT 
        lcso.Id,
        lcso.ColumnId AS ListDynamicColumnId,
        lcso.DisplayName,
        lcso.DisplayColor,
        lcso.DisplayOrder,
        lcso.Context
    FROM 
        ListColumnSettingObject lcso
    INNER JOIN 
        ListDynamicColumn ldc ON lcso.ColumnId = ldc.Id 
    WHERE 
        lcso.Context = 'LIST'
        AND LDC.ListId = 1;

    -- Get all views of a specific list
    SELECT 
        lv.Id, lv.ViewName, vt.Icon
    FROM 
        ListView lv
    INNER JOIN
        ViewType vt ON lv.ViewTypeId = vt.Id
    WHERE 
        lv.ListId = 1
    ORDER BY
        lv.DisplayOrder ASC

    -- Get all view settings of a specific list view
    SELECT 
        vs.Id, 
        vs.SettingKey,
        vs.ValueType,
        lvs.ListViewId,
        lvs.GroupByColumnId,
        lvs.RawValue
    FROM 
        ViewSetting vs
    INNER JOIN 
        ViewTypeSetting vts ON vs.Id = vts.ViewSettingId
    LEFT JOIN 
        ListViewSetting lvs 
            ON vts.Id = lvs.ViewTypeSettingId
            AND lvs.ListViewId = 1
    WHERE
        vts.ViewTypeId = 4

    -- Get all comment of a row
    SELECT 
        lrc.Id AS RowId, a.Avatar, a.FirstName, a.LastName, lrc.Content, lrc.UpdateAt
    FROM 
        Account a
    INNER JOIN 
        ListRowComment lrc ON a.Id = lrc.CreatedBy
    WHERE 
        lrc.ListRowId = 1
    ORDER BY
        lrc.UpdateAt DESC

    -- Get all attachment files of a row
    SELECT 
        fa.Id, fa.[FileName], fa.FileUrl
    FROM 
        FileAttachment fa
    WHERE 
        fa.ListItemId = 1
    ORDER BY
        fa.CreateAt DESC

    -- Get all system data types
    SELECT
        sdt.Id, sdt.Icon, sdt.[Description], sdt.DisplayName
    FROM 
        SystemDataType sdt

    -- Get all key setting of a specific system data type
    SELECT
        ks.Id, ks.KeyName, ks.DefaultValue, ks.ValueType
    FROM 
       KeySetting ks 
    INNER JOIN 
        DataTypeSettingKey dtsk ON ks.Id = dtsk.KeySettingId
    WHERE 
        dtsk.DataTypeId = 1

    -- Get all datatype settings of a specific column
    SELECT 
        ks.Id, 
        ks.KeyName, 
        ks.DefaultValue,
        ks.ValueType,
        lcsv.KeyValue
    FROM 
        KeySetting ks
    INNER JOIN 
        DataTypeSettingKey dtsk ON ks.Id = dtsk.KeySettingId
    LEFT JOIN 
        ListColumnSettingValue lcsv 
            ON dtsk.Id = lcsv.DataTypeSettingKeyId 
            AND lcsv.ColumnId = 1
    WHERE 
        dtsk.DataTypeId = 1;

    -- Get all view types
    SELECT
        vt.Id, vt.Title, vt.[Description], vt.Icon
    FROM 
        ViewType vt

    -- Get all settings of a specific view type
    SELECT 
        vs.Id, 
        vs.SettingKey,
        vs.ValueType
    FROM 
        ViewSetting vs
    INNER JOIN 
        ViewTypeSetting vts ON vs.Id = vts.ViewSettingId
    WHERE 
        vts.ViewTypeId = 4;

    -- Get all permisions
    SELECT 
        p.Id, p.Code, p.[Name], p.Icon
    FROM 
        Permission p

    -- Get all scopes
    SELECT 
        s.Id, s.Code, s.[Name], s.Icon, s.[Description]
    FROM 
        Scope s

    -- Get all sharelink settings
    SELECT 
        ks.Id, ks.Icon, ks.KeyName, ks.ValueType, ks.DefaultValue
    FROM 
        KeySetting ks
    WHERE
        ks.IsShareLinkSetting = 1

    -- Get all current list members
    SELECT 
        a.Avatar,
        lmp.Email AS Email,
        a.FirstName,
        a.LastName
    FROM 
        ListMemberPermission lmp
    LEFT JOIN 
        Account a ON a.Id = lmp.AccountId
    WHERE 
        lmp.ListId = 1;  
    
    -- Get all sharelinks of a specific list
    SELECT 
        sl.Id, sl.[URL], sl.Note, p.[Name], s.Icon, a.Avatar, slua.AccountId, slua.Email
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
        sl.ListId = 1 

   -- Get all settings of a specific sharelink
    SELECT 
        ks.Id, ks.KeyName, ks.Icon, ks.ValueType, ks.DefaultValue, slsv.KeyValue
    FROM 
        KeySetting ks
    LEFT JOIN 
        ShareLinkSettingValue slsv 
            ON ks.Id = slsv.KeySettingId 
            AND slsv.ShareLinkId = 1
    WHERE 
        ks.isShareLinkSetting = 1

-- SCREEN: TRASH 
    -- Get all trash items of a specific user
    SELECT 
        ti.Id, ti.ObjectId, ti.ObjectName, ti.ObjectTypeId, ot.Icon, ti.DeleteBy, ti.DeletedAt, ti.ObjectStatus, ti.[Path]
    FROM 
        TrashItem ti
    INNER JOIN
        ObjectType ot ON ti.ObjectId = ot.Id
    WHERE
        ti.CreatedBy = 1
