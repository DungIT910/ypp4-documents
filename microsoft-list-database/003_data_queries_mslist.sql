USE MsList

-- SCREEN: DASHBOAD

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

--  SCREEN: CREATE NEW LIST 

    -- Get all list types 
    SELECT lt.Id, lt.Icon, lt.Title, lt.HeaderImage, lt.[Description] FROM ListType lt

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

--  SCREEN: CREATE NEW LIST BASE ON TEMPLATE
    
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


-- SCREEN: LIST MANAGEMENT
    
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

    -- Get all share link settings
    SELECT 
        ks.Id, ks.Icon, ks.KeyName, ks.ValueType, ks.DefaultValue
    FROM 
        KeySetting ks
    WHERE
        ks.IsShareLinkSetting = 1

    -- Get all current list members
    SELECT 
    a.Avatar,
        COALESCE(a.Email, lmp.Email) AS Email,
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
            AND slsv.ShareLinkId = 1;
