USE MsList
-- Get information of user 1
SELECT acc.*
FROM 
	Account AS acc
WHERE acc.Id = 1

-- Get personal lists of user 3 
SELECT l.*
FROM 
	List AS l
INNER JOIN 
	Account AS acc ON l.CreateBy = acc.Id AND acc.Id = 3
ORDER BY 
	l.CreateAt ASC

-- Get favorite lists of user 3
SELECT 
    l.*
FROM 
    List AS l
INNER JOIN 
    Account AS acc ON l.CreateBy = acc.Id
WHERE 
    acc.Id = 3
ORDER BY 
    l.CreateAt ASC;


-- Get all list types 
SELECT * FROM ListType lt


-- Get all data of a list
SELECT 
    lc.Id AS ColumnId,
    sdt.Icon AS ColumnIcon,
    lc.ColumnName AS ColumnName,
    lr.Id AS RowId,
    lcvl.Value AS CellValue
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

-- Get all listview of a list

    

-- Get all templates
SELECT * FROM ListTemplate
