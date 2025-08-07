-- -----------------------------------------------------------------------------
-- SCREEN 1: TAB BOARDS
-- -----------------------------------------------------------------------------

-- 1. Query all categories types
SELECT 
    ct.Id AS CategoryTypeId,
    ct.CategoryTypeValue
FROM 
    CategoryTypes ct;

-- 2. Query suggested templates by Template Category
SELECT 
    c.Id AS CategoryId,
    c.CategoryName
FROM 
    Categories c
WHERE
    c.IsActive = 1 AND c.CategoryTypeId = 6 -- Template
ORDER BY
    c.Position ASC;

-- 3. Query starred Boards by user
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
    usb.UserId = 1 AND b.BoardStatus = 'active'
ORDER BY
    b.UpdatedAt DESC;

-- 4. Query recently accessed board by user
SELECT 
    uvh.OwnerId AS BoardId,
    c.CategoryName,
    b.BoardName,
    t.Id AS TemplateId
FROM 
    Boards b
INNER JOIN 
    UserViewHistories uvh ON uvh.OwnerId = b.Id 
INNER JOIN 
    Categories c ON c.Id = uvh.CategoryId
LEFT JOIN 
    Templates t ON t.BoardId = uvh.OwnerId
WHERE 
    uvh.UserId = 1 AND c.Id = 2 -- Board
ORDER BY 
    uvh.AccessedAt DESC;

-- 5. Query all Workspaces where User is a Member 
--    and for each workspace, get all Boards where User is also a Member
SELECT DISTINCT
    w.Id AS WorkspaceId,
    w.WorkspaceName,
    w.LogoUrl AS WorkspaceIcon,
    brd.Id AS BoardId,
    brd.BoardName AS BoardName,
    brd.BoardDescription,
    brd.BackgroundUrl,
    brd.CreatedAt
FROM 
    Workspaces w
INNER JOIN 
    Members mw ON mw.OwnerId = w.Id
INNER JOIN 
    Categories cgr ON cgr.Id = mw.CategoryId AND cgr.Id = 1 -- Workspace
INNER JOIN 
    Boards brd ON brd.WorkspaceId = w.Id
INNER JOIN 
    Members mb ON mb.OwnerId = brd.Id
INNER JOIN 
    Categories cgr2 ON cgr2.Id = mb.CategoryId AND cgr2.Id = 2 -- Board
WHERE 
    mb.UserId = 1;

-- 6. Query all closed boards where user is a member
SELECT 
    b.Id AS BoardID,
    b.BoardName, 
    w.Id AS WorkspaceId,
    w.WorkspaceName
FROM 
    Boards b
INNER JOIN 
    Workspaces w ON w.Id = b.WorkspaceId
INNER JOIN 
    Members mb ON mb.OwnerId = b.Id
INNER JOIN 
    Categories cgr ON cgr.Id = mb.CategoryId AND cgr.Id = 2 -- Board
WHERE 
    mb.UserId = 3 AND b.BoardStatus = 'CLOSED';

-- -----------------------------------------------------------------------------
-- SCREEN 2: TEMPLATES TAB
-- -----------------------------------------------------------------------------

-- 7. Get template categories
SELECT
    cg.Id,
    cg.CategoryName,
    cg.Icon
FROM 
    Categories cg
INNER JOIN 
    CategoryTypes cgt 
        ON cgt.Id = cg.CategoryTypeId 
        AND cgt.Id = 6; -- TemplateType

-- 8. Get New and notable templates
SELECT 
    t.Id AS TemplateId,
    t.Title,
    t.BackgroundUrl,
    t.CreatedAt,
    t.CreatedBy,
    t.Copied,
    t.Viewed,
    t.TemplateDescription,
    u.Username AS CreatedBy
FROM 
    Templates t
INNER JOIN
    Users u ON t.CreatedBy = u.Id
ORDER BY 
    t.CreatedAt DESC, 
    t.Viewed DESC, 
    t.Copied DESC;

-- -----------------------------------------------------------------------------
-- SCREEN 3: TEMPLATE DETAIL
-- -----------------------------------------------------------------------------

-- 9. Get template details and the Board associated with that template

-- Get template detail
SELECT 
    tpl.Id AS TemplateId,
    tpl.Title, 
    usr.Username,
    tpl.Copied,
    tpl.Viewed,
    tpl.TemplateDescription,
    tpl.BoardId AS SampleBoardId
FROM 
    Templates tpl
INNER JOIN 
    Users usr ON tpl.CreatedBy = usr.Id
WHERE 
    tpl.Id = 1;

-- Get Board Detail
SELECT 
    b.Id AS BoardId,
    b.BoardName,
    b.BackgroundUrl,
    b.BoardStatus
