-- -----------------------------------------------------------------------------
-- SCREEN 1: TAB BOARDS
-- -----------------------------------------------------------------------------

-- 1. Query all categories types
SELECT 
    ct.Id AS CategoryTypeId,
    ct.CategoryTypeValue
FROM 
    CategoryTypes ct

-- 2. Query suggested templates by Template Category
SELECT 
    c.Id AS CategoryId,
    c.CategoryName
FROM 
    Categories c
WHERE
    c.IsActive = 1 AND c.CategoryTypeId = 6 -- Template
ORDER BY
    c.Position ASC

-- 3. Query starred Boards by user
DECLARE @UserId INT;
SET @UserId = 1;

SELECT 
    b.Id AS BoardId,
    b.BackgroundUrl,
    b.BoardName,
    b.BoardStatus
FROM 
    Boards b
INNER JOIN
    UserStarredBoards usb ON b.Id = usb.BoardId
WHERE 
    usb.UserId = @UserId

-- 3. Query recently accessed board by user
DECLARE @UserId INT;
SET @UserId = 1;

SELECT 
    uvh.OwnerId AS EntityId,
    c.CategoryName,
    b.BoardName
FROM 
    Boards b
INNER JOIN 
    UserViewHistories uvh ON uvh.OwnerId  = b.Id
INNER JOIN 
    Categories c ON c.Id = uvh.CategoryId
WHERE 
    uvh.UserId = @UserId
ORDER BY 
    uvh.AccessedAt DESC;

-- 4. Query all Workspaces where User is a Member
DECLARE @UserId INT;
SET @UserId = 1;

SELECT 
    w.Id, 
    w.WorkspaceName
FROM Workspaces w
    INNER JOIN Members m ON m.OwnerId = w.Id
    INNER JOIN Categories c ON m.CategoryId = c.Id AND c.CategoryName = 'WORKSPACE' 
WHERE m.UserId = @UserId;