FROM 
    Boards b
WHERE 
    b.Id = 20; -- Sample Board of Template with Id = 1 

-- Get Stages in Board
SELECT 
    s.Id AS StageId, 
    s.Title AS StageTitle,
    c.ColorName AS BackgroundColor,
    s.Position AS StagePosition
FROM 
    Stages s
INNER JOIN 
    Colors c ON c.Id = s.ColorId
WHERE 
    s.BoardId = 20
ORDER BY
    s.Position ASC

-- Get Cards in Stages
SELECT 
    crd.Id AS CardId,
    crd.Title AS CardTitle,
    crd.StageId,
    crd.CoverValue,
    clr.ColorName,
    a.AttachmentPath,
    crd.Position,
    crd.CardDescription
FROM 
    Cards crd
INNER JOIN 
    Stages s ON s.Id = crd.StageId
LEFT JOIN 
    Colors clr ON TRY_CAST(CASE WHEN crd.CategoryId = 60 THEN crd.CoverValue ELSE NULL END AS INT) = clr.Id
LEFT JOIN 
    Attachments a ON TRY_CAST(CASE WHEN crd.CategoryId = 61 THEN crd.CoverValue ELSE NULL END AS INT) = a.Id
WHERE 
    s.BoardId = 20
ORDER BY 
    crd.StageId;

-- -----------------------------------------------------------------------------
-- SCREEN 4: TAB BOARDS IN WORKSPACE
-- -----------------------------------------------------------------------------

-- 10. Get suggested Boards by Template Category
SELECT
    brd.Id AS BoardId,
    brd.BoardName,
    brd.BackgroundUrl
FROM
    Boards brd
INNER JOIN 
    Templates tpl ON tpl.BoardId = brd.Id
INNER JOIN 
    Categories ctg ON tpl.CategoryId = ctg.Id
WHERE 
    ctg.Id = 49 -- Engineering IT category
ORDER BY 
    tpl.Viewed DESC, 
    tpl.Copied DESC;

-- 11. Get "Your boards" section: 
--     Get Boards belonging to Workspace where User is also a Member of the Board
SELECT DISTINCT
    brd.Id AS BoardId,
    brd.BoardName,
    brd.BackgroundUrl
FROM 
    Boards brd
INNER JOIN 
    Members mbr ON mbr.OwnerId = brd.Id
INNER JOIN 
    Categories ctg ON ctg.Id = mbr.CategoryId AND ctg.Id = 2 -- Board 
WHERE 
    brd.WorkspaceId = 1;

-- -----------------------------------------------------------------------------
-- SCREEN 5: MEMBER TAB OF WORKSPACE
-- -----------------------------------------------------------------------------
WITH WorkspaceMembers AS (
    SELECT 
        m.UserId, 
        m.RolePermissonId
    FROM 
        Members m
    INNER JOIN 
        Categories ctg ON ctg.Id = m.CategoryId 
                     AND ctg.Id = 1  -- WORKSPACE
    WHERE 
        m.OwnerId = 1
),
BoardsInWorkspace AS (
    SELECT 
        b.Id AS BoardId,
        b.BoardName,
        b.BackgroundUrl
    FROM 
        Boards b
    WHERE 
        b.WorkspaceId = 1
),
BoardMembersInWorkspace AS (
    SELECT 
        m.UserId,
        m.OwnerId AS BoardId
    FROM 
        Members m
    INNER JOIN 
        Categories ctg ON ctg.Id = m.CategoryId 
                     AND ctg.Id = 2  -- BOARD
    INNER JOIN 
        BoardsInWorkspace biw ON biw.BoardId = m.OwnerId
)
SELECT
    u.Id AS UserId,
    u.Username, 
    u.Email AS UserEmail,
    u.LastActive,
    p.PermissionName AS Permission,
    COUNT(DISTINCT biw.BoardId) AS NumBoardsJoined,
    STRING_AGG(biw.BoardName, ', ') AS JoinedBoardNames,
    STRING_AGG(biw.BackgroundUrl, ', ') AS JoinedBoardBackground
FROM 
    WorkspaceMembers wm
LEFT JOIN 
    BoardMembersInWorkspace bm ON bm.UserId = wm.UserId
LEFT JOIN 
    BoardsInWorkspace biw ON biw.BoardId = bm.BoardId
INNER JOIN 
    Users u ON u.Id = wm.UserId
INNER JOIN 
    RolePermissions p ON p.Id = wm.RolePermissonId
GROUP BY 
    u.Id, 
    u.Username, 
    u.Email, 
    u.LastActive, 
    p.PermissionName;